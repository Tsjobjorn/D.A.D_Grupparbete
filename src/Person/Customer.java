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


    public void addPetNameAndType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choice a pet type?\nPress 1 for dog\n Press 2 for cat" +
                            "\nPress 3 for Rabitt\nPress 4 for Bird."); //TODO Lings changes
        String petType = scanner.nextLine().trim();  // todo: visa Thomas
        System.out.println("Pet name?");
        String petName = scanner.nextLine().trim();
        setPetType(petType, petName);

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
                System.out.println("Invalid input. Please try again");
                addPetNameAndType();
        }
//        pet=new Dog(name);
//        pet=new Cat(name);
//        pet=new Rabbit(name);
//        pet=new Bird(name);


    }
}