/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author eugenio
 */
public class KeyManager implements KeyListener {
    
    public boolean left; // flag to move left
    public boolean right; // flag to move right
    public boolean space = false; // to start moving ball
    public boolean pause = false; // flag to pause the game
    public boolean restart = false;
    public boolean save;
    public boolean load;
    
    private boolean keys[]; // store flags for every key
    
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e){
    
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        // Change pause's state
        if (e.getKeyCode() == KeyEvent.VK_P){
            pause = !pause;
        }
        // Read imput if not paused
        if (!pause){
            keys[e.getKeyCode()] = true;
        }
        
        //Start moving ball
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
        // Restart Game
        if (e.getKeyCode() == KeyEvent.VK_R){
            restart = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
            keys[e.getKeyCode()] = false;
        
    }
    
    public void tick(){
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        save = keys[KeyEvent.VK_G];
        load = keys[KeyEvent.VK_C];
    }
    
}
