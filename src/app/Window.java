package app;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{

    public Window(){
        setSize(Vars.gameWidth,Vars.gameHeight);
        setVisible(true);
        setTitle("First GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}