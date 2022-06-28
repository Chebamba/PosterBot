package com.chatbot.PosterBot.botapi.handler.fillingorder;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.botapi.InputMessageHandler;
import com.chatbot.PosterBot.cache.UserDataCache;
import com.chatbot.PosterBot.model.Order;
import com.chatbot.PosterBot.service.keyboard.PosterSetMenuService;
import com.chatbot.PosterBot.service.OrderService;
import com.chatbot.PosterBot.service.keyboard.PostersSizeMenuService;
import com.chatbot.PosterBot.service.keyboard.YesOrNoService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import com.chatbot.PosterBot.validation.PosterSetValidator;
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
    private final YesOrNoService yesOrNoService;
    private final PosterSetValidator posterSetValidator;
    private final ColorOrderScenarioHandler colorOrderScenarioHandler;
    private final PlasticOrderScenarioHandler plasticOrderScenarioHandler;
    private final WoodenOrderScenarioHandler woodenOrderScenarioHandler;

    public FillingOrderMenuHandler(OrderService orderService, ReplyMessageService messageService, UserDataCache userDataCache,
                                   PostersSizeMenuService postersSizeMenuService, PosterSetMenuService posterSetMenuService, YesOrNoService yesOrNoService, PosterSetValidator posterSetValidator, ColorOrderScenarioHandler colorOrderScenarioHandler, PlasticOrderScenarioHandler plasticOrderScenarioHandler, WoodenOrderScenarioHandler woodenOrderScenarioHandler) {
        this.orderService = orderService;
        this.messageService = messageService;
        this.userDataCache = userDataCache;
        this.postersSizeMenuService = postersSizeMenuService;
        this.posterSetMenuService = posterSetMenuService;
        this.yesOrNoService = yesOrNoService;
        this.posterSetValidator = posterSetValidator;
        this.colorOrderScenarioHandler = colorOrderScenarioHandler;
        this.plasticOrderScenarioHandler = plasticOrderScenarioHandler;
        this.woodenOrderScenarioHandler = woodenOrderScenarioHandler;
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

        SendMessage replyToUser;


            replyToUser = posterSetMenuService.getOrderMenuMessage(chatId, messageService.getReplyText("reply.askSet"));
            String posterSet = posterSetValidator.containsPosterSet(usersAnswer);
            usersOrderData.setSet(usersAnswer);
            switch(posterSet){
                case "Plastic":
                    replyToUser = plasticOrderScenarioHandler.handle(usersInput);
                    break;
                case "Wooden":
                    replyToUser = woodenOrderScenarioHandler.handle(usersInput);
                    break;
                case "Color":
                    replyToUser = colorOrderScenarioHandler.handle(usersInput);
                    break;
            }
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SIZE);

        return replyToUser;
    }
}
//
//        if(botState.equals(BotState.ASK_SIZE)){
//            replyToUser = postersSizeMenuService.getPosterSizeMenuMessage(chatId, messageService.getReplyText("reply.askSize"), usersAnswer);
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_POWER_SUPPLY);
//        }
//
//        if(botState.equals(BotState.ASK_POWER_SUPPLY)){
//            usersOrderData.setSize(usersAnswer);
//            replyToUser = yesOrNoService.getYesOrNoMenuMessage(chatId, messageService.getReplyText("reply.askPowerSupply"));
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SIGN);
//        }
//
//        if(botState.equals(BotState.ASK_SIGN)){
//            usersOrderData.setPowerSupply(usersAnswer);
//            replyToUser = yesOrNoService.getYesOrNoMenuMessage(chatId, messageService.getReplyText("reply.askSign"));
//            userDataCache.setUsersCurrentBotState(userId, BotState.ORDER_FILLED);
//        }
//
//        if(botState.equals(BotState.ORDER_FILLED)){
//            usersOrderData.setSign(usersAnswer);
//            usersOrderData.setChatId(chatId);
//
//            //законектити бд і засейвити ордер
////            orderService.saveOrder(usersOrderData);
//
//            //добавити payment після оформлення замовлення
//            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);
//
//            //можливо добавити номер оформлення
//            String orderFilledMessage = messageService.getReplyText("reply.orderFilled");
//
//            replyToUser = new SendMessage(String.valueOf(chatId), orderFilledMessage);
//        }
//
//        userDataCache.saveUserOrderData(userId, usersOrderData);