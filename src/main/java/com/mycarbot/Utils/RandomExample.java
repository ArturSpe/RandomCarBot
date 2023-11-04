package com.mycarbot.Utils;

import java.util.Collection;
import java.util.List;

public interface RandomExample {
    <T> T getRandom(Collection<T> tList);
    int getRandom(int minInt, int maxInt);
}
