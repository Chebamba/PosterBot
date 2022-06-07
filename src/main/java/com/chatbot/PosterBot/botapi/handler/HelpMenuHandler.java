package com.chatbot.PosterBot.botapi.handler;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.MainMenuService;
import com.chatbot.PosterBot.service.ReplyMessageService;
import com.chatbot.PosterBot.util.Emojis;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class HelpMenuHandler implements InputMessageHandler {

    private final MainMenuService mainMenuService;
    private final ReplyMessageService messageService;

    public HelpMenuHandler(MainMenuService mainMenuService, ReplyMessageService messageService) {
        this.mainMenuService = mainMenuService;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(),
                messageService.getEmojisReplyText("reply.helpMenu.welcomeMessage", Emojis.HELP));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP;
    }
}
