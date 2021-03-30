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
}
