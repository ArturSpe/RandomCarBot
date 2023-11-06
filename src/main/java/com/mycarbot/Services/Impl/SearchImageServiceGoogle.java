package com.mycarbot.Services.Impl;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Rest.HandlerJson;
import com.mycarbot.Rest.Request;
import com.mycarbot.Factorys.Impl.SearcherFactory;
import com.mycarbot.Services.CacheImage;
import com.mycarbot.Factorys.Impl.GetterLinkFactory;
import com.mycarbot.Services.SearchImageService;
import com.mycarbot.Utils.GetterLink;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearchImageServiceGoogle implements SearchImageService {

    private final SearcherFactory searcherFactory;
    private final CacheImage cacheImage;
    private final GetterLinkFactory getterLinkFactory;

    private final Request request;

    private final HandlerJson handlerJson;

    private final GetterLink getterLink;

    @Value("${searcher}")
    private String searcherName;


    public SearchImageServiceGoogle(CacheImage cacheImage,
                                    SearcherFactory searcherFactory,
                                    GetterLinkFactory getterLinkFactory,
                                    Request request,
                                    HandlerJson handlerJson,
                                    GetterLink getterLink){
        this.searcherFactory = searcherFactory;
        this.cacheImage = cacheImage;
        this.getterLinkFactory = getterLinkFactory;
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
            e.printStackTrace();
            link = "https://www.iphones.ru/wp-content/uploads/2015/03/foto-big.jpg";
        }

        return link;
    }
}
