package com.mycarbot.Services;

import org.springframework.stereotype.Component;

@Component
public interface CacheImage {

    String getLink (String key);
    String saveLink (String key, String link);
}
