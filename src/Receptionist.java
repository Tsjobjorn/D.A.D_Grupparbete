import Person.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Receptionist implements ProtocolFunctionInterface {

    private static List<Customer> customerList = new ArrayList<>(); //Lista av typen Customer.  //
    private static Receptionist instance = new Receptionist();  // Singleton instans av Receptionistklassen
    public static Scanner scan;  // global användare av en scanner.

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    private Receptionist() {
    }

    public static Receptionist getInstance() {
        // när instansen av receptionisten hämtas så körs listan igång och skickar användaren till protokollet.
        scan = new Scanner(System.in);
        instance.protocol();
        return instance;
    }


    private void addCustomer() {
        System.out.println("Customer name?");
        String customerName = scan.nextLine();
        checkInput(customerName);
        System.out.println("Customer phone number?");
        String addPhoneNr = scan.nextLine();
        checkInput(addPhoneNr);
        customerList.add(new Customer(customerName, addPhoneNr));
        customerList.get(customerList.size() - 1).addPetNameAndType();
        System.out.println(customerName + " has been added as a customer with a pet " + customerList.get(customerList.size() - 1).getPet().getType() + ".\n");
        //Skickar in customer objektet i metoden writeCustomerInfoToFIle. Använder sig av nuvarande storleken av listan för att skicka rätt index.
        writeCustomerInfoToFile(customerList.get(customerList.size() - 1));

        Receptionist.getInstance().protocol();
    }

    private void checkInput(String input){
        if (input.isBlank() || input == null){
            System.err.println("Invalid input. Try again.");
            addCustomer();
        }
    }


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


    private static void printInformationFromList() {
        System.out.println("Customers in system:");
        System.out.println("Name\t\t\tPhoneNumber\t\tPet Type\tPet Name");

        Iterator var = customerList.iterator();


        while (var.hasNext()) {
            Customer c = (Customer) var.next();
            String name = c.getName();
            String phNumber = c.getPhoneNr();
            String petType = c.getPet().getType();
            String petName = c.getPet().getPetName();

            String paddedName = String.format("%-16s", name);
            String paddedphNumber = String.format("%-16s", phNumber);
            String paddedpetType = String.format("%-12s", petType.trim());
            System.out.println(paddedName + paddedphNumber + paddedpetType + petName);
        }
        System.out.println();
    }

    //Metod som fyller på befintliga kunder i filen till customerList
    protected static void fillCustomerListFromFile() {
        try (Scanner readTextFile = new Scanner(new File("customersInfo"))){
           // Scanner readTextFile = new Scanner(new File("customersInfo"));
            while (readTextFile.hasNextLine()) {

                if (readTextFile.hasNextLine()) {
                    String customerInfo = readTextFile.nextLine();
                    customerList.add(new Customer((customerInfo.substring(0, customerInfo.indexOf(':'))), customerInfo.substring(customerInfo.indexOf(':')+1)));
                    if (readTextFile.hasNextLine()) {
                        String petInfo = readTextFile.nextLine();

                        customerList.get(customerList.size() - 1).setPetType(petInfo.substring(0, petInfo.indexOf(':')), petInfo.substring(petInfo.indexOf(':')+1));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    public void protocol() {
        if(customerList.size()==0) {
            fillCustomerListFromFile(); //Läser in befintliga kunder från filen till customerList.
        }
        printChoices();
        String s = scan.nextLine();
        switch (s) {
            case "1" -> addCustomer();
            // Tar emot states och delegerar användaren till specifika delar av programmet.

            case "2" -> printInformationFromList();

            case "3" -> AnimalHandler.getInstance();
            // Om du vill byta till att vara en djurhanterare istället för receptionist

            default -> System.err.println("Invalid input. Try again");
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

