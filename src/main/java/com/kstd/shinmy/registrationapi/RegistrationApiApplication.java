package com.kstd.shinmy.registrationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.kstd.shinmy.registrationapi")
public class RegistrationApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RegistrationApiApplication.class);
    }
}
