package app;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{
    Window frame = new Window();

    public Window(){
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setTitle("First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}