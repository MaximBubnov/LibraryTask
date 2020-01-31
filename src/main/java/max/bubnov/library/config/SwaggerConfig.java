package max.bubnov.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/library.*"))
                .apis(RequestHandlerSelectors.basePackage("max.bubnov.library"))
                .build()
                .apiInfo(apiInformation());
    }

    private ApiInfo apiInformation() {
        return new ApiInfo(
                "Library book API",
                "API for Interview Task",
                "1.0",
                "Free",
                new Contact("Maskim Bubnov", "https://github.com/MaximBubnov", "max.bubnov97@gmail.com"),
                "Library license",
                "https://github.com/MaximBubnov",
                Collections.emptyList()
        );
    }
}
