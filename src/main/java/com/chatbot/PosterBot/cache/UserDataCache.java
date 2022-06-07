package com.chatbot.PosterBot.cache;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.model.Order;
import org.springframework.stereotype.Component;

@Component
public interface UserDataCache {

    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    Order getUserOrderData(int userId);

    void saveUserOrderData(int userId, Order order);

}
