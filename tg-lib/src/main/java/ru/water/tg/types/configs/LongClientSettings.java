package ru.water.tg.types.configs;

public record LongClientSettings(
        int responseTimeoutMs,
        int connectTimeoutMs
) {
}
