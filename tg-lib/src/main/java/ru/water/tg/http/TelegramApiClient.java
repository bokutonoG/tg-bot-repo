package ru.water.tg.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.water.tg.api.TelegramApi;
import ru.water.tg.config.properties.TelegramProperties;
import ru.water.tg.exception.TelegramRequestException;
import ru.water.tg.exception.TelegramResponseException;
import ru.water.tg.logger.Messages;
import ru.water.tg.types.common.ChatMember;
import ru.water.tg.types.send.AnswerCallbackQueryRequest;
import ru.water.tg.types.send.EditMessageTextRequest;
import ru.water.tg.types.send.SendMessageRequest;
import ru.water.tg.types.updates.Message;
import ru.water.tg.types.updates.Update;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.water.tg.logger.Messages.BASE_TG_CHECK_ERROR;

@RequiredArgsConstructor
@Slf4j
public class TelegramApiClient implements TelegramApi {

    private static final String OFFSET_PARAM_KEY = "offset";
    private static final String LIMIT_PARAM_KEY = "limit";
    private static final String LONG_POLLING_TIMEOUT_PARAM_KEY = "timeout";


    @Qualifier("shortTelegramClient")
    private final WebClient shortTelegramClient;
    @Qualifier("longTelegramClient")
    private final WebClient longTelegramClient;
    private final TelegramProperties telegramProperties;

    @Override
    public List<Update> getUpdates(Long offset) throws TelegramRequestException, TelegramResponseException {
        log.info(Messages.SEND_REQUEST_TO_TG.message, telegramProperties.paths().getUpdatesPath());
        ApiResponse<List<Update>> response = longTelegramClient
                .get()
                .uri(telegramProperties.paths().getUpdatesPath(), uriBuilder -> uriBuilder
                        .queryParam(LONG_POLLING_TIMEOUT_PARAM_KEY, telegramProperties.pollingQueries().timeoutSec())
                        .queryParam(LIMIT_PARAM_KEY, telegramProperties.pollingQueries().limit())
                        .queryParamIfPresent(OFFSET_PARAM_KEY, Optional.of(offset))
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<Update>>>() {})
                .onErrorMap(this::mapToDomainException)
                .doOnSuccess(res -> log.info(Messages.RESPONSE_RECEIVED.message, res))
                .doOnError(error -> log.error(Messages.ERROR_RECEIVED.message, error.getMessage(), error))
                .block();

        log.info(Messages.CHECK_TG_RESPONSE.message);
        return listOrEmpty(response);

    }

    @Override
    public Message sendMessage(SendMessageRequest sendMessageRequest) throws TelegramRequestException, TelegramResponseException {
        log.info(Messages.SEND_REQUEST_TO_TG.message, telegramProperties.paths().sendMessagePath());
        ApiResponse<Message> response = shortTelegramClient
                .post()
                .uri(telegramProperties.paths().sendMessagePath())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(sendMessageRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<Message>>() {})
                .onErrorMap(this::mapToDomainException)
                .doOnSuccess(res -> log.info(Messages.RESPONSE_RECEIVED.message, res))
                .doOnError(error -> log.error(Messages.ERROR_RECEIVED.message, error.getMessage(), error))
                .block();

        log.info(Messages.CHECK_TG_RESPONSE.message);
        return requireResultOrThrow(response);
    }

    @Override
    public Message editMessageText(EditMessageTextRequest editMessageTextRequest) throws TelegramRequestException,
            TelegramResponseException {
        log.info(Messages.SEND_REQUEST_TO_TG.message, telegramProperties.paths().editMessagePath());
        ApiResponse<Message> response = shortTelegramClient
                .post()
                .uri(telegramProperties.paths().editMessagePath())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(editMessageTextRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<Message>>() {})
                .onErrorMap(this::mapToDomainException)
                .doOnSuccess(res -> log.info(Messages.RESPONSE_RECEIVED.message, res))
                .doOnError(error -> log.error(Messages.ERROR_RECEIVED.message, error.getMessage(), error))
                .block();

        log.info(Messages.CHECK_TG_RESPONSE.message);
        return requireResultOrThrow(response);
    }

    @Override
    public void answerCallbackQuery(AnswerCallbackQueryRequest answerCallbackQueryRequest) throws TelegramRequestException,
            TelegramResponseException {
        log.info(Messages.SEND_REQUEST_TO_TG.message, telegramProperties.paths().answerCallbackQuery());
        ApiResponse<Boolean> response = shortTelegramClient
                .post()
                .uri(telegramProperties.paths().answerCallbackQuery())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(answerCallbackQueryRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<Boolean>>() {})
                .onErrorMap(this::mapToDomainException)
                .doOnSuccess(res -> log.info(Messages.RESPONSE_RECEIVED.message, res))
                .doOnError(error -> log.error(Messages.ERROR_RECEIVED.message, error.getMessage(), error))
                .block();

        log.info(Messages.CHECK_TG_RESPONSE.message);
        requireOkOrThrow(response);
    }

    @Override
    public ChatMember getChatMember(long chatId, long userId) throws TelegramRequestException, TelegramResponseException {
        log.info(Messages.SEND_REQUEST_TO_TG.message, telegramProperties.paths().getChatMember());
        ApiResponse<ChatMember> response = shortTelegramClient
                .get()
                .uri(telegramProperties.paths().getChatMember(), uriBuilder -> uriBuilder
                        .queryParam("chat_id", chatId)
                        .queryParam("user_id", userId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ChatMember>>() {})
                .onErrorMap(this::mapToDomainException)
                .doOnSuccess(res -> log.info(Messages.RESPONSE_RECEIVED.message, res))
                .doOnError(error -> log.error(Messages.ERROR_RECEIVED.message, error.getMessage(), error))
                .block();

        log.info(Messages.CHECK_TG_RESPONSE.message);
        return requireResultOrThrow(response);

    }


    private static <T> T requireResultOrThrow(ApiResponse<T> response) {
        if (response == null) {
            log.error(Messages.NULL_RESPONSE.message);
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }
        if(!response.ok()) {
            log.error(Messages.INVALID_RESPONSE.message, response.errorCode(), response.description());
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }
        if (response.result() == null) {
            log.info(Messages.EMPTY_RESPONSE.message, response);
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }

        return response.result();
    }
    private static <T> void requireOkOrThrow(ApiResponse<T> response) {
        if (response == null) {
            log.error(Messages.NULL_RESPONSE.message);
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }
        if (!response.ok()) {
            log.error(Messages.INVALID_RESPONSE.message, response.errorCode(), response.description());
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }
    }
    private static <E> List<E> listOrEmpty(ApiResponse<List<E>> response) {
        if (response == null) {
            log.error(Messages.NULL_RESPONSE.message);
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }
        if(!response.ok()) {
            log.error(Messages.INVALID_RESPONSE.message, response.errorCode(), response.description());
            throw new TelegramResponseException(Messages.BASE_TG_CHECK_ERROR.message);
        }
        if (response.result() == null) {
            log.info(Messages.EMPTY_RESPONSE.message, response);
            return Collections.emptyList();
        }
        return response.result();
    }
    private Throwable mapToDomainException(Throwable throwable) {
        if (throwable instanceof WebClientRequestException) {
            return new TelegramRequestException(throwable.getMessage(), throwable);

        } else if (throwable instanceof WebClientResponseException) {
            String errorMessage = ((WebClientResponseException) throwable).getResponseBodyAsString(StandardCharsets.UTF_8);
            return new TelegramResponseException(errorMessage.isBlank() ? throwable.getMessage() : errorMessage, throwable);
        }
        else {
            return new TelegramResponseException(throwable.getMessage(), throwable);
        }
    }
}
