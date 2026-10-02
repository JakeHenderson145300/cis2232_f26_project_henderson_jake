package ca.hccis.pocketLedger.bo;

import ca.hccis.pocketLedger.entity.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PocketLedgerAITestSuite {
    private PocketLedgerBO pocketLedgerBO;
    private ArrayList<Expense> sampleSeptemberLedger;

    @BeforeEach
    public void setUp() {
        pocketLedgerBO = new PocketLedgerBO();
        sampleSeptemberLedger = new ArrayList<>();

        // Populating using the official project worked example dataset rule specs
        sampleSeptemberLedger.add(new Expense(101, "2026-09-01", "Rent", "Housing/Utilities", 950.00, "Pre-authorized", true, true, ""));
        sampleSeptemberLedger.add(new Expense(102, "2026-09-03", "Groceries", "Groceries", 186.40, "Debit Card", false, true, ""));
        sampleSeptemberLedger.add(new Expense(103, "2026-09-05", "Bus pass", "Transportation", 20.00, "Debit Card", true, true, ""));
        sampleSeptemberLedger.add(new Expense(104, "2026-09-08", "Phone plan", "Housing/Utilities", 45.00, "Pre-authorized", true, true, ""));
        sampleSeptemberLedger.add(new Expense(105, "2026-09-12", "Dining out", "Dining Out", 122.75, "Credit Card", false, false, ""));
        sampleSeptemberLedger.add(new Expense(106, "2026-09-15", "Streaming", "Entertainment", 16.99, "Credit Card", true, false, ""));
    }

    /**
     * AI Generated Test: Verifies that cumulative collection totals match
     * the project document total ($1,341.14) perfectly.
     */
    @Test
    public void testAIGeneratedMonthlyTotalSpent() {
        double actualTotal = pocketLedgerBO.calculateMonthlyTotalSpent(sampleSeptemberLedger);
        double expectedTotal = 1341.14; // $950 + $186.40 + $20 + $45 + $122.75 + $16.99

        assertEquals(expectedTotal, actualTotal, 0.001, "The accumulation of September expenses must exactly equal $1,341.14");
    }

    /**
     * AI Generated Test: Verifies that on the 17th day of a 30-day month,
     * the safe daily allowance tracks exactly to the specification value ($50.68).
     */
    @Test
    public void testAIGeneratedSafeDailyAllowance() {
        double totalSpent = pocketLedgerBO.calculateMonthlyTotalSpent(sampleSeptemberLedger); // $1,341.14

        // September 17th means 13 days remaining in a 30-day month
        double actualAllowance = pocketLedgerBO.calculateSafeDailyAllowance(totalSpent, 17, 30);
        double expectedAllowance = 50.68; // ($2000.00 - $1341.14) / 13 = $658.86 / 13 = $50.6815

        assertEquals(expectedAllowance, actualAllowance, 0.01, "The safe daily allowance must reflect remaining budget pool pacing.");
    }

    /**
     * AI Generated Test: Verifies that passing an empty list returns
     * a clean baseline of 0.0 total spend without thrown runtime crashes.
     */
    @Test
    public void testAIGeneratedEmptyListHandling() {
        ArrayList<Expense> emptyList = new ArrayList<>();
        double actualTotal = pocketLedgerBO.calculateMonthlyTotalSpent(emptyList);

        assertEquals(0.0, actualTotal, 0.001, "Empty data structures should cleanly yield zero cash accumulation values.");
    }
}
