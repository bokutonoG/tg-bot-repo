package ru.water.tg.config.properties;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.water.tg.types.configs.LongClientSettings;
import ru.water.tg.types.configs.Paths;
import ru.water.tg.types.configs.Polling;
import ru.water.tg.types.configs.ShortClientSettings;

@Validated
@ConfigurationProperties("telegram")
public record TelegramProperties(@NotBlank String baseUrl,
                                 @NotBlank String botToken,
                                 @Valid Paths paths,
                                 @Valid ShortClientSettings shortClientSettings,
                                 @Valid LongClientSettings longClientSettings,
                                 @Valid Polling pollingQueries
                                 ) {
}
