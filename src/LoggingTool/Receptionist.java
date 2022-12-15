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

import static LoggingTool.AnimalType.*;

public class Receptionist implements ProtocolFunctionInterface {
    private static List<String> customerInformation;
    private static Receptionist instance = new Receptionist();
    public static Scanner scan;

    private Receptionist() {

    }

    public static Receptionist getInstance() {
        scan = new Scanner(System.in);
        customerInformation = new ArrayList<>();
        instance.protocol();
        return instance;
    }

    private static void addCustomer() {
        String s;
        System.out.println("Give me customer name");
        s = scan.nextLine();
        String name = s;
        customerInformation.add(s);

        System.out.println("Give me customer's animals name");
        s = scan.nextLine();
        String animalName = s;
        customerInformation.add(s);

        System.out.println("Enter the animaltype: ");
        s = scan.nextLine();
        String animaltype = s;
        checkAnimalType(animaltype);


        System.out.println("Give me customers phone number");
        s = scan.nextLine();
        customerInformation.add(s);
        String phoneNumber = s;
        if (name != null && animalName != null && phoneNumber != null) {
            writeCustomerInfoToFile(name, animalName, phoneNumber);
        }
        Receptionist.getInstance().protocol();
    }

    private static void writeCustomerInfoToFile(String customerName, String animal, String phoneNumber) {
        File file = new File("customersInfo");
        List<String> information = new ArrayList<>();
        information.add(customerName + " " + animal + " " + phoneNumber);
        try (FileWriter fw = new FileWriter(file, true)) {
            for (String s : information) {
                fw.write(s);
                fw.write("\n");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void checkAnimalType(String s){

        AnimalType[] animalTypes = AnimalType.values();
        while (true) {
            for (AnimalType animalType : animalTypes) {

                if (s.equals(animalType.toString())) {
                    customerInformation.add(s);
                }

            }
        }
    }

    private static void printInformationFromList() {
        Path path = Paths.get("customersInfo");
        try {
            System.out.println(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Receptionist.getInstance().protocol();
        }

    }

    @Override
    public void protocol() {
        printChoices();
        String s = scan.nextLine();
        switch (s) {
            case "1" -> addCustomer();
            case "2" -> printInformationFromList();
            case "3" -> AnimalHandler.getInstance();
            default -> System.out.println("Invalid input. Try again");
        }
        protocol();
    }

    @Override
    public void printChoices() {
        System.out.println("""
                1 to add customer
                2 to add print customer information
                3 to go to Animal handler""");
    }
}
