package ca.hccis.pocketLedger.bo;

import ca.hccis.pocketLedger.entity.Expense;

import java.util.ArrayList;

public class PocketLedgerBO {
    //Set at $2000 for now as a static placeholder. Plans to make this non-static later
    private static final double BUDGET_BASELINE = 2000.00;

    public double calculate(Expense expense) {
        if (expense == null) {
            return BUDGET_BASELINE;
        }
        return BUDGET_BASELINE -  expense.getAmount();
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

    public double calculateSafeDailyAllowance(double totalSpent, int activeDay, int totalDaysInMonth) {
        double remainingBudget = BUDGET_BASELINE - totalSpent;
        int daysRemaining = totalDaysInMonth - activeDay;
        if (daysRemaining <= 0) {
            return 0.0;
        }
        return remainingBudget / daysRemaining;
    }
}
