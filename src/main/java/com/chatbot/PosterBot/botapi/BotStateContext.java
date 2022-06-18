package com.chatbot.PosterBot.botapi;

import com.chatbot.PosterBot.validation.PosterSetValidator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private final Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message){
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler (BotState currentState){
        if(isFillingOrderState(currentState)){
            return messageHandlers.get(BotState.FILLING_ORDER);
        }

        return messageHandlers.get(currentState);
    }

    private boolean isFillingOrderState(BotState currentState){
        switch (currentState){
            case ASK_SET:
            case ASK_SIZE:
            case ASK_POWER_SUPPLY:
            case ASK_SIGN:
            case ADD_SIGN:
            case FILLING_ORDER:
            case ORDER_FILLED:
                return true;
            default:
                return false;
        }
    }
}
