package com.chatbot.PosterBot.botapi.handler.menu;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.keyboard.MainMenuService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MainMenuHandler implements InputMessageHandler {

    private final MainMenuService mainMenuService;
    private final ReplyMessageService messageService;

    public MainMenuHandler(MainMenuService mainMenuService, ReplyMessageService messageService) {
        this.mainMenuService = mainMenuService;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(), messageService.getReplyText("reply.mainMenu.welcomeMessage"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MAIN_MENU;
    }
}
