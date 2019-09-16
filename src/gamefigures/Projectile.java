package gamefigures;

import app.Collision;
import app.Vars;

import java.awt.image.BufferedImage;

public class Projectile extends FlyingObject{
    public int rot;
    private int warpcounter = 2;
    public FlyingObject creator;
    private  double shotspeed = 5;
    public Projectile(FlyingObject creator){
        warpcounter = 2;
        this.creator = creator;

        if (creator instanceof Spaceship){
            //from Spaceship
            posx = Vars.spaceship.getCenterX();
            posy = Vars.spaceship.getCenterY();
            rot = Vars.spaceship.getOrientation();
            xvel = Math.sin(Math.toRadians(rot)) * shotspeed;
            yvel = Math.cos(Math.toRadians(rot)) * shotspeed;
        } else {
            //from UFO
            posx = creator.getCenterX();
            posy = creator.getCenterY();
            double xlength = Vars.spaceship.getCenterY() - creator.getCenterY();
            double ylenght = Vars.spaceship.getCenterX() - creator.getCenterX();
            double vektorLength = pythagorean(xlength, ylenght);


            //sin calculation
            rot =  (int)Math.toDegrees(Math.asin(ylenght / vektorLength));
            rot = Math.abs(rot);


            //bottom right
            if (xlength > 0 && ylenght > 0){
                rot = 180 - rot;
            }
            //bottom left
            if (xlength > 0 && ylenght < 0){
                rot += 180;
            }

            //top left
            if (xlength < 0 && ylenght < 0){
                rot = 360 - rot;

            }

            //top right
            if (xlength > 0 && ylenght < 0){
                rot = rot;
            }

            xvel =  (Vars.spaceship.getX() - creator.getX())  / vektorLength * shotspeed;
            yvel =  (creator.getY() - Vars.spaceship.getY()) / vektorLength * shotspeed;

        }

    }

    private void reduceWarpCounter(){
        warpcounter -= 1;
    }

    public int getOrientation(){
        return rot;
    }

    public BufferedImage getImage(){
            return Vars.sp_shoot_small;
        }

    public void update(){
        if(warpcounter <= 0){
            Vars.projectilesToDelete.add(this);
        }
        warp();
        posx += xvel;
        posy -= yvel;
    }


    public void warp(){
        //bottom
        if (Vars.gameHeight + this.getHeight() < getY()){
            posy -= Vars.gameHeight;
            posx = Vars.gameWidth - posx;
            reduceWarpCounter();
            return;
        }
        //right
        if (Vars.gameWidth + this.getWidth() < getX()){
            posx -= Vars.gameWidth;
            posy = Vars.gameHeight - posy;
            reduceWarpCounter();
            return;
        }
        //top
        if (getY() < 0 - this.getHeight()){
            posy += Vars.gameHeight;
            posx = Vars.gameWidth - posx;
            reduceWarpCounter();
            return;
        }
        //left
        if (getX() < 0 - this.getWidth()){
            posx += Vars.gameWidth;
            posy = Vars.gameHeight - posy;
            reduceWarpCounter();
            return;
        }
    }


    public int getWarpcounter(){
        return warpcounter;
    }

//    public Enemy checkCollisionProjectileEnemy(){
//        for (Enemy e : Vars.enemyList) {
//
//            //Vertical inside
//            if (e.posx + e.getWidth()/2 > this.posx && e.posx + e.getWidth()/2 < this.posx + this.getWidth()){
//                //Horizontal inside
//                if (e.posy + e.getHeight()/2 > this.posy && e.posy + e.getHeight() < this.posy + this.getHeight()){
//                    return e;
//                }
//            }
//
//            //--Circle in Edge
//            //leftupper
//            if (pythagorean(Math.abs(e.getCenterY() - this.getLeftUpperCornerY()), Math.abs(e.getCenterX() - this.getLeftUpperCornerX())) < e.getRadian()){
//                return e;
//            }
//            // rightupper
//            if (pythagorean(Math.abs(e.getCenterX() - this.getRightUpperCornerX()), Math.abs(this.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
//                return e;
//            }
//            //lowerright
//            if (pythagorean(Math.abs(e.getCenterY()-this.getRightLowerCornerY()), Math.abs(e.getCenterX() - this.getRightLowerCornerX())) < e.getWidth()/2){
//                return e;
//            }
//            //lowerleft
//            if (pythagorean(Math.abs(e.getCenterY()-this.getLeftLowerCornerY()), Math.abs(this.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
//               return e;
//            }
//        }
//        return null;
//    }


}
