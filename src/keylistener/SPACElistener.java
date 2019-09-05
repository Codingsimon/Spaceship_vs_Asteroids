package keylistener;
//import key listening shit

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class SPACElistener implements KeyListener {

    //initialize variables for controls
    private boolean fire = false;

    //return state of the pressed keys
    public boolean getFireState(){
        return fire;
    }

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_SPACE){
            fire = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            fire = false;
        }
    }
}