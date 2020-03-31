package com.ascendingdc.training.project.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//AppBootstrap
@SpringBootApplication(scanBasePackages = {"com.ascendingdc.training.project"})
public class AppInitializer {
    public static void main(String[] args) {
        //AppInitializer app = new AppInitializer();
        SpringApplication.run(AppInitializer.class, args);
    }
}
