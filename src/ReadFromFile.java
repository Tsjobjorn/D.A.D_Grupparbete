import Person.Customer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFromFile {
    private static final ReadFromFile read = new ReadFromFile();

    private ReadFromFile() {
    }

    public static ReadFromFile getInstance() {
        return read;
    }

    protected void readFillCustomerListFromFile() {

        try (Scanner readTextFile = new Scanner(new File(FilePath.CUSTOMER_INFO_FILE.data))) {
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

    protected void checkInput(String input) {
        if (input.isBlank()) {
            System.err.println("Invalid input. Try again.");
            Receptionist.getInstance().addCustomer();
        }
    }
}
