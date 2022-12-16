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
        System.out.println("Pet type? Dog, Cat, Rabitt, Bird.");
        String petType = scanner.nextLine().trim();  // todo: visa Thomas
        System.out.println("Pet name?");
        String petName = scanner.nextLine().trim();
        setPetType(petType, petName);

    }

    public void setPetType(String petType, String petName) {


        switch (petType.toLowerCase()) {
            case "dog":
                pet = new Dog(petName);
                break;
            case "cat":
                pet=new Cat(petName);
                break;
            case "rabbit":
                pet=new Rabbit(petName);
                break;
            case "bird":
                pet=new Bird(petName);
                break;
        }
//        pet=new Dog(name);
//        pet=new Cat(name);
//        pet=new Rabbit(name);
//        pet=new Bird(name);


    }
}