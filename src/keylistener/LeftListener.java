package keylistener;
//import key listening shit

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class LeftListener implements KeyListener {

    //initialize variables for controls
    public boolean left = false;

    //return state of the pressed keys
    public boolean getLeftState(){
        return left;
    }

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }
    }
}