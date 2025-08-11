package main.java.dev.joaov.desafiocadastro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> questions = new ArrayList<>();

        final String FILE_PATH = "src/main/resources/formulario.txt" ;
        File file = new File(FILE_PATH);

        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                questions.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        questions.forEach(System.out::println);
    }
}
