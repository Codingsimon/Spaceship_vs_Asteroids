package app;

import gamefigures.items.*;
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
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Vars {
    public static boolean gameRunning;
    public static double deltaTime;
    public static long currentTime;
    public static long previousTime;
    public static Spaceship spaceship;
    public static Frame frame;
    public static int gameWidth = 800;
    public static int gameHeight = 800;
    public static DrawCanvas canvas;
    private static double scalefactor = 2.6;
    public static SpaceshipCollision spaceshipCollision;
    public static EnemyCollision enemyCollision;
    public static GameManager gameManager;

    public static UpListener wListener;
    public static LeftListener aListener;
    public static DownListener sListener;
    public static RightListener dListener;
    public static SPACElistener spaceListener;

    public static int level;
    public static EnemySporner sporner;

    public static ArrayList<Enemy> enemyList = new ArrayList<>();
    public static ArrayList<Projectile> projectileList = new ArrayList<>();
    public static ArrayList<Projectile> projectilesToDelete = new ArrayList<>();
    public static ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
    public static ArrayList<Explosion> explosionList = new ArrayList<>();
    public static ArrayList<Explosion> explosionsToDeletList = new ArrayList<>();
    public static ArrayList<Item> itemList = new ArrayList<>();
    public static ArrayList<Item> itemToDeleteList = new ArrayList<>();

    public static GuiManager guiManager;
    public static ListWriter listWriter;

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

    public static BufferedImage sp_item_shield = null;

    public static BufferedImage sp_ufo = null;
    public static Image sp_fire = null;
    public static Image sp_explosion = null;
    public static int firecounter = 0;

    public static BufferedImage sp_shoot_small = null;
    public static BufferedImage sp_heart = null;

    public static Font pixelfont = null;

    public Vars(){
        gameRunning = true;
        level = 0;


        //load images
        try {
            sp_ship_1 = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0013_ship_1.png"));
            sp_ship_2 = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0014_ship_2.png"));
            sp_ship_3 = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0015_ship_3.png"));
            sp_ship_4 = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0016_ship_4.png"));

            sp_asteroid_brown_big = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0006_Astereoid_big.png"));
            sp_asteroid_brown_medium = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0007_Astereoid_medium.png"));
            sp_asteroid_brown_small = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0008_Astereoid_small.png"));

            sp_asteroid_green_big = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0000_Farbton_Sättigung-1.png"));
            sp_asteroid_green_medium = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0001_Farbton_Sättigung-1-Kopie-2.png"));
            sp_asteroid_green_small = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0002_Farbton_Sättigung-1-Kopie.png"));

            sp_asteroid_red_big = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0003_Farbton_Sättigung-2.png"));
            sp_asteroid_red_medium = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0004_Farbton_Sättigung-2-Kopie.png"));
            sp_asteroid_red_small = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0005_Farbton_Sättigung-2-Kopie-2.png"));

            sp_shoot_small = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0010_shoot.png"));
            sp_heart = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0012_life.png"));

            sp_ufo = ImageIO.read(App.class.getResourceAsStream("/sprites/sp_0011_UFO.png"));


            URL fireImage = App.class.getResource("/sprites/fire.gif");
            sp_fire = Toolkit.getDefaultToolkit().createImage(fireImage);


            sp_item_shield = ImageIO.read(new File("/sprites/sp_00018_item_shield.png"));


        } catch (IOException e) {
            System.out.println("Image loading Problem");
        }


        try {
            InputStream is = this.getClass().getResourceAsStream("/font/Connection.otf");
            pixelfont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
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

}
