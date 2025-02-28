package com.invest.recommendation.service;

import com.invest.recommendation.dto.IdPhoneNumber;
import com.invest.recommendation.repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AnswersRepository answersRepository;

    public IdPhoneNumber getUserData(String jwt_token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("jwt_token", jwt_token);
        HttpEntity entity = new HttpEntity(headers);
        String url = "https://investpe-user-service-dev-production.up.railway.app";
        ResponseEntity<IdPhoneNumber> t = restTemplate.exchange(url + "/recommendation/user", HttpMethod.GET , entity, IdPhoneNumber.class);
        return t.getBody();
    }
}
