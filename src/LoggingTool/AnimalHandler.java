package LoggingTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalHandler implements ProtocolFunctionInterface {
    private static final List<String> fedAndWalkedAniamls = new ArrayList<>();
    private static final List<String> animalsNotFedNorWalked = new ArrayList<>();
    private static AnimalHandler instance = new AnimalHandler();

    static Scanner scan;

    private AnimalHandler() {
    }

    public static AnimalHandler getInstance() {
        scan = new Scanner(System.in);
        readData();
        instance.protocol();
        return instance;
    }

    private static void readData() {
        Path path = Paths.get("customersInfo");
        List<String> animalNames = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String[] allAnimals;
            String s;
            int counter;
            while ((s = br.readLine()) != null) {
                counter = s.indexOf(" ");
                animalNames.add(s.substring(counter, s.lastIndexOf(" ")).trim());
                allAnimals = s.split(" ");
                animalsNotFedNorWalked.add(allAnimals[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void protocol() {
        printChoices();
        switch (scan.nextLine()) {
            case "1" -> walkAnimalAndFeed();
            case "2" -> informationAnimal();
            case "3" -> Receptionist.getInstance();
            default -> System.out.println("Invalid input. Try again");
        }
        protocol();
    }

    @Override
    public void printChoices() {
        System.out.println("""
                Press 1 to walk and feed animal
                Press 2 to get information
                Press 3 to go to reception""");
    }

    private static void informationAnimal() {

        if (fedAndWalkedAniamls.size() > 0) {
            System.out.println("Animals that have been walked and fed: " + fedAndWalkedAniamls);
        } else {
            System.out.println("No animal has been walked yet");
        }

        if (animalsNotFedNorWalked.size() > 0) {
            System.out.println("Still not walked and fed animals: " + animalsNotFedNorWalked);
        } else {
            System.out.println("All animals have been walked and fed");
        }
        getInstance().protocol();
    }

    private static void walkAnimalAndFeed() {
        while (true) {
            System.out.println("which animal would you like to walk and feed?");
            String s = scan.nextLine();
            for (String value : animalsNotFedNorWalked) {
                if (value.equals(s)) {
                    fedAndWalkedAniamls.add(value);
                    removeFromList(value);
                    getInstance().protocol();
                    break;
                }
            }
            System.out.println("No animal with that name exist, try again");
        }
    }

    private static void removeFromList(String name) {
        animalsNotFedNorWalked.removeIf(name::equals);
        walkedAnimals(fedAndWalkedAniamls);
    }

    public static void walkedAnimals(List<String> walkedAnimals) {
        Path path = Paths.get("WalkedAndFetAnimals");
        try (BufferedWriter br = Files.newBufferedWriter(path)) {
            br.write(walkedAnimals.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
