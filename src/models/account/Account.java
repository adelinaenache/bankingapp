package models.account;

import models.card.Card;
import models.transaction.Transaction;
import services.AuditService;
import utils.RandomGenerator;

import java.util.*;

public abstract class Account {
    protected final String IBAN;
    protected double balance;
    protected final List<Card> cards = new ArrayList<>();

    abstract public double getWithdrawFee(int amount);

    abstract public double getDepositFee(int amount);

    abstract public double getTransactionFee(double amount);

    private double getWithdrawAmountWithFees(int amount) {
        return amount + getWithdrawFee(amount);
    }

    private double getDepositAmountWithFees(int amount) {
        return amount - getDepositFee(amount);
    }

    private double getTransferAmountWithFees(double amount) { return amount + getTransactionFee(amount); }

    public Account() {
        this.IBAN = RandomGenerator.generateRandomString(16);
    }

    public Account(String IBAN, String balance) {
        this.IBAN = IBAN;
        this.balance = Double.parseDouble(balance);
    }

    // used for showcase purposes. Normally, you shouldn't have access to cards.
    public Card getFirstCard() {
        return cards.get(0);
    }

    public void withdraw(String cardNumber, String pin, int amount) throws Exception {
        Card card = this.getCard(cardNumber);

        if (!card.hasAcces(pin)) {
            throw new Exception("Invalid access code");
        }

        double totalAmount = this.getWithdrawAmountWithFees(amount);

        if (this.balance < totalAmount) {
            throw new Exception("Not enough balance.");
        }

        balance -= totalAmount;
    }

    public void deposit(String cardNumber, String pin, int amount) throws Exception {
        Card card = this.getCard(cardNumber);

        if (!card.hasAcces(pin)) {
            throw new Exception("Invalid access code");
        }

        double totalAmount = this.getDepositAmountWithFees(amount);
        this.balance += totalAmount;
    }

    public void createCard() {
        AuditService audit = AuditService.getInstance();
        audit.logAction("Action 3: Add card");
        cards.add(new Card());
    }


    public Card getCard(String cardNumber) throws Exception {
        for (Card card: cards) {
            if (card.hasNumber(cardNumber)) {
                return card;
            }
        }

        throw new Exception("Card " + cardNumber + " not found.");
    }


    public boolean hasCard(String cardNumber) {
        for (Card card: cards) {
            if (card.getNumber().equals(cardNumber)) {
                return true;
            }
        }

        return false;
    }
    public String getIBAN() {
        return this.IBAN;
    }

    public boolean canTransfer(double amount) {
        return this.getTransferAmountWithFees(amount) <= balance;
    }

    public void extractTransferAmount(double amount) throws Exception {
        double newBalance =  balance -= getTransferAmountWithFees(amount);
        if (newBalance < 0) {
            throw new Exception("Cannot extract " + amount + ". Insuficient funds.");
        }
        balance -= getTransferAmountWithFees(amount);
    }

    public void setBalance(double b) throws Exception {
        if (b < 0) {
            throw new Exception("Balance cannot be less than 0");
        }
        this.balance = b;
    }


    public void receiveTransferMoney(double amount) {
        balance += amount;
    }

}