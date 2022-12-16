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
    String petType;
    String petName;
    Scanner scanner = new Scanner(System.in);


    public Customer(String name, String phoneNr) {
        super(name, phoneNr);
    }


    public void addPetNameAndType() {

        System.out.println("\n    ********************\n");
        System.out.println("Choice a pet type?\nPress 1 for dog\nPress 2 for cat" +
                            "\nPress 3 for Rabitt\nPress 4 for Bird."); //TODO Lings changes
        petType = scanner.nextLine().trim();  // todo: visa Thomas

        checkPetName(petName); // TODO: Lings changes


    }

    private void checkPetName(String petName) { //TODO: Lings changes

        System.out.println("Pet name?");
        petName = scanner.nextLine().trim();
        if (petName.isBlank() || petName == null) {
            System.err.println("Invalid pet name. Try again");
            checkPetName(petName);
        }
        else {
            setPetType(petType, petName);
        }

    }

    public void setPetType(String petType, String petName) {


        switch (petType) {
            case "1":
                pet = new Dog(petName);
                break;
            case "2":
                pet=new Cat(petName);
                break;
            case "3":
                pet=new Rabbit(petName);
                break;
            case "4":
                pet=new Bird(petName);
                break;
            default:
                System.err.println("Invalid input. Please try again"); //TODO: Lings changes
                addPetNameAndType();
        }
//        pet=new Dog(name);
//        pet=new Cat(name);
//        pet=new Rabbit(name);
//        pet=new Bird(name);


    }
}