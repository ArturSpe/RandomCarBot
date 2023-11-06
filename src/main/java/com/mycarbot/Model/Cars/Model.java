package com.mycarbot.Model.Cars;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface Model {

    String getId();
    String getName();
    String getCyrillic_name();
    int getYearFrom();
    int getYearTo();
}
