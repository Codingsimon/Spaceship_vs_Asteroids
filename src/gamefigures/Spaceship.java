package gamefigures;

import app.GameListener;
import app.Vars;

import java.awt.image.BufferedImage;

public class Spaceship extends FlyingObject{

    int upgradeLevel = 1;

    // position/velocity/acceleration variables
    double accel = 1;

    //rotation/orientation variables
    double rotvel = 45;
    double orientation = 0;



    //constructor TODO
    public Spaceship(){
        posx = 300;
        posy = 300;
    }

    //updates every gametick
    public void update(){
        collision();
        boost();
        turn();
        warp();
    }

    //get spaceship data
    public int getOrientation(){
        return (int) orientation;
    }

    public int getUpgradeLevel(){
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel){
        this.upgradeLevel = upgradeLevel;
    }



    //modifies orientation every update
    private void turn(){
        if(Vars.gameListener.getLeftState()){
            orientation -= rotvel;
            Vars.gameListener.resetLeftState();
        }
        if(Vars.gameListener.getRightState()){
            orientation += rotvel;
            Vars.gameListener.resetRightState();
        }
        if(orientation < 0) {
            orientation += 360;
        }
        orientation = orientation%360;
    }

    //xvel, yvel, orientation -> posx posy
    private void boost(){
        //boost
        if(Vars.gameListener.getUpState()){
            xvel += (Math.sin(Math.PI*2*(orientation/360)) * accel);
            yvel += (Math.cos(Math.PI*2*(orientation/360)) * accel);
            Vars.gameListener.resetUpState();
        }
        //reverse
        if(Vars.gameListener.getDownState()){
            xvel -= (Math.sin(Math.PI*2*(orientation/360)) * accel);
            yvel -= (Math.cos(Math.PI*2*(orientation/360)) * accel);
            Vars.gameListener.resetDownState();
        }
        //flip coordinate system
        posx += xvel;
        posy -= yvel;
    }

    public BufferedImage getImage() {
        switch (upgradeLevel) {
                    case 1:
                        return Vars.sp_ship_1;
                    case 2:
                        return Vars.sp_ship_2;
                    case 3:
                        return Vars.sp_ship_3;
                    case 4:
                        return Vars.sp_ship_4;
            }
            return null;
        }

        public boolean collision(){
            for (Enemy e : Vars.enemyList) {
                //--Circle center inside of rectangel

                //Vertical inside
                if (e.posx + e.getWidth()/2 > this.posx && e.posx + e.getWidth()/2 < this.posx + this.getWidth()){
                    //Horizontal inside
                    if (e.posy + e.getHeight()/2 > this.posy && e.posy + e.getHeight() < this.posy + this.getHeight()){
                        return true;
                    }
                }

                //--Circle in Edge
                //leftupper
                if (pythagorean(Math.abs(e.getCenterY() - this.getLeftUpperCornerY()), Math.abs(e.getCenterX() - this.getLeftUpperCornerX())) < e.getRadian()){
                    return true;
                }
                // rightupper
                if (pythagorean(Math.abs(e.getCenterX() - Vars.spaceship.getRightUpperCornerX()), Math.abs(Vars.spaceship.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
                    return true;
                }
                //lowerright
                if (pythagorean(Math.abs(e.getCenterY()-Vars.spaceship.getRightLowerCornerY()), Math.abs(e.getCenterX() - Vars.spaceship.getRightLowerCornerX())) < e.getWidth()/2){
                    return true;
                }
                //lowerleft
                if (pythagorean(Math.abs(e.getCenterY()-Vars.spaceship.getLeftLowerCornerY()), Math.abs(Vars.spaceship.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
                    return true;
                }
            }
            return false;
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

        private double pythagorean(double kath1, double kath2){
            return Math.sqrt(Math.pow(kath1,2) + Math.pow(kath2,2));
        }

}