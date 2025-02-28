package com.invest.recommendation.controller.help;

import com.invest.recommendation.dto.AnswerDetails;
import com.invest.recommendation.service.CreateService;
import com.invest.recommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/help")
@CrossOrigin
public class CreatePreferences {
    @Autowired
    CreateService createService;

    @PostMapping("/create")
    public void createPreferences(@RequestHeader String jwt_token, @RequestBody AnswerDetails answers) {
        createService.createPreferences(jwt_token, answers.answers);

    }
}