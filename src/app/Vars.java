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

    public static BufferedImage sp_ship_1 = null;



    public Vars(){
        //load images
        try {
            sp_ship_1 = ImageIO.read(new File("sprites/sp_0013_ship_1.png"));
        } catch (IOException e) {
            System.out.println("Bilder konnten nicht geladen werden");
        }
    }


    // returns Image Scale Size
    public static int scalfactor(int origWidth){
        return (int)((double)origWidth/(double)100 * (double)scalefactor);
    }

}
