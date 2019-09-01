package gamefigures;

import app.GameListener;
import app.Vars;

import java.awt.image.BufferedImage;

public class Spaceship extends FlyingObject{

    int upgradeLevel = 1;

    // position/velocity/acceleration variables
    double accel = 1;

    //rotation/orientation variables
    double rotvel = 5;
    double orientation = 0;



    //constructor TODO
    public Spaceship(){
        posx = 300;
    }

    //updates every gametick
    public void update(){
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

}