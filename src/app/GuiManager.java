package app;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiManager {
    private int shortener = 500;
    String name;
    JPanel inputPanel;
    JTextField tfName;
    JButton buttonOK;
    JButton buttonReplay;
    JLabel label;



    public GuiManager(){
        Vars.frame = new Frame();

////        //Canvas Setup
        Vars.canvas = new DrawCanvas();
        Vars.canvas.setSize(Vars.gameWidth, Vars.gameHeight - shortener);
        Vars.canvas.setVisible(true);
        Vars.frame.add(Vars.canvas, BorderLayout.CENTER);

        inputPanel = new JPanel();

//        Label Description
        inputPanel.setBackground(Color.BLACK);
        label = new JLabel("Enter Name");
        label.setLayout(new FlowLayout());
        label.setForeground(Color.WHITE);
        label.setFont(Vars.pixelfont);
        inputPanel.add(label);


        //Replay Button
        buttonReplay = new JButton("Replay");
        buttonReplay.setFont(Vars.pixelfont);
        buttonReplay.setBackground(Color.DARK_GRAY);
        buttonReplay.setForeground(Color.WHITE);
        buttonReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("java -jar Spaceship_vs_Asteroids.jar");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        buttonReplay.setVisible(false);
        inputPanel.add(buttonReplay);


        // Textfield
        tfName = new JTextField("", 15);
        tfName.setForeground(Color.WHITE);
        tfName.setFont(Vars.pixelfont);
        tfName.setBackground(Color.BLACK);
        tfName.setCaretColor(Color.WHITE);
        inputPanel.add(tfName);

        buttonOK = new JButton("OK");
        buttonOK.setFont(Vars.pixelfont);
        buttonOK.setBackground(Color.DARK_GRAY);
        buttonOK.setForeground(Color.WHITE);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = tfName.getText();
                Vars.listWriter.writeNewEntry(name, Vars.gameManager.getPoints());
                Vars.canvas.repaint();
                hideInputField();
                showReplay();
            }
        });
        inputPanel.add(buttonOK);




        inputPanel.setVisible(false);
        Vars.frame.add(inputPanel, BorderLayout.NORTH);
        Vars.frame.setVisible(true);

    }

    public void hideInputField(){
        tfName.setVisible(false);
        label.setVisible(false);
        buttonOK.setVisible(false);
    }

    public void showReplay(){
        buttonReplay.setVisible(true);
    }

    public void showTextfield(){
        inputPanel.setVisible(true);
    }

    public void hideTextfield(){
        inputPanel.setVisible(false);
    }

}
