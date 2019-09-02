package gamefigures;

import app.Vars;

import java.awt.image.BufferedImage;

public class Projectile extends FlyingObject {
    public int rot;
    private  double shotspeed = 5;
    public Projectile(){
        posx = Vars.spaceship.getX();
        posy = Vars.spaceship.getY();
        rot = Vars.spaceship.getOrientation();
        xvel = Math.sin(Math.toRadians(rot)) * shotspeed;
        yvel = Math.cos(Math.toRadians(rot)) * shotspeed;
    }

    public BufferedImage getImage(){
            return Vars.sp_shoot_small;
        }

    public  void  update(){
        warp();
        posx += xvel;
        posy -= yvel;
    }
}
