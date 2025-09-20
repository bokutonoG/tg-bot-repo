package ru.water.telegram.bot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.water.telegram.bot.config.properties.ModuleProperties;

@Configuration
@RequiredArgsConstructor
public class TaskExecutorConfig {


    private final ModuleProperties moduleProperties;

    @Bean(name = "updatesExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(moduleProperties.botTaskPoolSize());
        executor.setMaxPoolSize(moduleProperties.botTaskPoolSize());

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setQueueCapacity(moduleProperties.queueCapacity());
        executor.setAwaitTerminationSeconds(moduleProperties.terminationSec());

        executor.setThreadNamePrefix("task-executor-");
        return executor;
    }
}
