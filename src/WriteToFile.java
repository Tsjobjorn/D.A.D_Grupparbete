import Person.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private static final WriteToFile write = new WriteToFile();
    private WriteToFile() {
    }

    public static WriteToFile getInstance(){
        return write;
    }

    protected void writeCustomerInfoToFile(Customer c) {
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
}
