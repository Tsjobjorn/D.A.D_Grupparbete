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
        readData();
        instance.protocol();
        return instance;
    }

//    private static void readData() {  // Metod för att hämta in data från textfilen customerInfo
//        Path path = Paths.get("customersInfo");
//        List<String> animalNames = new ArrayList<>();  // Skapar upp en lista med djurnamn. <- Den är tom här.
//        try (BufferedReader br = Files.newBufferedReader(path)) {
//            String[] allAnimals;  // en array som håller i djurnamn utelämnar ägare och telefonnummer.
//            String s;
//            int counter = 0;  // Räknare som håller koll på vilket index för substring
//            while ((s = br.readLine()) != null) {
//                counter++;
//                if(counter % 2 == 0){
//                    allAnimals = s.split(":");
//                    animalsNotFedNorWalked.add(allAnimals[1]);
//                }
//
//                /*counter = s.indexOf(" ");  // counter tar första indexet med ett whitespace och sparar index av typen int
//                animalNames.add(s.substring(counter, s.lastIndexOf(" ")).trim());  // animalNames sparar namnet av djur med hjälp av counter för index
//                allAnimals = s.split(" ");  // En array som sparar ENDAST djurets namn och nollställs vid varje körning. (är tanken)
//                animalsNotFedNorWalked.add(allAnimals[1]);  // Lista med djur som inte har blivit matade eller promenerade.*/
//                // todo: Gammalt skräp, kanske kan ta bort?
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static void informationAnimal() {
        System.out.println("Customers in system:");
        System.out.println("Pet name\t\t\tPet Type\t\tFed");

        Iterator var = Receptionist.getInstance().getCustomerList().iterator();


        while (var.hasNext()) {
            Customer c = (Customer) var.next();
            String petName = c.getName();
            String petType= c.getPet().getType();
            String fedStatus;
            if (c.getPet().isFed()) {
                fedStatus = ("Has been fed");
            } else {
                fedStatus = ("Needs to be fed");
            }


            String paddedppetName = String.format("%-16s", petName);
            String paddedpetType = String.format("%-12s", petType.trim());
            System.out.println(paddedppetName + paddedpetType + paddedpetType + fedStatus + "\n");
        }
    }


    @Override
    public void protocol() {  // en protokollmetod för att kontrollera vilket state programmet befinner sig i.
        if (Receptionist.getInstance().getCustomerList().size() == 0) {
            Receptionist.getInstance().fillCustomerListFromFile();
        }
        printChoices();
        switch (scan.nextLine()) {
            case "1" -> walkAnimalAndFeed();  // Case 1 skickar dig till walkingAnimalAndFeed() metoden osv osv.
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

//    private static void informationAnimal() {  // metod som skriver ut information om djuret.
//
//        if (fedAndWalkedAniamls.size() > 0) {  // Om listan är större än 0
//            System.out.println("Animals that have been walked and fed: " + fedAndWalkedAniamls);
//        } else {
//            System.out.println("No animal has been walked yet");  // om listan inte är större 0 så är listan tom och utskrift.
//        }
//
//        if (animalsNotFedNorWalked.size() > 0) {
//            System.out.println("Still not walked and fed animals: " + animalsNotFedNorWalked);
//            // Om listan fortfarande är större än 0 så finns det djur som inte har blivit matade / promenerade
//        } else {
//            System.out.println("All animals have been walked and fed");  // annars har alla djur gått.
//        }
//
//        getInstance().protocol();  // Kallar protokoll-klassen
//    }

//    private static void walkAnimalAndFeed() {  // metod för att gå och mata djur
//        while (true) {
//            System.out.println("which animal would you like to walk and feed?");
//            String s = scan.nextLine();
//            for (String value : animalsNotFedNorWalked) {
//                if (value.equals(s)) {  // itererar igenom listan animalsNotFedNorWalked
//                    fedAndWalkedAniamls.add(value);
//                    // Om scannern matchar value (namn i listan)
//                    // Så läggs det till i en ny lista för att hålla koll på djur som blivit rastade etc.
//
//                    removeFromList(value);  // Tar bort ett namn från en lista
//                    getInstance().protocol();  // kallar på protokollet
//                    break;
//                }
//            }
//            System.out.println("No animal with that name exist, try again");  // annars finns inte djuret med i listan
//        }
//    }

    private static void removeFromList(String name) {
        // tar bort ett namn som matchar parametern från
        // listan av djur som INTE har blivit rastade ännu men nu har blivit rastade.

        animalsNotFedNorWalked.removeIf(name::equals);
        walkedAnimals(fedAndWalkedAniamls);

    }

    public static void walkedAnimals(List<String> walkedAnimals) {  // tar emot en lista med djur som har blivit rastade
        Path path = Paths.get("WalkedAndFetAnimals");
        try (BufferedWriter br = Files.newBufferedWriter(path)) {  // Skriver till en fil med BufferedWriter och Files.
            br.write(walkedAnimals.toString());  // sparas i en textfil
        } catch (IOException e) {
            e.printStackTrace();

            //  TODO: bugg när djurhanteraren skriver ut information så skrivs namnen i listan två gånger.
        }
    }
}
