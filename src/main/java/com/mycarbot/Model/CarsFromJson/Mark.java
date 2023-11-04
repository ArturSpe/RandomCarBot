package com.mycarbot.Model.CarsFromJson;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Mark {
    private String name;
    private boolean popular;
    private List<Model> models;
}
