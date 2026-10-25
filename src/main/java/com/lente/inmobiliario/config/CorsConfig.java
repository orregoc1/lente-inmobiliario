package com.lente.inmobiliario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * En esta clase configuro el acceso global de CORS para la API de Lente Inmobiliario.
 * Habilito esta política para permitir que mi aplicación de Frontend (HTML/JS/React)
 * pueda realizar peticiones HTTP a mis controladores sin que el navegador bloquee la conexión.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Defino que las reglas apliquen únicamente para las rutas que inicien con /api/
                registry.addMapping("/api/**")

                        // Permitimos peticiones desde cualquier origen durante la fase de desarrollo.
                        // Nota personal: Cuando pase a producción, cambiaré "*" por la URL exacta de mi dominio de frontend.
                        .allowedOrigins("*")

                        // Especifico los métodos HTTP que voy a permitir consumir desde el cliente
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                        // Acepto cualquier cabecera (Header) enviada en las peticiones del frontend
                        .allowedHeaders("*");
            }
        };
    }
}
