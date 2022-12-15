package LoggingTool;

import Person.*;
import Animal.*;

public class PersonFactory {  // factory design pattern

    /*
    https://studentportal.nackademin.se/pluginfile.php/273055/course/section/54890/Java22-OOAD-Lektion-04-v2.pdf
    Sida: 6 - Factory - Class Diagram
     */
    private static PersonFactory instance = new PersonFactory();

    private PersonFactory() {
    }

    public static PersonFactory getInstance(){
        if(instance == null){
            instance = new PersonFactory();
        }
        return instance;
    }



    public ProtocolFunctionInterface getProtocolFunctions(Data person) {

        if (person == null) {
            return null;
        }

        return switch (person) {
            // hämtar en singleton state för receptions klassen eller djurhanterare
            case RECEPTIONIST -> Receptionist.getInstance();
            case HANDLER -> AnimalHandler.getInstance();
        };
    }
}
