package app;

import javax.swing.JButton;
import javax.swing.JFrame;

import gamefigures.Spaceship;

@SuppressWarnings("serial")
public class App extends JFrame {
    static long deltaTime;
    static long currentTime;
    static long previousTime;
   /*  static JFrame window; */

    public static void main(String[] args) throws Exception {
    /*     System.out.println("sdafdfggfhgfjghjghj235+59978798798797798");
        Spaceship ship = new Spaceship();
        System.out.println(ship.goooooo);
        JFrame window = new Window();
        JButton b = new JButton("sdfg");
        window.add(b); */

        JFrame window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600,600);
        window.setVisible(true);
        window.setTitle("First GUI");
        System.out.println("x");
        /* setup();
        gameloop(); */
    }

    public static void gameloop(){
        while(true){
            previousTime = currentTime;
            currentTime = System.nanoTime();

            deltaTime = currentTime - previousTime;

            if(deltaTime > 150_000){
                deltaTime = 150_000;
            }

            update(deltaTime);
            draw();
        }
    }

    public static void setup(){
        //Set Frame Vars
        previousTime = 0;
        currentTime = System.nanoTime();

        //Window setup
       
    }

    public static void update(long deltaTime){

    }

    public static void draw(){

    }
}