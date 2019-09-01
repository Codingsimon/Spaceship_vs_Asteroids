package gamefigures;

import app.Vars;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FlyingObject {
    double posx;
    double posy;
    double xvel;
    double yvel;

    double randomNumber(int min, int max){
        double speed = ThreadLocalRandom.current().nextInt(min, max + 1) / 10;
        if (speed == 0){
            return randomNumber(min,max);
        }
        return speed;
    }

    void setStartPositionBorder(){
        this.posx = Math.random() * Vars.gameWidth;
        this.posy = Math.random() * Vars.gameHeight;
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
        }
        if (Vars.gameWidth <= getX()){
            posx -= Vars.gameWidth;
        }
        if (0 >= getY()){
            posy += Vars.gameHeight;
        }
        if (0 >= getX()){
            posx += Vars.gameWidth;
        }
    }
}
