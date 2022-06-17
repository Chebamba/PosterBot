package com.chatbot.PosterBot.botapi;

import com.chatbot.PosterBot.PosterBot;
import com.chatbot.PosterBot.cache.UserDataCacheImpl;
import com.chatbot.PosterBot.service.keyboard.MainMenuService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramFacade {

    private final UserDataCacheImpl userDataCache;
    private final BotStateContext botStateContext;
    private final MainMenuService mainMenuService;
    private final PosterBot posterBot;
    private final ReplyMessageService replyMessageService;

    public TelegramFacade(UserDataCacheImpl userDataCache, BotStateContext botStateContext, MainMenuService mainMenuService,
                          @Lazy PosterBot posterBot, ReplyMessageService replyMessageService) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
        this.mainMenuService = mainMenuService;
        this.posterBot = posterBot;
        this.replyMessageService = replyMessageService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMessage = message.getText();
        int chatId = Math.toIntExact(message.getFrom().getId());
        BotState botState;
        SendMessage replyMessage;

        switch (inputMessage) {
            case "/start":
                botState = BotState.SHOW_MAIN_MENU;
                break;
            case "Оформити замовлення":
                posterBot.sendPhoto(chatId, "static/images/Постер + пластик.jpg");
                posterBot.sendPhoto(chatId, "static/images/Постер + дерево.jpg");
                posterBot.sendPhoto(chatId, "static/images/Постер + біла підсвітка.jpg");
                posterBot.sendPhoto(chatId, "static/images/Постер + кольорова підсвітка.jpg");
                posterBot.sendPhoto(chatId, "static/images/Постер + кольорова підсвітка з колонкою.jpg");
                botState = BotState.FILLING_ORDER;
                break;
            case "Зв'язатись з менеджером":
                botState = BotState.CONTACT_WITH_MANAGER;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(chatId);
        }

        userDataCache.setUsersCurrentBotState(chatId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }
}
