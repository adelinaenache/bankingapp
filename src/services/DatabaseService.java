package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseService {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/bankingPAO";

    private final Connection conn;
    private static DatabaseService instance;

    private DatabaseService() {
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user","root");
            props.setProperty("password","root");
            conn = DriverManager.getConnection(DB_URL, props);
        }

        catch (SQLException exception) {
            throw new RuntimeException("Error at initializing conn: " + exception);
        }

        catch (ClassNotFoundException exception) {
            throw new RuntimeException("JBDC driver not found: " + exception);
        }
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }

        return instance;
    }

        public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

}
