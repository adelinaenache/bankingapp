// use a singleton because i want the same accounts persisted everywhere
package services;

import models.account.Account;
import models.client.Client;

import java.util.*;

public class AccountsService {
    private final List<Account> accounts = new ArrayList<Account>();
    private static AccountsService instance = null;

    private AccountsService() {};

    public static AccountsService getInstance() {
        if (instance == null) {
            instance = new AccountsService();
        }
        return instance;
    }

    public void createAccount(Client client) {
        Account account = client.createAccount();
        account.createCard();
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
