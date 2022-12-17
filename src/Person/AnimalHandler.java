package Person;

import LoggingTool.ProtocolFunctionInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AnimalHandler implements ProtocolFunctionInterface {
    private static final List<String> fedAndWalkedAniamls = new ArrayList<>();
    private static final List<String> animalsNotFedNorWalked = new ArrayList<>();
    private static AnimalHandler instance = new AnimalHandler();

    static Scanner scan;

    private AnimalHandler() {  // Tom konstruktor för singleton design pattern.
    }

    public static AnimalHandler getInstance() {  // Hämtar en instance av klassen Animalhandler (singleton)
        scan = new Scanner(System.in);
        instance.protocol();
        return instance;
    }


    private void displayUnfedPets() {
        System.out.println("\nThe following animals have not been fed:");
        Iterator var = getIterator();
        while (var.hasNext()) {
            Customer c = (Customer) var.next();
            if (!c.getPet().isFed()) {
                String petName = c.getPet().getPetName();
                System.out.println(petName);
            }
        }
        System.out.println();
    }

    private void informationAnimal() {
        System.out.println("Pet name\t\t\tPet Type\t\tFed"); //Skippa visa pet Type?
        Iterator var = getIterator();

        while (var.hasNext()) {
            Customer c = (Customer) var.next();
            String petName = c.getPet().getPetName();
            String petType = c.getPet().getType();
            String fedStatus;
            if (c.getPet().isFed()) {
                fedStatus = ("Has been fed");
            } else {
                fedStatus = ("Needs to be fed");
            }

            String paddedpetName = String.format("%-20s", petName);
            String paddedpetType = String.format("%-16s", petType.trim());
            System.out.println(paddedpetName + paddedpetType  + fedStatus + "\n");
        }
    }

    private static Iterator getIterator(){
        Iterator var = Receptionist.getCustomerList().iterator();
        return var;
    }
    private void feedAnimal() {
        Iterator var = Receptionist.getCustomerList().iterator(); //todo kolla om det bara är iteratorlistan som blir markerad eller den faktiska listan.
        displayUnfedPets();
        System.out.println("Enter the name of the animal you want to set to fed.");
        String s = scan.nextLine();
        while (var.hasNext()) {
            Customer c = (Customer) var.next();
            if(s.equalsIgnoreCase(c.getPet().getPetName())){
                c.getPet().setFed(true);
                System.out.println(c.getPet().getPetName()+" have been set as fed.");
                System.out.println();
                break;
            }

        }
    }




    @Override
    public void protocol() {  // en protokollmetod för att kontrollera vilket state programmet befinner sig i.
        if (Receptionist.getCustomerList().size() == 0) {
            Receptionist.fillCustomerListFromFile();
        }
        printChoices();
        switch (scan.nextLine()) {
            case "1" -> feedAnimal();  // Case 1 skickar dig till feedAnimal() metoden osv osv.
            case "2" -> informationAnimal();
            case "3" -> Receptionist.getInstance();
            // Returnerar dig till instansen av Receptionisten och pga att det
            // är en singleton så är det alltid samma objekt och inga nya instanser skapas / sparar minne

            default -> System.out.println("Invalid input. Try again");
        }
        protocol();  // Om inte protokollet inte skickar dig vidare till en ny metod 1, 2 eller 3 så körs protokollet om.
    }

    @Override
    public void printChoices() {  // Alternativen som har för varje klass, metoden implementeras av ProtocolFunctionInterface
        System.out.println("""  
                Press 1 to walk and feed animal
                Press 2 to get information
                Press 3 to go to reception""");
    }



//    public static void walkedAnimals(List<String> walkedAnimals) {  // tar emot en lista med djur som har blivit rastade
//        Path path = Paths.get("WalkedAndFetAnimals");
//        try (BufferedWriter br = Files.newBufferedWriter(path)) {  // Skriver till en fil med BufferedWriter och Files.
//            br.write(walkedAnimals.toString());  // sparas i en textfil
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            //  TODO: bugg när djurhanteraren skriver ut information så skrivs namnen i listan två gånger.
//        }
//    }
}
