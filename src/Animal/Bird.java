package Animal;

import LoggingTool.AnimalFood;

public class Bird extends Animal{

    public Bird(String name){
        super(AnimalFood.SEEDS, 0.3);
        setPetName(name);
        setType("Bird");
    }

    @Override
    public void getFoodInfo(){
        System.out.println(getPetName() + " needs to have " + getMängd() + "kg " + getFoodType() +
                ". Press enter when you've fed the " + getPetName());
    }


}
