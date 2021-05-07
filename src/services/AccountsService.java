// use a singleton because i want the same accounts persisted everywhere
package services;

import models.account.Account;
import models.account.AccountFactory;
import models.client.Client;

import java.io.FileNotFoundException;
import java.util.*;

public class AccountsService {
    private final List<Account> accounts = new ArrayList<Account>();
    private static AccountsService instance = null;
    private final ReaderService reader;
    private final AuditService audit;

    private AccountsService() {
        this.reader = ReaderService.getInstance();
        this.audit = AuditService.getInstance();

        this.loadData();
    };

    public static AccountsService getInstance() {
        if (instance == null) {
            instance = new AccountsService();
        }
        return instance;
    }

    private void loadData() {
        try {
            List<List<String>> data = this.reader.read("src/data/accounts.csv");
            for (List<String> row: data) {
                accounts.add(AccountFactory.loadAccount(row.get(0), row.get(1), row.get(2)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void createAccount(Client client) {
        Account account = client.createAccount();
        account.createCard();
        audit.logAction("Action 2: Create account");
        accounts.add(account);
    }

    public Account getAccountByCardNumber(String cardNumber) throws Exception {
        for (Account acc: accounts) {
            if (acc.hasCard(cardNumber)) {
                return acc;
            }
        }

        throw new Exception("Account not found.");
    }

    public Account getAccount(String IBAN) throws Exception {
        for (Account acc: accounts) {
            if (acc.getIBAN().equals(IBAN)) {
                return acc;
            }
        }

        throw new Exception("Account not found.");
    }

}
