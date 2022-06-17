package com.chatbot.PosterBot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    String sign;
    String set;
    String size;
    String powerSupply;
    long chatId;
}
