INSERT INTO employees (first_name, middle_name, last_name, position, date_of_birth) 
VALUES ('John', 'Quincy', 'Adams', 'Manager', '1985-07-12');

INSERT INTO employees (first_name, middle_name, last_name, position, date_of_birth) 
VALUES ('Jane', NULL, 'Doe', 'Developer', '1992-03-25');

INSERT INTO employees (first_name, middle_name, last_name, position, date_of_birth) 
VALUES ('Robert', 'Bruce', 'Wayne', 'CEO', '1970-02-19');


INSERT INTO compensation (employee_id, type, amount, description, payment_date)
VALUES (1, 'SALARY', 5000.00, NULL, CURRENT_DATE);

INSERT INTO compensation (employee_id, type, amount, description, payment_date)
VALUES (1, 'BONUS', 1200.50, 'Annual Performance Bonus', CURRENT_DATE);

INSERT INTO compensation (employee_id, type, amount, description, payment_date)
VALUES (2, 'ADJUSTMENT', 450.00, 'Project Launch Weekend', '2024-01-15');

INSERT INTO compensation (employee_id, type, amount, description, payment_date)
VALUES (3, 'COMMISSION', 2500.00, 'Q4 Sales Target Achieved', '2024-01-30');

