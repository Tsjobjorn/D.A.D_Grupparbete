import Person.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Receptionist implements ProtocolFunctionInterface {

    public static List<Customer> customerList = new ArrayList<>(); //Lista av typen Customer.  //
    private static final Receptionist instance = new Receptionist();  // Singleton instans av Receptionistklassen
    private static final Scanner scan = new Scanner(System.in);  // global användare av en scanner.

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    private Receptionist() {
    }

    public static Receptionist getInstance() {
        return instance;
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

    protected void addCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Customer name?");
        String customerName = scan.nextLine();
        ReadInputFromUser.getInstance().checkInput(customerName);
        System.out.println("Customer phone number?");
        String addPhoneNr = scan.nextLine();
        ReadInputFromUser.getInstance().checkInput(addPhoneNr);
        Receptionist.customerList.add(new Customer(customerName, addPhoneNr));
        Receptionist.customerList.get(Receptionist.customerList.size() - 1).addPetNameAndType();
        System.out.println(customerName + " has been added as a customer with a pet " +
                Receptionist.customerList.get(Receptionist.customerList.size() - 1).getPet().getType() + ".\n");

        //Skickar in customer objektet i metoden writeCustomerInfoToFIle. Använder sig av
        // nuvarande storleken av listan för att skicka rätt index.

        WriteToFile.getInstance().writeCustomerInfoToFile(Receptionist.customerList.get(Receptionist.customerList.size() - 1));

        Receptionist.getInstance().protocol();
    }

    //Metod som fyller på befintliga kunder i filen till customerList

    @Override
    public void protocol() {
        if (customerList.size() == 0) {
            ReadInputFromUser.getInstance().readFillCustomerListFromFile(); //Läser in befintliga kunder från filen till customerList.
        }
        printChoices();
        String s = scan.nextLine();
        switch (s) {
            case "1" -> Receptionist.getInstance().addCustomer();
            // Tar emot states och delegerar användaren till specifika delar av programmet.

            case "2" -> printInformationFromList();

            case "3" -> AnimalHandler.getInstance().protocol();
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

