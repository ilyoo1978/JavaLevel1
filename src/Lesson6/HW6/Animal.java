package Lesson6.HW6;

import java.util.Random;

public abstract class Animal {
    protected String name;
    protected int maxRunDistance;// cчитается в метрах
    protected int maxJumpHeight;//считается в сантиметрах
    protected int maxSwimDistance;//в метрах
    public Animal(String name){
        this.name = name;
    }

    void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    void setMaxJumpHeight(int maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    void setMaxSwimDistance(int maxSwimDistance) {
        this.maxSwimDistance = maxSwimDistance;
    }

    abstract boolean jump(int height);
    abstract boolean run(int distance);
    abstract boolean swim(int distance);
}
