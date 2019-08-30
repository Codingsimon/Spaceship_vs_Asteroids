package gamefigures;

import app.GameListener;

public class Spaceship{
    // position/velocity/acceleration
    int posx;
    int posy = 200;
    int vel = 0;
    int accel = 1;
    //rotation/orientation stuff
    int rotvel = 0;
    int orientation = 0;
    // control - bools
//    boolean turnleft = false;
//    boolean turnright = false;
//    boolean boosting = false;
//    boolean reversing = false;
//    boolean shooting = false;

    //constructor TODO
    public Spaceship(){
        posx = 300;
    }

    //updates every gametick
    public void update(long deltaTime){
       /*  posx = (int)((long)posx + (long)velocity * deltaTime); */
        turn();
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

    //modifies orientation every update
    private void turn(){
        if(GameListener.getLeftState()){
            orientation += rotvel;
            GameListener.resetLeftState();
        }
        if(GameListener.getRightState()){
            orientation -= rotvel;
            GameListener.resetRightState();
        }
    }

    //modifies velocity and calculates position every update
    private void boost(){
        if(boosting){
            vel += accel;
            boosting = false;
        }
        if(reversing){
            vel -= accel;
            reversing = false;
        }
        posx = (int) (Math.sin(orientation) * vel);
        posy = (int) (Math.cos(orientation) * vel);
    }
}