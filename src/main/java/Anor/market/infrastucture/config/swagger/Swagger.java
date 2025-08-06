package Anor.market.infrastucture.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Anor market")
                .version("1.0.0")
                .description("")
                .contact(new Contact()
                        .name("Alisher")
                        .email("alisherdaminov135@gmail.com")
                        .url("https://github.com/alisherdaminov/anormarket"))
                .license(new License()
                        .name("Anor market"));
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("bearerAuth");
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setName("bearerAuth");
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.bearerFormat("JWT");
        securityScheme.setIn(SecurityScheme.In.HEADER);
        securityScheme.setScheme("bearer");
        Components components = new Components();
        components.addSecuritySchemes("bearerAuth", securityScheme);
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.setSecurity(List.of(securityRequirement));
        openAPI.components(components);
        return openAPI;
    }
}
