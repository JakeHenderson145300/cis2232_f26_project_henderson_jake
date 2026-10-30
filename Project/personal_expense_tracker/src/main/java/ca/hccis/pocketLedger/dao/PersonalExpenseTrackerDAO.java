package ca.hccis.pocketLedger.dao;

import ca.hccis.pocketLedger.jpa.entity.Expense;
import ca.hccis.pocketLedger.util.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Handles raw JDBC database manipulation operations for PocketLedger.
 * Isolate database lookups safely away from squash metrics.
 *
 * @author Jake Henderson
 * @since 2026-10-29
 */
public class PersonalExpenseTrackerDAO {

    /**
     * Sprint 2 Dynamic Lookup: Pulls an ordered unique list of active spending
     * categories from your live database rows to populate dropdown forms.
     */
    public ArrayList<String> selectDistinctCategories() {
        ArrayList<String> categories = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Reuses your project's built-in application properties connection pool loader
            conn = DatabaseUtility.getConnection("");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT category FROM expenses ORDER BY category ASC;");

            while (rs.next()) {
                String cat = rs.getString("category");
                if (cat != null && !cat.trim().isEmpty()) {
                    categories.add(cat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error closing distinct category resources");
            }
        }
        return categories;
    }

    /**
     * Sprint 2 Report Filter: Extracts database rows via low-level JDBC statements.
     * Filters completely by category with an optional date constraint.
     */
    public ArrayList<Expense> selectByReportCriteria(String category, String dateFilter) {
        ArrayList<Expense> expensesList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder("SELECT id, expenseDate, description, category, amount, paymentMethod, isRecurring, isEssential, notes FROM expenses WHERE category = ?");

        boolean hasDate = (dateFilter != null && !dateFilter.trim().isEmpty());
        if (hasDate) {
            sql.append(" AND expenseDate = ?");
        }

        try {
            conn = DatabaseUtility.getConnection("");
            stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1, category);

            if (hasDate) {
                stmt.setString(2, dateFilter);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Expense item = new Expense();
                item.setId(rs.getInt("id"));
                item.setExpenseDate(rs.getString("expenseDate"));
                item.setDescription(rs.getString("description"));
                item.setCategory(rs.getString("category"));
                item.setAmount(rs.getDouble("amount"));
                item.setPaymentMethod(rs.getString("paymentMethod"));
                item.setIsRecurring(rs.getBoolean("isRecurring"));
                item.setIsEssential(rs.getBoolean("isEssential"));
                item.setNotes(rs.getString("notes"));

                expensesList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error closing statement query execution pools");
            }
        }
        return expensesList;
    }
}

