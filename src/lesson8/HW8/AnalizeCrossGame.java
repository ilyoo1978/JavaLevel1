package lesson8.HW8;

public class AnalizeCrossGame {
    private char[][]field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLineLength;
    private char playerDot;
    private char aiDot;
    private char emptyDot;

    public AnalizeCrossGame(char[][]field, int fieldSizeX, int fieldSizeY, int winLineLength, char playerDot, char aiDot, char emptyDot) {
        this.field = field;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLineLength = winLineLength;
        this.playerDot = playerDot;
        this.aiDot = aiDot;
        this.emptyDot = emptyDot;
    }

    public int[]findMove(){
        int[]coordinates = canSomeOneWin(aiDot);
        if(coordinates[0] != -1){
            return coordinates;
        }
        coordinates = canSomeOneWin(playerDot);
        if(coordinates[0] != -1){
            return coordinates;
        }
//        coordinates = smartMove();
        coordinates[0] = -1;
        return coordinates;
    }

    public void printAllPossibleLines(){
        int counter = 1;
        char[][][]allLines = getAllLines();
        for (char[][]line : allLines){
            System.out.print(counter + ": ");
            for (char[]sym: line){
                System.out.print(sym[0]);
            }
            System.out.println();
            counter++;
        }
    }

    public boolean checkWin(char sym){
        for(char[][]line : getAllLines()){
            if(checkLine(line, sym, 0)){
                return true;
            }
        }
        return false;
    }

    private int[]getCoordinates(char[]cell){
        int[]coordinates = new int[2];
        coordinates[0] = (int)cell[1];
        coordinates[1] = (int)cell[2];
        return coordinates;
    }

    private boolean checkLine(char[][]line, char sym, int numOfEmptyCells){
        int counter = 0;
        for(char[]cell : line){
            if(cell[0] != sym && cell[0] != emptyDot){
                return false;
            }else if (cell[0] == emptyDot){
                counter++;
            }
        }
        return counter == numOfEmptyCells;
    }

    private int[]smartMove(){
        int[]coordinates = new int[2];

        return coordinates;
    }

    private int[] canSomeOneWin(char sym){
        int[]coordinates = {-1, -1};
        char[][][]allLines = getAllLines();
        for(char[][]line : allLines){
            if(checkLine(line, sym, 1)){
                for (char[]cell : line){
                    if(cell[0] == emptyDot){
                        return getCoordinates(cell);
                    }
                }
            }
        }
        return coordinates;
    }

    private int findNumOfCombinations(){
        int num = 0;
        for(int y = 0; y < fieldSizeY; y++){
           for(int x = 0; x < fieldSizeX; x++){
               num += getNumOfLinesForCell(y, x);
           }
        }
        return num;
    }

    private int getNumOfLinesForCell(int y, int x){
        int num = 0;
        if(x + winLineLength <= fieldSizeX && y + winLineLength <= fieldSizeY && y - winLineLength + 1 >= 0){
            num += 4;
            return num;
        }
        if(x + winLineLength <= fieldSizeX && y + winLineLength <= fieldSizeY){
            num +=3;
            return num;
        }
        if (x + winLineLength <= fieldSizeX && y - winLineLength + 1 >= 0){
            num += 2;
            return num;
        }
        if (x + winLineLength <= fieldSizeX){
            num++;
            return num;
        }
        if (y + winLineLength <= fieldSizeY){
            num++;
            return num;
        }
        return num;
    }

    private void setCellToline(char[]cell, int y, int x){
        cell[0] = field[y][x];
        cell[1] = (char)y;
        cell[2] = (char)x;
    }

    private void getUpDiagonal(char[][]line, int y, int x){
        for(int i = 0; i < winLineLength; i++, y--, x++){
            setCellToline(line[i], y, x);
        }
    }

    private void getDownDiagonal(char[][]line, int y, int x){
        for(int i = 0; i < winLineLength; i++, y++, x++){
            setCellToline(line[i], y, x);
        }
    }

    private void getHorizontal(char[][]line, int y, int x){
        for (int i = 0; i < winLineLength; i++, x++){
            setCellToline(line[i], y, x);
        }
    }

    private void getVertical(char[][]line, int y, int x){
        for (int i = 0; i < winLineLength; i++, y++){
            setCellToline(line[i], y, x);
        }
    }

    private char[][][] getAllLines(){
        int index = 0;
        char[][][]allLines = new char[findNumOfCombinations()][winLineLength][3];
        for(int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if(getNumOfLinesForCell(y, x) != 0){
                    char[][][]linesFromCell = getPossebleLinesFromCell(y, x);
                    for(char[][]line : linesFromCell){
                       allLines[index] = line;
                       index++;
                    }
                }
            }
        }
        return allLines;
    }

    private char[][][] getPossebleLinesFromCell(int y, int x){
        char[][][]lines = new char[getNumOfLinesForCell(y, x)][winLineLength][3];
        if(getNumOfLinesForCell(y, x) == 4){
            getUpDiagonal(lines[0], y, x);
            getDownDiagonal(lines[1], y, x);
            getHorizontal(lines[2], y, x);
            getVertical(lines[3], y, x);
        }
        if(getNumOfLinesForCell(y, x) == 3){
            getHorizontal(lines[0], y, x);
            getDownDiagonal(lines[1], y, x);
            getVertical(lines[2], y, x);
        }
        if (getNumOfLinesForCell(y, x) == 2){
            getUpDiagonal(lines[0], y, x);
            getHorizontal(lines[1], y, x);
        }
        if(getNumOfLinesForCell(y, x) == 1){
            if (x + winLineLength <= fieldSizeX){
                getHorizontal(lines[0], y, x);
            }else {
                getVertical(lines[0], y, x);
            }

        }

        return lines;
    }
}
