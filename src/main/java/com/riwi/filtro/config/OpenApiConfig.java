package com.riwi.filtro.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuracion de swagger
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Api para administrar citas de un salon", version = "1.0", description = "Documentación api de filtro lovelace"))
public class OpenApiConfig {

}