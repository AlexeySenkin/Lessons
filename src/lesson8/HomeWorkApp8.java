package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWorkApp8 {
    private static int counter = 0;
    private static JLabel counterValueView;
    private static JTextField counterValueStep;


    public static void main(String[] args) {
        JFrame mainFrame = initFrame("Счетчик",300,300,400,200);
        Font font = new Font("Arial", Font.BOLD, 50);
        Font buttonFont = new Font("Arial", Font.ITALIC, 30);
        createCounterView(mainFrame, font);
        createCounterStep(mainFrame, font);
        createButtons(mainFrame, buttonFont);
        mainFrame.setVisible(true);
    }

    private static void createButtons(JFrame mainFrame, Font font) {
        JButton decrementButton = new JButton("<");
        decrementButton.setFont(font);
        mainFrame.add(decrementButton, BorderLayout.LINE_START);

        JButton incrementButton = new JButton(">");
        incrementButton.setFont(font);
        mainFrame.add(incrementButton, BorderLayout.LINE_END);

        JButton resetButton = new JButton("Сброс счетчика");
        resetButton.setFont(font);
        mainFrame.add(resetButton,BorderLayout.SOUTH);

        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter = counter - Integer.parseInt(counterValueStep.getText());
                counterValueView.setText(String.valueOf(counter));
            }
        });

        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter = counter + Integer.parseInt(counterValueStep.getText());
                counterValueView.setText(String.valueOf(counter));
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter = 0;
                counterValueView.setText(String.valueOf(counter));
            }
        });
    }

    private static JFrame initFrame(String caption, int x, int y, int width, int height) {
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle(caption);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setBounds(x, y, width, height);
        return mainFrame;
    }

    private static void createCounterView(JFrame mainFrame, Font font) {
        counterValueView = new JLabel(getCounterValueAsText());
        counterValueView.setHorizontalAlignment(SwingConstants.CENTER);
        counterValueView.setFont(font);
        mainFrame.add(counterValueView, BorderLayout.CENTER);
    }

    private static String getCounterValueAsText() {
        return String.valueOf(counter);
    }

    private static void createCounterStep(JFrame maiJFrame, Font font) {
        counterValueStep = new JTextField();
        counterValueStep.setText("1");
        counterValueStep.setHorizontalAlignment(SwingConstants.CENTER);
        counterValueStep.setFont(font);
        maiJFrame.add(counterValueStep,BorderLayout.NORTH);


    }
}



