package com.chatbot.PosterBot.service.keyboard;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class PosterSetMenuService {

    public SendMessage getOrderMenuMessage(final long chatId, final String textMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = getOrderMenuKeyboard();
        SendMessage orderMenuMessage = createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
        return orderMenuMessage;
    }

    private ReplyKeyboardMarkup getOrderMenuKeyboard() {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();

        row1.add(new KeyboardButton("Постер + пластикова стійка"));
        row2.add(new KeyboardButton("Постер + дерев'яна стійка"));
        row3.add(new KeyboardButton("Постер + стійка з теплою білою підсвіткою"));
        row4.add(new KeyboardButton("Постер + стійка з кольоровою підсвіткою"));
        row5.add(new KeyboardButton("Постер + стійка з кольоровою підсвіткою та блютуз колонкою"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(long chatId, String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
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
