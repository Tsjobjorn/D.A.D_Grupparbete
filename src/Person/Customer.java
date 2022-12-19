package Person;
import Animal.*;
import java.util.Scanner;

public class Customer extends Person {
    Scanner scanner = new Scanner(System.in);

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

        System.out.println("Pet type? Dog, Cat, Rabitt, Bird.");
        String petType = addPetType();
        System.out.println("Pet name?");
        String petName = addPetName();
        setPetType(petType, petName);

    }

    public String addPetName() {
        String petName = "";
        while (petName.isBlank()) {
            petName = scanner.nextLine();
            if (petName.isBlank()) {
                System.out.println("Invalid input. Try again.");
            }
        }
        return petName.trim();
    }

    public String addPetType() {

        while (true) {
            String petType = scanner.nextLine().trim();
            switch (petType.toLowerCase()) {
                case "dog":
                    break;
                case "cat":
                    break;
                case "bird":
                    break;
                case "rabbit":
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
                    continue;
            }
            return petType;
        }

    }


    public void setPetType(String petType, String petName) {

        switch (petType.toLowerCase()) {
            case "dog" -> pet = new Dog(petName);
            case "cat" -> pet = new Cat(petName);
            case "rabbit" -> pet = new Rabbit(petName);
            case "bird" -> pet = new Bird(petName);
            default -> System.out.println("Invalid pet type");

        }

    }
}