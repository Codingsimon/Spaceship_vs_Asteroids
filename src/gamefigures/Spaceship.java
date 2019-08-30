package gamefigures;

import app.GameListener;

public class Spaceship{

    // position/velocity/acceleration variables
    int posx;
    int posy = 200;
    int vel = 0;
    int accel = 1;

    //rotation/orientation variables
    int rotvel = 0;
    int orientation = 0;

    //instantiate key listener
    GameListener ship = new GameListener();

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
        if(ship.getLeftState()){
            orientation += rotvel;
            ship.resetLeftState();
        }
        if(ship.getRightState()){
            orientation -= rotvel;
            ship.resetRightState();
        }
    }

    //modifies velocity and calculates position every update
    private void boost(){
        if(ship.getUpState()){
            vel += accel;
            ship.resetUpState();
        }
        if(ship.getDownState()){
            vel -= accel;
            ship.resetDownState();
        }
        posx = (int) (Math.sin(orientation) * vel);
        posy = (int) (Math.cos(orientation) * vel);
    }
}