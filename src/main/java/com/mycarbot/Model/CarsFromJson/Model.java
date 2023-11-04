package com.mycarbot.Model.CarsFromJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Model {
    private String id;
    private String name;
    @JsonProperty("cyrillic-name")
    private String cyrillic_name;
    @JsonProperty("year-from")
    private int yearFrom;
    @JsonProperty("year-to")
    private int yearTo;
}
