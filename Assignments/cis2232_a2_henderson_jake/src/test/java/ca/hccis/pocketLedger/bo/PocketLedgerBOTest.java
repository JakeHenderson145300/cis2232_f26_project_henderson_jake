package ca.hccis.pocketLedger.bo;

import ca.hccis.pocketLedger.entity.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PocketLedgerBOTest {

    private PocketLedgerBO pocketLedgerBO;

    @BeforeEach
    public void setUp() {
        pocketLedgerBO = new PocketLedgerBO();
    }

    /**
     * Test 1 created by Jake following TDD
     * @since 2026-10-01
     * @author Jake Henderson
     */
    @Test
    public void testCalculateRemainingBudget() {
        Expense expense = new Expense(1, "2026-09-17", "Groceries", "Groceries", 186.40, "Debit Card", false, true, "Weekly run");

        // Expected: $2,000.00 baseline - $186.40 = $1,813.60
        double expectedRemaining = 1813.60;
        double actualRemaining = pocketLedgerBO.calculate(expense);

        assertEquals(expectedRemaining, actualRemaining, 0.001, "Remaining budget should equal baseline minus expense amount.");
    }

    /**
     * Test 2 created by Jake following TDD
     * @since 2026-10-01
     * @author Jake Henderson
     */
    @Test
    public void testCalculateRemainingWithLargeExpense() {
        Expense expense = new Expense(2, "2026-09-01", "Monthly Rent", "Housing/Utilities", 950.00, "Pre-authorized", true, true, "N/A");

        // Expected: $2,000.00 baseline - $950.00 = $1,050.00
        double expectedRemaining = 1050.00;
        double actualRemaining = pocketLedgerBO.calculate(expense);

        assertEquals(expectedRemaining, actualRemaining, 0.001);
    }

    /**
     * Test 3 created by Jake following TDD
     * @since 2026-10-01
     * @author Jake Henderson
     */
    @Test
    public void testCalculatePoolIsPositive() {
        Expense expense = new Expense(3, "2026-09-12", "Dining out", "Dining Out", 122.75, "Credit Card", false, false, "N/A");

        double actualRemaining = pocketLedgerBO.calculate(expense);

        // Asserting that the remaining balance pool is true (> 0)
        assertTrue(actualRemaining > 0, "Remaining cash pool should remain positive.");
    }


}
