package com.chatbot.PosterBot;

import com.chatbot.PosterBot.botapi.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

@Setter
@Getter
public class PosterBot extends TelegramWebhookBot {

    private final TelegramFacade telegramFacade;

    public PosterBot(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }

    private String botToken;

    private String botUserName;

    private String webHookPath;

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        final BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);

        return replyMessageToUser;
    }

    @SneakyThrows
    public void sendPhoto(long chatId, String imagePath){
        File image = ResourceUtils.getFile("classpath:" + imagePath);
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(new InputFile(image));
            sendPhoto.setChatId(String.valueOf(chatId));
            execute(sendPhoto);

        }
    }