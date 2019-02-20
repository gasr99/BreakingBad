/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Graphics;

/**
 *
 * @author eugenio
 */
public abstract class Item {
    
    protected int x; // store x position
    protected int y; //store y position
    
    /**
     * Set the initial values to create the item
     * @param x position of the object
     * @param y position of the object
     */
    public Item(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x value
     * @return x
     */
        public int getX() {
        return x;
    }
    
    /**
     * Get y value
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * To update positions for every tick
     */
    public abstract void tick();
    
    /**
     * @param g Graphics object to paint the item
     */
    public abstract void render(Graphics g);
  
}
