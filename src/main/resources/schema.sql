CREATE TABLE debt (
    id INTEGER NOT NULL,
    amount REAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE paymentplan (
    id INTEGER NOT NULL,
    amount_to_pay REAL NOT NULL,
    installment_frequency VARCHAR (9) NOT NULL,
    installment_amount REAL NOT NULL,
    start_date DATE NOT NULL,
    debt_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (debt_id) REFERENCES debt(id)
);

CREATE TABLE payment (
    payment_plan_id INTEGER NOT NULL,
    amount REAL NOT NULL,
    date DATE NULL,
    FOREIGN KEY (payment_plan_id) REFERENCES paymentplan(id)
);