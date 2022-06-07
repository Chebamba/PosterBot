package com.chatbot.PosterBot.botapi.handler;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.service.OrderMenuService;
import com.chatbot.PosterBot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class OrderMenuHandler implements InputMessageHandler {

    private final OrderMenuService orderMenuService;
    private final ReplyMessageService messageService;

    public OrderMenuHandler(OrderMenuService orderMenuService, ReplyMessageService messageService) {
        this.orderMenuService = orderMenuService;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return orderMenuService.getOrderMenuMessage(message.getChatId(), messageService.getReplyText("reply.orderMenu.welcomeMessage"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_ORDER;
    }
}
