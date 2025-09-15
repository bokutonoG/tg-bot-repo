package ru.water.tg.types.configs;

import java.util.List;

public record Polling(
        int timeoutSec,
        int limit,
        List<String> allowedUpdates
) {
}
