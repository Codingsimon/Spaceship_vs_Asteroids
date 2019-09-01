package gamefigures;

import app.GameListener;
import app.Vars;

import java.awt.image.BufferedImage;

public class Spaceship{

    int upgradeLevel = 1;

    // position/velocity/acceleration variables
    int posx;
    int posy = 300;
    int vel = 1;
    int accel = 1;

    //rotation/orientation variables
    int rotvel = 1;
    int orientation = 0;

    //instantiate key listener
    GameListener ship = new GameListener();

    //constructor TODO
    public Spaceship(){
        posx = 300;
    }

    //updates every gametick
    public void update(){
        boost();
        turn();
//        System.out.println("x: "+posx+", y: "+posy+", or: "+orientation+ ", vel: "+vel);
    }

    //get spaceship data
    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
    public int getOrientation(){
        return orientation;
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
            orientation += rotvel;
            Vars.gameListener.resetLeftState();
        }
        if(Vars.gameListener.getRightState()){
            orientation = orientation-rotvel;
            ship.resetRightState();
        }
        orientation = orientation%360;
//        System.out.println(orientation);
    }

    //modifies velocity and calculates position every update
    private void boost(){
        if(Vars.gameListener.getUpState()){
            vel += accel;
            Vars.gameListener.resetUpState();
        }
        if(Vars.gameListener.getDownState()){
            vel -= accel;
            Vars.gameListener.resetDownState();
        }
        posx += (int) (Math.sin(orientation) * -vel);
        posy += (int) (Math.cos(orientation) * -vel);
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

        public int getWidthScale(){
            return (int) (this.getImage().getWidth() * Vars.getScalfactor());
        }

    public int getHeightScale(){
        return (int) (this.getImage().getHeight() * Vars.getScalfactor());
    }


}