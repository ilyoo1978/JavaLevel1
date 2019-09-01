package Lesson7.HW7;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Plate plate = new Plate(20);
        String []catNames = {"Tommy", "Barsik", "Matroskin"};
        Cat []cats = new Cat[catNames.length];
        for (int i = 0; i < catNames.length; i++){
            cats[i] = new Cat(catNames[i], rand.nextInt(10) + 5);
        }
        for(Cat cat: cats){
            cat.eat(plate);
            if(!cat.fulnessInfo()){
                plate.fillThePlate();
                cat.eat(plate);
                cat.fulnessInfo();
            }
        }
    }
}

