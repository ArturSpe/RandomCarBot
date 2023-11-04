package com.mycarbot.Utils.Impl;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Model.Answers.Google.AnswerGoogle;
import com.mycarbot.Utils.GetterLink;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("googleGetterLink")
public class GoogleGetterLink implements GetterLink {

    private Random random = new Random();
    @Override
    public String getCar(Answer answer) {
        AnswerGoogle answerGoogle = (AnswerGoogle) answer;
        return answerGoogle.getItems().get(random.nextInt(9)).getLink();
    }
}
