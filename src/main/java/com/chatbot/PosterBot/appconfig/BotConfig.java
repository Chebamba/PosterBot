package com.chatbot.PosterBot.appconfig;

import com.chatbot.PosterBot.PosterBot;
import com.chatbot.PosterBot.botapi.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public PosterBot MyTelegramBot(TelegramFacade telegramFacade) {
        PosterBot myTelegramBot = new PosterBot(telegramFacade);
        myTelegramBot.setBotToken(botToken);
        myTelegramBot.setBotUserName(botUserName);
        myTelegramBot.setWebHookPath(webHookPath);

        return myTelegramBot;
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;

    }

}
