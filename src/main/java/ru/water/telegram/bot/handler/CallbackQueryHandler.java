package ru.water.telegram.bot.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.water.tg.types.updates.Update;

@Component("callbackQueryHandler")
public class CallbackQueryHandler implements UpdatesHandler{

    @Async("updatesExecutor")
    @Override
    public void handle(Update update) {

    }
}
