package lesson8.HW8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameField extends JPanel {
    char [][]field;
    int fieldSizeX;
    int fieldSizeY;
    int cellHeight;
    int cellWidth;
    int gameMode;
    int winLineLength;
    boolean isInitialized = false;
    boolean playersTurn;
    AnalizeCrossGame brain;

    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';
    static final int MODE_H_V_A = 0;
    static final int MODE_H_V_H = 1;
    static final Random rand = new Random();

    public GameField() {
        setBackground(Color.BLUE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                playersMove(e);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        if(!isInitialized){
            return;
        }
        for (int i = 0; i < fieldSizeY; i++){
            g.drawLine(0, i * cellHeight, getWidth(), i * cellHeight);
        }
        for (int i = 0; i < fieldSizeX; i++){
            g.drawLine(i * cellWidth,0,i * cellWidth,  getHeight());
        }
        for(int y = 0; y < field.length; y++){
            for(int x = 0; x < field[y].length; x++ ){
                switch (field[y][x]){
                    case 'X':
                        drawCross(g, x, y);
                        break;
                    case '0':
                        drawZero(g, x, y);
                }
            }
        }

    }

    private void initField() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }

    }

    private boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > fieldSizeX - 1 || y > fieldSizeY - 1) {
            return false;
        }

        return (field[y][x] == EMPTY_DOT);
    }

    private boolean isFieldFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    private void playersMove(MouseEvent e){
        if(!isInitialized  || !playersTurn){
            return;
        }
        int x = e.getX() / cellWidth;
        int y = e.getY() / cellHeight;
        if(isCellValid(y, x)){
            setSym(y, x, PLAYER_DOT);
            playersTurn =false;
            update();
        }else {
            GameWindow.infoScreen.setText("invalid move!");
        }
    }

    private void aiMove(){
        int[]coordinates = brain.findMove();
        int x, y;
        do {
            x = rand.nextInt(fieldSizeX);
            y = rand.nextInt(fieldSizeY);
        } while (!isCellValid(y,x));
        if(coordinates[0] != -1){
           y = coordinates[0];
           x = coordinates[1];
        }
        setSym(y, x, AI_DOT);
        playersTurn = true;
        update();
    }

    private void update(){
        if(brain.checkWin(PLAYER_DOT)){
            print("You Win!!!!");
            repaint();
            return;
        }
        if(brain.checkWin(AI_DOT)){
            print("Skynet Win!!!!");
            repaint();
            return;
        }
        if (isFieldFull()){
            print("Draw");
            repaint();
            return;
        }
       if(playersTurn){
           print("Your move!");
           repaint();
           return;
       }else {
            print("Ai's move!");
            aiMove();
            repaint();
       }
    }

    public void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLineLength, boolean playersTurn) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.cellHeight = getHeight() / fieldSizeY;
        this.cellWidth = getWidth() / fieldSizeX;
        this.field = new char[fieldSizeY][fieldSizeX];
        this.winLineLength = winLineLength;
        this.playersTurn = playersTurn;
        initField();
        isInitialized = true;
        brain = new AnalizeCrossGame(this.field, fieldSizeX,fieldSizeY, winLineLength, PLAYER_DOT, AI_DOT, EMPTY_DOT);
        update();
    }

    private void drawCross(Graphics g, int x, int y){
        g.drawLine(x * cellWidth + (int)(cellWidth * 0.1), y * cellHeight + (int)(cellWidth * 0.1), x * cellWidth + (int)(cellWidth * 0.9), y * cellHeight+ (int)(cellHeight * 0.9));
        g.drawLine(x * cellWidth + (int)(cellWidth * 0.1) , y * cellHeight + (int)(cellHeight * 0.9), x * cellWidth + (int)(cellWidth * 0.9), y * cellHeight + (int)(cellHeight * 0.1));
    }

    private void drawZero(Graphics g, int x, int y){
        int centerX = x * cellWidth + (int)(cellWidth * 0.1);
        int centerY = y * cellHeight + (int)(cellHeight * 0.1);
        g.drawOval(centerX, centerY, (int)(cellWidth * 0.8), (int)(cellHeight * 0.8));
    }

    private void print(String str){
        GameWindow.infoScreen.setText(str);
    }
}
