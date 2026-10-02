# For hccis.ca version of the database
# DROP DATABASE IF EXISTS bjmac_squash_skills_w26;
# CREATE DATABASE bjmac_squash_skills_w26;
# use bjmac_squash_skills_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_pocket_ledger;
CREATE DATABASE cis2232_pocket_ledger;
use cis2232_pocket_ledger;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE expenses (
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

# ALTER TABLE SkillsAssessmentSquashTechnical
#     ADD PRIMARY KEY (id);
# ALTER TABLE SkillsAssessmentSquashTechnical
#     MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
#     AUTO_INCREMENT = 1;


# CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
#                        englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                        frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                        createdDateTime datetime DEFAULT NULL,
#                        createdUserId varchar(20) DEFAULT NULL,
#                        updatedDateTime datetime DEFAULT NULL,
#                        updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT 'This tables holds the code types that are available for the application';
#
# ALTER TABLE CodeType
#     ADD PRIMARY KEY (CodeTypeId);
#
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 'Squash Technical Types', 'Squash Technical Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
#
#
#
# CREATE TABLE CodeValue (
#                            codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
#                            codeValueSequence int(3) NOT NULL,
#                            englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                            englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
#                            frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                            frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
#                            sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
#                            createdDateTime datetime DEFAULT NULL,
#                            createdUserId varchar(20) DEFAULT NULL,
#                            updatedDateTime datetime DEFAULT NULL,
#                            updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT='This will hold code values for the application.';
#
# ALTER TABLE CodeValue
#     ADD PRIMARY KEY (CodeTypeId, codeValueSequence);
#
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 1, 'Forehand Drives', 'FH Drives', 'Forehand DrivesFR', 'FH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 2, 'Backhand Drives', 'BH Drives', 'Backhand DrivesFR', 'BH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
#

