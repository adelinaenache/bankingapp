package models.client;

import models.account.Account;

import java.time.LocalDate;
import java.util.*;

public abstract class Client {
    protected List<Account> accounts = new ArrayList<Account>();
    private final String email, phoneNumber, address, city, country;
    private final LocalDate registrationDate;

    Client(String country, String city, String address, String email, String phoneNumber) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = LocalDate.now();
    }

    Client(String country, String city, String address, String email, String phoneNumber, String registrationDate) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = LocalDate.parse(registrationDate);
    }


    public abstract Account createAccount();

    // used for showcase purposes
    public Account getFirstAccount() {
        return accounts.get(0);
    }

    @Override
    public String toString() {
        return "Client{" +
                "accounts=" + accounts +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
