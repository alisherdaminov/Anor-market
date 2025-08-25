package Anor.market.domain.service.catalog.product.images;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    StoredFile store(MultipartFile file, String folder) throws IOException;

    void delete(String folder, String fileName) throws IOException;

    record StoredFile(String fileName, String url, String contentType, long sizeBytes) {
    }
}
