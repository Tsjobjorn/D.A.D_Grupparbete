package LoggingTool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receptionist implements ProtocolFunctionInterface {
    private static List<String> customerInformation;  // lista som sparar information som skrivs till textfil.
    private static Receptionist instance = new Receptionist();  // Singleton instans av Receptionistklassen
    public static Scanner scan;  // global användare av en scanner.

    private Receptionist() {
    }

    public static Receptionist getInstance() {
        // när instansen av receptionisten hämtas så körs listan igång och skickar användaren till protokollet.

        scan = new Scanner(System.in);
        customerInformation = new ArrayList<>();  // skapar upp listan av customers här för att spara minne.
        instance.protocol();
        return instance;
    }

    private static void addCustomer() {
        /*
        Frågar användaren om namn på person, namn på djur och telefonnummer / kontaktuppgifter.
        Sparar informationen i customerInformation listan.
        */

        String s;
        System.out.println("Give me customer name");
        s = scan.nextLine();
        String name = s;
        customerInformation.add(s);

        System.out.println("Give me customer's animals name");
        s = scan.nextLine();
        String animalName = s;
        customerInformation.add(s);

        System.out.println("Give me customers phone number");
        s = scan.nextLine();
        customerInformation.add(s);
        String phoneNumber = s;
        if (name != null && animalName != null && phoneNumber != null) {
            // Skriver namn, djurnamn, och telefonnummer till textfil.
            writeCustomerInfoToFile(name, animalName, phoneNumber);
        }
        Receptionist.getInstance().protocol();  // kallar på protokollklassen igen.
    }

    private static void writeCustomerInfoToFile(String customerName, String animal, String phoneNumber) {
        // metoden som skriver till textfilen.
        File file = new File("customersInfo");
        List<String> information = new ArrayList<>();
        information.add(customerName + " " + animal + " " + phoneNumber);
        try (FileWriter fw = new FileWriter(file, true)) {
            // skriver till textfilen med en
            // FileWriter och appendar
            // ligger i en try-with resources så skrivaren stängs själv.
            for (String s : information) {
                fw.write(s);
                fw.write("\n");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private static void printInformationFromList() {
        // Metod för att hämta information från textfilen customerInfo.
        // Med hjälp av files

        Path path = Paths.get("customersInfo");
        try {
            System.out.println(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Receptionist.getInstance().protocol();  // Kallar alltid på protokollet.
        }

    }

    @Override
    public void protocol() {
        printChoices();
        String s = scan.nextLine();
        switch (s) {
            case "1" -> addCustomer();
            // Tar emot states och delegerar användaren till specifika delar av programmet.

            case "2" -> printInformationFromList();

            case "3" -> AnimalHandler.getInstance();
            // Om du vill byta till att vara en djurhanterare istället för receptionist

            default -> System.out.println("Invalid input. Try again");
        }
        protocol();
    }

    @Override
    public void printChoices() {  // återkommande kod i programmet som delades upp i en metod istället.
        System.out.println("""
                1 to add customer
                2 to add print customer information
                3 to go to Animal handler""");
    }
}
