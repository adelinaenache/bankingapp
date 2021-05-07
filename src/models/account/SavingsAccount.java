package models.account;

public class SavingsAccount extends Account {
    SavingsAccount() {
        super();
    }

    public SavingsAccount(String IBAN, String balance) {
        super(IBAN, balance);
    }


    public double getWithdrawFee(int amount) {
        return 0;
    }


    // for every deposit you get 1% extra
    public double getDepositFee(int amount) {
        return -amount / 100;
    }

    @Override
    public double getTransactionFee(double amount) {
        return 0;
    }

    @Override
    public void withdraw(String cardNumber, String pin, int amount) throws Exception {
        throw new Exception("Cannot withdraw from savings account");
    }

    @Override
    public boolean canTransfer(double amount) {
        return false;
    }

    public void extractTransferAmount(double amount) throws Exception {
        throw new Error("Cannot transfer from savings account!");
    }
}
