package com.mycarbot.Rest;

import com.mycarbot.Model.Answers.Answer;
import com.mycarbot.Factorys.BeanFactory;
import com.mycarbot.Factorys.Impl.BeanFactoryImpl;
import org.springframework.stereotype.Component;

@Component
public interface HandlerJson <A extends Answer> {

     BeanFactory b = new BeanFactoryImpl();

      A handle (String body);

     static HandlerJson getInstance(String name){
         return b.getBeanByName(name, HandlerJson.class);
     }


}
