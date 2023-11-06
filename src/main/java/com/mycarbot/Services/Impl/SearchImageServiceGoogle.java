package com.mycarbot.Services.Impl;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Rest.HandlerJson;
import com.mycarbot.Rest.Request;
import com.mycarbot.Services.CacheImage;
import com.mycarbot.Services.SearchImageService;
import com.mycarbot.Utils.GetterLink;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearchImageServiceGoogle implements SearchImageService {


    private final CacheImage cacheImage;

    private final Request request;

    private final HandlerJson handlerJson;

    private final GetterLink getterLink;

    public SearchImageServiceGoogle(CacheImage cacheImage,
                                    Request request,
                                    HandlerJson handlerJson,
                                    GetterLink getterLink){
        this.cacheImage = cacheImage;
        this.request = request;
        this.handlerJson = handlerJson;
        this.getterLink = getterLink;
    }
    @Override
    public String getPhotoLink(String search) {
        String link = cacheImage.getLink(search);
        try {
            if (link == null){
                Answer answer = handlerJson.handle(request.request(search));
                link = getterLink.getCar(answer);
                cacheImage.saveLink(search, link);
            }
        }catch (Exception e){
            link = "https://www.iphones.ru/wp-content/uploads/2015/03/foto-big.jpg";
        }

        return link;
    }
}
