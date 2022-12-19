package LoggingTool;

public enum Data {
    // enums med states om du är receptionist eller djurhanterare.

    RECEPTIONIST ("1"),
    HANDLER("2");

    final String data;

    Data(String s) { data = s;}
}
