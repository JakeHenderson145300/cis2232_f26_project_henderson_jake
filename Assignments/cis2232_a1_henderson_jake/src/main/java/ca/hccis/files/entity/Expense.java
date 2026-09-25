package ca.hccis.files.entity;

import ca.hccis.util.CisUtility;

import java.util.Scanner;

/**
 * PocketLedger Entity Model
 * @author Jake Henderson
 * @since 2026-09-24
 */

public class Expense {

    private int expenseId;
    private String expenseDate;
    private String expenseDescription;
    private String category;
    private double amount;
    private String paymentMethod;
    private boolean isRecurring;
    private boolean isEssential;
    private String notes;

    private static final String[] CATEGORIES = {
            "Groceries", "Dining Out", "Transportation", "Housing/Utilities", "School", "Personal",
            "Entertainment", "Other"
    };

    private static final String[] PAYMENT_METHODS = {
            "Cash", "Debit Card", "e-Transfer", "Pre-authorized"
    };

    public Expense() {
    }

    public Expense(int expenseId, String expenseDate, String expenseDescription, String category, double amount,
                   String paymentMethod, boolean isRecurring, boolean isEssential, String notes) {
        this.expenseId = expenseId;
        this.expenseDate = expenseDate;
        this.expenseDescription = expenseDescription;
        this.category = category;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isRecurring = isRecurring;
        this.isEssential = isEssential;
        this.notes = notes;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public boolean isEssential() {
        return isEssential;
    }

    public void setEssential(boolean essential) {
        isEssential = essential;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //Method for gathering user input
    public void getInformation(int nextId) {
        System.out.println("\n--- Enter Expense Details ---");

        this.expenseId = nextId;
        this.expenseDate = CisUtility.getValidatedDateString("Enter expense date");
        this.expenseDescription = CisUtility.getValidatedRequiredString("Enter short description: ");
        this.category = arraySelectionChoice("Category", CATEGORIES);
        this.amount = CisUtility.getValidatedPositiveDouble("\nEnter expense amount: ");
        this.paymentMethod = arraySelectionChoice("Payment Method", PAYMENT_METHODS);
        System.out.println();
        this.isRecurring = CisUtility.getValidatedInputBoolean("Does this expense repeat every month?");
        this.isEssential =  CisUtility.getValidatedInputBoolean("Is this an essential expense?");
        this.notes = CisUtility.getInputString("Enter notes (Optional): ");
    }

    //Method to utilize Final String arrays
    private String arraySelectionChoice(String title, String[] optionsArray) {
        System.out.println("\nSelect " + title + ":");
        for (int i = 0; i < optionsArray.length; i++) {
            System.out.println((i + 1)+") " + optionsArray[i]);
        }
        int choice = 0;
        while (choice < 1 || choice > optionsArray.length) {
            choice = CisUtility.getValidatedInteger("Enter selection number: ");
            if (choice < 1 || choice > optionsArray.length) {
                System.out.println("Invalid selection. Please pick a number between 1 and " + (optionsArray.length - 1) +".");
            }
        }
        return optionsArray[choice - 1];
    }

  public void display() {
        System.out.println("----------------------------------------------------");
        System.out.println("Expense ID:          " + expenseId);
        System.out.println("Expense Date:        " + expenseDate);
        System.out.println("Expense Description: " + expenseDescription);
        System.out.println("Category:            " + category);
        System.out.println("Amount:             $" + String.format("%.2f", amount));
        System.out.println("PaymentMethod:       " + paymentMethod);
        System.out.println("Recurring Expense:   " + (isRecurring ? "Yes" : "No"));
        System.out.println("Essential Expense:   " + (isEssential ? "Yes" : "No"));
        System.out.println("Notes:               " + (notes.isEmpty() ? "None" : notes));
  }
}
