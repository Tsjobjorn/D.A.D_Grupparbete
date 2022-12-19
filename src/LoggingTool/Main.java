package LoggingTool;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        startRun();
    }

    public static void startRun() {
        PersonFactory pf = PersonFactory.getInstance();
        System.out.println("Press 1 for reception\nPress 2 to animal handler");
        String s = scan.nextLine().trim();

        while (true) {
            if (Data.RECEPTIONIST.data.equals(s)) {
                pf.getProtocolFunctions(Data.RECEPTIONIST);
            }
            else if (Data.HANDLER.data.equals(s)) {
                pf.getProtocolFunctions(Data.HANDLER);
            }
            else {
                System.out.println("Invalid input try again. '1' for reception '2' for handler.");
                s = scan.nextLine().trim();
            }
        }
    }
}