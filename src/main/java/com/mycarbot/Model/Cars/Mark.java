package com.mycarbot.Model.Cars;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Mark <M extends Model> {
    String getName();
    boolean isPopular();
    List<M> getModels();
}
