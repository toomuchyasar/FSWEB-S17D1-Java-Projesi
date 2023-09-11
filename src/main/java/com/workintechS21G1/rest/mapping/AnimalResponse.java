package com.workintechS21G1.rest.mapping;

import com.workintechS21G1.rest.entity.Animal;

public class AnimalResponse {
    private Animal animal;
    private String message;
    private int status;

    public AnimalResponse(Animal animal, String message, int status) {
        this.animal = animal;
        this.message = message;
        this.status = status;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
