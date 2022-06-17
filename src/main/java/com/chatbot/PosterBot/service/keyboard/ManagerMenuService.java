package com.chatbot.PosterBot.service.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ManagerMenuService {

    public SendMessage getManagersContactMessage(final long chatId) {
        List<InlineKeyboardButton> button = getManagerContactButton();
        final SendMessage managerContactButton = createMessageWithContact(chatId, button);

        return managerContactButton;
    }

    private List<InlineKeyboardButton> getManagerContactButton() {
        List<InlineKeyboardButton> button = new ArrayList<>();
        button.add(
                InlineKeyboardButton.builder()
                        .text("Контактні дані")
                        .url("https://t.me/music_posters_ua")
                        .build());
        return button;
    }

    private SendMessage createMessageWithContact(long chatId, List<InlineKeyboardButton> button) {
        final SendMessage message = SendMessage.builder()
                .text("Зв'язатись з менеджером")
                .parseMode("html")
                .chatId(String.valueOf(chatId))
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(Collections.singleton(button)).build())
                .build();

        return message;
    }
}
