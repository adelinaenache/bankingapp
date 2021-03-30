package models.card;

import utils.RandomGenerator;

import java.time.LocalDate;

public class Card {
    // all are strings because they can start with "0". We never actually use them
    // as numbers.
    final Integer NUMBER_DIGITS = 16;
    final Integer CCV_DIGITS = 3;
    final Integer PIN_DIGITS = 4;

    private final String ccv;
    private final String number;
    private final String pin;
    private boolean isActive;
    private final LocalDate expirationDate;

    /// MAYBE TODO: generate actual card number using real rules

    public Card() {
        this.pin = RandomGenerator.generateRandomString(PIN_DIGITS);
        this.number = RandomGenerator.generateRandomString(NUMBER_DIGITS);
        this.ccv = RandomGenerator.generateRandomString(CCV_DIGITS);
        this.isActive = true;
        this.expirationDate = LocalDate.now().plusYears(1);
    }

    private boolean isValid() {
        if (this.isActive && LocalDate.now().isBefore(this.expirationDate)) {
            return true;
        }
        return false;
    }

    public boolean hasAcces(String pin) {
        return this.isValid() && this.pin == pin;
    }

    public void deactivate() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Card{" +
                ", ccv='" + ccv + '\'' +
                ", number='" + number + '\'' +
                ", pin='" + pin + '\'' +
                ", isActive=" + isActive +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public String getNumber() {
        return number;
    }

    // normally this one should be private, but i use it for demo purposes.
    public String getPin() {
        return this.pin;
    }

    public boolean hasNumber(String cardNumber) {
        return this.getNumber() == cardNumber;
    }
}