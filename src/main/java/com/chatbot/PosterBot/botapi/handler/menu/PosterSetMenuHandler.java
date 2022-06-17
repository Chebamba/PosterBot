package com.chatbot.PosterBot.botapi.handler.menu;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.keyboard.PosterSetMenuService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class PosterSetMenuHandler implements InputMessageHandler {

    private final PosterSetMenuService posterSetMenuService;
    private final ReplyMessageService messageService;

    public PosterSetMenuHandler(PosterSetMenuService posterSetMenuService, ReplyMessageService messageService) {
        this.posterSetMenuService = posterSetMenuService;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return posterSetMenuService.getOrderMenuMessage(message.getChatId(), messageService.getReplyText("reply.orderMenu.askSet"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_SET;
    }
}
