package ru.water.telegram.bot.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.water.telegram.bot.dto.UserView;
import ru.water.telegram.bot.entity.UserEntity;
import ru.water.telegram.bot.repository.UserRepository;
import ru.water.telegram.bot.service.UserService;
import ru.water.telegram.bot.utils.UserEntityMapper;
import ru.water.telegram.bot.utils.UserViewMapper;
import ru.water.tg.api.TelegramApi;
import ru.water.tg.types.common.ChatMember;
import ru.water.tg.types.common.User;
import ru.water.tg.types.common.enums.ChatMemberStatus;

import static ru.water.tg.types.common.enums.ChatMemberStatus.MEMBER;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TelegramApi telegramClient;
    private final UserRepository userRepository;
    private static final Long CHAT_ID = -1002237921383L;

    @Transactional
    @Override
    public UserView upsertTelegramUser(User user) {
        log.info("Начинаем процесс учета пользователя с id={}", user.userId());
        UserEntity userEntity = userRepository.findByTelegramId(user.userId()).orElse(null);

        if (userEntity == null) {
            log.info("Новый пользователь с id={}, начинаем регистрацию", user.userId());
            var newEntity = UserEntityMapper.mapToUserEntityOnCreate(user);
            userEntity = userRepository.save(newEntity);
        }
        else {
            log.info("Пользователь с id={}, уже был зарегистрирован, обновляем", user.userId());
            userEntity.setUsername(user.username());
        }

        return UserViewMapper.mapToUserView(userEntity);
    }

    @Override
    public UserView getTelegramUser(long telegramId) {
        return null;
    }


    // вынести в другой сервис
    private boolean isSubscriber(long userId) {
        try {
            ChatMember chatMember = telegramClient.getChatMember(CHAT_ID, userId);
            if (chatMember.status() == MEMBER || chatMember.status() == ChatMemberStatus.CREATOR ||
                    chatMember.status() == ChatMemberStatus.ADMINISTRATOR) {

                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
