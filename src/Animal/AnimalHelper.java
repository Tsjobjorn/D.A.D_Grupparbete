package Animal;

import javax.xml.crypto.Data;

public class AnimalHelper {
    public AnimalInterface bird;
    public AnimalInterface cat;
    public AnimalInterface dog;
    public AnimalInterface rabbit;

    public AnimalHelper(){
        bird = new Bird();
        cat = new Cat();
        dog = new Dog();
        rabbit = new Rabbit();
    }

    public void feedBird(){
        bird.feed();
    }

    public void feedCat(){
        cat.feed();
    }

    public void feedDog(){
        dog.feed();
    }

    public void feedRabbit(){
        rabbit.feed();
    }


}
