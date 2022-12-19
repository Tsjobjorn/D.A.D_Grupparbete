import Person.Customer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadInputFromUser {
    static ReadInputFromUser read = new ReadInputFromUser();

    private ReadInputFromUser() {
    }

    public static ReadInputFromUser getInstance() {
        return read;
    }

    protected void addCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Customer name?");
        String customerName = scan.nextLine();
        checkInput(customerName);
        System.out.println("Customer phone number?");
        String addPhoneNr = scan.nextLine();
        checkInput(addPhoneNr);
        Receptionist.customerList.add(new Customer(customerName, addPhoneNr));
        Receptionist.customerList.get(Receptionist.customerList.size() - 1).addPetNameAndType();
        System.out.println(customerName + " has been added as a customer with a pet " +
                Receptionist.customerList.get(Receptionist.customerList.size() - 1).getPet().getType() + ".\n");

        //Skickar in customer objektet i metoden writeCustomerInfoToFIle. Använder sig av
        // nuvarande storleken av listan för att skicka rätt index.

        WriteToFile.getInstance().writeCustomerInfoToFile(Receptionist.customerList.get(Receptionist.customerList.size() - 1));

        Receptionist.getInstance().protocol();
    }

    protected void readFillCustomerListFromFile() {

        try (Scanner readTextFile = new Scanner(new File(FilePath.CUSTOMER_INFO_FILE.data))) {
            // Scanner readTextFile = new Scanner(new File("customersInfo"));
            while (readTextFile.hasNextLine()) {

                if (readTextFile.hasNextLine()) {
                    String customerInfo = readTextFile.nextLine();
                    Receptionist.customerList.add(
                            new Customer((customerInfo.substring(0,
                                    customerInfo.indexOf(':'))),
                                    customerInfo.substring(customerInfo.indexOf(':') + 1)));

                    if (readTextFile.hasNextLine()) {
                        String petInfo = readTextFile.nextLine();

                        Receptionist.customerList.get(
                                Receptionist.customerList.size() - 1).setPetType(petInfo.substring(0,
                                        petInfo.indexOf(':')),
                                petInfo.substring(petInfo.indexOf(':') + 1));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void checkInput(String input) {
        if (input.isBlank()) {
            System.err.println("Invalid input. Try again.");
            addCustomer();
        }
    }
}
