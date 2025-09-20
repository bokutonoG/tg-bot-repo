CREATE TABLE IF NOT EXISTS users (
 id BIGSERIAL PRIMARY KEY,
 telegram_id BIGINT NOT NULL UNIQUE,
 user_name TEXT,
 is_subscribed BOOLEAN NOT NULL DEFAULT FALSE,
 is_premium   BOOLEAN NOT NULL DEFAULT FALSE,
 quota        INT     NOT NULL DEFAULT 0,
 created_at   TIMESTAMP NOT NULL,
 updated_at   TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_users_telegram_id ON users(telegram_id);