package ru.water.telegram.bot.utils;


import ru.water.telegram.bot.dto.UserView;
import ru.water.telegram.bot.entity.UserEntity;



public class UserViewMapper {

    public static UserView mapToUserView(UserEntity userEntity) {
        return new UserView(
                userEntity.getTelegramId(),
                userEntity.isSubscribed(),
                userEntity.isPremium()
        );
    }

}
