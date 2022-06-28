package com.chatbot.PosterBot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    boolean sign;
    String set;
    String size;
    boolean powerSupply;
    long chatId;
}
