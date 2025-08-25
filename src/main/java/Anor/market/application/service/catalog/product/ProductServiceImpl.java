package Anor.market.application.service.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.mapper.catalog.product.ProductMapper;
import Anor.market.application.service.catalog.product.images.FileStorageServiceImpl;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.catalog.CategoryItemListRepository;
import Anor.market.domain.repository.catalog.product.ProductRepository;
import Anor.market.domain.service.catalog.product.ProductService;
import Anor.market.domain.service.catalog.product.images.FileStorageService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ProductServiceImpl implements ProductService that the seller can create and sell his/her own product in anor market
 * all data these come from other DATABASE such as a catalog, category and category item list, once accepted above data with category item list ID
 * seller can announce an ads in this case!
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryItemListRepository categoryItemListRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileStorageServiceImpl fileStorageService;
    @Autowired
    private FileStorageService storage;
    @Autowired
    private ProductMapper productMapper;


    /// CREATE A PRODUCT AND IMAGES
    @Override
    public ProductDTO createProduct(String categoryItemListId, ProductCreateDTO createDTO, List<MultipartFile> files) throws IOException {
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User id is not found!"));
        boolean isSeller = userEntity.isSeller();
        if (!isSeller && SpringSecurityValid.hasRole(Roles.USER)) {
            throw new AppBadException("You are not a seller!");
        }
        CategoryItemListEntity categoryItemList = categoryItemListRepository.findById(categoryItemListId).orElseThrow(() -> new RuntimeException("CategoryItemList not found"));
        ProductEntity productEntity = productMapper.toProductEntity(createDTO);
        productEntity.setCategoryItemListEntity(categoryItemList);

        List<FileStorageService.StoredFile> saved = new ArrayList<>();
        try {
            int order = 0;
            for (MultipartFile multipartFile : files) {
                ensureImage(multipartFile);
                var stored = storage.store(multipartFile, "products");
                saved.add(stored);

                var img = new ProductImageEntity();
                img.setFileName(stored.fileName());
                img.setUrl(stored.url());
                img.setContentType(Optional.ofNullable(stored.contentType()).orElse("application/octet-stream"));
                img.setSizeBytes(stored.sizeBytes());
                img.setSortOrder(order++);
                // add images
                productEntity.addImage(img);
            }
            // saved to database
            productRepository.save(productEntity);
            return productMapper.toProductDTO(productEntity);
        } catch (Exception exception) {
            for (var savedData : saved) {
                try {
                    storage.delete("products", savedData.fileName());
                } catch (IOException ignore) {
                }
            }
            throw exception;
        }
    }

    /// get images with products by product & image id!
    @Transactional()
    public ResponseEntity<Resource> getImage(String productId, String imageId) throws IOException {
        // Find product
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        // Find image in product
        ProductImageEntity img = product.getImages().stream()
                .filter(i -> i.getImageId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        // Load file from disk
        Path file = Paths.get("uploads/products/" + img.getFileName());
        Resource resource = new UrlResource(file.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("Could not read file: " + file);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(img.getContentType()))
                .contentLength(img.getSizeBytes())
                .body(resource);
    }

    /// Once the user wants to add some images for the already announced product image and implements by product id
    @Transactional
    public ProductDTO addImages(String productId, List<MultipartFile> files) throws IOException {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        int startOrder = productEntity.getImages().size();
        List<FileStorageService.StoredFile> saved = new ArrayList<>();
        try {
            int idx = 0;
            for (MultipartFile f : files) {
                ensureImage(f);
                var stored = storage.store(f, "products");
                saved.add(stored);
                var img = new ProductImageEntity();
                img.setFileName(stored.fileName());
                img.setUrl(stored.url());
                img.setContentType(Optional.ofNullable(stored.contentType()).orElse("application/octet-stream"));
                img.setSizeBytes(stored.sizeBytes());
                img.setSortOrder(startOrder + idx++);
                productEntity.addImage(img);
            }
            return productMapper.toProductDTO(productEntity);
        } catch (Exception ex) {
            for (var s : saved) {
                try {
                    storage.delete("products", s.fileName());
                } catch (IOException ignore) {
                }
            }
            throw ex;
        }
    }

    /// delete the image by product & image id
    @Transactional
    public void removeImage(String productId, String imageId) throws IOException {
        ProductEntity p = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        ProductImageEntity img = p.getImages().stream().filter(i -> i.getImageId().equals(imageId)).findFirst().orElseThrow(() -> new IllegalArgumentException("Image not found"));
        String fileName = img.getFileName();
        p.removeImage(imageId); // orphanRemoval => DB’dan ketadi
        productRepository.flush();     // darhol delete bo‘lsin
        try {
            storage.delete("products", fileName);
        } catch (IOException ignore) {
        }
    }

    /// ensure whether the loading images size less than 5 mb!
    private void ensureImage(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) throw new IllegalArgumentException("File is empty!");
        if (multipartFile.getSize() > 5 * 1024 * 1024) // 5 MB
            throw new IllegalArgumentException("File is larger (<=5MB).");
        String ct = Optional.ofNullable(multipartFile.getContentType()).orElse("");
        if (!(ct.equals("image/jpeg") || ct.equals("image/png") || ct.equals("image/webp")))
            throw new IllegalArgumentException("Only JPEG/PNG/WEBP allow!");
    }

    /// GET ALL PRODUCT DATA IN PAGEABLE LIST
    @Override
    public Page<ProductDTO> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("localDateTime").descending());
        Page<ProductEntity> productEntityPage = productRepository.findByOrderByLocalDateTimeDesc(pageRequest);
        List<ProductDTO> productDTOList = productEntityPage.getContent().stream().map(productMapper::toProductDTO).collect(Collectors.toList());
        return new PageImpl<>(productDTOList, pageRequest, productEntityPage.getTotalElements());
    }

    /// UPDATE PRODUCT BY ID
    @Override
    public ProductDTO updateProduct(String productId, ProductCreateDTO createDTO) {
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        boolean isSeller = user.isSeller();
        if (!isSeller) {
            throw new AppBadException("you are not seller!");
        }
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AppBadException("Product id is not found!"));
        ProductEntity productEntity = productMapper.toUpdateProductEntity(product.getProductId(), createDTO);
        productRepository.save(productEntity);
        return productMapper.toProductDTO(productEntity);
    }

    /// DELETE PRODUCT BY ID
    @Override
    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return "Deleted!";
    }
}
