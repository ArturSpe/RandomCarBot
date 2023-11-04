package com.mycarbot.Model.Answers.Google;

import com.mycarbot.Model.Answers.Answer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Data
@Setter
public class AnswerGoogle implements Answer {
    private List<Item> items;
}
