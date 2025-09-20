package ru.water.telegram.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.water.telegram.bot.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByTelegramId(long telegramId);
    boolean existsByTelegramId(long telegramId);
}
