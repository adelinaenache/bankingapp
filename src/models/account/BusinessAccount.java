package models.account;

public class BusinessAccount extends Account {
    private int withdrawFee;
    private int depositFeePercent;

    BusinessAccount() {
        super();

        this.withdrawFee = 20;
        this.depositFeePercent = 1;

        System.out.println("CREATED BUSINESS ACCOUNT");
    }

    public BusinessAccount(String IBAN, String balance) {
        super(IBAN, balance);

        this.withdrawFee = 20;
        this.depositFeePercent = 1;
    }

    public double getWithdrawFee(int amount) {
        return withdrawFee;
    }

    public double getDepositFee(int amount) {
        return amount * this.depositFeePercent / 100;
    }

    public double getTransactionFee(double amount) {
        return 5;
    }

    @Override
    public String toString() {
        return "BusinessAccount{" +
                "IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                ", cards=" + cards +
                "} " + super.toString();
    }
}