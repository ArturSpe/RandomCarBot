package com.mycarbot.Services;

import com.mycarbot.Model.InlineField;
import org.springframework.stereotype.Component;

@Component
public interface RandomCar {
    InlineField getRandomCar(Boolean popular);
}
