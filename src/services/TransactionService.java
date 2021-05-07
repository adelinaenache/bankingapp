// use a singleton because i want to have the same transactions list persisted everywhere
package services;

import models.account.Account;
import models.transaction.Transaction;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TransactionService {
    private static TransactionService instance = null;
    private final TreeSet<Transaction> transactions = new TreeSet<>();
    private final AccountsService accountsService;
    private final ReaderService reader;
    private final AuditService audit;

    private TransactionService(){
        this.accountsService = AccountsService.getInstance();
        this.reader = ReaderService.getInstance();
        this.audit = AuditService.getInstance();
        this.loadData();
    };

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }

        return instance;
    }

    private void loadData() {
        try {
            List<List<String>> data = this.reader.read("src/data/transactions.csv");

            for (List<String> row: data) {
                transactions.add(new Transaction(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void makeTransaction(String giverIBAN, String receiverIBAN, double amount) throws Exception {
        this.audit.logAction("Action 8: Make transaction");
        Account giverAccount = this.accountsService.getAccount(giverIBAN);
        Account receiverAccount = this.accountsService.getAccount(receiverIBAN);

        // check that giver has enough money in account, including taxes
        if (!giverAccount.canTransfer(amount)) {
            throw new Exception("Cannot transfer " +  amount +". Not enough funds.");
        }

        giverAccount.extractTransferAmount(amount);
        receiverAccount.receiveTransferMoney(amount);
        transactions.add(new Transaction(giverAccount, receiverAccount, amount, giverAccount.getTransactionFee(amount)));
    }


    public List<Transaction> getClientTransactions(String IBAN) throws Exception {
        Account account = this.accountsService.getAccount(IBAN);
        this.audit.logAction("Action 10: List all transactions");

        return transactions.stream().filter(t -> t.getGiverIdentification().equals(account.getIBAN()) || t.getReceiverIdentification().equals(account.getIBAN())).collect(Collectors.toList());
    }
}
