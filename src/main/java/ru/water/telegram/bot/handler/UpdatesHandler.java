package ru.water.telegram.bot.handler;



import ru.water.tg.types.updates.Update;


public interface UpdatesHandler {


    void handle(Update update);
}
