public enum FilePath {
    // enums med states om du är receptionist eller djurhanterare.

    CUSTOMER_INFO_FILE("customersInfo");

    final String filePath;

    FilePath(String s) {
        filePath = s;
    }
}
