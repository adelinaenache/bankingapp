package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class AuditDatabase {
    private final DatabaseService db;
    private final DatabaseQueries queries;
    private static AuditDatabase instance = null;
    private PreparedStatement addAudit = null;


    private AuditDatabase() {
        this.db = DatabaseService.getInstance();
        this.queries = DatabaseQueries.getInstance();

        try {
            this.addAudit = this.db.getPreparedStatement(this.queries.addAudit());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static AuditDatabase getInstance() {
        if (instance == null) {
            instance = new AuditDatabase();
        }

        return instance;
    }

    public void addAction(String action) {
        if (this.addAudit != null) {
            try {
                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
                this.addAudit.setString(1, action);
                this.addAudit.setTimestamp(2, currentTimestamp);
                this.addAudit.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            System.out.println("cannot add action because prepared statement not intialized");
        }
    }

}
