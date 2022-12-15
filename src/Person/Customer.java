package Person;


import Animal.*;

import java.util.Scanner;

public class Customer extends Person{


    Animal pet;
    public Animal getAnimal() {
        return pet;
    }
    public void setAnimal(Animal animal) {
        this.pet = animal;
    }


    public Customer(String name, String phoneNr){
        super(name, phoneNr);
    }


    public void addPetNameAndType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pet type?\n1.Dog\n2.Cat\n3.Rabbit\n4.Bird");
        int petType = Integer.parseInt(scanner.nextLine());
        System.out.println("Pet name?");
        String petName = scanner.nextLine();

        setPetType(petType, name);

    }
    public void setPetType(int type, String name){

        /**
         * switch-sats?
         * */
//        pet=new Dog(name);
//        pet=new Cat(name);
//        pet=new Rabbit(name);
//        pet=new Bird(name);


}
}