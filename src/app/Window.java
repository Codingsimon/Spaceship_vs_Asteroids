package app;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{

    public Window(){
        setSize(600,600);
        setVisible(true);
        setTitle("First GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static int scalfactor(int origWidth){
        return (int)((double)origWidth/(double)100 * (double)260);
    }
}