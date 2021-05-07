package models.account;

public class AccountFactory {
    public static Account createAccount(String type) {
        switch (type) {
            case "savings":
                return new SavingsAccount();
            case "business":
                return new BusinessAccount();
            case "individual":
                return new PersonalAccount();
            default:
                return null;
        }
    }

    public static Account loadAccount(String type, String IBAN, String balance) {
        switch (type) {
            case "savings":
                return new SavingsAccount(IBAN, balance);
            case "business":
                return new BusinessAccount(IBAN, balance);
            case "individual":
                return new PersonalAccount(IBAN, balance);
            default:
                return null;
        }
    }
}
