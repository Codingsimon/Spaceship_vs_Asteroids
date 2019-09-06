package app;

import javax.swing.JFrame;
import gamefigures.Enemy;
import gamefigures.FlyingObject;
import gamefigures.Projectile;
import gamefigures.Spaceship;
import keylistener.*;


@SuppressWarnings("serial")
public class App extends JFrame {
    static final double FPS = 60.0;

    public static void main(String[] args) throws Exception {
        new Vars();
        setup();
        gameloop();
    }

    public static void gameloop(){
        double ns = 1000_000_000 / FPS;

        while(true){
            Vars.previousTime = Vars.currentTime;
            Vars.currentTime = System.nanoTime();


            Vars.deltaTime += (Vars.currentTime - Vars.previousTime) / ns;

            if (Vars.deltaTime >= 1){
                update();
                Vars.deltaTime --;

                draw();
            }

            if (System.currentTimeMillis() - Vars.currentTime > 1000){
                Vars.currentTime += 1000;
            }
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
        Vars.wListener = new Wlistener();
        Vars.aListener = new Alistener();
        Vars.sListener = new Slistener();
        Vars.dListener = new Dlistener();
        Vars.spaceListener = new SPACElistener();

        Vars.window.addKeyListener(Vars.wListener);
        Vars.window.addKeyListener(Vars.aListener);
        Vars.window.addKeyListener(Vars.sListener);
        Vars.window.addKeyListener(Vars.dListener);
        Vars.window.addKeyListener(Vars.spaceListener);


        //Game objects
        Vars.spaceship = new Spaceship();
        Vars.sporner = new EnemySporner();

    }

    public static void update(){
        //Check if Level finished
        if (Vars.enemyList.isEmpty()){
            Vars.sporner.levelUp();
            Vars.sporner.newLevelSetup();
        }

        //update Gameobjects
        Vars.spaceship.update();
        for (Projectile projectile : Vars.projectileList){
            projectile.update();
        }
        for (Enemy enemy : Vars.enemyList) {
            enemy.update();
        }

        //delete Projectiles
        Collision.update();
    }

    public static void draw(){
        Vars.canvas.repaint();
    }
}