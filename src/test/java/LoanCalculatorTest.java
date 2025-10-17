import com.yourbank.loan.LoanCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest {

    // === 1. Các trường hợp hợp lệ (Valid Cases - EP) ===

    @Test
    @DisplayName("TC01: Kiểm thử trường hợp cơ bản, ghép lãi hàng năm")
    void test_Valid_AnnualCompounding() {
        assertEquals(179084.77, LoanCalculator.calculateLumpSumPayment(100000, 6.0, 10, 1));
    }

    @Test
    @DisplayName("TC02: Kiểm thử trường hợp ghép lãi hàng tháng")
    void test_Valid_MonthlyCompounding() {
        assertEquals(181939.67, LoanCalculator.calculateLumpSumPayment(100000, 6.0, 10, 12));
    }

    // === 2. Các trường hợp biên (Boundary Cases - BVA) ===

    @Test
    @DisplayName("TC03 (BVA): Kiểm thử principal = 0")
    void test_Boundary_ZeroPrincipal() {
        // Test nhánh 'if (principal == 0)'
        assertEquals(0.0, LoanCalculator.calculateLumpSumPayment(0, 5.0, 5, 4));
    }

    @Test
    @DisplayName("TC04 (BVA): Kiểm thử lãi suất = 0")
    void test_Boundary_ZeroInterestRate() {
        assertEquals(50000.00, LoanCalculator.calculateLumpSumPayment(50000, 0.0, 20, 1));
    }

    @Test
    @DisplayName("TC05 (BVA): Kiểm thử số năm tối thiểu = 1")
    void test_Boundary_MinimumYears() {
        assertEquals(21648.64, LoanCalculator.calculateLumpSumPayment(20000, 8.0, 1, 4));
    }

    @Test
    @DisplayName("TC06 (BVA): Kiểm thử số lần ghép lãi tối thiểu = 1")
    void test_Boundary_MinimumCompoundPeriod() {
        assertEquals(29386.56, LoanCalculator.calculateLumpSumPayment(20000, 8.0, 5, 1));
    }

    // === 3. Các trường hợp bất hợp lệ (Invalid Cases - EP & BVA) ===
    // Các test này đảm bảo nhánh 'if (principal < 0 || ...)' được bao phủ 100%

    @Test
    @DisplayName("TC09 (BVA): Kiểm thử principal âm")
    void test_Invalid_NegativePrincipal() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(-0.01, 10.0, 5, 1);
        });
    }

    @Test
    @DisplayName("TC11 (BVA): Kiểm thử lãi suất âm")
    void test_Invalid_NegativeInterestRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(50000, -0.01, 10, 1);
        });
    }

    @Test
    @DisplayName("TC13 (BVA): Kiểm thử số năm = 0")
    void test_Invalid_ZeroYears() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(10000, 5.0, 0, 1);
        });
    }

    @Test
    @DisplayName("TC15 (BVA): Kiểm thử số lần ghép lãi = 0")
    void test_Invalid_ZeroCompoundPeriod() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateLumpSumPayment(10000, 5.0, 5, 0);
        });
    }
}