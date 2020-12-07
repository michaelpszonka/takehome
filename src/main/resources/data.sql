INSERT INTO debt (id, amount) VALUES (0, 123.46);
INSERT INTO debt (id, amount) VALUES (1, 100);
INSERT INTO debt (id, amount) VALUES (2, 4920.34);
INSERT INTO debt (id, amount) VALUES (3, 12938);
INSERT INTO debt (id, amount) VALUES (4, 9238.02);

INSERT INTO paymentplan (id, amount_to_pay, installment_frequency,
                         installment_amount, start_date, debt_id)
                         VALUES(0, 102.5, 'WEEKLY', 51.25, '2020-09-28', 0);

INSERT INTO paymentplan (id, amount_to_pay, installment_frequency,
                         installment_amount, start_date, debt_id)
                        VALUES(1, 100, 'WEEKLY', 25, '2020-08-01', 1);

INSERT INTO paymentplan (id, amount_to_pay, installment_frequency,
                         installment_amount, start_date, debt_id)
                        VALUES(2, 4920.34, 'BI_WEEKLY', 1230.085, '2020-01-01', 2);

INSERT INTO paymentplan (id, amount_to_pay, installment_frequency,
                         installment_amount, start_date, debt_id)
                        VALUES(3, 4312.67, 'WEEKLY', 1230.085, '2020-08-01', 3);

INSERT into payment (payment_plan_id, amount, date) VALUES(0, 51.25, '2020-09-29');
INSERT into payment (payment_plan_id, amount, date) VALUES(0, 51.25, '2020-10-29');
INSERT into payment (payment_plan_id, amount, date) VALUES(1, 25, '2020-08-08');
INSERT into payment (payment_plan_id, amount, date) VALUES(1, 25, '2020-08-08');
INSERT into payment (payment_plan_id, amount, date) VALUES(2, 4312.67, '2020-08-08');
INSERT into payment (payment_plan_id, amount, date) VALUES(3, 1230.05, '2020-01-02');
INSERT into payment (payment_plan_id, amount, date) VALUES(3, 1230.05, '2020-01-15');
INSERT into payment (payment_plan_id, amount, date) VALUES(3, 1230.05, '2020-02-14');