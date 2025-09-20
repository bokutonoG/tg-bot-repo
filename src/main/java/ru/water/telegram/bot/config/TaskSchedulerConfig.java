package ru.water.telegram.bot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.water.telegram.bot.config.properties.ModuleProperties;

@Configuration
@RequiredArgsConstructor
public class TaskSchedulerConfig {

    private final ModuleProperties moduleProperties;

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(moduleProperties.schedulerTaskPoolSize());
        return scheduler;
    }
}
