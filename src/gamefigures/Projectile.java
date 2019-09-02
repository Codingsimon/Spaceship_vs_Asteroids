package gamefigures;

import app.Vars;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
        collision();
        warp();
        posx += xvel;
        posy -= yvel;
    }

    public void collision(){
        for (Enemy e : Vars.enemyList) {

            //Vertical inside
            if (e.posx + e.getWidth()/2 > this.posx && e.posx + e.getWidth()/2 < this.posx + this.getWidth()){
                //Horizontal inside
                if (e.posy + e.getHeight()/2 > this.posy && e.posy + e.getHeight() < this.posy + this.getHeight()){
                    Vars.points -= 10;
                    Vars.enemyList.remove(e);
                    Vars.projectileList.remove(this);
                }
            }

            //--Circle in Edge
            //leftupper
            if (pythagorean(Math.abs(e.getCenterY() - this.getLeftUpperCornerY()), Math.abs(e.getCenterX() - this.getLeftUpperCornerX())) < e.getRadian()){
                Vars.points -= 10;
                Vars.enemyList.remove(e);
                Vars.projectileList.remove(this);
            }
            // rightupper
            if (pythagorean(Math.abs(e.getCenterX() - this.getRightUpperCornerX()), Math.abs(this.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
                Vars.points -= 10;
                Vars.enemyList.remove(e);
                Vars.projectileList.remove(this);
            }
            //lowerright
            if (pythagorean(Math.abs(e.getCenterY()-this.getRightLowerCornerY()), Math.abs(e.getCenterX() - this.getRightLowerCornerX())) < e.getWidth()/2){
                Vars.points -= 10;
                Vars.enemyList.remove(e);
                Vars.projectileList.remove(this);
            }
            //lowerleft
            if (pythagorean(Math.abs(e.getCenterY()-this.getLeftLowerCornerY()), Math.abs(this.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
                Vars.points -= 10;
                Vars.enemyList.remove(e);
                Vars.projectileList.remove(this);
            }
        }
    }

}
