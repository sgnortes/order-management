package com.sgnortes.ordermanagement.common.config;

import com.sgnortes.ordermanagement.common.properties.SwaggerCustomProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Configuration class for swagger where we include information of our api.
 *
 * @author Sergio
 */
@Configuration
public class OpenApiConfig {

    private final SwaggerCustomProperties swaggerCustomProperties;

    public OpenApiConfig(SwaggerCustomProperties swaggerCustomProperties) {
        this.swaggerCustomProperties = swaggerCustomProperties;
    }

    @Bean
    public OpenAPI openAPI(){

        List<Server> servers = swaggerCustomProperties.getServersInfo().stream().map(serverInfo ->
                        new Server().url(serverInfo.getUrl()).description(serverInfo.getDescription()))
                .collect(Collectors.toList());

        Contact contact = new Contact();
        contact.setEmail(swaggerCustomProperties.getContactEmail());
        contact.setName(swaggerCustomProperties.getContactName());

        Info info = new Info()
                .title(swaggerCustomProperties.getApiTitle())
                .version(swaggerCustomProperties.getApiVersion())
                .contact(contact)
                .description(swaggerCustomProperties.getApiDescription());

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        Components components = new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        return new OpenAPI().info(info).servers(servers).addSecurityItem(securityRequirement).components(components);

    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
