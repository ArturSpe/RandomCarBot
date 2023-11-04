package com.mycarbot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycarbot.Model.CarsFromJson.Mark;
import com.mycarbot.Model.CarsFromJson.Model;
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

    List<Mark> markList;
    Random random = new Random();
    boolean popular;
    private String jsonPath;
    private String token;

    private Environment env;
    private int cacheMin;

    private SearchImageService searchImageService;

    private final Probability probability;

    private final RandomExample randomExample;

    public Start (Environment env1,
                  SearchImageService searchImageService,
                  Probability probability,
                  RandomExample randomExample) throws IOException {

        this.randomExample = randomExample;
        this.probability = probability;
        this.searchImageService = searchImageService;
        this.env = env1;
        this.token = env.getProperty("token");
        this.popular = Boolean.parseBoolean(env.getProperty("popular"));
        this.cacheMin = 60 * Integer.parseInt(Objects.requireNonNull(env.getProperty("cache")));

        jsonPath = env.getProperty("jsonPath");
        System.out.println(jsonPath);

        if (popular){
            markList = getMarkList().stream().filter(Mark::isPopular).toList();
        }else {
            markList = getMarkList();
        }

    }
    @PostConstruct
    public void start (){


        TelegramBot telegramBot = new TelegramBot(token);
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                if (update != null) {
                    InlineQuery inlineQuery = update.inlineQuery();
                    if (inlineQuery != null) {

                        String myCar;
                        if (!probability.getProbability()) {
                            Mark mark = getRandomMark(markList);
                            Model model = getRandomModel(mark.getModels());
                            int year = getRandomYear(model.getYearFrom(), model.getYearTo());
                            myCar = getCarString(mark, model, year);
                        }else {
                            myCar = "Бутылке";
                        }
                        InlineQueryResult<InlineQueryResultArticle> inlineQueryResult = new InlineQueryResultArticle(
                                UUID.randomUUID().toString(),
                                "Узнать Авто",
                                new InputTextMessageContent("Сегодня я на " +
                                        "["  + myCar + "]" +
                                        "(" +
                                        searchImageService.getPhotoLink(myCar) +
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

    private String getCarString (Mark mark, Model model, int year){
        return mark.getName() + " " + model.getName() + " " + year;
    }

    private int getRandomYear (int start, int finish){
        if (finish == 0){
            finish = Calendar.getInstance().get(Calendar.YEAR);
        }
        return randomExample.getRandom(start, finish);
    }
    private Mark getRandomMark (List<Mark> markList){

        return randomExample.getRandom(markList);
    }

    private Model getRandomModel (List<Model> modelList){
        return randomExample.getRandom(modelList);
    }

    private List<Mark> getMarkList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Mark> markList = objectMapper.readValue(new File(jsonPath), new TypeReference<>(){});
        return markList;
    }

}
