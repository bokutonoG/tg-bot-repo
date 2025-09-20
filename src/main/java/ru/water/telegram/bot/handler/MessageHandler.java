package ru.water.telegram.bot.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.water.telegram.bot.dto.UserView;
import ru.water.telegram.bot.service.UserService;
import ru.water.telegram.bot.telegram.TelegramMessages;
import ru.water.tg.api.TelegramApi;
import ru.water.tg.types.common.ChatMember;
import ru.water.tg.types.common.User;
import ru.water.tg.types.common.enums.ChatMemberStatus;
import ru.water.tg.types.send.InlineKeyboardButton;
import ru.water.tg.types.send.InlineKeyboardMarkup;
import ru.water.tg.types.send.SendMessageRequest;
import ru.water.tg.types.updates.Message;
import ru.water.tg.types.updates.Update;

import java.util.List;

import static ru.water.tg.types.common.enums.ChatMemberStatus.MEMBER;


@Component("messageHandler")
@Slf4j
@RequiredArgsConstructor
public class MessageHandler implements UpdatesHandler {

    private static final String START_COMMAND = "/start";
    private static final String REQUIRED_TYPE_CHAT = "private";
    private static final String ONBOARDING_URL = "https://t.me/silktunnel";

    private final UserService userService;
    private final TelegramApi telegramClient;

    @Async("updatesExecutor")
    @Override
    public void handle(Update update) {
        Message message = update.message();
        if (!message.chat().type().equals(REQUIRED_TYPE_CHAT))
        {
            telegramClient.sendMessage(
                    new SendMessageRequest(message.chat().chatId(), TelegramMessages.INVALID_CHAT_TYPE.message, null)
            );
            return;
        }
        if (message.user() == null) {
            log.error(TelegramMessages.EMPTY_USER.message);
            return;
        }
        User telegramUser = message.user();
        if (message.text() == null || message.text().isBlank()) {
            log.error(TelegramMessages.EMPTY_MESSAGE_TEXT.message);
            return;
        }
        if (message.text().startsWith(START_COMMAND)) {
            UserView view = userService.upsertTelegramUser(telegramUser);
            if (view.isSubscribed()) {
                telegramClient.sendMessage(
                        new SendMessageRequest(message.chat().chatId(), "Рады видеть вас снова %s".formatted(telegramUser.firstName()),
                                new InlineKeyboardMarkup(List.of(getMenuButtons())))
                );
            } else {
                telegramClient.sendMessage(
                        new SendMessageRequest(message.chat().chatId(), TelegramMessages.ONBOARDING_MESSAGE.message,
                                new InlineKeyboardMarkup(List.of(getOnboardingButtons())))
                );
            }
        } else {
            telegramClient.sendMessage(new SendMessageRequest(
                  message.chat().chatId(),
                    TelegramMessages.UNKNOWN_COMMAND.message,
                    null)
            );
        }
    }
    private static List<InlineKeyboardButton> getOnboardingButtons() {
        return List.of(
                new InlineKeyboardButton("Подписаться", null, ONBOARDING_URL),
                new InlineKeyboardButton("Проверить", "stub-callback-data", null));
    }

    private static List<InlineKeyboardButton> getMenuButtons() {
        return List.of(
                new InlineKeyboardButton("Открыть приложение", "stub-callback-data", null));
    }
}
