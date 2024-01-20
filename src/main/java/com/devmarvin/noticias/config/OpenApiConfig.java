package com.devmarvin.noticias.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NoticiasAPI - API de Noticias")  // Coloca aquí el título deseado
                        .description("API de Noticias. Permite a los usuarios visualizar las noticias mas recientes, detalladas asimismo las noticias recomendadas.")
                        .version("v1")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .email("devmarvinlopez@gmail.com")
                                .name("Marvin"))
                        //Licencia
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth"));

    }


}