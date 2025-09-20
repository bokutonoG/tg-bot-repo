package ru.water.telegram.bot.utils;

import ru.water.telegram.bot.entity.UserEntity;
import ru.water.tg.types.common.User;


public class UserEntityMapper {

    public static UserEntity mapToUserEntityOnCreate(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setTelegramId(user.userId());
        userEntity.setUsername(user.username());
        userEntity.setPremium(false);
        userEntity.setSubscribed(false);
        userEntity.setQuota(0);

        return userEntity;
    }
}
