package keylistener;
//import key listening shit

import app.Vars;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//listener class
public class Wlistener implements KeyListener {

    //initialize variables for controls
    private boolean up = false;

    //return state of the pressed keys
    public boolean getUpState(){
        return up;
    }

    //sets respective-pressed-key state to true on keypress
    public void keyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_UP){
            Vars.firecounter = 1500;
            up = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }
    }
}