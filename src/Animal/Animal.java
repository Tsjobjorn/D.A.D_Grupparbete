package Animal;

import LoggingTool.AnimalFood;

public abstract class Animal implements AnimalInterface{


    String petName;
    boolean fed=false;
    boolean walked=false;

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

    public boolean isWalked() {
        return walked;
    }

    public void setWalked(boolean walked) {
        this.walked = walked;
    }

    @Override
    public void getFoodInfo(){
        System.out.println("Default foodInfo");
    }
}
