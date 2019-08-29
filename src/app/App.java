package app;

import javax.swing.JFrame;

import gamefigures.Spaceship;


@SuppressWarnings("serial")
public class App extends JFrame {

    public static void main(String[] args) throws Exception {
        new Vars();
        setup();
        gameloop();
    }

    public static void gameloop(){
        while(true){
            Vars.previousTime = Vars.currentTime;
            Vars.currentTime = System.nanoTime();

            Vars.deltaTime = Vars.currentTime - Vars.previousTime;

            if(Vars.deltaTime > 150_000){
                Vars.deltaTime = 150_000;
            }

           /*  spaceship.update(deltaTime); */
            draw();
        }
    }

    public static void setup(){
        //Set Frame Vars
        Vars.previousTime = 0;
        Vars.currentTime = System.nanoTime();


        //Window setup
        Vars.window = new Window();
        Vars.canvas = new DrawCanvas();
        Vars.canvas.setVisible(true);
        Vars.window.getContentPane().add(Vars.canvas);

        //Game objects
        Vars.spaceship = new Spaceship();

    }

    public static void update(long deltaTime){

    }

    public static void draw(){
        Vars.canvas.repaint();
    }
}