package com.mycarbot.Rest;

import com.mycarbot.Model.TypeSearher;
import com.mycarbot.Rest.Impl.GoogleRequestImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public interface Request {

    String request (String name);


}
