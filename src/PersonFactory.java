/*
public class PersonFactory {  // factory design pattern

    private static final PersonFactory instance = new PersonFactory();

    private PersonFactory() {
    }

    public static PersonFactory getInstance(){
        return instance;
    }


    public ProtocolFunctionInterface getProtocolFunctions(Data person) {

        if (person == null) {
            return null;
        }

        return switch (person) {
            // hämtar en singleton state för receptions klassen eller djurhanterare
            case RECEPTIONIST -> Receptionist.getInstance().protocol();
            case HANDLER -> AnimalHandler.getInstance();
        };
    }
}
*/
