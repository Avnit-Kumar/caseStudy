
CREATE TABLE IF NOT EXISTS employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) DEFAULT NULL,
    last_name VARCHAR(50) NOT NULL,
    position VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    
    CONSTRAINT uk_employee_identity UNIQUE (first_name, middle_name, last_name, date_of_birth)
);

CREATE TABLE IF NOT EXISTS compensation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    comp_type VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    description TEXT,
    payment_date DATE NOT NULL,

    CONSTRAINT chk_comp_type CHECK (
        comp_type IN ('Salary', 'Bonus', 'Commission', 'Allowance', 'Adjustment')
    ),

    CONSTRAINT chk_description_required CHECK (
        comp_type = 'Salary' OR description IS NOT NULL
    ),

    CONSTRAINT fk_employee_comp 
        FOREIGN KEY (employee_id) REFERENCES employees(id) 
        ON DELETE CASCADE
);
