package models.atm;

public class Atm {
    private final String address;
    private int funds = 0;
    private final String openCode;
    public final String identification;


    public Atm(String identification, String address, String openCode) {
        this.identification = identification;
        this.address = address;
        this.openCode = openCode;
    }

    public String getIdentification() {
        return this.identification;
    }

    public int getFunds() {
        return funds;
    }

    private void setFunds(int funds) {
        this.funds = funds;
    }

    private String getOpenCode()  {
        return openCode;
    }


    public boolean hasFunds(int amount) {
       return  amount <= this.getFunds();
    }

    public void withdraw(int amount) throws Exception {
        // should check first if the atm hasFunds(). Add the error handling for safety.
        if (this.getFunds() < amount) {
            throw new Exception("Not enough funds in ATM.");
        }

        this.setFunds(this.getFunds() - amount);
    }

    public void deposit(int amount) {
        this.setFunds(this.getFunds() + amount);
    }

    public boolean is(String identification) {
        return  this.getIdentification() == identification;
    }


    public boolean canOpen(String openCode) {
        return this.getOpenCode() == openCode;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "address='" + address + '\'' +
                ", funds=" + funds +
                ", openCode='" + openCode + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }
}
