package com.accenture.locationvoitures.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    private static final String ACCEPT_LANGUAGE_PARAM_KEY = "AcceptLanguageHeader";
    private static final String ACCEPT_LANGUAGE_REF = "#/components/parameters/" + ACCEPT_LANGUAGE_PARAM_KEY;

    @Bean
    public OpenAPI customOpenAPI() {
        Parameter acceptLanguage = new Parameter()
                .in("header")
                .name("Accept-Language")
                .required(false)
                .description("Response Locale (ex: fr-FR, en-US, es-ES)")
                .schema(new StringSchema()
                        ._default("en-US")
                        ._enum(List.of("en-US"))
                );

        Components components = new Components()
                .addParameters(ACCEPT_LANGUAGE_PARAM_KEY, acceptLanguage);

        return new OpenAPI()
                .components(components)
                .info(new Info()
                        .title("Car Rental API")
                        .version("1.0.0")
                        .description("REST API to rent cars.")
                        .contact(new Contact()
                                .name("Accenture")
                                .email("contact@accenture.com")
                                .url("https://www.accenture.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

    @Bean
    public OperationCustomizer addAcceptLanguageHeaderToAllOperations() {
        return (operation, _) -> {
            boolean alreadyPresent = operation.getParameters() != null
                    && operation.getParameters().stream()
                    .anyMatch(p -> "Accept-Language".equalsIgnoreCase(p.getName()));

            if (!alreadyPresent) {
                operation.addParametersItem(new Parameter().$ref(ACCEPT_LANGUAGE_REF));
            }
            return operation;
        };
    }

}
