package com.mycarbot.Utils.Impl;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Model.Answers.Google.AnswerGoogle;
import com.mycarbot.Model.Answers.Google.Item;
import com.mycarbot.Utils.GetterLink;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("googleGetterLink")
public class GoogleGetterLink implements GetterLink {

    private final RandomFromCollection random;

    public GoogleGetterLink (RandomFromCollection random){
        this.random = random;
    }
    @Override
    public String getCar(Answer answer) {
        AnswerGoogle answerGoogle = (AnswerGoogle) answer;
        Item item = random.getRandom(answerGoogle.getItems());
        return item.getLink();
    }
}
