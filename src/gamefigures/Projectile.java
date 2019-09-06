package gamefigures;

import app.Collision;
import app.Vars;

import java.awt.image.BufferedImage;

public class Projectile extends FlyingObject{
    public int rot;
    private int projectilelife = 2;
    public FlyingObject creator;
    private  double shotspeed = 5;
    public Projectile(FlyingObject creator){
        if (creator instanceof Spaceship){
            posx = Vars.spaceship.getX();
            posy = Vars.spaceship.getY();
            rot = Vars.spaceship.getOrientation();
            xvel = Math.sin(Math.toRadians(rot)) * shotspeed;
            yvel = Math.cos(Math.toRadians(rot)) * shotspeed;
        } else {
            posx = creator.getX() - this.getWidth()/2;
            posy = creator.getY() - this.getHeight()/2;
            double xlength = Vars.spaceship.getY() - creator.getY();
            double ylenght = Vars.spaceship.getX() - creator.getX();
            double vektorLength = pythagorean(xlength, ylenght);


            //sin calculation
            rot =  (int)Math.toDegrees(Math.asin(ylenght / vektorLength));

            if (xlength > 0 && ylenght > 0){
                rot += 90;
            }
            if (xlength > 0 && ylenght < 0){
                rot -= 90;
            }

            xvel =  (Vars.spaceship.getX() - creator.getX())  / vektorLength * shotspeed;
            yvel =  (creator.getY() - Vars.spaceship.getY()) / vektorLength * shotspeed;

        }

    }

    private void loseLife(){
        projectilelife -= 1;
    }

    public int getOrientation(){
        return rot;
    }

    public BufferedImage getImage(){
            return Vars.sp_shoot_small;
        }

    public void update(){
        if(projectilelife <= 0){
            Vars.projectilesToDelete.add(this);
        }
        Collision.collisionProjectileAstereoid( this);
        warp();
        posx += xvel;
        posy -= yvel;
    }


    void warp(){
        if (Vars.gameHeight <= getY()){
            posy -= Vars.gameHeight;
            loseLife();
            return;
        }
        if (Vars.gameWidth <= getX()){
            posx -= Vars.gameWidth;
            loseLife();
            return;
        }
        if (0 >= getY()){
            posy += Vars.gameHeight;
            loseLife();
            return;
        }
        if (0 >= getX()){
            posx += Vars.gameWidth;
            loseLife();
            return;
        }
    }



//    public Enemy collisionProjectileAstereoid(){
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
