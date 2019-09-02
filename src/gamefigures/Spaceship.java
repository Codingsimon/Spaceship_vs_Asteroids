package gamefigures;

import app.Vars;

import java.awt.image.BufferedImage;

public class Spaceship extends FlyingObject{

    int upgradeLevel = 1;

    // position/velocity/acceleration variables
    double accel = 0.3;

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
        if (collision()){

        }
        fire();
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
    private int turncounter = 0;
    private void turn(){
        turncounter++;
        turncounter = turncounter%15;
        if(turncounter == 0) {
            if (Vars.aListener.getLeftState()) {
                orientation -= rotvel;
            }
            if (Vars.dListener.getRightState()) {
                orientation += rotvel;
            }
            if (orientation < 0) {
                orientation += 360;
            }
        }
        orientation = orientation%360;
    }

    //xvel, yvel, orientation -> posx posy
    private void boost(){
        //boost
        if(Vars.wListener.getUpState()){
            xvel += (Math.sin(Math.PI*2*(orientation/360)) * accel);
            yvel += (Math.cos(Math.PI*2*(orientation/360)) * accel);
        }
        //reverse
        if(Vars.sListener.getDownState()){
            xvel -= (Math.sin(Math.PI*2*(orientation/360)) * accel);
            yvel -= (Math.cos(Math.PI*2*(orientation/360)) * accel);
        }
        //flip coordinate system
        posx += xvel;
        posy -= yvel;
    }
    private int firecounter = 0;
    private void fire(){
        //fire!!1
        firecounter++;
        firecounter = firecounter%20;
        if (firecounter == 0)   {
            if(Vars.spaceListener.getFireState()) {
                Projectile projectile = new Projectile();
                Vars.projectileList.add(projectile);
            }
        }
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
                    Vars.points -= 10;
                    return true;
                }
            }

            //--Circle in Edge
            //leftupper
            if (pythagorean(Math.abs(e.getCenterY() - this.getLeftUpperCornerY()), Math.abs(e.getCenterX() - this.getLeftUpperCornerX())) < e.getRadian()){
                Vars.points -= 10;
                return true;
            }
            // rightupper
            if (pythagorean(Math.abs(e.getCenterX() - Vars.spaceship.getRightUpperCornerX()), Math.abs(Vars.spaceship.getRightUpperCornerY() - e.getCenterY())) < e.getRadian()){
                Vars.points -= 10;
                return true;
            }
            //lowerright
            if (pythagorean(Math.abs(e.getCenterY()-Vars.spaceship.getRightLowerCornerY()), Math.abs(e.getCenterX() - Vars.spaceship.getRightLowerCornerX())) < e.getWidth()/2){
                Vars.points -= 10;
                return true;
            }
            //lowerleft
            if (pythagorean(Math.abs(e.getCenterY()-Vars.spaceship.getLeftLowerCornerY()), Math.abs(Vars.spaceship.getLeftLowerCornerX() - e.getCenterX())) < e.getWidth()/2){
                Vars.points -= 10;
                return true;
            }
        }
        return false;
    }

}