# For hccis.ca version of the database
# DROP DATABASE IF EXISTS jhenderson_pocketledger_w26;
# CREATE DATABASE jhenderson_pocketledger_w26;
# use jhenderson_pocketledger_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_pocketledger;
CREATE DATABASE cis2232_pocketledger;
use cis2232_pocketledger;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------
CREATE TABLE expenses
(
    id                 int(5) NOT NULL AUTO_INCREMENT,
    expenseDate        varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    description        varchar(100) NOT NULL COMMENT 'Purchase label text',
    category           varchar(50) NOT NULL COMMENT 'Groceries, School, Entertainment, etc.',
    amount             double NOT NULL COMMENT 'Dollar cost value in CAD',
    paymentMethod      varchar(50) NOT NULL COMMENT 'Cash, Debit, Credit Card, etc.',
    isRecurring        boolean NOT NULL DEFAULT FALSE COMMENT 'Does billing repeat monthly',
    isEssential        boolean NOT NULL DEFAULT FALSE COMMENT 'Is it a critical Need vs Want',
    notes              varchar(255) NULL COMMENT 'Optional transaction annotations',
    PRIMARY KEY (id)
) COMMENT 'This table holds personal finance transaction details';

INSERT INTO expenses (id, expenseDate, description, category, amount, paymentMethod, isRecurring, isEssential, notes)
VALUES (1, '2026-09-01', 'Sobeys Groceries Weekly', 'Groceries', 145.50, 'Debit', FALSE, TRUE, 'Weekly family stock'),
       (2, '2026-09-03', 'Monthly Bus Pass', 'Transportation', 65.00, 'Cash', TRUE, TRUE, 'Commute to campus'),
       (3, '2026-09-05', 'McDonalds Dinner', 'Dining Out', 18.25, 'Credit Card', FALSE, FALSE, 'Late night study snack'),
       (4, '2026-09-10', 'Monthly Phone Bill', 'Housing/Utilities', 85.00, 'Pre-authorized', TRUE, TRUE, 'Bell data plan'),
       (5, '2026-09-12', 'College Textbooks', 'School', 210.00, 'Debit', FALSE, TRUE, 'CIS2232 course books'),
       (6, '2026-09-15', 'Cineplex Movie Tickets', 'Entertainment', 34.50, 'Credit Card', FALSE, FALSE, 'Weekend outing'),
       (7, '2026-09-17', 'New Haircut', 'Personal', 25.00, 'Cash', FALSE, FALSE, 'Local barber shop appointment'),
       (8, '2026-09-20', 'Gas Station Coffee', 'Other', 2.75, 'Debit', FALSE, FALSE, 'Quick morning refuel'),
       (9, '2026-09-25', 'Netflix Subscription', 'Entertainment', 16.99, 'Credit Card', TRUE, FALSE, 'Monthly stream package');
