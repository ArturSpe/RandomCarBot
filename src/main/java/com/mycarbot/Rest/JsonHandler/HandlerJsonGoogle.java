package com.mycarbot.Rest.JsonHandler;

import com.mycarbot.Model.Answers.Google.AnswerGoogle;
import com.mycarbot.Model.Answers.Google.Item;
import com.mycarbot.Rest.HandlerJson;
import com.mycarbot.Utils.Impl.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("googleHandlerJson")
public class HandlerJsonGoogle implements HandlerJson<AnswerGoogle> {

    private final JsonParser jsonParser;

    @Autowired
    public HandlerJsonGoogle (JsonParser jsonParser){
        this.jsonParser = jsonParser;
    }
    @Override
    public AnswerGoogle handle(String body) {
        AnswerGoogle answer = new AnswerGoogle();
            try {
                answer = jsonParser.readValue(body, AnswerGoogle.class);
            }catch (Exception e) {
                System.out.println(e.getMessage());
                answer = new AnswerGoogle();
                List<Item> items = new ArrayList<>();
                items.add(Item.builder()
                                .link("https://avatars.mds.yandex.net/get-vertis-journal/4080458/2020-04-29-e50da248cde04f9888bc929f9e44206d.jpg_1622736281806/orig")
                        .build());
                answer.setItems(items);
            }
        return answer;
    }
}
