package ru.water.telegram.bot;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.water.telegram.bot.config.properties.ModuleProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(ModuleProperties.class)
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
