package ca.hccis.pocketLedger.threads;

import ca.hccis.pocketLedger.Controller;
import ca.hccis.pocketLedger.bo.PocketLedgerBO;
import ca.hccis.pocketLedger.entity.Expense;

import java.util.ArrayList;

/**
 * Handles the text based console user interface for A3
 *
 * @author Jake Henderson
 * @since 2026-10-12
 */
public class ConsoleMenu implements Runnable {

    @Override
    public void run() {
        String option = "";
        do {
            System.out.println("\n*** POCKETLEDGER MAIN MENU ***");
            System.out.println("A) Add Expense");
            System.out.println("B) View Expenses");
            System.out.println("X) Exit");
            option = ca.hccis.util.CisUtility.getInputString("Select an option: ").toUpperCase();

            switch (option) {
                case "A":
                    System.out.println("\n[Console] Fetching shared identifier tracking index...");
                    int nextId = Controller.getSharedNextId();

                    Expense newExpense = new Expense();
                    newExpense.getInformation(nextId);

                    Controller.addSharedExpense(newExpense);

                    PocketLedgerBO bo = new PocketLedgerBO();
                    ArrayList<Expense> liveList = Controller.getSharedExpensesList();

                    double totalSpent = bo.calculateMonthlyTotalSpent(liveList);
                    double remainingBudget = bo.calculateRemainingBudget(liveList);

                    System.out.println("\n==============================================");
                    System.out.println("[LEDGER BUDGET ACCUMULATION STATUS]");
                    System.out.println("Total Tracked Items:       " + liveList.size());
                    System.out.println("Cumulative Total Spent:    $" + String.format("%.2f", totalSpent));
                    System.out.println("Remaining Monthly Budget:  $" + String.format("%.2f", remainingBudget));
                    if (remainingBudget < 0) {
                        System.out.println("WARNING: You have exceeded your $2,000 limit!");
                    }
                    System.out.println("==============================================");
                    break;
                case "B":
                    Controller.viewSharedExpenses();
                    break;
                case "X":
                    System.out.println("Exiting Console Worker Thread Interface.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (!option.equals("X"));
    }
}
