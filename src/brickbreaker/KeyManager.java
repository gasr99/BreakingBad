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
    public boolean pause = false; // flag to pause the game
    
    private boolean keys[]; // store flags for every key
    
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e){
    
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_P){
            pause = !pause;
        }
        if(!pause){
            keys[e.getKeyCode()] = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        keys[e.getKeyCode()] = false;
        
    }
    
    public void tick(){
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        pause = keys[KeyEvent.VK_P];
    }
    
}
