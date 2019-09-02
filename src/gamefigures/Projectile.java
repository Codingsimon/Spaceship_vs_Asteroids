package gamefigures;

import app.Vars;

import java.awt.image.BufferedImage;

public class Projectile extends FlyingObject {
    public int rot;
    public Projectile(){
        System.out.println("shoot");
        posx = Vars.spaceship.getX();
        posy = Vars.spaceship.getY();
        rot = Vars.spaceship.getOrientation();
        xvel = Math.sin(Math.toRadians(rot));
        yvel = Math.cos(Math.toRadians(rot));
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
