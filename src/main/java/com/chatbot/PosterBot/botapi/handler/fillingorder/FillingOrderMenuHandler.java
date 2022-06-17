package com.chatbot.PosterBot.botapi.handler.fillingorder;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.cache.UserDataCache;
import com.chatbot.PosterBot.model.Order;
import com.chatbot.PosterBot.service.keyboard.PosterSetMenuService;
import com.chatbot.PosterBot.service.OrderService;
import com.chatbot.PosterBot.service.keyboard.PostersSizeMenuService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class FillingOrderMenuHandler implements InputMessageHandler {

    private final OrderService orderService;
    private final ReplyMessageService messageService;
    private final UserDataCache userDataCache;
    private final PostersSizeMenuService postersSizeMenuService;
    private final PosterSetMenuService posterSetMenuService;

    public FillingOrderMenuHandler(OrderService orderService, ReplyMessageService messageService, UserDataCache userDataCache,
                                   PostersSizeMenuService postersSizeMenuService, PosterSetMenuService posterSetMenuService) {
        this.orderService = orderService;
        this.messageService = messageService;
        this.userDataCache = userDataCache;
        this.postersSizeMenuService = postersSizeMenuService;
        this.posterSetMenuService = posterSetMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        if(userDataCache.getUsersCurrentBotState(Math.toIntExact(message.getFrom().getId())).equals(BotState.FILLING_ORDER)){
            userDataCache.setUsersCurrentBotState(Math.toIntExact(message.getFrom().getId()), BotState.ASK_SET);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_ORDER;
    }

    private SendMessage processUsersInput(Message usersInput) {
        String usersAnswer = usersInput.getText();
        int userId = Math.toIntExact(usersInput.getFrom().getId());
        long chatId = usersInput.getChatId();

        Order usersOrderData = userDataCache.getUserOrderData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if(botState.equals(BotState.ASK_SET)){
            replyToUser = posterSetMenuService.getOrderMenuMessage(chatId, messageService.getReplyText("reply.askSet"));
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SIZE);
        }

        if(botState.equals(BotState.ASK_SIZE)){
            usersOrderData.setSet(usersAnswer);
            replyToUser = postersSizeMenuService.getPosterSizeMenuMessage(chatId, messageService.getReplyText("reply.askSize"), usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_POWER_SUPPLY);
        }

        if(botState.equals(BotState.ASK_POWER_SUPPLY)){
            usersOrderData.setSize(usersAnswer);
            replyToUser = messageService.getReplyMessage(chatId, "reply.askPowerSupply");

            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SIGN);
        }

        if(botState.equals(BotState.ASK_SIGN)){
            usersOrderData.setPowerSupply(usersAnswer);
            replyToUser = messageService.getReplyMessage(chatId, "reply.askSign");
            userDataCache.setUsersCurrentBotState(userId, BotState.ORDER_FILLED);
        }

        if(botState.equals(BotState.ORDER_FILLED)){
            usersOrderData.setSign(usersAnswer);
            usersOrderData.setChatId(chatId);

            //законектити бд і засейвити ордер
//            orderService.saveOrder(usersOrderData);

            //добавити payment після оформлення замовлення
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);

            //можливо добавити номер оформлення
            String profileFilledMessage = messageService.getReplyText("reply.profileFilled");

            replyToUser = new SendMessage(String.valueOf(chatId), profileFilledMessage);
        }

        userDataCache.saveUserOrderData(userId, usersOrderData);

        return replyToUser;
    }
}
