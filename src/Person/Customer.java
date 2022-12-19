package Person;


import Animal.*;

import java.util.Scanner;

public class Customer extends Person {



    Animal pet;


    public Animal getPet() {
        return pet;
    }

    public void setPet(Animal pet) {
        this.pet = pet;
    }


    public Customer(String name, String phoneNr) {
        super(name, phoneNr);
    }


    public void addPetNameAndType() {//TODO Lings changes
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pet type? Dog, Cat, Rabitt, Bird.");
        String petType = scanner.nextLine().trim();// todo: visa Thomas
        checkInput(petType);
        System.out.println("Pet name?");
        String petName = scanner.nextLine().trim();
        checkInput(petName);
        setPetType(petType, petName);

    }

    public void checkInput(String input){
        if (input.isBlank()){
            System.out.println("Invalid input. Try again.");
            addPetNameAndType();
        }
    }

    public void setPetType(String petType, String petName) {


        switch (petType.toLowerCase()) {
            case "dog" -> pet = new Dog(petName);
            case "cat" -> pet = new Cat(petName);
            case "rabbit" -> pet = new Rabbit(petName);
            case "bird" -> pet = new Bird(petName);
            default -> pet=new Cat(petName);

        }

    }
}