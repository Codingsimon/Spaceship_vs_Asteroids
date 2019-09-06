package keylistener;
//import key listening shit

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class Slistener implements KeyListener {

    //initialize variables for controls
    private boolean down = false;

    //return state of the pressed keys
    public boolean getDownState(){
        return down;
    }

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}