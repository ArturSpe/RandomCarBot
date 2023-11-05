package com.mycarbot.Model;

import lombok.*;


@Builder
@Setter
@Getter
public class InlineField {
    private final String mark;
    private final String model;
    private final int year;
    private String link;

    @Override
    public String toString() {
        if (mark != null && model != null && year != 0) {
            return mark + " " + model + " " + year;
        }else {
            return mark;
        }
    }
}
