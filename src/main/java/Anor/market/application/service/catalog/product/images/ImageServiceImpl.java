package Anor.market.application.service.catalog.product.images;

import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import Anor.market.application.mapper.catalog.product.image.ProductImageMapper;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import Anor.market.domain.repository.catalog.product.products.ProductRepository;
import Anor.market.domain.repository.catalog.product.image.ProductImageRepository;
import Anor.market.domain.service.catalog.product.images.ImageService;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductImageMapper productImageMapper;

    @Value("${attach.upload.folder}")
    private String folderName;
    @Value("${attach.upload.url}")
    private String url;


    private static final long MAX_SIZE = 5L * 1024 * 1024; // 5MB
    private static final Set<String> ALLOWED = Set.of("image/jpeg", "image/png", "image/webp");

    /// CREATE OF THE PRODUCTS IMAGES
    @Override
    public List<ProductImageDTO> saveImages(String productId, List<MultipartFile> files) {
        List<ProductImageDTO> result = new ArrayList<>();
        try {
            //calling product for parent link
            ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

            //all images through out the for loop selection
            for (MultipartFile file : files) {

                //checking file isEmpty, size and type!
                if (file == null || file.isEmpty())
                    throw new IllegalArgumentException("Empty file");
                if (file.getSize() > MAX_SIZE)
                    throw new IllegalArgumentException("File too large (max 5MB)");
                if (!ALLOWED.contains(file.getContentType()))
                    throw new IllegalArgumentException("Invalid type: " + file.getContentType());

                //extensions
                String pathFolder = LocalDate.now().toString();
                String keyUUID = UUID.randomUUID().toString();
                String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

                //creating folder
                File folder = new File(folderName + "/" + pathFolder);
                if (!folder.exists()) folder.mkdirs();

                Path path = Paths.get(folderName + "/" + pathFolder + "/" + keyUUID + "." + extension);
                Files.write(path, file.getBytes());

                //full url
                String fullUrl = url + "/" + pathFolder + "/" + keyUUID + "." + extension;

                //creating image
                ProductImageEntity productImage = new ProductImageEntity();
                productImage.setImageId(keyUUID);
                productImage.setPath(pathFolder);
                productImage.setExtension(extension);
                productImage.setOrigenName(file.getOriginalFilename());
                productImage.setSize(file.getSize());
                productImage.setUrl(fullUrl);
                productImage.setProductEntityImage(product); //<--------> PARENT LINK

                //saved into the DATABASE

                productImageRepository.save(productImage);

                result.add(productImageMapper.toDTO(productImage, fullUrl));
            }
            return result;
        } catch (IOException e) {
            throw new AppBadException("File saving error: " + e.getMessage());
        }
    }

    /// GET ALL THE PRODUCTS IMAGES
    @Override
    @Transactional
    public ResponseEntity<Resource> getImage(String productId, String imageId) throws IOException {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ProductImageEntity img = product.getImages().stream()
                .filter(i -> i.getImageId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        Path filePath = Paths.get(folderName + "/" + img.getImageId() + "." + img.getExtension()).normalize();
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new AppBadException("Photo is not found!");
            }
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (IOException e) {
            throw new AppBadException("Error while reading image file: " + e.getMessage());
        }
    }

    /// DELETE THE PRODUCTS IMAGES WITHIN THE FILE
    @Transactional
    public void removeImage(String productId, String imageId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ProductImageEntity img = product.getImages().stream()
                .filter(i -> i.getImageId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        product.getImages().remove(img);
        //  product.getImages().forEach(i -> i.setSortOrder(product.getImages().indexOf(i)));
        productRepository.flush();
    }

    /// EXTENSION OF THE FILE LAST INDEX AND SUBSTRING!
    private String getExtension(String filename) {
        int lastIndex = filename.lastIndexOf(".");
        return filename.substring(lastIndex + 1);
    }

}