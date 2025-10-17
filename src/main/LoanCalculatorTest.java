package com.yourbank.loan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest {

    /**
     * Tính toán tổng số tiền (cả gốc và lãi) phải trả một lần vào cuối kỳ cho khoản vay trả sau.
     * Phép tính này dựa trên công thức lãi kép.
     *
     * @param principal            Số tiền vay ban đầu (phải là số không âm).
     * @param annualInterestRate   Lãi suất hàng năm dưới dạng phần trăm (ví dụ: 6 cho 6%, phải là số không âm).
     * @param years                Thời hạn vay tính bằng năm (phải lớn hơn 0).
     * @param compoundPeriodPerYear Số lần ghép lãi trong một năm (ví dụ: 1 cho hàng năm, 12 cho hàng tháng. Phải lớn hơn hoặc bằng 1).
     * @return Tổng số tiền phải trả khi đáo hạn, đã được làm tròn đến 2 chữ số thập phân.
     * @throws IllegalArgumentException nếu bất kỳ tham số đầu vào nào không hợp lệ.
     */

    private static final double DELTA = 0.001; // Sai số cho phép khi so sánh số thực

    @Test
    void testValidCaseFromExample() {
        // principal = 100,000; annualInterestRate = 6; years = 10; compound = 1 (Annually)
        double expected = 179084.77;
        double actual = LoanCalculator.calculateLumpSumPayment(100000, 6, 10, 1);
        assertEquals(expected, actual, DELTA);
    }

    @Test
    void testValidCaseMonthlyCompound() {
        // principal = 100,000; annualInterestRate = 6; years = 10; compound = 12 (Monthly)
        double expected = 181939.67; // Calculated from web
        double actual = LoanCalculator.calculateLumpSumPayment(100000, 6, 10, 12);
        assertEquals(expected, actual, DELTA);
    }

    @Test
    void testZeroPrincipal() {
        assertEquals(0.0, LoanCalculator.calculateLumpSumPayment(0, 10, 5, 12), DELTA);
    }

    // Parameterized test for invalid inputs
    @ParameterizedTest
    @CsvSource({
            "-100, 5, 5, 1",      // Invalid principal
            "100, -5, 5, 1",      // Invalid rate
            "100, 5, 0, 1",       // Invalid years (zero)
            "100, 5, -1, 1",      // Invalid years (negative)
            "100, 5, 5, 0",       // Invalid compound period (zero)
            "100, 5, 5, -1"       // Invalid compound period (negative)
    })
    void testInvalidInputsShouldThrowException(double p, double r, int y, int n) {
        // Dùng lambda để kiểm tra exception được ném ra
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(p, r, y, n);
        });
    }
}