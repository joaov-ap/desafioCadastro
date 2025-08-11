package main.java.dev.joaov.desafiocadastro;

import main.java.dev.joaov.desafiocadastro.service.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> questions = new ArrayList<>();
        FileHandler.reader(questions);
        questions.forEach(System.out::println);
    }
}
