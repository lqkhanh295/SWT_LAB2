package com.yourbank.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanCalculator {

    public static double calculateLumpSumPayment(double principal, double annualInterestRate, int years, int compoundPeriodPerYear) {
        // Step 1: Validate inputs
        if (principal < 0 || annualInterestRate < 0 || years <= 0 || compoundPeriodPerYear < 1) {
            throw new IllegalArgumentException("Input values are not valid. Principal and rate must be non-negative, years must be positive, and compound periods must be at least 1.");
        }

        // Special case: if principal is 0, the result is 0
        if (principal == 0) {
            return 0.0;
        }

        // Step 2: Calculate based on the formula A = P * (1 + r/n)^(n*t)
        double r = annualInterestRate / 100.0;
        double n = compoundPeriodPerYear;
        double t = years;

        double amount = principal * Math.pow(1 + r / n, n * t);

        // Step 3: Round the result to 2 decimal places
        BigDecimal bd = new BigDecimal(Double.toString(amount));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

