package LoggingTool;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        startRun();
    }

    public static void startRun() {
        PersonFactory pf = PersonFactory.getInstance();
        System.out.println("    **************");
        System.out.println("Press 1 for receptionist\nPress 2 to animal handler");
        String s = scan.nextLine().trim();
        while (true) {
            switch (s) {
                case "1" -> pf.getProtocolFunctions(Data.RECEPTIONIST);
                case "2" -> pf.getProtocolFunctions(Data.HANDLER);
                default -> System.err.println("Invalid input try again. '1' for reception '2' for handler.");
            }
            s = scan.nextLine();

            //TODO: Släng in kommentarer
        }
    }
}