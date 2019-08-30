package app;

import gamefigures.Spaceship;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vars {
    public static long deltaTime;
    public static long currentTime;
    public static long previousTime;
    public static Spaceship spaceship;
    public static Window window;
    public static DrawCanvas canvas;
    public static int scalefactor = 260;
    public static GameListener gameListener;

    public static BufferedImage sp_ship_1 = null;
    public static BufferedImage sp_ship_2 = null;
    public static BufferedImage sp_ship_3 = null;
    public static BufferedImage sp_ship_4 = null;



    public Vars(){
        //load images
        try {
            sp_ship_1 = ImageIO.read(new File("sprites/sp_0013_ship_1.png"));
            sp_ship_2 = ImageIO.read(new File("sprites/sp_0014_ship_2.png"));
            sp_ship_3 = ImageIO.read(new File("sprites/sp_0015_ship_3.png"));
            sp_ship_4 = ImageIO.read(new File("sprites/sp_0016_ship_4.png"));
        } catch (IOException e) {
            System.out.println("Bilder konnten nicht geladen werden");
        }
    }


    // returns Image Scale Size
    public static int scalfactor(int origWidth){
        return (int)((double)origWidth/(double)100 * (double)scalefactor);
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
