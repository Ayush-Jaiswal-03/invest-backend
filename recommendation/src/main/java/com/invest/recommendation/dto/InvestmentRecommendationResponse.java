package com.invest.recommendation.dto;

import java.util.Map;

public class InvestmentRecommendationResponse {
    private Map<String, Integer> allocation;
    private String suggestion;
    public String riskLevel;
    public String monthlyInvestment;

    public InvestmentRecommendationResponse(Map<String, Integer> allocation, String suggestion, String riskLevel, String monthlyInvestment) {
        this.allocation = allocation;
        this.suggestion = suggestion;
        this.riskLevel = riskLevel;
        this.monthlyInvestment = monthlyInvestment;
    }

    public Map<String, Integer> getAllocation() {
        return allocation;
    }

    public void setAllocation(Map<String, Integer> allocation) {
        this.allocation = allocation;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}

