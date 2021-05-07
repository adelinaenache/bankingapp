package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReaderService {
    private static ReaderService instance = null;
    private ReaderService() {
    }

    public static ReaderService getInstance() {
        if (instance == null) {
            instance = new ReaderService();
        }

        return instance;
    }


    public List<List<String>> read(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        List<List<String>> data = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> items = Arrays.asList(line.split(","));
            data.add(items);
        }

        scanner.close();
        return data;
    }
}
