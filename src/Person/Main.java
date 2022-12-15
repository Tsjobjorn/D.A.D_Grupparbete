package Person;

import Animal.AnimalHelper;

public class Main {

    public static void main(String[] args) {
        AnimalHelper animalHelper = new AnimalHelper();
        animalHelper.feedBird();
        animalHelper.feedCat();
        animalHelper.feedDog();
        animalHelper.feedRabbit();
    }



}