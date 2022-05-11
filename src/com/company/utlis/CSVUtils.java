package com.company.utlis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<String> read(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(fileName + " invalid");
        }
    }

    public static <T> void write(String fileName, List<T> items){
        try {
            PrintWriter printWriter = new PrintWriter(fileName);

            for(Object item : items){
                printWriter.write(item.toString());
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
