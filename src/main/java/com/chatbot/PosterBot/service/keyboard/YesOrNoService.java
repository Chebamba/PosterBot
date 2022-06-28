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

    public SendMessage getYesOrNoMenuMessage(final long chatId, final String textMessage) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getYesOrNoMenuKeyboard();
        final SendMessage YesOrNoMenuMessage =
                createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
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

    private SendMessage createMessageWithKeyboard(long chatId, String textMessage, ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}
