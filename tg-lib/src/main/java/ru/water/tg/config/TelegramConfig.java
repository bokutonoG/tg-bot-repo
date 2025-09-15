package ru.water.tg.config;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import ru.water.tg.api.TelegramApi;
import ru.water.tg.config.properties.TelegramProperties;
import reactor.netty.http.client.HttpClient;
import ru.water.tg.http.TelegramApiClient;

import java.time.Duration;


@Configuration
@EnableConfigurationProperties(TelegramProperties.class)
@RequiredArgsConstructor
public class TelegramConfig {

    private final TelegramProperties telegramProperties;

    private String botBaseUrl() {
        String botBaseUrl = telegramProperties.baseUrl();
        if(botBaseUrl.endsWith("/")) {
            botBaseUrl = botBaseUrl.substring(0, telegramProperties.baseUrl().length() - 1);
        }
        return botBaseUrl + "/bot" + telegramProperties.botToken();
    }

    @Bean(name = "longTelegramClient")
    public WebClient TelegramLongWebClient() {
        HttpClient httpLongClient = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, telegramProperties.longClientSettings().connectTimeoutMs())
                .responseTimeout(Duration.ofMillis(telegramProperties.longClientSettings().responseTimeoutMs()))
                .compress(true);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpLongClient))
                .baseUrl(botBaseUrl())
                .build();
    }

    @Bean(name = "shortTelegramClient")
    public WebClient shortTelegramClient() {
        HttpClient httpShortClient = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, telegramProperties.shortClientSettings().connectTimeoutMs())
                .responseTimeout(Duration.ofMillis(telegramProperties.shortClientSettings().responseTimeoutMs()))
                .compress(true);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpShortClient))
                .baseUrl(botBaseUrl())
                .build();
    }

    @Bean
    public TelegramApi telegramApiClient(@Qualifier("shortTelegramClient") WebClient shortTelegramClient,
                                         @Qualifier("longTelegramClient") WebClient longTelegramClient) {

        return new TelegramApiClient(shortTelegramClient, longTelegramClient, telegramProperties);
    }
}
