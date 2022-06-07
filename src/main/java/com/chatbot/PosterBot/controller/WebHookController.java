package com.chatbot.PosterBot.controller;

import com.chatbot.PosterBot.PosterBot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


@RestController
public class WebHookController {

    private final PosterBot posterBot;

    public WebHookController(PosterBot posterBot) {
        this.posterBot = posterBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
       return posterBot.onWebhookUpdateReceived(update);
    }

}
