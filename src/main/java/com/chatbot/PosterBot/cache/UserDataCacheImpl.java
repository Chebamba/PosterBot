package com.chatbot.PosterBot.cache;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCacheImpl implements UserDataCache {

    private final Map<Integer, BotState> usersBotStates = new HashMap<>();
    private final Map<Integer, Order> usersOrderData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.SHOW_MAIN_MENU;
        }
        return botState;
    }

    @Override
    public void saveUserOrderData(int userId, Order order) {
        usersOrderData.put(userId, order);
    }

    @Override
    public Order getUserOrderData(int userId) {
        Order usersOrder = usersOrderData.get(userId);
        if (usersOrder == null) {
            usersOrder = new Order();
        }

        return usersOrder;
    }
}
