package main.java.dev.joaov.desafiocadastro.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "src/main/resources/formulario.txt";
    private static final File FILE = new File(FILE_PATH);

    public static void reader(List<String> questions) {
        try (FileReader fileReader = new FileReader(FILE);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                questions.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
