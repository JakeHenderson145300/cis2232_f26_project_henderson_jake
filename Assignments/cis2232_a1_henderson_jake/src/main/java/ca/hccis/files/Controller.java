package ca.hccis.files;

import ca.hccis.files.entity.Expense;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PocketLedger Core Menu and Data Storage Controller
 *
 * @author Jake Henderson
 * @since 2026-09-24
 */
public class Controller {

    private static final String FIlE_PATH = "c:\\cis2232\\data_henderson_jake.json";
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        ensureDirectoryExists();
        loadData();

        String option = "";
        do {
            System.out.println("\n*** POCKETLEDGER MAIN MENU ***");
            System.out.println("A) Add Expense");
            System.out.println("B) View Expenses");
            System.out.println("X) Exit");
            option = CisUtility.getInputString("Select an option: ").toUpperCase();

            switch (option) {
                case "A":
                    addExpense();
                    break;
                case "B":
                    viewExpenses();
                    break;
                case "X":
                    System.out.println("Exiting Application");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (!option.equals("X"));
    }

    //Method to view all expenses from the data_henderson_jake.json file
    private static void viewExpenses() {
        System.out.println("\n===Stored PocketLedger Expenses===");
        loadData();

        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else {
            for (Expense expense : expenses) {
                expense.display();
            }
        }
    }

    //Method to add an expense
    private static void addExpense() {
        loadData();
        int nextId = expenses.isEmpty() ? 1 : expenses.get(expenses.size() - 1).getExpenseId() + 1;
        Expense newExpense = new Expense();
        newExpense.getInformation(nextId);
        expenses.add(newExpense);
        saveData();
        System.out.println("Expense added an saved successfully");
    }

    //Method to save data into the data_henderson_jake.json file
    private static void saveData() {
        try (FileWriter writer = new FileWriter(FIlE_PATH)) {
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file data path: " + e.getMessage());
        }
    }

    //Method that ensures the correct Directory is in place
    private static void ensureDirectoryExists() {
        File file = new File(FIlE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    //Method that updates the expenses array to keep it up to date with the data_henderson_jake.json file
    private static void loadData() {
        File file = new File(FIlE_PATH);
        if (file.exists()) {
            try (FileReader reader = new FileReader(FIlE_PATH)) {
                Type type = new TypeToken<ArrayList<Expense>>() {
                }.getType();
                ArrayList<Expense> loadedData = gson.fromJson(reader, type);
                if (loadedData != null) {
                    expenses = loadedData;
                }
            } catch (IOException e) {
                System.out.println("Error reading application history file data structure: " + e.getMessage());
            }
        }
    }
}
