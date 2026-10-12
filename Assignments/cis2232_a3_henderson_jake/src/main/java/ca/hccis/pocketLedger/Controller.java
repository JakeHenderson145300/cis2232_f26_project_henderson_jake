package ca.hccis.pocketLedger;

import ca.hccis.pocketLedger.entity.Expense;
import ca.hccis.pocketLedger.threads.ConsoleMenu;
import ca.hccis.pocketLedger.threads.PopUpMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * PocketLedger Core Menu and Data Storage Controller
 *
 * @author Jake Henderson
 * @since 2026-10-12
 */
public class Controller {

    private static final String FILE_PATH = "c:\\cis2232\\data_henderson_jake.json";
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        ensureDirectoryExists();
        loadData();

        System.out.println("Initializing Parallel Processing Thread Pools...");

        Thread consoleThread = new Thread(new ConsoleMenu());
        Thread popUpThread = new Thread(new PopUpMenu());
        consoleThread.start();
        popUpThread.start();

        try {
            consoleThread.join();
            popUpThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread error: " + e.getMessage());
        }
        System.out.println("\nAll menu thread structures shut down cleanly. PocketLedger session closed.");
    }

    //Method to view all expenses from the data_henderson_jake.json file
    public static synchronized void viewSharedExpenses() {
        System.out.println("\n=== Stored PocketLedger Expenses ===");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else  {
            for (Expense expense : expenses) {
                expense.display();
            }
        }
    }

    //Method to add an expense
    public static synchronized void addSharedExpense(Expense newExpense) {
        expenses.add(newExpense);
        saveData();
        System.out.println("Expense Logged. Accumulator Count: " + expenses.size());
    }

    public static synchronized int getSharedNextId() {
        return expenses.isEmpty() ? 0 : expenses.getLast().getExpenseId() +1;
    }

    //Method to save data into the data_henderson_jake.json file
    private static synchronized void saveData() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file data path: " + e.getMessage());
        }
    }

    //Method that ensures the correct Directory is in place
    private static void ensureDirectoryExists() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    //Method that updates the expenses array to keep it up to date with the data_henderson_jake.json file
    private static void loadData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileReader reader = new FileReader(FILE_PATH)) {
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

    public static synchronized ArrayList<Expense> getSharedExpensesList() {
        return expenses;
    }
}
