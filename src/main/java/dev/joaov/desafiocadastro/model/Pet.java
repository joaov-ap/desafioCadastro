package main.java.dev.joaov.desafiocadastro.model;

import java.util.Objects;

public class Pet {
    private String name;
    private String surname;
    private PetType petType;
    private PetSex petSex;
    private String address;
    private String age;
    private String weight;
    private String breed;

    public Pet() {
    }

    public Pet(String name, String surname, PetType petType, PetSex petSex, String address, String age, String weight, String breed) {
        this.name = name;
        this.surname = surname;
        this.petType = petType;
        this.petSex = petSex;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, petType, petSex, address, age, weight, breed);
    }

    @Override
    public String toString() {
        return "1 - %s %s%n2 - %s%n3 - %s%n4 - %s%n5 - %s anos%n6 - %skg%n7 - %s"
                .formatted(name, surname, petType, petSex, address, age, weight, breed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
