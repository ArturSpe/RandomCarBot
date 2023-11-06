package com.mycarbot.Factorys.Impl;

import com.mycarbot.Factorys.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryImpl implements BeanFactory {

    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Override
    public <T> T getBeanByName(String nameBean, Class<T> classT) {
        return applicationContext.getBean(nameBean, classT);
    }
    @Override
    public <T> T getBeanByClass(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }

}
