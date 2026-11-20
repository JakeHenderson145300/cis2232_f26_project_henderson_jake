package ca.hccis.pocketLedger.bo;

import ca.hccis.pocketLedger.jpa.entity.Expense;

import java.util.*;

public class PocketLedgerBO {
    //Set at $2000 for now as a static placeholder. Plans to make this non-static later
    private static final double BUDGET_BASELINE = 2000.00;

    public static void recalculateAllRemainingBudgets(Iterable<Expense> allExpenses) {
        if (allExpenses == null) {
            return;
        }

        // Copy into a list and sort chronologically (by expenseDate, then by id)
        List<Expense> sortedExpenses = new ArrayList<>();
        allExpenses.forEach(sortedExpenses::add);
        sortedExpenses.sort(
                Comparator.comparing(Expense::getExpenseDate, Comparator.nullsLast(String::compareTo))
                        .thenComparing(Expense::getId, Comparator.nullsLast(Integer::compareTo))
        );

        // Track running remaining budget per month ("yyyy-MM")
        Map<String, Double> runningMonthBalance = new HashMap<>();

        for (Expense exp : sortedExpenses) {
            String monthKey = getYearMonth(exp.getExpenseDate());
            double currentBalance = runningMonthBalance.getOrDefault(monthKey, BUDGET_BASELINE);
            double amt = (exp.getAmount() != null) ? exp.getAmount() : 0.0;

            double updatedBalance = Math.round((currentBalance - amt) * 100.0) / 100.0;
            exp.setRemainingBudget(updatedBalance);

            // Store the new balance for the next entry in the same month
            runningMonthBalance.put(monthKey, updatedBalance);
        }
    }

    /**
     * Helper to extract "yyyy-MM" from an expenseDate string ("yyyy-MM-dd").
     */
    private static String getYearMonth(String expenseDate) {
        if (expenseDate != null && expenseDate.length() >= 7) {
            return expenseDate.substring(0, 7);
        }
        return "UNKNOWN";
    }


    public static double calculate(Expense expense) {
        if (expense == null) {
            return BUDGET_BASELINE;
        }
        return BUDGET_BASELINE - expense.getAmount();
    }

    public double calculateMonthlyTotalSpent(ArrayList<Expense> monthlyExpenses) {
        if (monthlyExpenses == null || monthlyExpenses.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Expense expense : monthlyExpenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public double calculateRemainingBudget(ArrayList<Expense> monthlyExpenses) {
        double totalSpent = calculateMonthlyTotalSpent(monthlyExpenses);
        return BUDGET_BASELINE - totalSpent;
    }

    public double calculateSafeDailyAllowance(double totalSpent, int activeDay, int totalDaysInMonth) {
        double remainingBudget = BUDGET_BASELINE - totalSpent;
        int daysRemaining = totalDaysInMonth - activeDay;
        if (daysRemaining <= 0) {
            return 0.0;
        }
        return remainingBudget / daysRemaining;
    }
}
