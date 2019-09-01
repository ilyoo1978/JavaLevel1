package lesson8.HW8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsWindow extends JFrame {
    private final GameWindow gameWindow;

    private static final int WIN_WIDTH = 300;
    private static final int WIN_HEIGHT = 450;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 20;
    private static final int MIN_WIN_LINE_LEN = 3;
    private static final int MAX_WIN_LINE_LEN = 20;
    private static final String STR_FIELD_SIZE= "Field size: ";
    private static final String STR_WIN_LINE_SIZE = "Wining line length: ";
    private JRadioButton firstMovePlayer = new JRadioButton("Player first", true);
    private JRadioButton firstMoveAi = new JRadioButton("AI first");
    private ButtonGroup firstMoveButtons = new ButtonGroup();
    private JRadioButton humanVsAiRadioBtn = new JRadioButton("Human vs Ai", true);
    private JRadioButton humanVsHumanRadioBtn = new JRadioButton("Human vs Human");
    private ButtonGroup gameModeRadioButtons = new ButtonGroup();
    private JSlider fieldSizeSlider = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
    private JSlider winLineLenSlider = new JSlider(MIN_WIN_LINE_LEN, MAX_WIN_LINE_LEN, MIN_WIN_LINE_LEN);

    public OptionsWindow(GameWindow gameWindow) throws HeadlessException {
        this.gameWindow = gameWindow;
        setLocation((int)gameWindow.getBounds().getCenterX() - WIN_WIDTH / 2,
                (int)gameWindow.getBounds().getCenterY() - WIN_HEIGHT / 2);
        setSize(WIN_WIDTH,WIN_HEIGHT);
        setTitle("Game Options");
        setLayout(new GridLayout(12,1));
        addRadioButtons();
        addSliders();
        JButton startGameBtn = new JButton("START");
        startGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initOptionsAndBegin();
            }
        });
        add(startGameBtn);
    }

    private void addRadioButtons(){
        firstMoveButtons.add(firstMovePlayer);
        firstMoveButtons.add(firstMoveAi);
        gameModeRadioButtons.add(humanVsAiRadioBtn);
        gameModeRadioButtons.add(humanVsHumanRadioBtn);
        add(new JLabel("Choose Game Mode:"));
        add(humanVsAiRadioBtn);
        add(humanVsHumanRadioBtn);
        add(new JLabel("First move: "));
        add(firstMovePlayer);
        add(firstMoveAi);
    }

    private void addSliders(){
        final JLabel fieldSizeValueLabel = new JLabel(STR_FIELD_SIZE + MIN_FIELD_SIZE);
        add(fieldSizeValueLabel);
        add(fieldSizeSlider);
        fieldSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int fieldSizeValue = fieldSizeSlider.getValue();
                fieldSizeValueLabel.setText(STR_FIELD_SIZE + fieldSizeValue);
                winLineLenSlider.setMaximum(fieldSizeValue);
            }
        });
        final JLabel winLineSizeValueLabel = new JLabel(STR_WIN_LINE_SIZE + MIN_WIN_LINE_LEN);
        add(winLineSizeValueLabel);
        add(winLineLenSlider);
        winLineLenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int winLineSizeValue = winLineLenSlider.getValue();
                winLineSizeValueLabel.setText(STR_WIN_LINE_SIZE + winLineSizeValue);
            }
        });
        humanVsHumanRadioBtn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(humanVsHumanRadioBtn.isSelected()){
                    firstMoveAi.setVisible(false);
                    firstMovePlayer.setVisible(false);
                }else {
                    firstMoveAi.setVisible(true);
                    firstMovePlayer.setVisible(true);
                }
            }
        });
    }
    void initOptionsAndBegin(){
        int gameMode = humanVsAiRadioBtn.isSelected()? GameField.MODE_H_V_A: GameField.MODE_H_V_H;
        gameWindow.startNewGame(gameMode, fieldSizeSlider.getValue(), fieldSizeSlider.getValue(), winLineLenSlider.getValue(), firstMovePlayer.isSelected());
        setVisible(false);

    }
}
