package gamefigures;
import app.Vars;
import java.awt.image.BufferedImage;

public class Spaceship extends FlyingObject {

    private int upgradeLevel;
    private double accel;
    private double rotvel;
    private double orientation;
    private int firecounter;
    private int firerate;
    private double maxspeed;

    //constructor TODO
    public Spaceship() {
        posx = 420;
        posy = 420;
        xvel = 0;
        yvel = 0;
        orientation = 0;
        upgradeLevel = 1;
        accel = 0.3;
        rotvel = 45;
        orientation = 0;
        //fire at most every <firerate>th frame
        firerate = 15;
        maxspeed = 2.5;
    }

    //updates every gametick
    public void update() {
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
    private void turn() {
            if (Vars.aListener.getLeftState()) {
                orientation -= rotvel;
            }
            if (Vars.dListener.getRightState()) {
                orientation += rotvel;
            }
            if (orientation < 0) {
                orientation += 360;
            }
            orientation = orientation%360;
            Vars.aListener.left = false;
            Vars.dListener.right = false;
    }

    //xvel, yvel, orientation -> posx posy
    private void boost() {
        //boost function
        if(Vars.wListener.getUpState()){
            xvel += (Math.sin(Math.PI*2*(orientation/360)) * accel);
            yvel += (Math.cos(Math.PI*2*(orientation/360)) * accel);
        }
        //reverse
        if(Vars.sListener.getDownState()){
            xvel -= (Math.sin(Math.PI*2*(orientation/360)) * accel);
            yvel -= (Math.cos(Math.PI*2*(orientation/360)) * accel);
        }
        //maxspeed
        if(xvel > maxspeed) {
            xvel = maxspeed;
        }
        if(xvel < -maxspeed) {
            xvel = -maxspeed;
        }
        if(yvel > maxspeed) {
            yvel = maxspeed;
        }
        if(yvel < -maxspeed) {
            yvel = -maxspeed;
        }

        posx += xvel;
        posy -= yvel;
    }

    private void fire() {
        //fire function
        firecounter++;
        firecounter = firecounter%firerate;
        if (firecounter == 0)   {
            if(Vars.spaceListener.getFireState()) {
                Projectile projectile = new Projectile();
                Vars.projectileList.add(projectile);
            }
        }
    }

    public BufferedImage getImage() {
        //get sprite
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
}