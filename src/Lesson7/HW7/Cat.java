package Lesson7.HW7;

public class Cat {
    private String name;
    private int appetite;
    private boolean fullness = false;

    public Cat(String name, int appetite){
        this.name = name;
        this.appetite = appetite;
    }
    public void eat(Plate p){
        fullness = p.decreaseFood(appetite);
        if(!fullness){
            System.out.println("Please, fill the plate!");
        }
    }
    public boolean fulnessInfo(){
        String result = fullness? " doesn't want to eat.": " is hungry.";
        System.out.println(name + result);
        return fullness;
    }
}

