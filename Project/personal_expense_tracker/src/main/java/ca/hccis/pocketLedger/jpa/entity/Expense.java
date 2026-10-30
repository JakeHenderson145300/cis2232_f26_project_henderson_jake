package ca.hccis.pocketLedger.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Clean independent JPA Entity class for PocketLedger database columns.
 * Built to isolate Sprint 2 features away from the squash skills framework.
 *
 * @author Jake Henderson
 * @since 2026-10-29
 */
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "expenseDate", nullable = false, length = 10)
    private String expenseDate;

    @Size(max = 100)
    @NotNull
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Size(max = 50)
    @NotNull
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Size(max = 50)
    @NotNull
    @Column(name = "paymentMethod", nullable = false, length = 50)
    private String paymentMethod;

    @NotNull
    @Column(name = "isRecurring", nullable = false)
    private Boolean isRecurring = false;

    @NotNull
    @Column(name = "isEssential", nullable = false)
    private Boolean isEssential = false;

    @Size(max = 255)
    @Column(name = "notes")
    private String notes;

    public Expense() {}

    /**
     * Required custom toString format so BJM's native file utility
     * automatically converts object elements inside lists to text blocks.
     */
    @Override
    public String toString() {
        return String.format("ID: %-4d | Date: %-10s | Category: %-15s | Amt: $%7.2f | Desc: %s",
                id, expenseDate, category, amount, description);
    }

    // --- Core Getters and Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getExpenseDate() { return expenseDate; }
    public void setExpenseDate(String expenseDate) { this.expenseDate = expenseDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Boolean getIsRecurring() { return isRecurring; }
    public void setIsRecurring(Boolean isRecurring) { this.isRecurring = isRecurring; }

    public Boolean getIsEssential() { return isEssential; }
    public void setIsEssential(Boolean isEssential) { this.isEssential = isEssential; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

