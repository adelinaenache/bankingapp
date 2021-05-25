package services;

import models.atm.Atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtmDatabaseService {
    private final DatabaseService db;
    private final DatabaseQueries queries;
    private PreparedStatement saveAtm = null;
    private PreparedStatement updateAtmFunds = null;
    private static AtmDatabaseService instance = null;

    private AtmDatabaseService() {
        this.db = DatabaseService.getInstance();
        this.queries = DatabaseQueries.getInstance();
        try {
            this.saveAtm = this.db.getPreparedStatement(this.queries.saveAtm());
            this.updateAtmFunds = this.db.getPreparedStatement(this.queries.updateAtmFunds());
        } catch (SQLException throwables) {
            System.out.print("Error while creating save atm statement" + throwables.getMessage());
        }
    }

    public static AtmDatabaseService getInstance() {
        if (instance == null) {
            instance = new AtmDatabaseService();
        }

        return instance;
    }


    public void updateAtm(String identificationName, int funds) {
        if (this.updateAtmFunds != null) {
            try {
                this.updateAtmFunds.setInt(1, funds);
                this.updateAtmFunds.setString(2, identificationName);
                this.updateAtmFunds.executeUpdate();
            } catch (SQLException throwables) {
                System.out.println("Update ATM funds" + throwables.getMessage());
            }
        } else {
            System.out.println("Cannot update atm because prepared query can't be found");
        }
    }

    public List<Atm> getAtms() {
        List<Atm> atms = new ArrayList<>();
        try {
            PreparedStatement getAtms = this.db.getPreparedStatement(this.queries.getAtms());
            ResultSet res = getAtms.executeQuery();

            while (res.next()) {
                atms.add(new Atm(res.getString(1), res.getString(2), res.getString(3), res.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return atms;
    }

    public void createAtm(Atm atm) {
        try {
            if (this.saveAtm != null) {
                this.saveAtm.setString(1, atm.getIdentification());
                this.saveAtm.setString(2, atm.getAddress());
                this.saveAtm.setString(3, atm.getOpenCode());
                this.saveAtm.setInt(4, atm.getFunds());

                this.saveAtm.executeUpdate();
            } else {
                System.out.println("Cannot save atm because prepared query can't be found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAtm(String identification) {
        try {
            PreparedStatement deleteAtms = this.db.getPreparedStatement(this.queries.deleteAtm());
            deleteAtms.setString(1, identification);
            deleteAtms.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("cannot delete atm from db: " + throwables.getMessage());
        }
    }



}
