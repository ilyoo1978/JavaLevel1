package Lesson1.HW1;

public class HW1 {

    public static void main(String[] args) {
        byte a = 127;
        short b = 32767;
	    int c = 100;
	    long d = 100000L;
	    float e = 3.14f;
        double f = 156.76;
        char g = '$';
	    boolean isTrue = true;
        System.out.println(calculateTask3(a, b, c, d));
        System.out.println(isSumInRange(34, 17));
        isPositive(-56);
        System.out.println(isNegative(67));
        sayHelloTo("Moto");
        isLeap(3004);
    }
//задание №8
    private static void isLeap(int year){
        if(!(year % 4 == 0) || (year % 100 == 0) && !(year % 400 == 0)){
            System.out.println(year + " это не високосный год.");
        }else{
            System.out.println(year + " это високосный год.");
        }
    }
//задание №7
    private static void sayHelloTo(String name){
        System.out.println("Hello, " + name + "!");
    }
//задание №6
    private  static boolean isNegative(int a){
        return a < 0;
    }
//задание №5
    private static void isPositive(int a){
        if(a < 0){
            System.out.println(a + " - Это отрицательное чило.");
        }else {
            System.out.println(a + " - Это положительное число.");
        }
    }
//задание №4
    private static boolean isSumInRange(int a, int b){
        return a + b >= 10 && a + b <= 20;
    }
//задание №3
    private static double calculateTask3(double a, double b, double c, double d){
        return a * (b + (c / d));
    }
}
