package Lesson7.HW7;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 600);
        JPanel display = new JPanel();
        JPanel numbers = new JPanel(new GridLayout(4, 3));
        JPanel operation = new JPanel(new GridLayout(1, 5));
        for(int i = 0; i < 10; i++){
            numbers.add(new JButton("" +i));
        }
        add(numbers, BorderLayout.CENTER);
        String []operations = {"+", "-", "*", "/", "="};
        for(String sym: operations){
            operation.add(new Button(sym));
        }
        add(operation, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);
    }
}
class MainCalc{
    public static void main(String[] args) {
        new Calculator();
    }
}


