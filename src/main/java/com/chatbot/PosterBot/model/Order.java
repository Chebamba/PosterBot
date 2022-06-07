package com.chatbot.PosterBot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    String song;
    String background;
    String photo;
    int height;
    int width;
    double price;
}
