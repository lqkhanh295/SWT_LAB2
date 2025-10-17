package com.yourbank.loan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class LoanCalculatorTest_Qodo {

    // Test for invalid inputs - negative principal
    @Test
    @DisplayName("Should throw IllegalArgumentException when principal is negative")
    public void testCalculateLumpSumPayment_NegativePrincipal() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(-1000.0, 5.0, 10, 12);
        });
    }

    // Test for invalid inputs - negative interest rate
    @Test
    @DisplayName("Should throw IllegalArgumentException when interest rate is negative")
    public void testCalculateLumpSumPayment_NegativeInterestRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(1000.0, -5.0, 10, 12);
        });
    }

    // Test for invalid inputs - zero years
    @Test
    @DisplayName("Should throw IllegalArgumentException when years is zero")
    public void testCalculateLumpSumPayment_ZeroYears() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, 0, 12);
        });
    }

    // Test for invalid inputs - negative years
    @Test
    @DisplayName("Should throw IllegalArgumentException when years is negative")
    public void testCalculateLumpSumPayment_NegativeYears() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, -5, 12);
        });
    }

    // Test for invalid inputs - zero compound periods
    @Test
    @DisplayName("Should throw IllegalArgumentException when compound periods is zero")
    public void testCalculateLumpSumPayment_ZeroCompoundPeriods() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, 10, 0);
        });
    }

    // Test for invalid inputs - negative compound periods
    @Test
    @DisplayName("Should throw IllegalArgumentException when compound periods is negative")
    public void testCalculateLumpSumPayment_NegativeCompoundPeriods() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, 10, -1);
        });
    }

    // Test for zero principal - special case
    @Test
    @DisplayName("Should return 0.0 when principal is zero")
    public void testCalculateLumpSumPayment_ZeroPrincipal() {
        double result = LoanCalculator.calculateLumpSumPayment(0.0, 5.0, 10, 12);
        assertEquals(0.0, result, 0.001);
    }

    // Test for zero interest rate - boundary value
    @Test
    @DisplayName("Should handle zero interest rate correctly")
    public void testCalculateLumpSumPayment_ZeroInterestRate() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 0.0, 10, 12);
        assertEquals(1000.0, result, 0.001);
    }

    // Test for minimum valid compound periods - boundary value
    @Test
    @DisplayName("Should handle minimum compound periods (1) correctly")
    public void testCalculateLumpSumPayment_MinimumCompoundPeriods() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, 1, 1);
        assertEquals(1050.0, result, 0.001);
    }

    // Test for minimum valid years - boundary value
    @Test
    @DisplayName("Should handle minimum years (1) correctly")
    public void testCalculateLumpSumPayment_MinimumYears() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, 1, 12);
        assertEquals(1051.16, result, 0.01);
    }

    // Test for typical valid inputs - equivalence partition: normal values
    @Test
    @DisplayName("Should calculate correctly for typical valid inputs")
    public void testCalculateLumpSumPayment_TypicalValues() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 5.0, 10, 12);
        assertEquals(1643.62, result, 0.01);
    }

    // Test for high principal - equivalence partition: large values
    @Test
    @DisplayName("Should handle large principal amounts correctly")
    public void testCalculateLumpSumPayment_LargePrincipal() {
        double result = LoanCalculator.calculateLumpSumPayment(100000.0, 3.5, 5, 4);
        assertEquals(118769.47, result, 0.01);
    }

    // Test for high interest rate - equivalence partition: large values
    @Test
    @DisplayName("Should handle high interest rates correctly")
    public void testCalculateLumpSumPayment_HighInterestRate() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 15.0, 5, 12);
        assertEquals(2107.18, result, 0.01);
    }

    // Test for many years - equivalence partition: large values
    @Test
    @DisplayName("Should handle long time periods correctly")
    public void testCalculateLumpSumPayment_LongTimePeriod() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 4.0, 30, 12);
        assertEquals(3320.30, result, 0.01);
    }

    // Test for high compound frequency - equivalence partition: large values
    @Test
    @DisplayName("Should handle high compound frequency correctly")
    public void testCalculateLumpSumPayment_HighCompoundFrequency() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 6.0, 2, 365);
        assertEquals(1127.50, result, 0.01);
    }

    // Test for fractional principal - equivalence partition: decimal values
    @Test
    @DisplayName("Should handle fractional principal amounts correctly")
    public void testCalculateLumpSumPayment_FractionalPrincipal() {
        double result = LoanCalculator.calculateLumpSumPayment(1234.56, 4.25, 7, 4);
        assertEquals(1658.89, result, 0.01);
    }

    // Test for fractional interest rate - equivalence partition: decimal values
    @Test
    @DisplayName("Should handle fractional interest rates correctly")
    public void testCalculateLumpSumPayment_FractionalInterestRate() {
        double result = LoanCalculator.calculateLumpSumPayment(5000.0, 3.75, 8, 12);
        assertEquals(6789.95, result, 0.01);
    }

    // Test for very small principal - boundary value
    @Test
    @DisplayName("Should handle very small principal amounts correctly")
    public void testCalculateLumpSumPayment_VerySmallPrincipal() {
        double result = LoanCalculator.calculateLumpSumPayment(0.01, 5.0, 1, 12);
        assertEquals(0.01, result, 0.001);
    }

    // Test for very small interest rate - boundary value
    @Test
    @DisplayName("Should handle very small interest rates correctly")
    public void testCalculateLumpSumPayment_VerySmallInterestRate() {
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 0.01, 10, 12);
        assertEquals(1001.00, result, 0.01);
    }

    // Test rounding behavior - ensure proper rounding to 2 decimal places
    @Test
    @DisplayName("Should round result to 2 decimal places correctly")
    public void testCalculateLumpSumPayment_RoundingBehavior() {
        // This should test the rounding logic
        double result = LoanCalculator.calculateLumpSumPayment(1000.0, 5.5, 3, 12);
        assertEquals(1178.65, result, 0.001);
        
        // Verify it's actually rounded to 2 decimal places
        String resultStr = String.valueOf(result);
        int decimalIndex = resultStr.indexOf('.');
        if (decimalIndex != -1) {
            int decimalPlaces = resultStr.length() - decimalIndex - 1;
            assertTrue(decimalPlaces <= 2, "Result should have at most 2 decimal places");
        }
    }

    // Test for edge case: all minimum valid values
    @Test
    @DisplayName("Should handle all minimum valid values correctly")
    public void testCalculateLumpSumPayment_AllMinimumValidValues() {
        double result = LoanCalculator.calculateLumpSumPayment(0.01, 0.0, 1, 1);
        assertEquals(0.01, result, 0.001);
    }
}
