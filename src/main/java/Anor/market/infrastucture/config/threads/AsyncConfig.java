package Anor.market.infrastucture.config.threads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);   // minimal thread
        executor.setMaxPoolSize(10);   // maximal thread
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("ImageUploader-");
        executor.initialize();
        return executor;
    }
}
