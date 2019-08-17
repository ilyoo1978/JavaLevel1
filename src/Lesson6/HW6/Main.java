package Lesson6.HW6;

import java.util.Random;

class Main{
    public static void main(String[] args) {
        Random rand = new Random();
        String []dogNames = {"Snoopy", "Sharik", "Bobby"};
        String []catNames = {"Tommy", "Barsik", "Matroskin"};
        Dog []dogs = new Dog[dogNames.length];
        Cat []cats = new Cat[catNames.length];
        for(int i = 0; i < dogNames.length; i++){
            dogs[i] = new Dog(dogNames[i]);
        }
        for (int i = 0; i < catNames.length; i++){
            cats[i] = new Cat(catNames[i]);
        }
        for (Dog dog: dogs){
            dog.run(rand.nextInt(201) + 400);
            dog.jump(rand.nextInt(51) + 50);
            dog.swim(rand.nextInt(9) + 7);
        }
        for(Cat cat: cats){
            cat.run(rand.nextInt(201) + 100);
            cat.jump(rand.nextInt(101) + 150);
            cat.swim(rand.nextInt(9) + 7);
        }

    }
}