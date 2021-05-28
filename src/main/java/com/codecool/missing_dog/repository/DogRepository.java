package com.codecool.missing_dog.repository;

import com.codecool.missing_dog.model.Dog;
import com.codecool.missing_dog.model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DogRepository {
    private final List<Dog> data;

    public DogRepository(List<Dog> data) {
        this.data = data;
    }

    /**
     * Return Dog if found one with given id,
     * otherwise return Optional.empty()
     *
     * @param id dog's id
     * @return optional of Dog
     */
    public Optional<Dog> getById(int id) {
        return data.stream()
                   .filter(dog -> dog.getId() == id)
                   .findFirst();
    }

    /**
     * @return list of all dogs
     */
    public List<Dog> getAll() {
        return data;
    }

    /**
     * Return owner's phone number for the dog of given id,
     * if dog is not found return Optional.empty()
     *
     * @param id dog's id
     * @return owner's phone number
     */
    public Optional<String> getOwnerPhoneNoByDogId(int id) {
        Optional<Dog> optionalDog = data.stream()
                                        .filter(dog -> dog.getId() == id)
                                        .findFirst();
        if (optionalDog.isPresent()) {
            Owner owner = optionalDog.get()
                                     .getOwner();
            return Optional.ofNullable(owner.getPhoneNo());
        }
        return Optional.empty();
    }

    /**
     * Return owner's email for the dog of given id,
     * if dog is not found return Optional.empty()
     *
     * @param id dog's id
     * @return owner's email
     */
    public Optional<String> getOwnerEmailByDogId(int id) {
        Optional<Dog> optionalDog = data.stream()
                                        .filter(dog -> dog.getId() == id)
                                        .findFirst();
        return optionalDog.map(dog -> dog.getOwner()
                                         .getEmail());
    }

    /**
     * Return owner's full name for the dog of given id,
     * if dog is not found return Optional.empty()
     * * @param id
     *
     * @return optional of owner's phone number
     */
    public Optional<String> getOwnerFullNameByDogId(int id) {
        Optional<Dog> optionalDog = data.stream()
                                        .filter(dog -> dog.getId() == id)
                                        .findFirst();
        return optionalDog.map(dog -> dog.getOwner()
                                         .getFullName());
    }

    /**
     * Return all dogs of given breed.
     * List can be emtpy
     *
     * @param breed value to look by
     * @return list of dogs of given breed
     */
    public List<Dog> getDogsByBreed(String breed) {
        return data.stream()
                   .filter(dog -> dog.getBreed()
                                     .equals(breed))
                   .collect(Collectors.toList());
    }

    /**
     * Return all dogs by their sociability.
     * Pass "true" to find sociable dogs,
     * pass any other value to find unsociable ones.
     * Lists can be empty
     *
     * @param value value to look by
     * @return list of dogs of given sociability
     */
    public List<Dog> getDogsBySociability(Boolean value) {
        return data.stream()
                   .filter(dog -> dog.getSociable()
                                     .equals(value))
                   .collect(Collectors.toList());
    }

    /**
     * Return all dogs owned by Owner of given id, list can be empty
     *
     * @param id owner's id
     * @return list of dogs owned by specific owner
     */
    public List<Dog> getDogsByOwnerId(int id) {
        return data.stream()
                   .filter(dog -> dog.getOwner()
                                     .getId() == id)
                   .collect(Collectors.toList());
    }

    /**
     * First find owner of dog with given id.
     * Then return count of dogs the owner has.
     *
     * @param id dog's id
     * @return number of dogs the owner has
     */
    public int getOwnerDogCountByDogId(int id) {
        Optional<Owner> optionalOwner = data
                .stream()
                .filter(dog -> dog.getId() == id)
                .findFirst()
                .map(Dog::getOwner);
        return optionalOwner
                .map(owner -> Math.toIntExact(data.stream()
                                                  .filter(dog -> dog.getOwner()
                                                                    .equals(owner))
                                                  .count()))
                .orElse(0);
    }

    /**
     * Find dog with given id,
     * then find all dogs that have the same breed
     *
     * @param id dog's id
     * @return list of dogs of the same breed as the dog of given id
     */
    public List<Dog> getOtherDogsOfSameBreedAsDogWithId(int id) {
        Optional<Dog> optionalDog = getById(id);
        return optionalDog.map(value -> data.stream()
                                            .filter(dog -> dog.getBreed()
                                                              .equals(value.getBreed()))
                                            .collect(Collectors.toList()))
                          .orElseGet(ArrayList::new);
    }
}
