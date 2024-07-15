package com.choi.springshop;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringshopApplication.class, args);
    }

}
