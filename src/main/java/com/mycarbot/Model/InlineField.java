package com.mycarbot.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class InlineField {
    private final String name;
    private final String link;

}
