package fr.um3.info.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {

        switch(String.valueOf(e.getKeyChar()).toLowerCase()){
            case "z":
                upPressed=true;
                break;
            case "s":
                downPressed=true;
                break;
            case "q":
                leftPressed=true;
                break;
            case "d" :
                rightPressed=true;
                break;


        }


    }

    @Override
    public void keyPressed(KeyEvent e) {






    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode=e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                upPressed=false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed=false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed=false;
                break;
            case KeyEvent.VK_RIGHT :
                rightPressed=false;
                break;


        }

    }
}
