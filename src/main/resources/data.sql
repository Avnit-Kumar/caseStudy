-- data.sql

-- 1) Seed employee only if not exists
INSERT INTO employees (first_name, middle_name, last_name, position, date_of_birth)
SELECT 'John', 'Quincy', 'Adams', 'Manager', '1985-07-12'
WHERE NOT EXISTS (
  SELECT 1 FROM employees
  WHERE first_name='John'
    AND middle_name='Quincy'
    AND last_name='Adams'
    AND date_of_birth='1985-07-12'
);

-- 2) Seed a compensation row only if not already present for today
INSERT INTO compensation (employee_id, comp_type, amount, description, payment_date)
SELECT e.id, 'Salary', 5000.00, NULL, CURRENT_DATE
FROM employees e
WHERE e.first_name='John'
  AND e.middle_name='Quincy'
  AND e.last_name='Adams'
  AND e.date_of_birth='1985-07-12'
  AND NOT EXISTS (
    SELECT 1
    FROM compensation c
    WHERE c.employee_id = e.id
      AND c.comp_type   = 'Salary'
      AND c.payment_date = CURRENT_DATE
  );