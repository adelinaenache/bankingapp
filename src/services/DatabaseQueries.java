package services;

public class DatabaseQueries {
    private static DatabaseQueries instance;

    private DatabaseQueries() {}

    public static DatabaseQueries getInstance() {
        if (instance == null) {
            instance = new DatabaseQueries();
        }

        return instance;
    }

    public String getAtms() {
        System.out.println("select * FROM public.\"Atm\";");
        return "select * FROM public.\"Atm\";";
    }

    public String saveAtm() {
        return "insert into public.\"Atm\" (identification, address, code, funds) VALUES (?,?,?,?);";
    }

    public String updateAtmFunds() {
        return "update public.\"Atm\" set funds = ? WHERE identification = ?";
    }

    public String deleteAtm() {
        return "delete FROM public.\"Atm\" where identification = ?";
    }

    public String getTransations() {
        return "select id, date, giverIdentification, receiverIdentification, amount, fee FROM public.\"Transaction\"";
    }

    public String deleteTransaction() {
        return "delete FROM public.\"Transaction\" WHERE id=?";
    }

    public String updateTransaction() {
        return "update  public.\"Transaction\" set date = ?, giverIdentification = ?, receiverIdentification = ?, amount = ?, fee = ? WHERE id = ?";
    }

    public String insertTransaction() {
        return "insert into public.\"Transaction\"(id, date, giverIdentification, receiverIdentification, amount, fee) VALUES (?,?,?,?,?,?)";
    }

    public String addAudit() {
        return "insert into public.\"Audit\" (action, time) VALUES (?,?);";
    }
}
