package app;

import gamefigures.Enemy;
import gamefigures.Spaceship;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Vars {
    public static double deltaTime;
    public static double currentTime;
    public static double previousTime;
    public static Spaceship spaceship;
    public static Window window;
    public static int gameWidth = 600;
    public static int gameHeight = 600;
    public static DrawCanvas canvas;
    private static double scalefactor = 2.6;
    public static GameListener gameListener;
    public static int level = 1;
    public static EnemySporner sporner;

    public static ArrayList<Enemy> enemyList = new ArrayList<>();


    public static BufferedImage sp_ship_1 = null;
    public static BufferedImage sp_ship_2 = null;
    public static BufferedImage sp_ship_3 = null;
    public static BufferedImage sp_ship_4 = null;

    public static BufferedImage sp_asteroid_brown_big = null;
    public static BufferedImage sp_asteroid_brown_medium = null;
    public static BufferedImage sp_asteroid_brown_small = null;

    public static BufferedImage sp_asteroid_green_big = null;
    public static BufferedImage sp_asteroid_green_medium = null;
    public static BufferedImage sp_asteroid_green_small = null;

    public static BufferedImage sp_asteroid_red_big = null;
    public static BufferedImage sp_asteroid_red_medium = null;
    public static BufferedImage sp_asteroid_red_small = null;


    public Vars(){
        //load images
        try {
            sp_ship_1 = ImageIO.read(new File("sprites/sp_0013_ship_1.png"));
            sp_ship_2 = ImageIO.read(new File("sprites/sp_0014_ship_2.png"));
            sp_ship_3 = ImageIO.read(new File("sprites/sp_0015_ship_3.png"));
            sp_ship_4 = ImageIO.read(new File("sprites/sp_0016_ship_4.png"));

            sp_asteroid_brown_big = ImageIO.read(new File("sprites/sp_0006_Astereoid_big.png"));
            sp_asteroid_brown_medium = ImageIO.read(new File("sprites/sp_0007_Astereoid_medium.png"));
            sp_asteroid_brown_small = ImageIO.read(new File("sprites/sp_0008_Astereoid_small.png"));

            sp_asteroid_green_big = ImageIO.read(new File("sprites/sp_0000_Farbton_Sättigung-1.png"));
            sp_asteroid_green_medium = ImageIO.read(new File("sprites/sp_0001_Farbton_Sättigung-1-Kopie-2.png"));
            sp_asteroid_green_small = ImageIO.read(new File("sprites/sp_0002_Farbton_Sättigung-1-Kopie.png"));

            sp_asteroid_red_big = ImageIO.read(new File("sprites/sp_0003_Farbton_Sättigung-2.png"));
            sp_asteroid_red_medium = ImageIO.read(new File("sprites/sp_0004_Farbton_Sättigung-2-Kopie.png"));
            sp_asteroid_red_small = ImageIO.read(new File("sprites/sp_0005_Farbton_Sättigung-2-Kopie-2.png"));

        } catch (IOException e) {
            System.out.println("Bilder konnten nicht geladen werden");
        }
    }


    // returns Image Scale Size
    public static double getScalfactor(){
        return scalefactor;
    }

    public static BufferedImage getShipImage(){
        switch (spaceship.getUpgradeLevel()){
            case 1: return sp_ship_1;
            case 2: return sp_ship_2;
            case 3: return sp_ship_3;
            case 4: return sp_ship_4;
        }
        return null;
    }
}
