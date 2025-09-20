package ru.water.telegram.bot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "module")
public record ModuleProperties(int schedulerTaskPoolSize,
                               int botTaskPoolSize,
                               int pollingIntervalMs,
                               int terminationSec,
                               int queueCapacity) {

}

