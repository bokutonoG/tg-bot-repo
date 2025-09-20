package ru.water.telegram.bot.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.water.telegram.bot.config.properties.ModuleProperties;
import ru.water.telegram.bot.dispatch.UpdatesDispatcher;
import ru.water.telegram.bot.logger.Messages;
import ru.water.tg.api.TelegramApi;
import ru.water.tg.types.updates.Update;

import java.security.MessageDigestSpi;


@RequiredArgsConstructor
@Component
@Slf4j
public class PollingRunner {

    private Long offset = null;

    private final ModuleProperties moduleProperties;
    private final UpdatesDispatcher updatesDispatcher;
    private final TelegramApi telegramClient;


    @Scheduled(fixedDelayString = "${module.polling-interval-ms}")
    public void runPolling() {
        try {
            var res = telegramClient.getUpdates(offset);
            if (res.isEmpty()) {
                log.debug(Messages.EMPTY_UPDATES.message);
                return;
            }
            long maxOffset = res.stream().mapToLong(Update::updateId).max().getAsLong();
            for (Update update : res) {
                updatesDispatcher.dispatch(update);
            }
            log.info(Messages.COMMIT_UPDATE_INFO.message, maxOffset);
            offset = maxOffset + 1;


        } catch (Exception e) {
            log.error(Messages.ERROR_WHILE_POLLING.message,e.getMessage(), e);
        }

    }
}
