package com.example.group.petsogramapp.pet;

public class Pet {
    private String id;
    private String name;
    private String gender;
    private String bio;
    private int age;
    private String type;
    private String species;
    private String interests;

    public Pet(String id, String name, String gender, String bio, int age, String type, String species, String interests) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.bio = bio;
        this.age = age;
        this.type = type;
        this.species = species;
        this.interests = interests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}
