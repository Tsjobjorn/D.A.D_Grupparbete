public enum FilePath {
    // enums med states om du är receptionist eller djurhanterare.

    CUSTOMER_INFO_FILE("customersInfo");

    final String data;

    FilePath(String s) {
        data = s;
    }
}
