package app;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Frame extends JFrame{

    public Frame(){
        setSize(Vars.gameWidth,Vars.gameHeight);
        setVisible(true);
        setTitle("Spaceship vs Asteroids");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setIconImage(Vars.sp_ship_4);
    }

}