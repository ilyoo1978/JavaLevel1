package Lesson6.HW6;

import java.util.Random;

class Cat extends Animal{
    private Random rand = new Random();
    public Cat(String name){
        super(name);
        setMaxRunDistance(rand.nextInt(201) + 100);
        setMaxJumpHeight(rand.nextInt(101) + 150);
        if(rand.nextInt(1000) == 1){
            setMaxSwimDistance(rand.nextInt(9) + 7);
        }
    }
    public boolean jump(int height){
        System.out.println("Барьер: " + height + "см.");
        System.out.println("котан " + name + " может прыгнуть на " + maxJumpHeight +"см.");
        System.out.println("jump: " + (height <= maxJumpHeight));
        return height <= maxJumpHeight;
    }
    public boolean run(int distance){
        System.out.println("Дистанция: " + distance);
        System.out.println("котан " + name + " может пробежать " + maxRunDistance +"м.");
        System.out.println("run: " + (distance <= maxRunDistance));
        return distance <= maxRunDistance;
    }
    public boolean swim(int distance){
        System.out.println("Дистанция: " + distance);
        System.out.println("наши котаны не плавают.");
        if(maxSwimDistance != 0){
            System.out.println("Но " + name + " очень редкий кот и может проплыть " + maxSwimDistance + " м.");
        }
        System.out.println("swim: " + (distance <= maxSwimDistance));
        return distance <= maxSwimDistance;
    }

}