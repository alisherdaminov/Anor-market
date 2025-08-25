package Anor.market.application.service.catalog.product.images;

import Anor.market.domain.service.catalog.product.images.FileStorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${app.upload.root:uploads}")
    private Path root;

    @PostConstruct
    void init() throws IOException {
        Files.createDirectories(root);
    }


    @Override
    public StoredFile store(MultipartFile multipartFile, String folder) throws IOException {
        String safeExt = Optional.ofNullable(multipartFile.getOriginalFilename())
                .filter(n -> n.contains("."))
                .map(n -> n.substring(n.lastIndexOf(".")))
                .orElse("");
        String fileName = UUID.randomUUID() + safeExt;
        Path dir = root.resolve(folder);
        Files.createDirectories(dir);
        Path target = dir.resolve(fileName);
        try (var in = multipartFile.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        String url = "/static/" + folder + "/" + fileName;
        return new StoredFile(fileName, url, multipartFile.getContentType(), multipartFile.getSize());
    }

    @Override
    public void delete(String folder, String fileName) throws IOException {
        Files.deleteIfExists(root.resolve(folder).resolve(fileName));
    }
}
