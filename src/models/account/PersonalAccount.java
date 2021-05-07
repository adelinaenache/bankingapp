package models.account;

public class PersonalAccount extends Account {
    private final int withdrawFeePercent;
    private final int depositFee;

    PersonalAccount() {
        super();

        this.withdrawFeePercent = 5;
        this.depositFee = 1;
    }

    public PersonalAccount(String IBAN, String balance) {
        super(IBAN, balance);

        this.withdrawFeePercent = 5;
        this.depositFee = 1;
    }

    public double getWithdrawFee(int amount) {
        return amount * this.withdrawFeePercent / 100;
    }

    public double getDepositFee(int amount) {
        return this.depositFee;
    }

    @Override
    public double getTransactionFee(double amount) {
        return 0;
    }

    @Override
    public String toString() {
        return "PersonalAccount{" +
                "IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                ", cards=" + cards +
                "} " + super.toString();
    }
}