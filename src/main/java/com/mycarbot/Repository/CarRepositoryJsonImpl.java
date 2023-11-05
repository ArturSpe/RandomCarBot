package com.mycarbot.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycarbot.Model.Cars.Mark;
import com.mycarbot.Model.Cars.MarkJson;
import com.mycarbot.Utils.Impl.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CarRepositoryJsonImpl implements CarRepository{

    @Value("${jsonPath}")
    private String jsonPath;
    private final JsonParser jsonParser;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CarRepositoryJsonImpl (
            JsonParser jsonParser){
        this.jsonParser = jsonParser;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<Mark> getAllMarks(Boolean popular) {
        List<Mark> markList = new ArrayList<>(Objects.requireNonNull(parseJson()));

        if (!popular) {
            return markList;
        }else {
            return markList.stream().filter(Mark::isPopular).toList();
        }
    }



    private List<MarkJson> parseJson (){
        try {
            List<MarkJson> markJsonList = jsonParser.readValue(new File(jsonPath), new TypeReference<>(){});
            return markJsonList;
        }catch (Exception e){
            return null;
        }
    }
}
