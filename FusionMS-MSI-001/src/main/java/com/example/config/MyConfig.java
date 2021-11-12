package com.example.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
//@ConfigurationProperties(prefix = "testData")
@Component
public class MyConfig {
    private String data1;
    private String data2;
}
