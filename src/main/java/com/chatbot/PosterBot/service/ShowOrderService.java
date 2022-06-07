package com.chatbot.PosterBot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ShowOrderService {

    public SendMessage showOrder(final long chatId, final String textMessage){
        final SendMessage showOrderMessage = null;
        return showOrderMessage;
    }

}
