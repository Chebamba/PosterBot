package com.chatbot.PosterBot.botapi.handler;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.ReplyMessageService;
import com.chatbot.PosterBot.service.ShowOrderService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ShowOrderHandler implements InputMessageHandler {

    private final ShowOrderService showOrderService;
    private final ReplyMessageService messageService;

    public ShowOrderHandler(ShowOrderService showOrderService, ReplyMessageService messageService) {
        this.showOrderService = showOrderService;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return showOrderService.showOrder(message.getChatId(), messageService.getReplyText("reply.showOrder.welcomeMessage"));
    }

    @Override
    public BotState getHandlerName() { return BotState.ORDER_FILLED; }

}
