package gamefigures;

import app.App;
import app.Vars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Explosion {
    private int posx;
    private int posy;
    private int width;
    private int height;
    private long startTime;
    private long deltaTime;
    private long currentTime;
    Image tempImage;

    public Explosion(int posx, int posy){

            URL explosionImage = App.class.getResource("/sprites/Explosion_small.gif");
            tempImage = Toolkit.getDefaultToolkit().createImage(explosionImage);

        this.height = tempImage.getHeight(null);
        this.width = tempImage.getWidth(null);

        startTime = System.currentTimeMillis();
        this.posx = posx - getWidth()/2;
        this.posy = posy - getHeight()/2;

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
        return tempImage;
    }

    public int getY(){
        return posy;
    }

    public boolean readyToDelete(){
        currentTime = System.currentTimeMillis();
        deltaTime = currentTime - startTime;
        if (deltaTime/1000 > 2){
            return true;
        } else {
            return false;
        }
    }
}
