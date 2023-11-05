package com.mycarbot.Model.Cars;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@NoArgsConstructor
public class ModelJson implements Model {
    private String id;
    private String name;
    @JsonProperty("cyrillic-name")
    private String cyrillic_name;
    @JsonProperty("year-from")
    private int yearFrom;
    @JsonProperty("year-to")
    private int yearTo;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCyrillic_name() {
        return cyrillic_name;
    }

    @Override
    public int getYearFrom() {
        return yearFrom;
    }

    @Override
    public int getYearTo() {
        return yearTo;
    }
}
