package com.chatbot.PosterBot.botapi.handler.menu;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.keyboard.PostersSizeMenuService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class PosterSizeMenuHandler implements InputMessageHandler{

    private final ReplyMessageService replyMessageService;
    private final PostersSizeMenuService postersSizeMenuService;

    public PosterSizeMenuHandler(ReplyMessageService replyMessageService, PostersSizeMenuService postersSizeMenuService) {
        this.replyMessageService = replyMessageService;
        this.postersSizeMenuService = postersSizeMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return postersSizeMenuService.getPosterSizeMenuMessage(message.getChatId(), message.getText(), replyMessageService.getReplyText("reply.askSize"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_SIZE;
    }
}
