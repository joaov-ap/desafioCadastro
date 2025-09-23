package main.java.dev.joaov.desafiocadastro;

import main.java.dev.joaov.desafiocadastro.model.Pet;
import main.java.dev.joaov.desafiocadastro.service.PetService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();
        PetService petService = new PetService();
        int userInput;

        do {
            System.out.println();
            menu();
            userInput = userInput();

            switch (userInput) {
                case 1:
                    Pet pet = petService.registerPet();
                    pets.add(pet);
                    break;
                case 2:
                    System.out.println("Alterando dados do pet...");
                    break;
                case 3:
                    System.out.println("Deletando pet...");
                    break;
                case 4:
                    System.out.println("Listando todos pets...");
                    break;
                case 5:
                    System.out.println("Listando pets por idade, nome, raça...");
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor inválido, digite um número entre 1 e 6.");
            }
        } while (userInput != 6);
    }

    public static void menu() {
        System.out.println("""
                1 - Cadastrar um novo pet
                2 - Alterar os dados do pet cadastrado
                3 - Deletar um pet cadastrado
                4 - Listar todos os pets cadastrados
                5 - Listar pets por algum critério (idade, nome, raça)
                6 - Sair
                """);
    }

    public static int userInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            int userInput = scanner.nextInt();
            return userInput;
        } catch (InputMismatchException e) {
            System.out.println("O sistema aceita apenas números.");
        }

        return 0;
    }
}
