This is a Spring Boot Command Line Debt calculation Service leveraging the following three endpoints

Debt API:  https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts
Payment Plan API:  https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans
Payments API : https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments

To run the application you should only need to run a maven install to gather all of the dependencies

The Main method is within TakehomeApplication.java, and the test cases are within DebtServiceTest.java

Notes
Most time working on gathering each entity into data structures that allowed for efficent access and searching using 
HashMaps and lists allowing for constant access time.  With more time,I would have liked to solidify some of the business
requirements around calculating the amount of debt paid off. For example.
 
 1. How to handle discrepancies between payment plans "amount_to_pay" and debts "amounts" of different quantities.
 2.   How should the next payment date be calculated.  From the most recent payment date, or from todays date? 