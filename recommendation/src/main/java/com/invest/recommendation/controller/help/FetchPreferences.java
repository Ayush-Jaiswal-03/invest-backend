package com.invest.recommendation.controller.help;

import com.invest.recommendation.dto.AnswerDetails;
import com.invest.recommendation.dto.IdPhoneNumber;
import com.invest.recommendation.dto.InvestmentRecommendationResponse;
import com.invest.recommendation.model.Answers;
import com.invest.recommendation.repository.AnswersRepository;
import com.invest.recommendation.service.CreateService;
import com.invest.recommendation.service.FetchService;
import com.invest.recommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/help")
@CrossOrigin
public class FetchPreferences {
    @Autowired
    private UserService userService;

    @Autowired
    private AnswersRepository answersRepository;

    @GetMapping("/fetch")
    public InvestmentRecommendationResponse fetchPreferences(@RequestHeader String jwt_token) {

        IdPhoneNumber userData = userService.getUserData(jwt_token);

        Optional<Answers> answersOptional = answersRepository.findById(userData.getId());

        String investmentFrequency, monthlySavings, investmentAmount, investmentDuration, preferredInvestment, investmentGoal;

        if (answersOptional.isPresent()) {
            Answers answers = answersOptional.get();

            investmentFrequency = answers.getAnswer1();
            monthlySavings = answers.getAnswer2();
            investmentAmount = answers.getAnswer3();
            investmentDuration = answers.getAnswer4();
            preferredInvestment = answers.getAnswer5();
            investmentGoal = answers.getAnswer6();
        } else {
            investmentFrequency = "";
            monthlySavings = "";
            investmentAmount = "";
            investmentDuration = "";
            preferredInvestment = "";
            investmentGoal = "";
        }

        String riskLevel = FetchService.determineRiskLevel(investmentFrequency, investmentDuration, investmentGoal, monthlySavings);

        int investAmount = FetchService.parseInvestmentAmount(investmentAmount);

        String suggestion = FetchService.getRiskAdjustmentSuggestion(riskLevel, monthlySavings, investAmount);

        Map<String, Integer> allocation = FetchService.allocateInvestments(investAmount, riskLevel, preferredInvestment);

        return new InvestmentRecommendationResponse(allocation, suggestion, investmentGoal, Integer.toString(investAmount));

    }
}