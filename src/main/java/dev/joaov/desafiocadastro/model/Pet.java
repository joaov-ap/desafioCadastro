package main.java.dev.joaov.desafiocadastro.model;

import java.util.Objects;

public class Pet {
    private String name;
    private PetType petType;
    private PetSex petSex;
    private String address;
    private String age;
    private String weight;
    private String breed;

    public Pet() {
    }

    public Pet(String name, PetType petType, PetSex petSex, String address, String age, String weight, String breed) {
        this.name = name;
        this.petType = petType;
        this.petSex = petSex;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, petType, petSex, address, age, weight, breed);
    }

    @Override
    public String toString() {
        return "%s - %s - %s - %s - %s - %s - %s"
                .formatted(name, petType, petSex, address, age, weight, breed);
    }

    public String toStringFile() {
        return "1 - %s%n2 - %s%n3 - %s%n4 - %s%n5 - %s%n6 - %s%n7 - %s"
                .formatted(name, petType, petSex, address, age, weight, breed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetSex getPetSex() {
        return petSex;
    }

    public void setPetSex(PetSex petSex) {
        this.petSex = petSex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
