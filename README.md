# Banking application for university project at PAO

## Actions
1. Create individual client and business clients. 
2. Create one or more accounts per client. 
    2.1 Individual accounts may have savings accounts. Saving accounts do not support withdrawing, only depositing. 
    2.2 Business clients shall have business accounts, indiviudal clients individual accounts. Each account type has it's own fees.
3. Create one or more cards per account. 
    3.1 Cards are valid one year, afterwards they expire. 
4. Create ATMS. 
5. Deposit funds in an ATM as the "bank". The atm must be "opened" using the code that was set when the atm was created  
6. Deposit funds in an account, as the client, using a card and it's pin. The card must be active and not expired. Also, the pin must match.
7. Withdraw money from atm using a card. Same constraints for cards as above.
8. Make a transaction between 2 accounts. Register the transaction. 
9. Deactivate card
10. List all transactions for a specific account
   

## Objects
1. IndividualClient 
2. BusinessClient
3. IndividualAccount 
4. BusinessAccount
5. SavingsAccount 
6. Atm 
7. Card 
8. Transaction 


