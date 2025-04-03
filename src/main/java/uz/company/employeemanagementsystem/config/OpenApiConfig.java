package uz.company.employeemanagementsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${application.serverUrl}")
    private String serverUrl;

    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Employee Management System API"))
                .servers(List.of(new Server().url(serverUrl)));
    }
}