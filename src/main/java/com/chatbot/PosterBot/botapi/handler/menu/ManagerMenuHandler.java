package com.chatbot.PosterBot.botapi.handler.menu;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.keyboard.ManagerMenuService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ManagerMenuHandler implements InputMessageHandler {

    private final ManagerMenuService managerMenuService;

    public ManagerMenuHandler(ManagerMenuService managerMenuService) {
        this.managerMenuService = managerMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return managerMenuService.getManagersContactMessage(message.getChatId());
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CONTACT_WITH_MANAGER;
    }
}
