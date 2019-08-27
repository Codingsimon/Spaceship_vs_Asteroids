package gamefigures;

public class Spaceship{
    // position/velocity/acceleration
    int posx;
    int posy = 200;
    int vel = 1;
    int accel = 1;
    //rotation/orientation stuff
    int rotvel = 0;
    int orientation = 0;
    // control - bools
    boolean turnleft = false;
    boolean turnright = false;
    boolean boosting = false;
    boolean reversing = false;
    boolean shooting = false;

    //constructor
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
        if(turnleft){
            orientation += rotvel;
            turnleft = false;
        }
        if(turnright){
            orientation -= rotvel;
            turnright = false;
        }
    }

    private void boost(){
        if(boosting){
           // posx += Math.Sin();
        }
    }
}