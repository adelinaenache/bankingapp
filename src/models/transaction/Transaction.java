package models.transaction;

import models.account.Account;
import models.atm.Atm;

import java.time.LocalDate;
import java.util.Comparator;

public class Transaction implements Comparable<Transaction> {
    private final LocalDate date;
    private final double fee;
    private final double amount;
    private final String giverIdentification;
    private final String receiverIdentification;

    public LocalDate getDate() {
        return this.date;
    }

    public Transaction(Account giver, Account receiver, double amount, double fee) {
        this.date = LocalDate.now();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = giver.getIBAN();
        this.receiverIdentification = receiver.getIBAN();
    }

    public Transaction(Atm atm, Account receiver, int amount, double fee) {
        this.date = LocalDate.now();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = atm.getIdentification();
        this.receiverIdentification = receiver.getIBAN();
    }

    public Transaction(Account giver, Atm receiver, int amount, double fee) {
        this.date = LocalDate.now();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = giver.getIBAN();
        this.receiverIdentification = receiver.getIdentification();
    }

    // used to load transactions from csv
    public Transaction(String giver, String receiver, String amount, String fee, String date) {
        this.date = LocalDate.parse(date);
        this.fee = Double.parseDouble(fee);
        this.amount = Double.parseDouble(amount);
        this.giverIdentification = giver;
        this.receiverIdentification = receiver;
    }


    @Override
    public int compareTo(Transaction o2) {
        return this.getDate().compareTo(o2.getDate());
    }

    public String getGiverIdentification() {
        return giverIdentification;
    }

    public String getReceiverIdentification() {
        return receiverIdentification;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", fee=" + fee +
                ", amount=" + amount +
                ", giverIdentification='" + giverIdentification + '\'' +
                ", receiverIdentification='" + receiverIdentification + '\'' +
                '}';
    }
}
