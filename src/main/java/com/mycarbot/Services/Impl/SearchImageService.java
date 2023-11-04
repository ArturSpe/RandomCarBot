package com.mycarbot.Services.Impl;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Model.Answers.Google.AnswerGoogle;
import com.mycarbot.Rest.Request;
import com.mycarbot.Factorys.Impl.SearcherFactory;
import com.mycarbot.Services.CacheImage;
import com.mycarbot.Factorys.Impl.GetterLinkFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearchImageService implements com.mycarbot.Services.SearchImageService {

    private final SearcherFactory searcherFactory;
    private final CacheImage cacheImage;

    private final GetterLinkFactory getterLinkFactory;

    @Value("${searcher}")
    private String searcherName;


    public SearchImageService(CacheImage cacheImage, SearcherFactory searcherFactory, GetterLinkFactory getterLinkFactory){
        this.searcherFactory = searcherFactory;
        this.cacheImage = cacheImage;
        this.getterLinkFactory = getterLinkFactory;
    }
    @Override
    public String getPhotoLink(String search) {
        String link = cacheImage.getLink(search);
        if (link == null){
            Request request = searcherFactory.getRequestFrom();
            Answer answer = searcherFactory.getHandlerJson().handle(request.request(search), AnswerGoogle.class);
            link = getterLinkFactory.getterLink(searcherName).getCar(answer);
        }
        return link;
    }
}
