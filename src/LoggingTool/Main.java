package LoggingTool;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        startRun();
    }

    public static void startRun() {
        PersonFactory pf = PersonFactory.getInstance();
        System.out.println("Press 1 for receptionist\nPress 2 to animal handler");
        String s = scan.nextLine().trim();
        while (true) {
            switch (s) {
                case "1" -> pf.getProtocolFunctions(Data.RECEPTIONIST);
                case "2" -> pf.getProtocolFunctions(Data.HANDLER);
                default -> System.out.println("Invalid input try again. '1' for reception '2' for handler.");
            }
            s = scan.nextLine();

            //TODO: Släng in kommentarer
        }
    }














































    /*public static void receptionistProtocol() {
        System.out.println("1 to add customer");
        System.out.println("2 to add print customer information");
        System.out.println("3 for animal handler");
        String s = scan.nextLine();
        switch (s) {
            case "1" -> addCustomer();
            case "2" -> printInformationFromList();
//            case "3" -> animalHandlerProtocol();
        }
    }*/

    /*private static void animalHandlerProtocol() {
        System.out.println("Press 1 to feed animal.\nPress 2 to walk animal\nPress 3 to get information");
        switch (scan.nextLine()) {
            case "1" -> feedAnimal();
            case "2" -> walkAnimal();
            case "3" -> informationAnimal();
        }
    }

    private static void informationAnimal() {
        System.out.println("Which animal would you like to have information about?");
        String s = scan.nextLine();
        for (String fedAndWalkedAnimal : customerInformation) {
            if (fedAndWalkedAnimal.contains(s)) {
                System.out.println(s + " Has been walked and fed");
                animalHandlerProtocol();
                break;
            }
        }
        System.out.println(s + " has not been walked and fed");
        animalHandlerProtocol();
    }

    private static void walkAnimal() {
        while (true) {
            System.out.println("which animal would you like to walk?");
            String s = scan.nextLine();
            for (String value : customerInformation) {
                if (value.equals(s)) {
                    fedAndWalkedAniamls.add(value + " Has been walked");
                    animalHandlerProtocol();
                    break;
                }
            }
            System.out.println("No animal with that name exist, try again");
        }
    }

    private static void feedAnimal() {

        while (true) {
            System.out.println("which animal would you like to feed?");
            String s = scan.nextLine();
            for (String value : customerInformation) {
                if (value.equals(s)) {
                    fedAndWalkedAniamls.add(value + " Has been fed");
                    animalHandlerProtocol();
                    break;
                }
            }
            System.out.println("No animal with that name exist, try again");
        }
    }*/


    /*public static void addCustomer() {
        String s;
        System.out.println("Give me customer name");
        s = scan.nextLine();
        customerInformation.add(s);

        System.out.println("Give me customer's animals name");
        s = scan.nextLine();
        customerInformation.add(s);

        System.out.println("Give me customers phone number");
        s = scan.nextLine();
        customerInformation.add(s);

        System.out.println("Press '1' to add another customer or '2' to print information");
        receptionistProtocol();
    }

    public static void printInformationFromList() {
        for (String s : customerInformation) {
            System.out.println(s);
        }
        receptionistProtocol();
    }

    public static void handlerProtocol() {
        System.out.println("Handler");
    }*/
}