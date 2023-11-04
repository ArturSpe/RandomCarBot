package com.mycarbot.Utils.Impl;

import com.mycarbot.Utils.Probability;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class ProbabilityImpl implements Probability {
    private final int percent = 10;
    private final Random random = new Random();
    @Override
    public boolean getProbability() {
        int trigger = random.nextInt(99) + 1;
        return trigger < percent;
    }
}
