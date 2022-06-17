package com.chatbot.PosterBot.service.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class YesOrNoService {

    public SendMessage getYesOrNoMenuMessage(long chatId) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getYesOrNoMenuKeyboard();
        final SendMessage YesOrNoMenuMessage =
                createMessageWithKeyboard(chatId, replyKeyboardMarkup);
        return YesOrNoMenuMessage;
    }

    private ReplyKeyboardMarkup getYesOrNoMenuKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRow = new ArrayList<>();
        KeyboardRow firstKeyboardRow = new KeyboardRow();
        firstKeyboardRow.add(new KeyboardButton("Так"));
        firstKeyboardRow.add(new KeyboardButton("Ні"));
        keyboardRow.add(firstKeyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRow);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(long chatId, ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}
