package lesson8.HW8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 550;
    private static final int POSITION_X = 300;
    private static final int POSITION_Y = 300;

    private static GameField gameField;
    private static OptionsWindow optionsWindow;
    protected static JTextField infoScreen;


    public GameWindow() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POSITION_X, POSITION_Y, WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("KRESTIKI NOLIKI");

        infoScreen = new JTextField();
        infoScreen.setEditable(false);
        add(infoScreen, BorderLayout.NORTH);

        JButton btnNewGame = new JButton("New Game");
        JButton btnExit = new JButton("Exit");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsWindow.setVisible(true);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bye");
                System.exit(0);
            }
        });
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(btnNewGame);
        buttons.add(btnExit);
        add(buttons, BorderLayout.SOUTH);

        gameField = new GameField();
        add(gameField, BorderLayout.CENTER);

        optionsWindow = new OptionsWindow(this);

        setResizable(false);
        setVisible(true);

    }
    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLineLength, boolean firstMove){
        gameField.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLineLength, firstMove);
    }
}
