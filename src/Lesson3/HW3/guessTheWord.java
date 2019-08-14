package Lesson3.HW3;

import java.util.Random;
import java.util.Scanner;

public class guessTheWord {
    private static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    public static void main(String[] args) {
        playGame();
        scanner.close();
    }
    private static void playGame(){
        int counter = 1;
        String guessWord;
        boolean win;
        String targetWord = words[random.nextInt(words.length)];
        printArr();
        do {
            System.out.println("Угадайте какое слово загадала ЭВМ?\nВведите свой вариант.\nпопытка номер " + counter + ":");
            guessWord = scanner.nextLine();
            win = checkGuessWord(targetWord, guessWord);
            counter++;

        }while (!win);
        if(playAgain()){
            playGame();
        }
    }
    private static void printArr(){
        int counter = 0;
        System.out.println("ЭВМ загадала одно из этих слов:\n");
        for(String word: words){
            System.out.print(word + ", ");
            counter++;
            if(counter % 5 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }

    private static boolean checkGuessWord(String target, String guess) {
        if (target.equals(guess)) {
            System.out.println("win!");
            return true;
        } else {
            printArr();
            System.out.println("ваша предыдущая попытка:");
            System.out.println(guess);
            for (int i = 0; i < 15; i++) {
                if (i < target.length() && i < guess.length()) {
                    if (target.charAt(i) == guess.charAt(i)) {
                        System.out.print(target.charAt(i));
                        continue;
                    }
                }
                System.out.print("#");
            }
            System.out.println();
        }
        return false;
    }

    private static boolean playAgain(){
        String yesNo;
        do{
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            yesNo = scanner.nextLine();
        }while (!yesNo.equals("1") && !yesNo.equals("0"));
        if(yesNo.equals("1")){
            return true;
        }else {
            System.out.println("Спасибо, до свидания!");
            return false;
        }
    }
}
