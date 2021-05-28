package com.codecool.missing_dog;

import com.codecool.missing_dog.model.Data;
import com.codecool.missing_dog.model.DataGenerator;
import com.codecool.missing_dog.view.View;
import com.codecool.missing_dog.controller.Controller;
import com.codecool.missing_dog.input.Input;
import com.codecool.missing_dog.repository.DogRepository;
import com.codecool.missing_dog.repository.OwnerRepository;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Input input = new Input();

        Data data = new DataGenerator().createData();

        DogRepository dogRepository = new DogRepository(data.getDogList());
        OwnerRepository ownerRepository = new OwnerRepository(data.getOwnerList());

        Controller controller = new Controller(dogRepository, ownerRepository, view, input);
        controller.run();
    }
}
