package com.chatbot.PosterBot.validation;

import org.springframework.stereotype.Component;

@Component
public class Validator {

    public boolean yesOrNoValidation(String usersAnswer) {
        return usersAnswer.equals("Так");
    }

}
