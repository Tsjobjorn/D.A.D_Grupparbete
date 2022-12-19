package Animal;


public abstract class Animal implements AnimalInterface{


    String petName;
    boolean fed=false;


    AnimalFood foodType;

    public double getMängd() {
        return mängd;
    }

    public AnimalFood getFoodType() {
        return foodType;
    }

    double mängd;

    public Animal(AnimalFood foodType, double mängd) {
        this.foodType = foodType;
        this.mängd = mängd;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    String Type;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public boolean isFed() {
        return fed;
    }

    public void setFed(boolean fed) {
        this.fed = fed;
    }

    @Override
    public void getFoodInfo(){
        System.out.println("Default foodInfo");
    }
}
