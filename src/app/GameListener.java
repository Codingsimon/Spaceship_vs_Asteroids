package app;
//import key listening shit
import gamefigures.Projectile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class GameListener implements KeyListener {

    //initialize variables for controls
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean fire = false;

    //return state of the pressed keys
    public boolean getUpState(){
        return up;
    }
    public boolean getDownState(){
        return down;
    }
    public boolean getLeftState(){
        return left;
    }
    public boolean getRightState(){
        return right;
    }
    public boolean getFireState(){
        return fire;
    }

    //reset state of pressed key
    public void resetUpState(){
        up = false;
    }
    public void resetDownState(){
        down = false;
    }
    public void resetLeftState(){
        left = false;
    }
    public void resetRightState(){
        right = false;
    }
    public void resetFireState(){
        fire = false;
    }

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_W){
            up = true;

        }
        if (key.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_SPACE){
            fire = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}