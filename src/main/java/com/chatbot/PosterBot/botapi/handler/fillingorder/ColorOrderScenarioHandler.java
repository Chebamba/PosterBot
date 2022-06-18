package com.chatbot.PosterBot.botapi.handler.fillingorder;

import com.chatbot.PosterBot.botapi.BotState;
import com.chatbot.PosterBot.cache.UserDataCache;
import com.chatbot.PosterBot.model.Order;
import com.chatbot.PosterBot.service.keyboard.PostersSizeMenuService;
import com.chatbot.PosterBot.service.keyboard.YesOrNoService;
import com.chatbot.PosterBot.service.message.ReplyMessageService;
import com.chatbot.PosterBot.validation.Validator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ColorOrderScenarioHandler{

    private final ReplyMessageService replyMessageService;
    private final PostersSizeMenuService postersSizeMenuService;
    private final YesOrNoService yesOrNoService;
    private final UserDataCache userDataCache;
    private final Validator validator;

    public ColorOrderScenarioHandler(ReplyMessageService replyMessageService, PostersSizeMenuService postersSizeMenuService, YesOrNoService yesOrNoService, UserDataCache userDataCache, Validator validator) {
        this.replyMessageService = replyMessageService;
        this.postersSizeMenuService = postersSizeMenuService;
        this.yesOrNoService = yesOrNoService;
        this.userDataCache = userDataCache;
        this.validator = validator;
    }

    public BotState getHandlerName() {
        return BotState.FILLING_COLOR_ORDER;
    }

    public SendMessage handleColorOrderScenario(Message usersInput){
        String usersAnswer = usersInput.getText();
        SendMessage replyToUser;
        long chatId = usersInput.getChatId();
        int userId = Math.toIntExact(usersInput.getFrom().getId());
        Order usersOrderData = userDataCache.getUserOrderData(userId);

        replyToUser = postersSizeMenuService.getPosterSizeMenuMessage(chatId, replyMessageService.getReplyText("reply.askSize"), usersAnswer);
        usersOrderData.setSize(usersAnswer);

        replyToUser = yesOrNoService.getYesOrNoMenuMessage(chatId, replyMessageService.getReplyText("reply.askPowerSupply"));
        usersOrderData.setPowerSupply(validator.yesOrNoValidation(usersAnswer));

        replyToUser = yesOrNoService.getYesOrNoMenuMessage(chatId, replyMessageService.getReplyText("reply.askSign"));
        usersOrderData.setSign(validator.yesOrNoValidation(usersAnswer));
        usersOrderData.setChatId(chatId);
        //Добавити ShowOrderMenuHandler (зробити його).
        userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);

        String orderFilledMessage = replyMessageService.getReplyText("reply.orderFilled");
        replyToUser = new SendMessage(String.valueOf(chatId), orderFilledMessage);
        userDataCache.saveUserOrderData(userId, usersOrderData);

        return replyToUser;
    }
}
