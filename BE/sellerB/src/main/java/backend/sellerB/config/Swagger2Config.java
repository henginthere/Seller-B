package backend.sellerB.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class Swagger2Config {
    // Swagger UI page : http://localhost:5000/api/swagger-ui/index.html
    // Swagger OpenAPI description : http://localhost:5000/api/v3/api-docs
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("SellerB-definition")
                .pathsToMatch("/api/**")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SellerB API")
                        .description("SellerB 프로젝트 API 명세서입니다.")
                        .version("v0.0.1"));
    }
}
