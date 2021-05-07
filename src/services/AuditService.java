package services;


import java.io.IOException;
import java.time.LocalDate;

public class AuditService {
    private final WriterService writer;
    private static AuditService instance = null;

    private AuditService() {
        this.writer = WriterService.getInstance();
    }

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logAction(String type) {
        String data = type + ',' + System.currentTimeMillis();
        try {
            this.writer.write("src/data/audit.csv", data,true);
        } catch (IOException e) {
            System.out.println("Cannot log action");
            e.printStackTrace();
        }
    }
}
