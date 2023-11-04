package com.mycarbot.Rest.Impl;

import com.mycarbot.Rest.Request;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("googleRequest")
public class GoogleRequestImpl implements Request {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Override
    public String request(String name) {
        String url =
                "https://www.googleapis.com/customsearch/v1?" +
                        "key=***" +
                        "cx=***" +
                        "q="+name+
                        "&num=10" +
                        "&searchType=image";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return response.getBody();
    }
}
