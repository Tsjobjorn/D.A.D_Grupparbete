package Animal;

import LoggingTool.AnimalFood;

public class Cat extends Animal{

    public Cat(String name){
        super(AnimalFood.FISH, 0.4);
        setPetName(name);
        setType("Cat");
    }

    @Override
    public void getFoodInfo(){
        System.out.println(getPetName() + " needs to have " + getMängd() + "kg " + getFoodType() +
                ". Press enter when you've fed " + getPetName());
    }


}
