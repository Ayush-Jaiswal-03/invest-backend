package com.invest.recommendation.service;

import com.invest.recommendation.dto.IdPhoneNumber;
import com.invest.recommendation.repository.AnswersRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CreateService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AnswersRepository answersRepository;

    @Autowired
    UserService userService;

    private Long id;

    public void createPreferences(String jwt_token, ArrayList<String> answers){
        IdPhoneNumber dataFromUserService = userService.getUserData(jwt_token);
        Long id = dataFromUserService.getId();
        String phoneNumber = dataFromUserService.phone_number;
        if(answersRepository.checkExistInDb(id) == 1){
            answersRepository.updateAnswersIntoDb(id, answers.get(0),answers.get(1),answers.get(2),answers.get(3),answers.get(4),answers.get(5));
        }
        else{
            answersRepository.insertAnswersIntoDb(id, phoneNumber, answers.get(0),answers.get(1),answers.get(2),answers.get(3),answers.get(4),answers.get(5));
        }
    }
}
