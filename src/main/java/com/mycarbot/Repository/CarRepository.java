package com.mycarbot.Repository;

import com.mycarbot.Model.Cars.Mark;
import com.mycarbot.Model.Cars.Model;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarRepository{

    List<Mark> getAllMarks (Boolean popular);

}
