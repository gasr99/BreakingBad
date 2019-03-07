/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author eugenio
 */
public class Brick extends Item {
    
    private int width;
    private int height;
    private Game game;    
    /**
     * 
     * @param x to set initial position in X
     * @param y to set initial position in Y
     * @param width to state the width of the instance
     * @param height to state the height of the instance
     * @param game 
     */
    public Brick(int x, int y, int width, int height, Game game){
        super(x,y);
        this.width = width;
        this.height = height;
        this.game = game;
    }

    /**
     * to get the width of brick
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * to set the width of brick
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * to get the height of brick
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * to set the height of brick
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * method that calculates perimeter of brick instance
     * @return Rectangle
     */
    public Rectangle getPerimeter() {
        //Assuming width 200
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * loop executed 50 times per second 
     */
    @Override
    public void tick() {
    }

    /**
     * draws the each brick 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.pillS, x, y,width, height, null);
        g.drawImage(Assets.bag, x, y,width, height, null);
    }
    
}
