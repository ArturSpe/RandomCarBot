package com.mycarbot.Services.Impl;

import com.mycarbot.Services.CacheImage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheImagesHashMap implements CacheImage {
    private final Map <String, String> carLinkMap = new HashMap<>();

    @Override
    public String getLink(String key) {
        return carLinkMap.get(key);
    }

    @Override
    public String saveLink(String key, String link) {
        return carLinkMap.put(key, link);
    }
}
