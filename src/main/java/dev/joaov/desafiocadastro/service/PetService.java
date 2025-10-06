package main.java.dev.joaov.desafiocadastro.service;

import main.java.dev.joaov.desafiocadastro.model.Pet;
import main.java.dev.joaov.desafiocadastro.model.PetSex;
import main.java.dev.joaov.desafiocadastro.model.PetType;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetService {
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_PATH = "src/main/resources/formulario.txt";
    private final File FILE = new File(FILE_PATH);
    private List<String> questions = new ArrayList<>(FileHandler.readAndSave(FILE));
    private final String EMPTY_INPUT = "NÃO INFORMADO";
    private List<Pet> pets = new ArrayList<>();

    public void registerPet() {
        Pet pet = new Pet();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));

            if (i == 0) {
                Pattern pattern = Pattern.compile("[^a-zA-Z\s]");

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
            }

            if (i == 1) {
                int petType = petTypeSelector();
                pet.setPetType(PetType.values()[petType]);
            }

            if (i == 2) {
                int petGender = petGenderSelector();
                pet.setPetSex(PetSex.values()[petGender]);
            }

            if (i == 3) {
                pet.setAddress(address());
            }

            if (i == 4) {
                System.out.print("Digite a idade aproximada do Pet: ");
                String age = scanner.nextLine();
                petAgeVerify(age);

                pet.setAge(age.trim().isEmpty() ? EMPTY_INPUT : "%s anos".formatted(age));
            }

            if (i == 5) {
                System.out.print("Digite o peso aproximado do Pet: ");
                String weight = scanner.nextLine();
                petWeightVerify(weight);

                pet.setWeight(weight.trim().isEmpty() ? EMPTY_INPUT : "%skg".formatted(weight));
            }

            if (i == 6) {
                Pattern pattern = Pattern.compile("[^a-zA-Z-]");
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
        File petFile = new File("src/main/resources/%sT%s-%s.txt".formatted(localDate, localTime, pet.getName().toUpperCase()));
        FileHandler.saveFile(petFile, pet);
        pets.add(pet);
    }

    public void showAllPetsFiltered() {
        for (int i = 0; i < filterPet().size(); i++) {
            System.out.printf("%d. %s%n".formatted(i+1, filterPet().get(i)));
        }
    }

    public void showAllPets() {
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        pets.forEach(System.out::println);
    }

    private void printEnums(Object[] values) {
        for (int j = 0; j < values.length; j++) {
            System.out.println((j + 1) + " - " + values[j]);
        }
    }

    private int petGenderSelector() {
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

        return petInput;
    }

    private int petTypeSelector() {
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

        return petInput;
    }

    private void petAgeVerify(String age) {
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
    }

    private void petWeightVerify(String weight) {
        if (!weight.trim().isEmpty()) {
            Pattern pattern = Pattern.compile("[^0-9.,]");
            Matcher matcher = pattern.matcher(weight);
            weight = weight.replace(",", ".");

            if (matcher.find()) {
                throw new IllegalArgumentException("Peso não pode ter letras ou caracteres especiais");
            }

            if (Byte.parseByte(weight) > 60 || Float.parseFloat(weight) < 0.5) {
                throw new IllegalArgumentException("Peso deve ser entre 0.5 e 60");
            }
        }
    }

    private String address() {
        System.out.println("Endereco:");
        System.out.print("  Digite o número da casa: ");
        String houseNumber = scanner.nextLine();
        System.out.print("  Digite a cidade: ");
        String city = scanner.nextLine();
        System.out.print("  Digite a rua: ");
        String street = scanner.nextLine();

        return street + ", " + (houseNumber.trim().isEmpty() ? EMPTY_INPUT : houseNumber) + ", " + city;
    }

    private List<Pet> filterPet() {
        System.out.println("Escolha quais os criterios para busca: ");
        System.out.println("0 para finalizar a escolha de criterios.");
        Map<String, String> criteriosList = new HashMap<>();
        int userInput;

        int petTypeIndex = petTypeSelector();
        PetType petType = PetType.values()[petTypeIndex];

        do {
            filterMenu();
            userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 0:
                    System.out.printf("Criterios escolhidos: %s%n".formatted(criteriosList));
                    break;
                case 1:
                    System.out.print("Digite o nome ou sobrenome que deseja buscar: ");
                    String name = scanner.nextLine();

                    Pattern pattern = Pattern.compile("[^a-zA-Z\s]");
                    Matcher matcher = pattern.matcher(name);
                    if (name.trim().isEmpty() || name == null) {
                        throw new IllegalArgumentException("Nome não deve ser vazio");
                    }

                    if (matcher.find()) {
                        System.out.println("Nome não deve ter caracteres especiais");
                        System.out.print("Digite o nome: ");
                        name = scanner.nextLine();
                    }
                    criteriosList.put("Nome ou sobrenome", name);
                    break;
                case 2:
                    System.out.println("Digite o genero do Pet que deseja buscar: ");
                    int petGender = petGenderSelector();
                    criteriosList.put("Genero", String.valueOf(PetSex.values()[petGender]));
                    break;
                case 3:
                    System.out.print("Digite a idade do pet: ");
                    String age = scanner.nextLine();
                    petAgeVerify(age);
                    criteriosList.put("Idade", age);
                    break;
                case 4:
                    System.out.print("Digite o peso do pet: ");
                    String weight = scanner.nextLine();
                    petWeightVerify(weight);
                    criteriosList.put("Peso", weight);
                    break;
                case 5:
                    System.out.print("Digite a raça do pet: ");
                    String breed = scanner.nextLine();
                    pattern = Pattern.compile("[^a-zA-Z\s-]");
                    matcher = pattern.matcher(breed);

                    if (matcher.find()) {
                        System.out.println("Raça não deve ter caracteres especiais");
                        System.out.print("Digite a raça do Pet: ");
                        breed = scanner.nextLine();
                    }

                    criteriosList.put("Raça", breed);
                    break;
                case 6:
                    String address = address();
                    criteriosList.put("Endereço", address);
                    break;
                default:
                    System.out.println("Opção inválida. Escolha entre 1 e 6.");
            }
        } while (userInput != 0);

        String name = null;
        String gender = null;
        String age = null;
        String weight = null;
        String breed = null;
        String address = null;

        for (Map.Entry<String, String> entry : criteriosList.entrySet()) {
            switch (entry.getKey()) {
                case "Nome ou sobrenome":
                    name = entry.getValue();
                    break;
                case "Genero":
                    gender = entry.getValue();
                    break;
                case "Idade":
                    age = entry.getValue();
                    break;
                case "Peso":
                    weight = entry.getValue();
                    break;
                case "Raça":
                    breed = entry.getValue();
                    break;
                case "Endereço":
                    address = entry.getValue();
                    break;
            }
        }

        String finalName = name;
        String finalGender = gender;
        String finalAge = age;
        String finalWeight = weight;
        String finalBreed = breed;
        String finalAddress = address;

        return pets.stream()
                .filter(p -> p.getPetType().equals(petType))
                .filter(p -> finalName == null || p.getName().toUpperCase().contains(finalName.toUpperCase()))
                .filter(p -> finalGender == null || p.getPetSex().name().toUpperCase().contains(finalGender.toUpperCase()))
                .filter(p -> finalAge == null || p.getAge().contains(finalAge))
                .filter(p -> finalWeight == null || p.getWeight().contains(finalWeight))
                .filter(p -> finalBreed == null || p.getBreed().toUpperCase().contains(finalBreed.toUpperCase()))
                .filter(p -> finalAddress == null || p.getAddress().toUpperCase().contains(finalAddress.toUpperCase()))
                .toList();
    }

    private void filterMenu() {
        System.out.println("""
                1 - Nome ou sobrenome
                2 - Sexo
                3 - Idade
                4 - Peso
                5 - Raça
                6 - Endereço
                0 - Sair
                """);
    }
}
