package com.mycarbot;

import com.mycarbot.Rest.HandlerJson;
import com.mycarbot.Rest.Impl.GoogleRequestImpl;
import com.mycarbot.Rest.JsonHandler.HandlerJsonGoogle;
import com.mycarbot.Rest.Request;
import com.mycarbot.Utils.GetterLink;
import com.mycarbot.Utils.Impl.GoogleGetterLink;
import com.mycarbot.Utils.Impl.JsonParser;
import com.mycarbot.Utils.Impl.RandomFromCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ConfigSearcher {

    @Value("${searcher}")
    private String typeSearcher;

    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private RandomFromCollection random;

    public Request request (){
        if (typeSearcher.equals("google")){
            return new GoogleRequestImpl();
        }else {
            return null;
        }
    }

    public HandlerJson handlerJson () {
        if (typeSearcher.equals("google")){
            return new HandlerJsonGoogle(jsonParser);
        }else {
            return null;
        }
    }

    public GetterLink getterLink (){
        if (typeSearcher.equals("google")){
            return new GoogleGetterLink(random);
        }else {
            return null;
        }
    }

}
