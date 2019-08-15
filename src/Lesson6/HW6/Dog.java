package Lesson6.HW6;

import java.util.Random;
class Dog extends Animal{
    private Random rand = new Random();
    public Dog(String name){
        super(name);
        setMaxRunDistance(rand.nextInt(201) + 400);
        setMaxJumpHeight(rand.nextInt(51) + 50);
        setMaxSwimDistance(rand.nextInt(9) + 7);
    }
    public boolean jump(int height){
        System.out.println("Барьер: " + height + "см.");
        System.out.println("собакен " + name + " может прыгнуть на " + maxJumpHeight +"см.");
        System.out.println("jump: " + (height <= maxJumpHeight));
        return height <= maxJumpHeight;
    }
    public boolean run(int distance){
        System.out.println("Дистанция: " + distance);
        System.out.println("собакен " + name + " может пробежать " + maxRunDistance +"м.");
        System.out.println("run: " + (distance <= maxRunDistance));
        return distance <= maxRunDistance;
    }
    public boolean swim(int distance){
        System.out.println("Дистанция: " + distance);
        System.out.println("собакен " + name + " может проплыть " + maxSwimDistance +"м.");
        System.out.println("swim: " + (distance <= maxSwimDistance));
        return distance <= maxSwimDistance;
    }
}