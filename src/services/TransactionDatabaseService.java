package services;
import models.transaction.Transaction;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionDatabaseService {
    private final DatabaseService db;
    private final DatabaseQueries queries;

    private PreparedStatement saveTransaction = null;
    private PreparedStatement updateTransaction = null;
    private static TransactionDatabaseService instance = null;

    private TransactionDatabaseService() {
        this.db = DatabaseService.getInstance();
        this.queries = DatabaseQueries.getInstance();

        try {
            this.saveTransaction = this.db.getPreparedStatement(this.queries.insertTransaction());
            this.updateTransaction = this.db.getPreparedStatement(this.queries.updateTransaction());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static TransactionDatabaseService getInstance() {
        if (instance == null) {
            instance = new TransactionDatabaseService();
        }
        return instance;
    }

    public List<Transaction> get() {
        List<Transaction> trans = new ArrayList<>();
        try {
            PreparedStatement getAtms = this.db.getPreparedStatement(this.queries.getTransations());
            ResultSet res = getAtms.executeQuery();

            while (res.next()) {
                trans.add(new Transaction(res.getString(1), res.getDate(2), res.getString(3), res.getString(4), res.getDouble(5), res.getDouble(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return trans;
    }

    public void save(Transaction transaction) {
        if (this.saveTransaction != null) {
            try {
                this.saveTransaction.setString(1, transaction.getId().toString());
                this.saveTransaction.setDate(2, Date.valueOf(transaction.getDate()));
                this.saveTransaction.setString(3, transaction.getGiverIdentification());
                this.saveTransaction.setString(4, transaction.getReceiverIdentification());
                this.saveTransaction.setDouble(5, transaction.getAmount());
                this.saveTransaction.setDouble(6, transaction.getFee());
                this.saveTransaction.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("cannot save transaction becasue prepared statement not found" );
        }
    }

    public void update(Transaction transaction) {
        if (this.updateTransaction != null) {
            try {
                this.updateTransaction.setString(6, transaction.getId().toString());
                this.updateTransaction.setDate(1, (Date) java.util.Date.from(transaction.getDate()
                        .atStartOfDay(
                                ZoneId.of( "Europe/Bucharest" )
                        )
                        .toInstant()));
                this.updateTransaction.setString(2, transaction.getGiverIdentification());
                this.updateTransaction.setString(3, transaction.getReceiverIdentification());
                this.updateTransaction.setDouble(4, transaction.getAmount());
                this.updateTransaction.setDouble(5, transaction.getFee());
                this.updateTransaction.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("cannot save transaction becasue prepared statement not found" );
        }
    }

    public void delete(UUID id) {
        try {
            PreparedStatement deleteAtms = this.db.getPreparedStatement(this.queries.deleteTransaction());
            deleteAtms.setString(1, id.toString());

        } catch (SQLException throwables) {
            System.out.println("cannot delete trans from db: " + throwables.getMessage());
        }

    }

}
