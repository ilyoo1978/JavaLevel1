package Lesson7.HW7;

public class Plate {
    private int foodCapacity;
    private int food;

    public Plate(int foodCapacity){
        this.food = foodCapacity;
        this.foodCapacity = foodCapacity;
    }

    public boolean decreaseFood(int appetite){
        if(food >= appetite){
            food -= appetite;
            return true;
        }
        return false;
    }
    public void fillThePlate(){
        food = foodCapacity;
    }

    public int info(){
        System.out.println("Plate: " + food);
        return food;
    }
}

