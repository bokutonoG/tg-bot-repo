package ru.water.telegram.bot.service;

import ru.water.telegram.bot.dto.UserView;
import ru.water.tg.types.common.User;

public interface UserService {

    UserView upsertTelegramUser(User user);
    UserView getTelegramUser(long telegramId);
}
