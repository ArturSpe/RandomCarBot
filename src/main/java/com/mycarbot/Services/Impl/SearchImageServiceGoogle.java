package com.mycarbot.Services.Impl;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Rest.Request;
import com.mycarbot.Factorys.Impl.SearcherFactory;
import com.mycarbot.Services.CacheImage;
import com.mycarbot.Factorys.Impl.GetterLinkFactory;
import com.mycarbot.Services.SearchImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearchImageServiceGoogle implements SearchImageService {

    private final SearcherFactory searcherFactory;
    private final CacheImage cacheImage;

    private final GetterLinkFactory getterLinkFactory;

    @Value("${searcher}")
    private String searcherName;


    public SearchImageServiceGoogle(CacheImage cacheImage, SearcherFactory searcherFactory, GetterLinkFactory getterLinkFactory){
        this.searcherFactory = searcherFactory;
        this.cacheImage = cacheImage;
        this.getterLinkFactory = getterLinkFactory;
    }
    @Override
    public String getPhotoLink(String search) {
        String link = cacheImage.getLink(search);
        try {
            if (link == null){
                Request request = searcherFactory.getRequestFrom();
                Answer answer = searcherFactory.getHandlerJson().handle(request.request(search));
                link = getterLinkFactory.getterLink(searcherName).getCar(answer);
                cacheImage.saveLink(search, link);
            }
        }catch (Exception e){
            link = "https://www.iphones.ru/wp-content/uploads/2015/03/foto-big.jpg";
        }

        return link;
    }
}
