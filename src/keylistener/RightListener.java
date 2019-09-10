package keylistener;
//import key listening shit

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class RightListener implements KeyListener {

    //initialize variables for controls
    public boolean right = false;

    //return state of the pressed keys
    public boolean getRightState(){
        return right;
    }

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
    }
}