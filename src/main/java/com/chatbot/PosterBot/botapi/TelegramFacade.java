package com.chatbot.PosterBot.botapi;

import com.chatbot.PosterBot.cache.UserDataCacheImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramFacade {

    private final UserDataCacheImpl userDataCache;
    private final BotStateContext botStateContext;

    public TelegramFacade(UserDataCacheImpl userDataCache, BotStateContext botStateContext) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId:{}, with text:{}", message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMessage = message.getText();
        int userId = Math.toIntExact(message.getFrom().getId());
        BotState botState;
        SendMessage replyMessage;

        switch (inputMessage) {
            case "/start":
                botState = BotState.SHOW_MAIN_MENU;
                break;
            case "/order":
                botState = BotState.FILLING_ORDER;
                break;
            case "/pay":
                botState = BotState.ORDER_FILLED;
            case "/help":
                botState = BotState.HELP;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }
}
