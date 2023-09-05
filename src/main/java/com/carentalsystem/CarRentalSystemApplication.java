package com.carentalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.carentalsystem.util.mapper"})
public class CarRentalSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarRentalSystemApplication.class, args);
    }
}
