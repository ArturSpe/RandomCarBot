package com.mycarbot.Factorys.Impl;

import com.mycarbot.Factorys.BeanFactory;
import com.mycarbot.Rest.HandlerJson;
import com.mycarbot.Rest.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SearcherFactory {

    private final BeanFactory beanFactory;
    @Value("${searcher}")
    private String searcher;

    public SearcherFactory (BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public Request getRequestFrom (){
        return beanFactory.getBeanByName(searcher + "Request", Request.class);
    }

    public HandlerJson getHandlerJson (){
        return beanFactory.getBeanByName(searcher + "HandlerJson", HandlerJson.class);
    }


}
