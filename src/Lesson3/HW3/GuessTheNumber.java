package Lesson3.HW3;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        playGame();
        scanner.close();
    }
    private static void playGame(){
        Random random = new Random();
        int number = random.nextInt(10);
        int lap = 1;
        int guessNum;
        int yesNo;
        System.out.println("Ваша задача угадать число от 0 до 9, у вас 3 попытки.");
        do{
            System.out.println("попытка №" + lap);
            System.out.println("Введите число от 0 до 9.");
            guessNum = scanner.nextInt();
            if(guessNum == number){
                System.out.println("Вы угадали!");
                break;
            }else {
                String msg = guessNum > number ? "Загаданное число меньше.": "Загаданное число больше.";
                System.out.println(msg);
            }
            lap++;
        }while (lap < 4);
        do{
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            yesNo = scanner.nextInt();
        }while (yesNo != 1 && yesNo != 0);
        if(yesNo == 1){
            playGame();
        }else {
            System.out.println("Спасибо, до свидания!");
        }
    }
}
