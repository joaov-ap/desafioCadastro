package main.java.dev.joaov.desafiocadastro.service;

import main.java.dev.joaov.desafiocadastro.model.Pet;
import main.java.dev.joaov.desafiocadastro.model.PetSex;
import main.java.dev.joaov.desafiocadastro.model.PetType;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetService {
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_PATH = "src/main/resources/formulario.txt";
    private final File FILE = new File(FILE_PATH);
    private List<String> questions = new ArrayList<>(FileHandler.readAndSave(FILE));
    private final String EMPTY_INPUT = "NÃO INFORMADO";

    public Pet registerPet() {
        Pet pet = new Pet();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));

            if (i == 0) {
                Pattern pattern = Pattern.compile("[^a-zA-Z]");

                System.out.print("Digite o nome: ");
                String name = scanner.nextLine();

                Matcher matcher = pattern.matcher(name);
                if (name.trim().isEmpty() || name == null) {
                    throw new IllegalArgumentException("Nome não deve ser vazio");
                }

                if (matcher.find()) {
                    System.out.println("Nome não deve ter caracteres especiais");
                    System.out.print("Digite o nome: ");
                    name = scanner.nextLine();
                }
                pet.setName(name);

                System.out.print("Digite o sobrenome: ");
                String surName = scanner.nextLine();
                if (surName.trim().isEmpty() || surName == null) {
                    throw new IllegalArgumentException("Sobrenome não pode ser vazio");
                }

                matcher = pattern.matcher(surName);
                if (matcher.find()) {
                    System.out.println("Sobrenome não deve ter caracteres especiais");
                    System.out.print("Digite o sobrenome: ");
                    surName = scanner.nextLine();
                }
                pet.setSurname(surName);
            }

            if (i == 1) {
                printEnums(PetType.values());
                System.out.print("Digite sua escolha entre 1 e " + PetType.values().length + ": ");
                int petInput = scanner.nextInt() - 1;
                scanner.nextLine();

                while (petInput >= PetType.values().length || petInput < 0) {
                    System.out.println();
                    printEnums(PetType.values());
                    System.out.print("Digite sua escolha entre 1 e " + PetType.values().length + ": ");
                    petInput = scanner.nextInt() - 1;
                    scanner.nextLine();
                }

                pet.setPetType(PetType.values()[petInput]);
            }

            if (i == 2) {
                printEnums(PetSex.values());
                System.out.print("Digite sua escolha entre 1 e " + PetSex.values().length + ": ");
                int petInput = scanner.nextInt() - 1;
                scanner.nextLine();

                while (petInput >= PetSex.values().length || petInput < 0) {
                    System.out.println();
                    printEnums(PetSex.values());
                    System.out.print("Digite sua escolha entre 1 e " + PetSex.values().length + ": ");
                    petInput = scanner.nextInt() - 1;
                    scanner.nextLine();
                }

                pet.setPetSex(PetSex.values()[petInput]);
            }

            if (i == 3) {
                System.out.println("Endereco:");
                System.out.print("  Digite o número da casa: ");
                String houseNumber = scanner.nextLine();
                System.out.print("  Digite a cidade: ");
                String city = scanner.nextLine();
                System.out.print("  Digite a rua: ");
                String street = scanner.nextLine();
                pet.setAddress(street + ", " + (houseNumber.trim().isEmpty() ? EMPTY_INPUT : houseNumber) + ", " + city);
            }

            if (i == 4) {
                System.out.print("Digite a idade aproximada do Pet: ");
                String age = scanner.nextLine();

                if (!age.trim().isEmpty()) {
                    Pattern pattern = Pattern.compile("[^0-9.,]");
                    Matcher matcher = pattern.matcher(age);
                    age = age.replace(",", ".");

                    if (matcher.find()) {
                        throw new IllegalArgumentException("Idade não pode ter letras ou caracteres especiais");
                    }

                    if (Float.parseFloat(age) > 20 || Float.parseFloat(age) < 0) {
                        throw new IllegalArgumentException("Idade deve ser entre 0 e 20 anos");
                    }
                }

                pet.setAge(age.trim().isEmpty() ? EMPTY_INPUT : "%s anos%n".formatted(age));
            }

            if (i == 5) {
                System.out.print("Digite o peso aproximado do Pet: ");
                String weight = scanner.nextLine();
                weight = weight.replace(",", ".");

                if (!weight.trim().isEmpty()) {
                    Pattern pattern = Pattern.compile("[^0-9.,]");
                    Matcher matcher = pattern.matcher(weight);
                    if (matcher.find()) {
                        throw new IllegalArgumentException("Peso não pode ter letras ou caracteres especiais");
                    }

                    if (Byte.parseByte(weight) > 60 || Float.parseFloat(weight) < 0.5) {
                        throw new IllegalArgumentException("Peso deve ser entre 0.5 e 60");
                    }
                }

                pet.setWeight(weight.trim().isEmpty() ? EMPTY_INPUT : "%skg%n".formatted(weight));
            }

            if (i == 6) {
                Pattern pattern = Pattern.compile("[^a-zA-Z]");
                System.out.print("Digite a raça do Pet: ");
                String breed = scanner.nextLine();
                Matcher matcher = pattern.matcher(breed);

                if (matcher.find()) {
                    System.out.println("Raça não deve ter caracteres especiais");
                    System.out.print("Digite a raça do Pet: ");
                    breed = scanner.nextLine();
                }

                pet.setBreed(breed.trim().isEmpty() ? EMPTY_INPUT : breed);
            }
            System.out.println();
        }

        String localDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String localTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
        String petFullName = "%s%s".formatted(pet.getName(), pet.getSurname()).toUpperCase();
        File petFile = new File("src/main/resources/%sT%s-%s.txt".formatted(localDate, localTime, petFullName));
        FileHandler.saveFile(petFile, pet);

        return pet;
    }

    private void printEnums(Object[] values) {
        for (int j = 0; j < values.length; j++) {
            System.out.println((j + 1) + " - " + values[j]);
        }
    }
}
