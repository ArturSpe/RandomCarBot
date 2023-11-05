package com.mycarbot.Model.Cars;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Component
public class MarkJson implements Mark<ModelJson> {

    private String name;
    private boolean popular;
    private List<ModelJson> models;


}
