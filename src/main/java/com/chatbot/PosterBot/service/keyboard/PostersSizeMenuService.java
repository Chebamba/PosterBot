package com.chatbot.PosterBot.service.keyboard;

import com.chatbot.PosterBot.service.message.ReplyMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class PostersSizeMenuService {

    private final Map<String, ReplyKeyboardMarkup> postersSizeMap = new HashMap<>();
    private final ReplyMessageService replyMessageService;

    public PostersSizeMenuService(ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }

    public SendMessage getPosterSizeMenuMessage(final long chatId, final String textMessage, final String usersAnswer) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getPostersSizeKeyboard(usersAnswer);
        final SendMessage posterSizeMenuMessage = createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
        return posterSizeMenuMessage;
    }

    private ReplyKeyboardMarkup getPostersSizeKeyboard(String usersAnswer) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = postersSizeKeyboardBuilder(usersAnswer);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        return replyKeyboardMarkup;
    }

    private ReplyKeyboardMarkup postersSizeKeyboardBuilder(String usersAnswer) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        KeyboardRow fourthRow = new KeyboardRow();
        switch (usersAnswer) {
            case "Постер + пластикова стійка": {
                firstRow.add(new KeyboardButton("A6 (148x105мм) 299грн"));
                secondRow.add(new KeyboardButton("A5 (210x148мм) 349грн"));
                thirdRow.add(new KeyboardButton("A4 (297x210мм) 449грн"));
                fourthRow.add(new KeyboardButton("A3 (420x497мм) 499грн"));
                break;
            }
            case "Постер + дерев'яна стійка": {
                firstRow.add(new KeyboardButton("A6 (148x105мм) 359грн"));
                secondRow.add(new KeyboardButton("A5 (210x148мм) 419грн"));
                thirdRow.add(new KeyboardButton("A4 (297x210мм) 549грн"));
                fourthRow.add(new KeyboardButton("A3 (420x497мм) 599грн"));
                break;
            }
            case "Постер + стійка з теплою білою підсвіткою": {
                firstRow.add(new KeyboardButton("A5 (210x148мм) 599грн"));
                secondRow.add(new KeyboardButton("A4 (297x210мм) 799грн"));
                thirdRow.add(new KeyboardButton("A3 (420x497мм) 849грн"));
                break;
            }
            case "Постер + стійка з кольоровою підсвіткою": {
                firstRow.add(new KeyboardButton("A5 (210x148мм) 649грн"));
                secondRow.add(new KeyboardButton("A4 (297x210мм) 849грн"));
                thirdRow.add(new KeyboardButton("A3 (420x497мм) 899грн"));
                break;
            }
            case "Постер + стійка з кольоровою підсвіткою та блютуз колонкою": {
                firstRow.add(new KeyboardButton("A5 (210x148мм) 699грн"));
                secondRow.add(new KeyboardButton("A4 (297x210мм) 899грн"));
                thirdRow.add(new KeyboardButton("A3 (420x497мм) 999грн"));
                break;
            }
        }

        List<KeyboardRow> keyboardRow = new ArrayList<>(Arrays.asList(firstRow, secondRow, thirdRow, fourthRow));
        replyKeyboardMarkup.setKeyboard(keyboardRow);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(final long chatId, String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setText(textMessage);
        sendMessage.setChatId(String.valueOf(chatId));
        if (replyKeyboardMarkup != null){
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}
