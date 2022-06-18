package com.chatbot.PosterBot.botapi.handler.menu;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.keyboard.YesOrNoService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class YesOrNoMenuHandler implements InputMessageHandler {

    private final YesOrNoService yesOrNoService;
    private final ReplyMessageService replyMessageService;

    public YesOrNoMenuHandler(YesOrNoService yesOrNoService, ReplyMessageService replyMessageService) {
        this.yesOrNoService = yesOrNoService;
        this.replyMessageService = replyMessageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return yesOrNoService.getYesOrNoMenuMessage(message.getChatId(), replyMessageService.getReplyText(""));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.YES_OR_NO;
    }
}
