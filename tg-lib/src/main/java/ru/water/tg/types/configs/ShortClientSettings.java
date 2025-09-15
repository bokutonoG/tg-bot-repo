package ru.water.tg.types.configs;

public record ShortClientSettings(
        int responseTimeoutMs,
        int connectTimeoutMs
) {
}
