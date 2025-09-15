package ru.water.tg.api;

import ru.water.tg.exception.TelegramRequestException;
import ru.water.tg.exception.TelegramResponseException;
import ru.water.tg.types.common.ChatMember;
import ru.water.tg.types.send.AnswerCallbackQueryRequest;
import ru.water.tg.types.send.EditMessageTextRequest;
import ru.water.tg.types.send.SendMessageRequest;
import ru.water.tg.types.updates.Message;
import ru.water.tg.types.updates.Update;

import java.util.List;

public interface TelegramApi {

    List<Update> getUpdates(Long offset) throws TelegramRequestException, TelegramResponseException;
    Message sendMessage(SendMessageRequest sendMessageRequest) throws TelegramRequestException, TelegramResponseException;
    Message editMessageText(EditMessageTextRequest editMessageTextRequest) throws TelegramRequestException, TelegramResponseException;
    void answerCallbackQuery(AnswerCallbackQueryRequest answerCallbackQueryRequest) throws TelegramRequestException, TelegramResponseException;
    ChatMember getChatMember(long chatId, long userId) throws TelegramRequestException, TelegramResponseException;

}
