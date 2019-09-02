package keylistener;
//import key listening shit

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class Dlistener implements KeyListener {

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

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        right = false;
    }
}