package com.mycarbot.Utils.Impl;

import com.mycarbot.Utils.RandomExample;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Random;

@Component
public class RandomFromCollection implements RandomExample {
    private final Random random = new Random();
    @Override
    public <T> T getRandom(Collection<T> tList) {
        return tList.stream().toList().get(random.nextInt(tList.size() - 1));
    }

    @Override
    public int getRandom(int minInt, int maxInt) {
        return random.nextInt(maxInt - minInt) + minInt;
    }
}
