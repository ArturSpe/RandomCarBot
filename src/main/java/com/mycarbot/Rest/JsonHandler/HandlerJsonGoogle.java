package com.mycarbot.Rest.JsonHandler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Model.Answers.Google.AnswerGoogle;
import com.mycarbot.Model.Answers.Google.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("googleHandlerJson")
public class HandlerJsonGoogle implements HandlerJson {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public HandlerJsonGoogle (){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    @Override
    public <T extends Answer> T handle(String body, Class<T> tClass) {
        AnswerGoogle answer = new AnswerGoogle();
            try {
                answer = objectMapper.readValue(body, AnswerGoogle.class);
//                int i = 1/0;
            }catch (Exception e) {
                System.out.println(e.getMessage());
                answer = new AnswerGoogle();
                List<Item> items = new ArrayList<>();
                items.add(Item.builder()
                                .link("https://avatars.mds.yandex.net/get-vertis-journal/4080458/2020-04-29-e50da248cde04f9888bc929f9e44206d.jpg_1622736281806/orig")
                        .build());
                answer.setItems(items);
            }
        return (T) answer;
    }
}
