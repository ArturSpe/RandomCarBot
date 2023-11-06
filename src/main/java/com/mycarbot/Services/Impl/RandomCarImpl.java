package com.mycarbot.Services.Impl;

import com.mycarbot.Model.Cars.Mark;
import com.mycarbot.Model.Cars.Model;
import com.mycarbot.Model.InlineField;
import com.mycarbot.Repository.CarRepository;
import com.mycarbot.Services.RandomCar;
import com.mycarbot.Services.SearchImageService;
import com.mycarbot.Utils.Probability;
import com.mycarbot.Utils.RandomExample;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class RandomCarImpl implements RandomCar {

    private final CarRepository carRepository;

    private final SearchImageService searchImageService;

    private final RandomExample randomExample;

    private final Probability probability;

    public RandomCarImpl(
            CarRepository carRepository,
            SearchImageService searchImageService,
            RandomExample randomExample,
            Probability probability
    ){
        this.carRepository = carRepository;
        this.searchImageService = searchImageService;
        this.randomExample = randomExample;
        this.probability = probability;
    }
    @Override
    public InlineField getRandomCar(Boolean popular) {

        InlineField inlineField;

        if (!probability.getProbability()) {
            Mark randomMark = randomExample.getRandom(carRepository.getAllMarks(popular));
            Model randomModel = (Model) randomExample.getRandom(randomMark.getModels());

            int yearModelTo = randomModel.getYearTo();
            int yearModelFrom = randomModel.getYearFrom();

            if (yearModelTo == 0) {
                yearModelTo = Calendar.getInstance().get(Calendar.YEAR);
            }

            String nameMark = randomMark.getName();
            String nameModel = randomModel.getName();
            int yearModel = randomExample.getRandom(yearModelFrom, yearModelTo);

            inlineField = InlineField.builder()
                    .mark(nameMark)
                    .model(nameModel)
                    .year(yearModel)
                    .link(searchImageService.getPhotoLink(nameMark + " " + nameModel + " " + yearModel))
                    .build();
            System.out.println(inlineField);

        }else {
            inlineField = InlineField.builder()
                    .mark("бутылке")
                    .link("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Bouteille.jpg/800px-Bouteille.jpg")
                    .build();
        }
        return inlineField;
    }
}
