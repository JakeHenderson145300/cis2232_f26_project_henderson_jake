package ca.hccis.pocketLedger.entity;

import ca.hccis.pocketLedger.jpa.entity.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDto {
    private List<Expense> expenses;

    public ExpenseDto() {
        expenses = new ArrayList<>();
    }

    public ExpenseDto(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}