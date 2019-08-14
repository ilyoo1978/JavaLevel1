package Lesson4.HW4;

import java.util.Random;
import java.util.Scanner;

public class Cross {
    static final int WIN_LINE_SIZE = 4;
    static final int SIZE_X = 5;
    static final int SIZE_Y = 5;

    static char[][] field = new char[SIZE_Y][SIZE_X];

    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = 'O';
    static final char EMPTY_DOT = '.';

    static Scanner scanner = new Scanner(System.in);
    static final Random rand = new Random();

    public static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }

    }

    public static void PrintField() {
        System.out.println("-----------");
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    public static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    public static void playerStep() {
        int x,y;
        do {
            System.out.println("Введите координаты: X Y (1-" + SIZE_X + ")");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));

        setSym(y, x, PLAYER_DOT);
    }

    public static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }



    public static boolean checkWin(char sym) {
        for (int x = 0; x < SIZE_X; x++){ //проверка по вертикали
            int counter = 1;
            for(int y = 0; y < SIZE_Y - 1; y++){
                if(field[y][x] == sym && field[y + 1][x] == sym) {
                    counter++;
                }else{
                    counter = 1;
                }
                if(counter >= WIN_LINE_SIZE){
                    return true;
                }
            }
        }
        for(int y = 0; y < SIZE_Y; y++){ //проверка по горизонтали
            int counter = 1;
            for (int x = 0; x < SIZE_X - 1; x++){
                if(field[y][x] == sym && field[y][x + 1] == sym){
                    counter++;
                }else{
                    counter = 1;
                }
                if(counter >=WIN_LINE_SIZE){
                    return true;
                }
            }
        }
        for (int i = 0; i <= SIZE_X - WIN_LINE_SIZE; i++){//проверка диагонали юго-восток, првая часть
            int counter = 1;
            for(int y = 0, x = i; x < SIZE_X - 1; y++, x++){
                if(field[y][x] == sym && field[y + 1][x +1] == sym) {
                    counter++;
                }else{
                    counter = 1;
                }
                if(counter >=WIN_LINE_SIZE){
                    return true;
                }
            }
        }

        for (int i = 0; i <= SIZE_Y - WIN_LINE_SIZE; i++){//проверка диагонали юго-восток, левая часть
            int counter = 1;
            for(int y = i, x = 0; y < SIZE_Y - 1; y++, x++){
                if(field[y][x] == sym && field[y + 1][x +1] == sym) {
                    counter++;
                }else{
                    counter = 1;
                }
                if(counter >=WIN_LINE_SIZE){
                    return true;
                }
            }
        }

        for (int i = 0; i <= SIZE_X - WIN_LINE_SIZE; i++){//проверка диагонали северо-восток6 правая часть
            int counter = 1;
            for(int y = field.length - 1, x = i; x < SIZE_X - 1; y--, x++){
                if(field[y][x] == sym && field[y - 1][x +1] == sym) {
                    counter++;
                }else{
                    counter = 1;
                }
                if(counter >=WIN_LINE_SIZE){
                    return true;
                }
            }
        }

        for (int i = field.length - 1; i >= WIN_LINE_SIZE; i--){//проверки диагонали северо восток, левая часть
            int counter = 1;
            for(int y = i, x = 0; y > 0; y--, x++){
                if(field[y][x] == sym && field[y - 1][x + 1] == sym) {
                    counter++;
                }else{
                    counter = 1;
                }
                if(counter >=WIN_LINE_SIZE){
                    return true;
                }
            }
        }

        return false;
    }


    public static void aiStep() {
        int x,y;
        int []winCoordinates = canAiWin();
        int []defendCoordinates = canPlayerWin();
        int []preWinCoordinates = canAiMakePreWin();
        int []defendPreWinCoordinates = canPlayerMakePreWin();
        int []centerCoordinates = findCenter();

        if(winCoordinates[0] != -1){
            setSym(winCoordinates[0], winCoordinates[1], AI_DOT);
            return;
        }
        if(defendCoordinates[0] != -1){
            setSym(defendCoordinates[0], defendCoordinates[1], AI_DOT);
            return;
        }
        if(isCellValid(centerCoordinates[0], centerCoordinates[1])){
            setSym(centerCoordinates[0], centerCoordinates[1], AI_DOT);
            return;
        }
        if(defendPreWinCoordinates[0] != -1){
            setSym(defendPreWinCoordinates[0], defendPreWinCoordinates[1], AI_DOT);
            return;
        }
        if(preWinCoordinates[0] != -1){
            setSym(preWinCoordinates[0], preWinCoordinates[1], AI_DOT);
            return;
        }
        if(WIN_LINE_SIZE > 3){
            int []move = move();
            if(move[0] != -1){
                setSym(move[0], move[1], AI_DOT);
                return;
            }
        }
        do {
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_Y);
        } while (!isCellValid(y,x));

        setSym(y, x, AI_DOT);
    }

    public static int[] canAiWin(){
        return findMove(AI_DOT, 1);
    }
    public static int[] canPlayerWin(){
        return findMove(PLAYER_DOT, 1);
    }
    public static int[] canAiMakePreWin(){
        return findMove(AI_DOT, 2);
    }
    public static int[] canPlayerMakePreWin(){
        return findMove(PLAYER_DOT, 2);
    }
    public static int[] move(){
        return findMove(AI_DOT, 3);
    }
    public static int[] findCenter(){
        int []center = new int[2];
        center[0] = SIZE_X % 2 == 0? SIZE_X / 2: (SIZE_X - 1) / 2;
        center[1] = SIZE_Y % 2 == 0? SIZE_Y / 2: (SIZE_Y - 1) / 2;

        return center;
    }
    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }

        return (field[y][x] == EMPTY_DOT);
    }

    public static int[] findMove(char sym, int dangerQ){ //возвращает победные координаты, если первая координата = -1, то значит нет комбинации
        int []winCoordinates = new int[2];
        for(int i = 0; i <= SIZE_X - WIN_LINE_SIZE; i++){ // проверка по диагонали направление юго-восток правая половина поля
            for(int a = 0, b = i; b <= SIZE_X - WIN_LINE_SIZE; a++, b++){
                int counter = 0;
                for(int j = 0, y = a, x = b; j < WIN_LINE_SIZE; j++, y++, x++){
                    if(field[y][x] == sym){
                        counter++;
                    }
                    if(field[y][x] != EMPTY_DOT && field[y][x]!= sym){
                        counter = 0;
                        break;
                    }
                    if(field[y][x] == EMPTY_DOT){
                        winCoordinates[0] = y;
                        winCoordinates[1] = x;
                    }
                }
                if(counter == WIN_LINE_SIZE - dangerQ){
                    return winCoordinates;
                }
            }
        }
        for(int i = 0; i <= SIZE_Y - WIN_LINE_SIZE; i++){ // проверка по диагонали напр юго-восток левая половина поля
            for(int b = 0, a = i; a <= SIZE_Y - WIN_LINE_SIZE; a++, b++){
                int counter =0;
                for(int j = 0, y = a, x = b; j < WIN_LINE_SIZE; j++, y++, x++){
                    if(field[y][x] == sym){
                        counter++;
                    }
                    if(field[y][x] != EMPTY_DOT && field[y][x]!= sym){
                        counter = 0;
                        break;
                    }
                    if(field[y][x] == EMPTY_DOT){
                        winCoordinates[0] = y;
                        winCoordinates[1] = x;
                    }
                }
                if(counter == WIN_LINE_SIZE - dangerQ){
                    return winCoordinates;
                }
            }
        }
        for(int i = 0; i <= SIZE_X - WIN_LINE_SIZE; i++){ // проверка по диагонали напр северо-восток правая половина поля
            for(int a = field.length - 1, b = i; b <= SIZE_X - WIN_LINE_SIZE; a--, b++){
                int counter =0;
                for(int j = 0, y = a, x = b; j < WIN_LINE_SIZE && y > -1 && x < SIZE_X; j++, y--, x++){
                    if(field[y][x] == sym){
                        counter++;
                    }
                    if(field[y][x] != EMPTY_DOT && field[y][x]!= sym){
                        counter = 0;
                        break;
                    }
                    if(field[y][x] == EMPTY_DOT){
                        winCoordinates[0] = y;
                        winCoordinates[1] = x;
                    }
                }
                if(counter == WIN_LINE_SIZE - dangerQ){
                    return winCoordinates;
                }
            }
        }
        for(int i = field.length - 1; i >=WIN_LINE_SIZE; i--){ //проверка диагональ северо-восток левая половина
            for(int b = 0, a = i; a >=WIN_LINE_SIZE; a--, b++){
                int counter = 0;
                for(int j = 0, y = a, x = b; j <= WIN_LINE_SIZE; j++, y--, x++){
                    if(field[y][x] == sym){
                        counter++;
                    }
                    if(field[y][x] != EMPTY_DOT && field[y][x]!= sym){
                        counter = 0;
                        break;
                    }
                    if(field[y][x] == EMPTY_DOT){
                        winCoordinates[0] = y;
                        winCoordinates[1] = x;
                    }
                }
                if(counter == WIN_LINE_SIZE - dangerQ){
                    return winCoordinates;
                }
            }
        }
        for (int x = 0; x < SIZE_X; x++){ //проверка по вертикали
            for(int i = 0; i <= SIZE_Y - WIN_LINE_SIZE; i++){
                int counter = 0;
                for (int y= i, j = 0; j < WIN_LINE_SIZE; j++, y++){
                    if(field[y][x] == sym){
                        counter++;
                    }
                    if(field[y][x] != EMPTY_DOT && field[y][x]!= sym){
                        counter = 0;
                        break;
                    }
                    if(field[y][x] == EMPTY_DOT){
                        winCoordinates[0] = y;
                        winCoordinates[1] = x;
                    }
                }
                if(counter == WIN_LINE_SIZE - dangerQ){
                    return winCoordinates;
                }
            }
        }
        for(int y = 0; y < SIZE_Y; y++){ //проверка по горизонтали
            for (int i = 0; i <= SIZE_X - WIN_LINE_SIZE; i++){
                int counter = 0;
                for (int x = i, j = 0; j < WIN_LINE_SIZE; j++, x++){
                    if(field[y][x] == sym){
                        counter++;
                    }
                    if(field[y][x] != EMPTY_DOT && field[y][x]!= sym){
                        counter = 0;
                        break;
                    }
                    if(field[y][x] == EMPTY_DOT){
                        winCoordinates[0] = y;
                        winCoordinates[1] = x;
                    }
                }
                if(counter == WIN_LINE_SIZE - dangerQ){
                    return winCoordinates;
                }
            }
        }
        winCoordinates[0] = -1; //это тпа флага, если первая координата отрицательная, значит нет выигрышной комбинации
        return winCoordinates;
    }


    public static void main(String[] args) {
        initField();
        PrintField();

        while (true) {
            playerStep();
            PrintField();
            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }
            aiStep();
            PrintField();
            if(checkWin(AI_DOT)) {
                System.out.println("SkyNet WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }
        }
        scanner.close();
    }

}
