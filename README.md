# cis2232_f26_project_henderson_jake #
**_PocketLedger_**

---

## Development Team

* **Business Client:**  Jonathan Dumaguing  
* **Developer:**   Jake Henderson  
* **Quality Control:**  Sean Huang

---

## Description ##

This console-based Java application serves as a personal finance tracker designed to monitor and catalog expenses. Users can dynamically add individual transaction details, view historical entries pulled directly from persistent local storage, and exit cleanly. The system features modular input validation to prevent invalid data entries and ensure overall system robustness. Future updates will leverage a Test-Driven Development (TDD) approach to implement advanced financial analysis and reporting logic.

---

## Color ##

* **Color:**  Teal

---

## Required Fields ##

| Field Name | Data Type | Description |
| --- | --- | --- |
| `id` | `int` | Unique identifier for tracking metrics | 
| `expenseDate` | `String` |  Date of the transaction (YYYY-MM-DD format) |
| `description` | `String` | Short descriptive label of the purchase |
| `category` | `String` | Predefined type (Groceries, Dining Out, etc.) |
| `amount` | `double` | Dollar cost of the expense in CAD | 
| `paymentMethod` | `String` | Transaction method (Cash, Debit, Credit Card, etc.) |
| `isRecurring` | `boolean` | Flags if the billing standard repeats monthly |
| `isEssential` | `boolean` | Identifies if the item is a critical need vs. want |
| `notes` | `String` | Optional extra transaction annotation notes |
          
---

## Calculation ##

Business rule (assumed): the user sets a fixed monthly budget of $2,000.00. All calculations below run over the records where ExpenseDate falls inside the selected month.
1. Monthly total spent = SUM(Amount) for the selected month.
2. Category subtotal = SUM(Amount) grouped by Category; category share % = (category subtotal ÷ monthly total) × 100.
3. Budget used % = (monthly total ÷ 2000.00) × 100. Remaining budget = 2000.00 − monthly total.
4. Safe daily allowance = remaining budget ÷ days left in the month (days in month − day number of today).
5. Fixed vs variable = SUM(Amount) where IsRecurring is true, compared against the rest of the monthly total.
6. Needs vs wants % = (SUM(Amount) where IsEssential is true ÷ monthly total) × 100.
7. Projected month-end spend = (monthly total ÷ days elapsed) × days in month. A warning is shown when the projection exceeds the budget.
Worked example: September 2026, evaluated on the 17th (30 days in the month, 13 days remaining):
Records: Rent $950.00 (Housing, recurring, essential); Groceries $186.40 (essential); Bus pass $20.00 (Transportation, recurring, essential); Phone plan $45.00 (recurring, essential); Dining out $122.75 (non-essential); Streaming $16.99 (recurring, non-essential).
Monthly total = $1,341.14. Budget used = 1341.14 ÷ 2000 × 100 = 67.06%. Remaining budget = $658.86. Safe daily allowance = 658.86 ÷ 13 = $50.68 per day. Recurring commitments = $1,031.99 (76.95% of the month). Needs = $1,201.40, so needs vs wants = 89.58% / 10.42%. Projected month-end spend = (1341.14 ÷ 17) × 30 = $2,366.72, which is over the $2,000 budget, so the application flags the month as on pace to overspend.
The calculation therefore depends on ExpenseDate, Amount, Category, IsRecurring and IsEssential together, not on any single field.

---

## Report Details ##

To be determined in a future sprint.

