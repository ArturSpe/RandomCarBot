package com.mycarbot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycarbot.Model.Cars.MarkJson;
import com.mycarbot.Model.Cars.ModelJson;
import com.mycarbot.Model.InlineField;
import com.mycarbot.Services.RandomCar;
import com.mycarbot.Services.SearchImageService;
import com.mycarbot.Utils.Probability;
import com.mycarbot.Utils.RandomExample;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.AnswerInlineQuery;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class Start {

    private String token;

    private Environment env;
    private int cacheMin;

    private final RandomCar randomCar;

    private final boolean popular;

    public Start (Environment env1,
                  RandomCar randomCar) throws IOException {

        this.randomCar = randomCar;
        this.env = env1;
        this.token = env.getProperty("token");
        this.cacheMin = 60 * Integer.parseInt(Objects.requireNonNull(env.getProperty("cache")));
        this.popular = Boolean.parseBoolean(Objects.requireNonNull(env.getProperty("popular")));

    }
    @PostConstruct
    public void start (){


        TelegramBot telegramBot = new TelegramBot(token);
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                if (update != null) {
                    InlineQuery inlineQuery = update.inlineQuery();
                    if (inlineQuery != null) {

                        InlineField inlineField = randomCar.getRandomCar(popular);
                        InlineQueryResult<InlineQueryResultArticle> inlineQueryResult = new InlineQueryResultArticle(
                                UUID.randomUUID().toString(),
                                "Узнать Авто",
                                new InputTextMessageContent("Сегодня я на " +
                                        "["  + inlineField.toString() + "]" +
                                        "(" +
                                        inlineField.getLink() +
                                        ")"
                                ).parseMode(ParseMode.Markdown)
                        );


                        telegramBot.execute(
                                new AnswerInlineQuery(inlineQuery.id(), inlineQueryResult
                                )
                                        .cacheTime(cacheMin)
                                        .isPersonal(true));
                    }
                }
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

}
