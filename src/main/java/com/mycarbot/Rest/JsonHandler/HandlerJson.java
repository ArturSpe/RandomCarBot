package com.mycarbot.Rest.JsonHandler;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Factorys.BeanFactory;
import com.mycarbot.Factorys.Impl.BeanFactoryImpl;
import org.springframework.stereotype.Component;

@Component
public interface HandlerJson {

     BeanFactory b = new BeanFactoryImpl();

     <T extends Answer> T handle (String body, Class<T> tClass);

     static HandlerJson getInstance(String name){
          return b.getBeanByName(name, HandlerJson.class);
     }
}
