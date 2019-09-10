package app;

import gamefigures.Enemy;
import gamefigures.Explosion;
import gamefigures.Projectile;
import gamefigures.Spaceship;
import keylistener.UpListener;
import keylistener.LeftListener;
import keylistener.DownListener;
import keylistener.RightListener;
import keylistener.SPACElistener;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Vars {
    public static boolean gameRunning = true;
    public static double deltaTime;
    public static double currentTime;
    public static double previousTime;
    public static Spaceship spaceship;
    public static Window window;
    public static int gameWidth = 800;
    public static int gameHeight = 800;
    public static DrawCanvas canvas;
    private static double scalefactor = 2.6;
    public static int live = 4;

    public static UpListener wListener;
    public static LeftListener aListener;
    public static DownListener sListener;
    public static RightListener dListener;
    public static SPACElistener spaceListener;

    public static int level = 0;
    public static EnemySporner sporner;
    public static int points = 0;
    public static int relativePoints = 0;

    public static ArrayList<Enemy> enemyList = new ArrayList<>();
    public static ArrayList<Projectile> projectileList = new ArrayList<>();
    public static ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
    public static ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
    public static ArrayList<Explosion> explosionList = new ArrayList<>();
    public static ArrayList<Explosion> explosionsToDeletList = new ArrayList<>();

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

    public static BufferedImage sp_ufo = null;
    public static Image sp_fire = null;
    public static Image sp_explosion = null;
    public static int firecounter = 0;

    public static BufferedImage sp_shoot_small = null;
    public static BufferedImage sp_heart = null;

    public static Font pixelfont = null;

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

            sp_shoot_small = ImageIO.read(new File("sprites/sp_0010_shoot.png"));
            sp_heart = ImageIO.read(new File("sprites/sp_0012_life.png"));

            sp_ufo = ImageIO.read(new File("sprites/sp_0011_UFO.png"));

            sp_fire = Toolkit.getDefaultToolkit().createImage("sprites/fire.gif");


        } catch (IOException e) {
            System.out.println("Bilder konnten nicht geladen werden. Da Pfad passt nu ned!!!");
        }


        try {
            pixelfont = Font.createFont(Font.TRUETYPE_FONT,new File("font/Connection.otf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/Connection.otf")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static String getFormatedPoints(){
        DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance(Locale.GERMAN);
        df.applyPattern( "#,###,##0" );
        return df.format(points);
    }
}
