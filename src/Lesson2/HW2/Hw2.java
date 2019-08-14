package Lesson2.HW2;

import java.util.Arrays;

public class Hw2 {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        int []arr = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(arr));
        int []arr1 = {1, 2, 3, 4, 5};
        shiftArrNSteps(arr1, -3);


        int []arrOfRandoms = new int[10];
        for (int i = 0; i < arrOfRandoms.length; i++){
            arrOfRandoms[i] = (int)((Math.random()) * 100);
        }
        System.out.println("исходный массив:        " + Arrays.toString(arrOfRandoms));
        System.out.println("отсортированный массив: " + Arrays.toString(sort(arrOfRandoms)));
    }
    //задание 1
    private static void task1(){
        int []arr = new int[10];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)Math.round(Math.random());
        }
        System.out.println("исходный массив:        " + Arrays.toString(arr));
        for(int i = 0; i < arr.length; i++){
            switch (arr[i]){
                case 1:
                    arr[i] = 0;
                    break;
                case 0:
                    arr[i] = 1;
                    break;
            }
        }
        System.out.println("преобразованный массив: " + Arrays.toString(arr));
    }
    //задание 2
    private static void task2(){
        int []arr = new int[8];
        for(int i = 0, j = 0; i < arr.length; i++, j += 3){
            arr[i] = j;
        }
        System.out.println("задание 2: " + Arrays.toString(arr));
    }
    //задание 3
    private static void task3(){
        int []arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int i = 0;
        for(int num: arr){
           arr[i] = num < 6? num * 2: num;
           i++;
        }
        System.out.println("задание 3: " + Arrays.toString(arr));
    }
    //задание 4
    private static void task4(){
        int [][]sqrArr = new  int[5][5];
        for(int i = 0; i < sqrArr.length; i++){
            sqrArr[i][i] = 1;
            for(int num: sqrArr[i]) {
                System.out.print(num);
            }
            System.out.println();
        }
    }
    //задание 5
    private static void task5(){
        int []arr = new int[10];
        int max = 0, min = 100;
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)((Math.random()) * 100);
        }
        System.out.println("задание 5: " + Arrays.toString(arr));
        for(int num: arr){
            min = num < min? num : min;
            max = num > max? num : max;
        }
        System.out.println("min = " + min + "; max = " + max);
    }
    //задание 6
    private static boolean checkBalance(int []arr){
        for (int i = 0; i < arr.length; i++){
            int sumLeft = 0,
                sumRight = 0;
            for(int j = i; j >= 0; j--){
                sumLeft += arr[j];
            }
            for (int j = i + 1; j <= arr.length - 1; j++){
                sumRight += arr[j];
            }
            if (sumLeft == sumRight){
                return true;
            }
       }
        return false;
    }
    //задание 7
    private static void shiftArrNSteps(int []arr, int n){
        int temp;
        if(n > 0) {
            for (int j = 0; j < n; j++) {
                temp = arr[arr.length - 1];
                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = temp;
            }
        }else {

            for (int j = 0; j > n; j--) {
                temp = arr[0];
                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    //задание 8
    private static int[] sort(int []arr){
        for(int i = arr.length; i > 1; i--){
            for(int j = 0; j < i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    arr[j] += arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] -= arr[j + 1];
                }

            }
        }
        return arr;
    }

}
