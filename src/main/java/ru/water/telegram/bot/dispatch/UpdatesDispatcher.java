package ru.water.telegram.bot.dispatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.water.telegram.bot.handler.UpdatesHandler;
import ru.water.tg.types.updates.Update;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdatesDispatcher {

    @Qualifier("messageHandler")
    private final UpdatesHandler messageHandler;
    @Qualifier("callbackQueryHandler")
    private final UpdatesHandler callbackQueryHandler;

    public void dispatch(Update update) {
        try {
            if (update.message() != null) {
                messageHandler.handle(update);
            } else if (update.callbackQuery() != null) {
                callbackQueryHandler.handle(update);
            } else {
                log.error("Unknown update type: {}", update);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
