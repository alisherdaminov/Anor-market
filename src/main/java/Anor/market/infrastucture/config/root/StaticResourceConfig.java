package Anor.market.infrastucture.config.root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

/**
 * Why do we need it?
 * When we upload files (e.g., product images) with MultipartFile, we store them in a folder on disk (e.g., uploads/).
 * By default, Spring Boot does not expose those files to the web.
 * With this configuration, Spring maps a URL prefix (/api/**) to that folder,
 * so our users (browser/mobile app) can fetch the images directly.
 * How does it work?
 * Suppose you save an image here:
 * uploads/products/12345.png
 * Thanks to this config, users can fetch it via:
 * <a href="http://localhost:8080/api/products/12345.png">...</a>
 * Spring internally resolves that URL → reads the file from uploads/products/12345.png → returns it as HTTP response.
 */
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Value("${app.upload.root:uploads}")
    private String root;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/**").addResourceLocations(Path.of(root).toUri().toString());
    }
}
