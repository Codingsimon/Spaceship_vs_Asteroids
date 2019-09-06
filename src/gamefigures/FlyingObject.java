package gamefigures;

import app.Vars;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FlyingObject {
    double posx;
    double posy;
    double xvel;
    double yvel;
    double orientation;

    double randomNumber(int min, int max){
        double speed = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (speed == 0){
            return randomNumber(min,max);
        }
        return speed;
    }

    public abstract BufferedImage getImage();

    public abstract void update();

    public int getWidth(){
        return (int)((double)getImage().getWidth() * Vars.getScalfactor());
    }

    public int getHeight(){
        return (int)((double)getImage().getHeight() * Vars.getScalfactor());
    }

    public int getX(){
        return (int)posx;
    }
    public int getY(){
        return (int)posy;
    }

    void warp(){
        if (Vars.gameHeight <= getY()){
            posy -= Vars.gameHeight;
            return;
        }
        if (Vars.gameWidth <= getX()){
            posx -= Vars.gameWidth;
            return;
        }
        if (0 >= getY()){
            posy += Vars.gameHeight;
            return;
        }
        if (0 >= getX()){
            posx += Vars.gameWidth;
            return;
        }
    }

    public void setPos(int posx, int posy){
        this.posx = posx;
        this.posy = posy;
    }

    public static int generateRandomInt(int max){
        Random random = new Random();
        return random.nextInt(max);
    }

    public abstract int getOrientation();

    double pythagorean(double kath1, double kath2){
        return Math.sqrt(Math.pow(kath1,2) + Math.pow(kath2,2));
    }

    public int getCenterX(){
        return (int) this.getX() + this.getWidth()/2;
    }

    public int getCenterY(){
        return (int) this.getY() + this.getHeight()/2;
    }

    public int getRadian(){
        return (int)this.getWidth()/2;
    }

    public int getLeftUpperCornerX(){
        return (int)posx - getWidth()/2;
    }

    public int getLeftUpperCornerY(){
        return (int)posy - getHeight()/2;
    }

    public int getRightUpperCornerX(){
        return (int)posx + getWidth()/2;
    }

    public int getRightUpperCornerY(){
        return (int)posy - getHeight()/2;
    }

    public int getLeftLowerCornerX(){
        return (int)posx - getWidth()/2;
    }

    public int getLeftLowerCornerY(){
        return (int)posy + getHeight()/2;
    }

    public int getRightLowerCornerX(){
        return (int)posx + getWidth()/2;
    }

    public int getRightLowerCornerY(){
        return (int)posy + getHeight()/2;
    }

}
