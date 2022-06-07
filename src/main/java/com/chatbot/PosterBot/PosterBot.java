package com.chatbot.PosterBot;

import com.chatbot.PosterBot.botapi.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Setter
@Getter
public class PosterBot extends TelegramWebhookBot {

    private final TelegramFacade telegramFacade;

    public PosterBot(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }

    private String botToken;

    private String botUserName;

    private String webHookPath;

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        final BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);

        return replyMessageToUser;
    }
}
//
//        if (update.getMessage() != null && update.getMessage().hasText()) {
//            if (update.getMessage().getText().equals("/start")) {
//                try {
//                    execute(menu1Service.mainMenuService1(update.getMessage().getChatId()));
//                    execute(menuService.getMainMenuMessage(update.getMessage().getChatId(), "Привіт! Я - PosterBot \uD83E\uDD16 \n" +
//                            "Допоможу вибрати дизайн та оформити замовлення для твого постеру"));
//
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }