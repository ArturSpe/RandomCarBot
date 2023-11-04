package com.mycarbot.Factorys.Impl;

import com.mycarbot.Factorys.BeanFactory;
import com.mycarbot.Utils.GetterLink;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetterLinkFactory {

    private final BeanFactory beanFactory;
    @Value("${searcher}")
    private String searcher;
    public GetterLinkFactory(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public GetterLink getterLink (String nameSearcher){
        return beanFactory.getBeanByName(searcher + "GetterLink", GetterLink.class);
    }

}
