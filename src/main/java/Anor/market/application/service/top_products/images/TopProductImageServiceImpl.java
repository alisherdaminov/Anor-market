package Anor.market.application.service.top_products.images;

import Anor.market.application.dto.top_products.images.TopProductImageDTO;
import Anor.market.application.mapper.top_products.images.TopProductImageMapper;
import Anor.market.domain.model.entity.top_products.images.TopProductImageEntity;
import Anor.market.domain.repository.top_products.TopProductsRepository;
import Anor.market.domain.repository.top_products.images.TopProductsImageRepository;
import Anor.market.domain.service.Top_products.images.TopProductImageService;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
/**
 * TopProductImageServiceImpl implements TopProductImageService and the top products main image is setting that is id related to the next page of the product
 * this images id is sending next page for fetch products details
 * this is actually for giving ads by sellers whose product on the top level and highly recommends by anor market according to their contract
 * */
@Service
public class TopProductImageServiceImpl implements TopProductImageService {

    @Autowired
    private TopProductsImageRepository topProductsImageRepository;
    @Autowired
    private TopProductsRepository topProductsRepository;
    @Autowired
    private TopProductImageMapper topProductImageMapper;

    @Value("${attach.upload.folder}")
    private String folderName;
    @Value("${attach.upload.url}")
    private String url;

    private static final long MAX_SIZE = 5L * 1024 * 1024; // 5MB
    private static final Set<String> ALLOWED = Set.of("image/jpeg", "image/png", "image/webp");

    /// CREATE OF THE PRODUCTS IMAGES
    @Override
    public TopProductImageDTO saveImage(MultipartFile multipartFile) {
        String fullUrl;
        try {
            //checking file isEmpty, size and type!
            if (multipartFile == null || multipartFile.isEmpty())
                throw new IllegalArgumentException("Empty file");
            if (multipartFile.getSize() > MAX_SIZE)
                throw new IllegalArgumentException("File too large (max 5MB)");
            if (!ALLOWED.contains(multipartFile.getContentType()))
                throw new IllegalArgumentException("Invalid type: " + multipartFile.getContentType());

            //extensions
            String pathFolder = LocalDate.now().toString();
            String keyUUID = UUID.randomUUID().toString();
            String extension = getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));

            //creating folder
            File folder = new File(folderName + "/" + pathFolder);
            if (!folder.exists()) folder.mkdirs();

            //file path and write in
            Path path = Paths.get(folderName + "/" + pathFolder + "/" + keyUUID + "." + extension);
            Files.write(path, multipartFile.getBytes());

            //full url
            fullUrl = url + "/" + pathFolder + "/" + keyUUID + "." + extension;

            //creating image
            TopProductImageEntity productImage = new TopProductImageEntity();
            productImage.setTopProductImageId(keyUUID);
            productImage.setPath(pathFolder);
            productImage.setExtension(extension);
            productImage.setOrigenName(multipartFile.getOriginalFilename());
            productImage.setSizes(multipartFile.getSize());
            productImage.setUrl(fullUrl);

            //saved database
            topProductsImageRepository.save(productImage);
            //to dto
            return topProductImageMapper.toDTO(productImage, fullUrl);
        } catch (Exception e) {
            throw new AppBadException("File saving error: " + e.getMessage());
        }

    }

    /// GET ALL THE PRODUCTS IMAGES
    @Override
    public ResponseEntity<Resource> getImage(String imageId) throws IOException {
        TopProductImageEntity topProducts = topProductsImageRepository.findById(imageId).orElseThrow(() -> new IllegalArgumentException("Top Product id not found"));
        //paths
        Path filePath = Paths.get(folderName + "/" + topProducts.getTopProductImageId() + "." + topProducts.getExtension()).normalize();
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

        } catch (Exception e) {
            throw new AppBadException("Error while reading image file: " + e.getMessage());
        }
    }

    /// DELETE THE PRODUCTS IMAGES WITHIN THE FILE
    @Override
    public String removeImage(String imageId) {
        topProductsImageRepository.deleteById(imageId);
        return "Deleted!";
    }

    /// EXTENSION OF THE FILE LAST INDEX AND SUBSTRING!
    private String getExtension(String filename) {
        int lastIndex = filename.lastIndexOf(".");
        return filename.substring(lastIndex + 1);
    }
}
