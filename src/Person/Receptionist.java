package Person;

import LoggingTool.ProtocolFunctionInterface;
import Animal.*;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Receptionist implements ProtocolFunctionInterface {
    private static List<String> customerInformation;  // lista som sparar information som skrivs till textfil.
    private static List<Customer> customerList = new ArrayList<>(); //Lista av typen Customer.  // TODO: Flyttade denna till globala scope
    private static Receptionist instance = new Receptionist();  // Singleton instans av Receptionistklassen
    public static Scanner scan;  // global användare av en scanner.

    private Receptionist() {
    }

    public static Receptionist getInstance() {
        // när instansen av receptionisten hämtas så körs listan igång och skickar användaren till protokollet.

        scan = new Scanner(System.in);
        customerInformation = new ArrayList<>();  // skapar upp listan av customers här för att spara minne.
        /*customerList*/ //Skapar upp listan av customers(klassen/objektet).
        instance.protocol();
        return instance;
    }


    public void addCustomer() {
        /*Scanner scanner = new Scanner(System.in);*/
        System.out.println("Customer name?");
        String customerName = scan.nextLine();
        System.out.println("Customer phone number?");
        String addPhoneNr = scan.nextLine();

        //Skapar upp en customer, lägger till i listan.
        System.out.println("DEBUG: storlek på listan=" + customerList.size());
        customerList.add(new Customer(customerName, addPhoneNr));
        System.out.println("DEBUG: storlek på listan=" + customerList.size());
        System.out.println("DEBUG: La till customer i customerList");
        //Skapar upp pet(Animal) under customer objektet, tar nuvarande storleken på listan som index for vilket customer som ska få pet.
        customerList.get(customerList.size() - 1).addPetNameAndType();
        System.out.println(customerName + " has been added as a customer with a pet " + customerList.get(customerList.size() - 1).getPet().getType() + ".");

        //Skickar in customer objektet i metoden writeCustomerInfoToFIle. Använder sig av nuvarande storleken av listan för att skicka rätt index.
        writeCustomerInfoToFile(customerList.get(customerList.size() - 1));

        Receptionist.getInstance().protocol();
    }

    //    private static void addCustomer() {
//        /*
//        Frågar användaren om namn på person, namn på djur och telefonnummer / kontaktuppgifter.
//        Sparar informationen i customerInformation listan.
//        */
//
//        String s;
//        System.out.println("Give me customer name");
//        s = scan.nextLine();
//        String name = s;
//        customerInformation.add(s);
//
//        System.out.println("Give me customer's animals name");
//        s = scan.nextLine();
//        String animalName = s;
//        customerInformation.add(s);
//
//        System.out.println("Give me customers phone number");
//        s = scan.nextLine();
//        customerInformation.add(s);
//        String phoneNumber = s;
//        if (name != null && animalName != null && phoneNumber != null) {
//            // Skriver namn, djurnamn, och telefonnummer till textfil.
//            writeCustomerInfoToFile(name, animalName, phoneNumber);
//        }
//        Receptionist.getInstance().protocol();  // kallar på protokollklassen igen.
//    }
    private static void writeCustomerInfoToFile(Customer c) {
        // metoden som skriver till textfilen.
        File file = new File("customersInfo");

        //Skriver customer ut info till fil. customerName:CustomerPhoneNr ny rad.
        //nästa rad skriver ut pet info till fil.
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(c.getName() + ":" + c.getPhoneNr() + "\n");
            fw.write(c.getPet().getType() + ":" + c.getPet().getPetName() + "\n");

        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    //    private static void writeCustomerInfoToFile(String customerName, String animal, String phoneNumber) {
//        // metoden som skriver till textfilen.
//        File file = new File("customersInfo");
//        List<String> information = new ArrayList<>();
//        information.add(customerName + " " + animal + " " + phoneNumber);
//        try (FileWriter fw = new FileWriter(file, true)) {
//            // skriver till textfilen med en
//            // FileWriter och appendar
//            // ligger i en try-with resources så skrivaren stängs själv.
//            for (String s : information) {
//                fw.write(s);
//                fw.write("\n");
//            }
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//    }
    private static void printInformationFromList() {
        System.out.println("customerlistSize=" + customerList.size());
        System.out.println("Customers in system:");
        System.out.println("Name\t\t\tPhoneNumber\t\t\tPet Type\t\tPet Name");
        System.out.println("customerlistSize=" + customerList.size());

        Iterator var2 = customerList.iterator();

        /*System.out.println("DEBUG: 0=" + customerList.get(0).getName());
        System.out.println("DEBUG: 1=" + customerList.get(1).getName());
        System.out.println("DEBUG: 0=" + customerList.get(0).getPet().getPetName());*/

        while (var2.hasNext()) {
            Customer c = (Customer) var2.next();
            String name = c.getName();
            String phNumber = c.getPhoneNr();
            String petType = c.getPet().getType();
            String petName = c.getPet().getPetName();

            String paddedName = String.format("%-15s", name);
            String paddedphNumber = String.format("%-15s", phNumber);
            String paddedpetType = String.format("%-10s", petType.trim());
            System.out.println(paddedName + paddedphNumber /*+ paddedpetType*/ + petName);
        }

        /*        System.out.println();*/
        Path path = Paths.get("customersInfo");  // TODO: visa Thomas skriver ut information.
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metod som fyller på befintliga kunder i filen till customerList
    private void fillCustomerListFromFile() {
//        String customerInfo;
//        String petInfo;
        try {
            Scanner readTextFile = new Scanner(new File("customersInfo"));
            while (readTextFile.hasNextLine()) {
                System.out.println("DEBUG: Försöker läsa in en data från fil");
                if (readTextFile.hasNextLine()) {
                    String customerInfo = readTextFile.nextLine();
                    System.out.println("DEBUG: Lyckades läsa in sträng: CustomerInfo: " + customerInfo);
                    System.out.println("DEBUG: Substringade namn= " + customerInfo.substring(0, customerInfo.indexOf(':')));
                    System.out.println("DEBUG: Substringade telenummer=" + customerInfo.substring(customerInfo.indexOf(':')));
                    customerList.add(new Customer((customerInfo.substring(0, customerInfo.indexOf(':'))), customerInfo.substring(customerInfo.indexOf(':'))));
                    System.out.println("DEBUG: Lyckades lägga in namn&nr i lista");
                    System.out.println("DEBUG: Storlek på lista=" + customerList.size());
                    if (readTextFile.hasNextLine()) {
                        System.out.println("DEBUG: Försöker läsa in petInfo");
                        String petInfo = readTextFile.nextLine();
                        System.out.println("DEBUG: Substringa petType=" + petInfo.substring(0, petInfo.indexOf(':')));
                        System.out.println("DEBUG: Substringade petName=" + petInfo.substring(petInfo.indexOf(':')));
                        customerList.get(customerList.size() - 1).setPetType(petInfo.substring(petInfo.indexOf(':')), petInfo.substring(0, petInfo.indexOf(':')));
                        System.out.println("DEBUG: La till petType och petName");
                        System.out.println("DEBUG: Kundnamn: " + customerList.get(customerList.size() - 1).getName());
                        System.out.println("DEBUG: Kundens djur: " + customerList.get(customerList.size() - 1).getPet().getPetName());

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

//    private static void printInformationFromList() {
//        // Metod för att hämta information från textfilen customerInfo.
//        // Med hjälp av files
//
//        Path path = Paths.get("customersInfo");
//        try {
//            System.out.println(Files.readAllLines(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            Receptionist.getInstance().protocol();  // Kallar alltid på protokollet.
//        }
//
//    }

    @Override
    public void protocol() {
//        fillCustomerListFromFile(); //Läser in befintliga kunder från filen till customerList.
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

