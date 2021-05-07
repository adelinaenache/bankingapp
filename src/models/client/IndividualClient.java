package models.client;

import models.account.Account;
import models.account.AccountFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class IndividualClient extends Client {
    private final String firstName, lastName, cnp;

    public IndividualClient(String firstName, String lastName, String cnp, String address, String country, String city,
            String email, String phoneNumber) {
        super(country, city, address, email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
    }

    public IndividualClient(String firstName, String lastName, String cnp, String address, String country, String city,
                            String email, String phoneNumber, String registrationDate) {
        super(country, city, address, email, phoneNumber, registrationDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCnp() {
        return this.cnp;
    }

    public Account createSavingsAccount() {
        Account account = AccountFactory.createAccount("savings");
        accounts.add(account);
        return account;
    }

    public Account createAccount() {
        Account account = AccountFactory.createAccount("individual");
        accounts.add(account);
        return account;
    }
}
