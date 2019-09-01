package app;

import javax.swing.JFrame;

import gamefigures.Enemy;
import gamefigures.Projectile;
import gamefigures.Spaceship;


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
        Vars.sporner.setLevel(0);

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
        Vars.gameListener = new GameListener();
        Vars.window.addKeyListener(Vars.gameListener);

        //Game objects
        Vars.spaceship = new Spaceship();
        Vars.sporner = new EnemySporner();

        Vars.sporner.setLevel(0);

    }

    public static void update(){
        if (Vars.sporner.getEnemycount() <= 0){
            Vars.sporner.setLevel(Vars.level);
            Vars.level++;
        }
        Vars.spaceship.update();
        for (Projectile projectile : Vars.projectileList){
            projectile.update();
        }
        for (Enemy enemy : Vars.enemyList) {
            enemy.update();
        }
    }

    public static void draw(){
        Vars.canvas.repaint();
    }
}