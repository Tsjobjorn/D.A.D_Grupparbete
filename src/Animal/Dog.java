package Animal;

import LoggingTool.AnimalFood;

public class Dog extends Animal{

    public Dog(String name){
        super(AnimalFood.MEAT, 0.5);
        setPetName(name);
        setType("Dog");
    }

    @Override
    public void getFoodInfo(){
        System.out.println(getPetName() + " needs to have " + getMängd() + "kg " + getFoodType() +
                ". Press enter when you've fed the " + getPetName());
    }
}
