package models.client;

import models.account.Account;
import models.account.AccountFactory;

public class BusinessClient extends Client {
    private final String businessName, CUI;

    public BusinessClient(String businessName, String CUI, String country, String city, String address, String email,
            String phoneNumber, String registrationDate) {
        super(country, city, address, email, phoneNumber, registrationDate);
        this.businessName = businessName;
        this.CUI = CUI;
    }

    public BusinessClient(String businessName, String CUI, String country, String city, String address, String email,
                          String phoneNumber) {
        super(country, city, address, email, phoneNumber);
        this.businessName = businessName;
        this.CUI = CUI;
    }

    public Account createAccount() {
        Account account = AccountFactory.createAccount("business");
        accounts.add(account);
        return account;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public String getCUI() {
        return this.CUI;

    }

    @Override
    public String toString() {
        return "BusinessClient{" +
                "businessName='" + businessName + '\'' +
                ", CUI='" + CUI + '\'' +
                ", accounts=" + accounts +
                "} " + super.toString();
    }
}