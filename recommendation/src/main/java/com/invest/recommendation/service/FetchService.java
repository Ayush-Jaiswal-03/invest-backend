package com.invest.recommendation.service;

import java.util.HashMap;
import java.util.Map;

public class FetchService {
    public static String determineRiskLevel(String investmentFrequency, String investmentDuration, String investmentGoal, String monthlySavings) {
        int riskScore = 0;

        // Investment goal impact on risk
        if (investmentGoal.equalsIgnoreCase("High")) {
            riskScore += 3;  // High risk
        } else if (investmentGoal.equalsIgnoreCase("Medium")) {
            riskScore += 2;  // Medium risk
        } else {
            riskScore += 1;  // Low risk
        }

        // Investment duration impact on risk
        if (investmentDuration.equalsIgnoreCase("More than 10 Year")) {
            riskScore += 3;  // High risk
        } else if (investmentDuration.equalsIgnoreCase("10 Year")) {
            riskScore += 2;  // Medium risk
        } else if (investmentDuration.equalsIgnoreCase("5 Year")) {
            riskScore += 1;  // Medium risk
        } else {
            riskScore += 0;  // Low risk
        }

        // Investment frequency impact on risk
        if (investmentFrequency.equalsIgnoreCase("Daily") || investmentFrequency.equalsIgnoreCase("Weekly")) {
            riskScore += 3;  // High risk
        } else if (investmentFrequency.equalsIgnoreCase("Monthly")) {
            riskScore += 2;  // Medium risk
        } else {
            riskScore += 1;  // Low risk
        }

        // Monthly savings impact on risk
        if (monthlySavings.equalsIgnoreCase("above 50,000 Rs")) {
            riskScore += 3;  // High risk tolerance
        } else if (monthlySavings.equalsIgnoreCase("20,000-50,000 Rs")) {
            riskScore += 2;  // Medium risk
        } else {
            riskScore += 1;  // Low risk
        }

        // Determine final risk category
        if (riskScore >= 8) {
            return "High Risk";
        } else if (riskScore >= 5) {
            return "Medium Risk";
        } else {
            return "Low Risk";
        }
    }

    public static int parseInvestmentAmount(String investmentAmount) {
        return switch (investmentAmount) {
            case "5,000-10,000 Rs" -> 10000;
            case "10,000-20,000 Rs" -> 20000;
            case "20,000-50,000 Rs" -> 50000;
            case "above 50,000 Rs" -> 75000;
            default -> 10000;
        };
    }

    public static int parseSavingsAmount(String monthlySavings) {
        return switch (monthlySavings) {
            case "less than 50,000 Rs" -> 35000;
            case "50,000-1,00,000 Rs" -> 75000;
            case "1,00,000-2,00,000 Rs" -> 150000;
            case "above 2,00,000" -> 200000;
            default -> 10000;
        };
    }

    public static Map<String, Integer> allocateInvestments(int totalInvestment, String riskLevel, String preferredInvestment) {
        Map<String, Integer> allocation = new HashMap<>();
        double fdPercentage, goldPercentage, mfPercentage;

        if (riskLevel.equals("Low Risk")) {
            fdPercentage = 0.70; // 70% in FD
            goldPercentage = 0.20; // 20% in Gold
            mfPercentage = 0.10; // 10% in MF
        } else if (riskLevel.equals("Medium Risk")) {
            fdPercentage = 0.40; // 40% in FD
            goldPercentage = 0.30; // 30% in Gold
            mfPercentage = 0.30; // 30% in MF
        } else { // High Risk
            fdPercentage = 0.10; // 10% in FD
            goldPercentage = 0.20; // 20% in Gold
            mfPercentage = 0.70; // 70% in MF
        }

        // Adjust allocations based on user's preferred investment choice
        if (preferredInvestment.equalsIgnoreCase("Fixed-Deposit")) {
            fdPercentage += 0.10;
            mfPercentage -= 0.05;
            goldPercentage -= 0.05;
        } else if (preferredInvestment.equalsIgnoreCase("Gold")) {
            goldPercentage += 0.10;
            fdPercentage -= 0.05;
            mfPercentage -= 0.05;
        } else if (preferredInvestment.equalsIgnoreCase("Mutual Funds")) {
            mfPercentage += 0.10;
            fdPercentage -= 0.05;
            goldPercentage -= 0.05;
        }

        // Allocate based on adjusted percentages
        allocation.put("Fixed Deposit", (int) (fdPercentage*100));
        allocation.put("Gold", (int) (goldPercentage*100));
        allocation.put("Mutual Funds", (int) (100 * mfPercentage));

        return allocation;
    }

    public static String getRiskAdjustmentSuggestion(String riskLevel, String monthlySavings, int investmentAmount) {
        int savingsAmount = parseSavingsAmount(monthlySavings);

        // If user is taking too much risk compared to savings
        if (riskLevel.equals("High Risk") && investmentAmount > (savingsAmount * 0.7)) {
            return "You are investing a large portion of your savings into high-risk assets. Consider shifting some funds to Fixed Deposits or Gold for better stability.";
        }

        // If user is taking too little risk despite having high savings
        if (riskLevel.equals("Low Risk") && savingsAmount > 50000 && investmentAmount < (savingsAmount * 0.3)) {
            return "You have a high monthly savings but are investing conservatively. You can consider taking more risk by investing in Mutual Funds for better growth.";
        }

        // If user can increase risk slightly based on savings
        if (riskLevel.equals("Medium Risk") && savingsAmount > 20000 && investmentAmount < (savingsAmount * 0.5)) {
            return "You have sufficient savings and moderate investment. If comfortable, consider increasing your investment in Mutual Funds to boost returns.";
        }

        return "Your risk level and investment amount are well balanced.";
    }

}

