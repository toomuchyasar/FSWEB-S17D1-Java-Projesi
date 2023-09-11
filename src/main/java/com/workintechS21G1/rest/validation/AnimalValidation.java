package com.workintechS21G1.rest.validation;

import com.workintechS21G1.rest.entity.Animal;

import java.util.Map;

public class AnimalValidation {

    public static boolean isIdValid(int id){
        return !(id < 0);
    }
    public static boolean isMapContainsKey(Map<Integer, Animal> animals, int id){
        return animals.containsKey(id);
    }
    public static boolean isAnimalCredentialsValid(Animal animal){
        return !(animal.getId() <= 0 || animal.getName() == null || animal.getName().isEmpty());
    }
}
