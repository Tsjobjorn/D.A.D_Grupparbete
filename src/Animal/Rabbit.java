package Animal;



public class Rabbit extends Animal {

    public Rabbit(String name){
        super(AnimalFood.VEGETABLES, 0.8);
        setPetName(name);
        setType("Rabbit");
    }

    @Override
    public void getFoodInfo(){
        System.out.println(getPetName() + " needs to have " + getMängd() + "kg " + getFoodType() +
                ". Press enter when you've fed " + getPetName());
    }
}
