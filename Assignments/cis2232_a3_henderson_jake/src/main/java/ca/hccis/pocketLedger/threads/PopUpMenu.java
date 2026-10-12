package ca.hccis.pocketLedger.threads;

import ca.hccis.pocketLedger.Controller;
import ca.hccis.pocketLedger.bo.PocketLedgerBO;
import ca.hccis.pocketLedger.entity.Expense;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Handles the JOptionPane based console user interface for A3
 *
 * @author Jake Henderson
 * @since 2026-10-12
 */
public class PopUpMenu implements Runnable {
    private static final String[] CATEGORIES = {
            "Groceries", "Dining Out", "Transportation", "Housing/Utilities", "School", "Personal",
            "Entertainment", "Other"
    };

    private static final String[] PAYMENT_METHODS = {
            "Cash", "Debit Card", "e-Transfer", "Pre-authorized"
    };

    @Override
    public void run() {
        String option = "";
        String menuPrompt = """
                *** POCKETLEDGER POPUP MENU ***
                A) Add Expense
                B) View Expenses
                X) Exit Popup Thread
                Select an option:""";

        do {
            String input = JOptionPane.showInputDialog(null, menuPrompt, "PocketLedger GUI Thread", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                option = "X";
            } else {
                option = input.trim().toUpperCase();
            }

            switch (option) {
                case "A":
                    addExpenseViaGUI();
                    break;
                case "B":
                    viewExpensesViaGUI();
                    break;
                case "X":
                    JOptionPane.showMessageDialog(null, "Exiting PopUp Worker Thread.", "Thread Shutdown", JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Option. Pick A, B, or X.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (!option.equals("X"));
    }

    private void addExpenseViaGUI() {
        try {
            int nextId = Controller.getSharedNextId();

            String dateInput = JOptionPane.showInputDialog(null, "Enter expense date (YYYY-MM-DD):", "Add Expense [ID: " + nextId + "]", JOptionPane.QUESTION_MESSAGE);
            if (dateInput == null || dateInput.trim().isEmpty()) return;

            String descriptionInput = JOptionPane.showInputDialog(null, "Enter short description:", "Add Expense [ID: " + nextId + "]", JOptionPane.QUESTION_MESSAGE);
            if (descriptionInput == null || descriptionInput.trim().isEmpty()) return;

            String categoryChosen = (String) JOptionPane.showInputDialog(null, "Select Category:", "Add Expense [ID: " + nextId + "]",
                    JOptionPane.QUESTION_MESSAGE, null, CATEGORIES, CATEGORIES[0]);
            if (categoryChosen == null) return;

            String amountStr = JOptionPane.showInputDialog(null, "Enter expense amount ($):", "Add Expense [ID: " + nextId + "]", JOptionPane.QUESTION_MESSAGE);
            if (amountStr == null || amountStr.trim().isEmpty()) return;

            double amountInput = Double.parseDouble(amountStr);
            if (amountInput < 0) {
                JOptionPane.showMessageDialog(null, "Amount must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String paymentChosen = (String) JOptionPane.showInputDialog(null, "Select Payment Method:", "Add Expense [ID: " + nextId + "]",
                    JOptionPane.QUESTION_MESSAGE, null, PAYMENT_METHODS, PAYMENT_METHODS[0]);
            if (paymentChosen == null) return;

            int recurringCheck = JOptionPane.showConfirmDialog(null, "Does this expense repeat every month?", "Recurring commitment?", JOptionPane.YES_NO_OPTION);
            boolean isRecurringInput = (recurringCheck == JOptionPane.YES_OPTION);

            int essentialCheck = JOptionPane.showConfirmDialog(null, "Is this an essential expense (Need)?", "Essential?", JOptionPane.YES_NO_OPTION);
            boolean isEssentialInput = (essentialCheck == JOptionPane.YES_OPTION);

            String notesInput = JOptionPane.showInputDialog(null, "Enter notes (Optional):", "Add Expense [ID: " + nextId + "]", JOptionPane.QUESTION_MESSAGE);
            if (notesInput == null) notesInput = "";

            Expense newExpense = new Expense(nextId, dateInput, descriptionInput, categoryChosen, amountInput, paymentChosen, isRecurringInput, isEssentialInput, notesInput);
            Controller.addSharedExpense(newExpense);

            String successReport = successString();

            JOptionPane.showMessageDialog(null, successReport, "Success & Multi-Thread Accumulator Report", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount value typed. Entry rejected.", "Data Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String successString() {
        PocketLedgerBO bo = new PocketLedgerBO();
        ArrayList<Expense> liveList = Controller.getSharedExpensesList();

        // UI purely pulls answers without knowing or running any mathematical operators
        double totalSpent = bo.calculateMonthlyTotalSpent(liveList);
        double remainingBudget = bo.calculateRemainingBudget(liveList);

        // Constructing a detailed confirmation summary popup window frame
        String successReport = "Expense added and saved successfully!\n\n" +
                "--- Cumulative Budget Assessment Status ---\n" +
                "Baseline Allotment Pool:      $2000.00\n" +
                "Combined Spending to Date:     $" + String.format("%.2f", totalSpent) + "\n" +
                "Total Remaining Balance:       $" + String.format("%.2f", remainingBudget) + "\n\n" +
                (remainingBudget < 0 ? "ALERT: Budget allocation cap exceeded!" : "Spending is within safe limits.");
        return successReport;
    }

    private void viewExpensesViaGUI() {
        ArrayList<Expense> sharedList = Controller.getSharedExpensesList();

        if (sharedList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No expenses recorded yet in the ledger database.", "Database Dashboard", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("=== Stored PocketLedger Expenses (GUI Thread View) ===\n");
        builder.append("Total Registered Record Entries Counter: ").append(sharedList.size()).append("\n\n");

        for (Expense expense : sharedList) {
            builder.append("----------------------------------------------------\n");
            builder.append("Expense ID:          ").append(expense.getExpenseId()).append("\n");
            builder.append("Expense Date:        ").append(expense.getExpenseDate()).append("\n");
            builder.append("Description:          ").append(expense.getExpenseDescription()).append("\n");
            builder.append("Category:            ").append(expense.getCategory()).append("\n");
            builder.append("Amount:             $").append(String.format("%.2f", expense.getAmount())).append("\n");
            builder.append("Payment Method:      ").append(expense.getPaymentMethod()).append("\n");
            builder.append("Recurring Expense:   ").append(expense.isRecurring() ? "Yes" : "No").append("\n");
            builder.append("Essential Expense:   ").append(expense.isEssential() ? "Yes" : "No").append("\n");
            builder.append("Notes:               ").append(expense.getNotes().isEmpty() ? "None" : expense.getNotes()).append("\n");
        }
        builder.append("----------------------------------------------------");

        JTextArea textArea = new JTextArea(builder.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(450, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "PocketLedger Database Viewer", JOptionPane.PLAIN_MESSAGE);
    }
}
