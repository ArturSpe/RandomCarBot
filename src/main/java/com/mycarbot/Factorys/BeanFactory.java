package com.mycarbot.Factorys;

import org.springframework.stereotype.Component;

@Component
public interface BeanFactory {

    <T> T getBeanByName (String nameBean, Class<T> classT);
}
