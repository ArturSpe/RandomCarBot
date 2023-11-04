package com.mycarbot.Services;

import com.mycarbot.Rest.Request;
import org.springframework.stereotype.Component;

@Component
public interface SearchImageService {
    String getPhotoLink (String search);
}
