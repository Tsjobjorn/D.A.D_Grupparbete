package Person;


import Animal.*;

import java.util.Scanner;

public class Customer extends Person {


    Animal pet;

    public Animal getAnimal() {
        return pet;
    }

    public void setAnimal(Animal animal) {
        this.pet = animal;
    }


    public Customer(String name, String phoneNr) {
        super(name, phoneNr);
    }


    public void addPetNameAndType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pet type?Dog, Cat, Rabbitt, Bird.");
        String petType = scanner.nextLine();
        System.out.println("Pet name?");
        String petName = scanner.nextLine();
        setPetType(petType, petName);

    }

    public void setPetType(String petType, String petName) {


        switch (petType.toLowerCase()) {
            case "dog":
                pet = new Dog(name);
                break;
            case "cat":
                pet=new Cat(name);
                break;
            case "rabbit":
                pet=new Rabbit(name);
                break;
            case "bird":
                pet=new Bird(name);
                break;
        }
//        pet=new Dog(name);
//        pet=new Cat(name);
//        pet=new Rabbit(name);
//        pet=new Bird(name);


    }
}