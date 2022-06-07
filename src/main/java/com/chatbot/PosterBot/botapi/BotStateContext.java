package com.chatbot.PosterBot.botapi;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

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
            case ASK_WIDTH:
            case ASK_HEIGHT:
            case ASK_PHOTO:
            case ASK_BACKGROUND:
            case ASK_SONG:
            case FILLING_ORDER:
            case ORDER_FILLED:
                return true;
            default:
                return false;
        }
    }
}
