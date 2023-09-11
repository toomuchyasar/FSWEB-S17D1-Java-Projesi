package com.workintechS21G1.rest.controller;

import com.workintechS21G1.rest.entity.Animal;
import com.workintechS21G1.rest.mapping.AnimalResponse;
import com.workintechS21G1.rest.validation.AnimalValidation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${programmer.name}")
    private String name;

    @Value("${programmer.surname}")
    private String surname;

    private Map<Integer, Animal> animalMap;

    @PostConstruct
    public void init() {
        animalMap = new HashMap<>();
    }

    @GetMapping("/deneme")
    public String welcome() {
        return name + " - " + surname + ", Hoşgeldin";
    }

    @GetMapping("/")
    public List<Animal> get() {
        return animalMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public AnimalResponse get(@PathVariable int id){
        if (!AnimalValidation.isIdValid(id)) {
            return new AnimalResponse(null, "Id is not valid", 400);
        }
        if (!AnimalValidation.isMapContainsKey(animalMap, id)) {
            return new AnimalResponse(null, "Animal is not exist", 400);
        }
        return new AnimalResponse(animalMap.get(id), "Success", 200);
    }

    @PostMapping("/")
    public AnimalResponse save(@RequestBody Animal animal){
        if (AnimalValidation.isMapContainsKey(animalMap, animal.getId())) {
            return new AnimalResponse(null, "Animal is already exit", 400);
        }
        if (!AnimalValidation.isAnimalCredentialsValid(animal)){
            return new AnimalResponse(null, "Animal credentials are not valid", 400);
        }

        animalMap.put(animal.getId(),animal);
        return new AnimalResponse(animalMap.get(animal.getId()), "success", 201);
    }

    @DeleteMapping("/{id}")
    public AnimalResponse delete(@PathVariable int id){
        if(!AnimalValidation.isMapContainsKey(animalMap, id)){
            return new AnimalResponse(null, "Animal is not exist", 400);
        }
        Animal foundAnimal = animalMap.get(id);
        animalMap.remove(id);
        return new AnimalResponse(foundAnimal, "Animal removed successfully", 200);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Animal Controller has been destroyed");
    }


}
