package main.java.dev.joaov.desafiocadastro.service;

import main.java.dev.joaov.desafiocadastro.model.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<String> readAndSave(File file) {
        List<String> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static String saveFile(File file, Pet pet) {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            writer.write(pet.toStringFile());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Arquivo %s salvo com sucesso.".formatted(file.getName());
    }
}
