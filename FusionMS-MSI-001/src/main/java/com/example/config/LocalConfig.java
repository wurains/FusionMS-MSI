package com.example.config;

import com.example.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class LocalConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }
}
