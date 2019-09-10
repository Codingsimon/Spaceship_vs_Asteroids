package gamefigures;

import app.Vars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion {
    private int posx;
    private int posy;
    private int width;
    private int height;
    Image image = null;
    private long startTime;
    private long deltaTime;
    private long currentTime;
    BufferedImage tempImage;

    public Explosion(int posx, int posy){
        try {
             tempImage= ImageIO.read(new File("sprites/Explosion_small.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.height = tempImage.getHeight();
        this.width = tempImage.getWidth();

        startTime = System.currentTimeMillis();
        this.posx = posx - getWidth()/2;
        this.posy = posy - getHeight()/2;


        image = Toolkit.getDefaultToolkit().createImage("sprites/Explosion_small.gif");
    }


    public int getWidth(){
        return width * (int)Vars.getScalfactor();
    }

    public int getHeight(){
        return height * (int)Vars.getScalfactor();
    }

    public int getCenterX(){
        return posx - getWidth()/2;
    }

    public int getCenterY(){
        return posy - getWidth()/2;
    }

    public int getX() {
        return posx;
    }

    public Image getImage(){
        return image;
    }

    public int getY(){
        return posy;
    }

    public boolean readyToDelete(){
        currentTime = System.currentTimeMillis();
        deltaTime = currentTime - startTime;
        System.out.println(deltaTime/1000);
        if (deltaTime/1000 > 2){
            return true;
        } else {
            return false;
        }
    }
}
