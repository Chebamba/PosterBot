package com.chatbot.PosterBot.validation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PosterSetValidator {

    private static Map<String, String> sd = new HashMap<>();

    public String containsPosterSet(String textMessage){
        sd.put("Постер + пластикова стійка", "Plastic");
        sd.put("Постер + дерев'яна стійка", "Wooden");
        sd.put("Постер + Постер + стійка з теплою білою підсвіткою", "Color");
        sd.put("Постер + стійка з кольоровою підсвіткою", "Color");
        sd.put("Постер + стійка з кольоровою підсвіткою та блютуз колонкою", "Color");
        return sd.getOrDefault(textMessage, "posterSet not found");
    }
}
