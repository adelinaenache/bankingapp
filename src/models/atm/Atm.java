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

    public Atm(String identification, String address, String openCode, int funds) {
        this.identification = identification;
        this.address = address;
        this.openCode = openCode;
        this.funds = funds;
    }

    public String getIdentification() {
        return this.identification;
    }

    public int getFunds() {
        return funds;
    }

    public String getAddress() {
        return this.address;
    }



    public void setFunds(int funds) {
        this.funds = funds;
    }

    public String getOpenCode()  {
        return openCode;
    }

    public boolean hasFunds(int amount) {
       return  amount <= this.getFunds();
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
