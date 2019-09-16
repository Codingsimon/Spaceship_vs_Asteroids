package app;

import javax.swing.*;

import gamefigures.Enemy;
import gamefigures.Explosion;
import gamefigures.Projectile;
import gamefigures.Spaceship;
import gamefigures.items.Item;
import keylistener.*;

import java.awt.*;

import static app.Vars.listWriter;


@SuppressWarnings("serial")
public class App extends JFrame {
    static final double FPS = 60.0;

    public static void main(String[] args) throws Exception {
            init();
    }

    public static void init(){
        setup();
        gameloop();
        stop();
    }

    public static void gameloop(){
        double ns = 1000_000_000 / FPS;

        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(Vars.gameRunning){
            Vars.currentTime = System.nanoTime();
            Vars.deltaTime += (Vars.currentTime - Vars.previousTime) / ns;
            Vars.previousTime = Vars.currentTime;


            if (Vars.deltaTime >= 1){
                update();
                draw();
                Vars.deltaTime --;
                frames++;
            }

// draw Framerate
//            if (System.currentTimeMillis() - timer > 1000){
//                timer += 1000;
//                System.out.println("Fps " + frames);
//                updates = 0;
//                frames = 0;
//            }
        }


    }

    public static void setup(){
        new Vars();

        //Set Frame Vars
        Vars.previousTime = System.nanoTime();
        Vars.guiManager = new GuiManager();
        Vars.listWriter = new ListWriter();


        //Game objects
        Vars.spaceship = new Spaceship();
        Vars.sporner = new EnemySporner();

        //Collision Objects
        Vars.spaceshipCollision = new SpaceshipCollision();
        Vars.enemyCollision = new EnemyCollision();
        Vars.gameManager = new GameManager();


        //Listener
        Vars.wListener = new UpListener();
        Vars.aListener = new LeftListener();
        Vars.sListener = new DownListener();
        Vars.dListener = new RightListener();
        Vars.spaceListener = new SPACElistener();

        Vars.frame.addKeyListener(Vars.wListener);
        Vars.frame.addKeyListener(Vars.aListener);
        Vars.frame.addKeyListener(Vars.sListener);
        Vars.frame.addKeyListener(Vars.dListener);
        Vars.frame.addKeyListener(Vars.spaceListener);

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
        for (Item item : Vars.itemList){
            item.update();
        }



        //find Explosions to delete
        for (Explosion x : Vars.explosionList) {
            if (x.readyToDelete()){
                Vars.explosionsToDeletList.add(x);
            }
        }

        //Clear Explosion List
        for (Explosion x : Vars.explosionsToDeletList){
            Vars.explosionList.remove(x);
        }

        //Gamemanager
        Vars.gameManager.update();

        //detect for collisions
        Vars.spaceshipCollision.update();
        Vars.enemyCollision.update();

    }

    public static void draw(){
        Vars.canvas.repaint();
    }

    public static void stop(){
        Vars.guiManager.showTextfield();
        draw();
    }
}