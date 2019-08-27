package app;

import java.awt.Canvas;

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

        //Game objects
        Vars.spaceship = new Spaceship();

        //Window setup
        Vars.window = new Window();
        Vars.canvas = new DrawWindow();
        Vars.canvas.setVisible(true);
        Vars.window.getContentPane().add(Vars.canvas);

        
        
    }

    public static void update(long deltaTime){

    }

    public static void draw(){

    }
}