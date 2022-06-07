package com.chatbot.PosterBot.service;

import com.chatbot.PosterBot.util.Emojis;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyMessageService {

    private final LocaleMessageService localeMessageService;

    public ReplyMessageService(LocaleMessageService localMessageService) {
        this.localeMessageService = localMessageService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage){
        return new SendMessage(String.valueOf(chatId), localeMessageService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage, Object... args){
        return new SendMessage(String.valueOf(chatId), localeMessageService.getMessage(replyMessage, args));
    }

    public SendMessage getSuccessReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(String.valueOf(chatId), getEmojisReplyText(replyMessage, Emojis.SUCCESS_MARK));
    }

    public SendMessage getWarningReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(String.valueOf(chatId), getEmojisReplyText(replyMessage, Emojis.FAIL_MARK));
    }

    public String getReplyText(String replyText) {
        return localeMessageService.getMessage(replyText);
    }

    public String getReplyText(String replyText, Object... args) {
        return localeMessageService.getMessage(replyText);
    }

    public String getEmojisReplyText(String replyText, Emojis emoji){
        return localeMessageService.getMessage(replyText, emoji);
    }

}
