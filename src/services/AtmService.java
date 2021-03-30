package services;

import models.account.Account;
import models.atm.Atm;
import models.card.Card;

import java.util.ArrayList;
import java.util.List;

public class AtmService {
    private final List<Atm> atms = new ArrayList<Atm>();
    private final AccountsService accountsService;

    public AtmService() {
        this.accountsService = AccountsService.getInstance();
    }

    public void createAtm(String identificationName, String address, String openCode) {
        Atm atm = new Atm(identificationName, address, openCode);
        atms.add(atm);
    }

    public Atm getAtm(String identification) throws Exception {
        for(Atm atm: atms) {
            if (atm.is(identification)) {
                return atm;
            }
        }
        throw new Exception("Atm " + identification + " not found");
    }

    // deposit money in atm, using the open code that only bank has access to
    public void deposit(String identification, String openCode, int amount) throws Exception {
        Atm atm = this.getAtm(identification);

        if (!atm.canOpen(openCode)) {
            throw  new Exception("Cannot open ATM");
        }

        atm.deposit(amount);
    }


    public void withdraw(String atmIdentification, String cardNumber, String pin, int amount) throws Exception {
        Atm atm = this.getAtm(atmIdentification);
        Account account = this.accountsService.getAccountByCardNumber(cardNumber);

        if (!atm.hasFunds(amount)) {
            throw new Error("Not enough funds in ATM. Please try again later!");
        }
        account.withdraw(cardNumber, pin, amount);
        atm.withdraw(amount);
    }

    public void depositInAccount(String atmIdentification, String cardNumber, String pin, int amount) throws Exception {
        Atm atm = this.getAtm(atmIdentification);
        Account account = this.accountsService.getAccountByCardNumber(cardNumber);

        account.deposit(cardNumber, pin, amount);
        atm.deposit(amount);
    }

}
