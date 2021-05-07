package services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WriterService {
    private static WriterService instance = null;

    private WriterService() {}

    public static WriterService getInstance() {
        if (instance == null) {
            instance = new WriterService();
        }
        return instance;
    }


    public void write(String path, String writeData, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);

        fileWriter.write(writeData + "\n");

        fileWriter.flush();
        fileWriter.close();
    }
}