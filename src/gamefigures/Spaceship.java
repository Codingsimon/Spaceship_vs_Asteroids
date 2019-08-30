package gamefigures;

import app.GameListener;
import app.Vars;

public class Spaceship{

    int upgradeLevel = 1;

    // position/velocity/acceleration variables
    int posx;
    int posy = 300;
    int vel = 0;
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
    public void update(long deltaTime){
        boost();
        turn();
        System.out.println("x: "+posx+", y: "+posy+", or: "+orientation+ ", vel: "+vel);
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
        orientation = Math.abs(orientation%360);
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
        posx += (int) (Math.sin(orientation) * vel / Vars.deltaTime);
        posy += (int) (Math.cos(orientation) * vel / Vars.deltaTime);
    }
}