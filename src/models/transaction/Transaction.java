package models.transaction;

import models.account.Account;
import models.atm.Atm;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

public class Transaction implements Comparable<Transaction> {
    private final LocalDate date;
    private final double fee;
    private final double amount;
    private final String giverIdentification;
    private final String receiverIdentification;
    private final UUID id;

    public LocalDate getDate() {
        return this.date;
    }

    public Transaction(Account giver, Account receiver, double amount, double fee) {
        this.id = UUID.randomUUID();
        this.date = LocalDate.now();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = giver.getIBAN();
        this.receiverIdentification = receiver.getIBAN();
    }

    public Transaction(Atm atm, Account receiver, int amount, double fee) {
        this.id = UUID.randomUUID();
        this.date = LocalDate.now();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = atm.getIdentification();
        this.receiverIdentification = receiver.getIBAN();
    }

    public Transaction(Account giver, Atm receiver, int amount, double fee) {
        this.id = UUID.randomUUID();
        this.date = LocalDate.now();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = giver.getIBAN();
        this.receiverIdentification = receiver.getIdentification();
    }

    // used to load transactions from csv
    public Transaction(String id, Date date, String giver, String receiver, Double amount, Double fee) {
        this.id = UUID.fromString(id);
        this.date = date.toLocalDate();
        this.fee = fee;
        this.amount = amount;
        this.giverIdentification = giver;
        this.receiverIdentification = receiver;
    }

    public UUID getId() {
        return this.id;
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

    public double getFee() {
        return fee;

    }

    public double getAmount() {
        return amount;
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
