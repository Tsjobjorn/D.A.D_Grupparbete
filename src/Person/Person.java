package Person;

public abstract class Person {
    String name;
    String phoneNr;

    public String getName() {
        return name;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public Person(){}
    public Person(String name, String phoneNr){
        this.name = name;
        this.phoneNr = phoneNr;
    }
}
