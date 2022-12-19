import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        startRun();
    }

    public static void startRun() {
//        PersonFactory pf = PersonFactory.getInstance();
        System.out.println("Press 1 for reception\nPress 2 to animal handler");
        String s = scan.nextLine().trim();
        while (true) {
            if (s.equals("1")) {
                Receptionist.getInstance().protocol();
                return;
            }
            else if (s.equals("2")) {
                AnimalHandler.getInstance().protocol();
                return;
            }
            else {
                System.out.println("Invalid input try again. '1' for reception '2' for handler.");
                s = scan.nextLine().trim();
            }
        }
    }
}