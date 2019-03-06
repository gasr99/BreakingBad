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
 * @author gerardosilvarazo
 */
public class Ball extends Item {
    private int width;
    private int height;
    private Game game;
    private boolean move;
    private int dirX;
    private int dirY;
    private Animation ball;
    
    /**
     * 
     * @param x to store the position in X
     * @param y to store the position in Y
     * @param width to store the width of the Ball
     * @param height to store the height of the Ball
     * @param game 
     */
    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.ball = new Animation(Assets.ball, 200);
        this.game = game;
        move = false;
        dirX = 0;
        dirY = -2;
    }

    /**
     * Getter for width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Getter for height
     * @return height
     */
    public int getHeight() {
        return height;
    }    

    /**
     * Getter for move (to know if the ball can)
     * @return move
     */
    public boolean getMove() {
        return move;
    }
    
    /**
     * Getter for direction in X
     * @return dirX
     */
    public int getDirX() {
        return dirX;
    }

    /**
     * Getter for direction in Y
     * @return dirY
     */
    public int getDirY() {
        return dirY;
    }

    /**
     * Setter for width
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setter for height
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Setter for move (if the ball can or can't move)
     * @param move 
     */
    public void setMove(boolean move) {
        this.move = move;
    }
    
    /**
     * Setter for direction in X
     * @param dirX 
     */
    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    /**
     * Setter for direction in Y
     * @param dirY 
     */
    public void setDirY(int dirY) {
        this.dirY = dirY;
    }
    
    /**
     * It gets the perimeter of the instance
     * @return Rectangle
     */
    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Validates intersection with left part of player
     * @param obj
     * @return 
     */
    public boolean intersectsPlayerLeft(Object obj) {
        return obj instanceof Player && getPerimeter().intersects(((Player) obj).getPerimeterLeft());
    }
    
    /**
     * Validates intersection with mid part of player
     * @param obj
     * @return 
     */
    public boolean intersectsPlayerMid(Object obj) {
        return obj instanceof Player && getPerimeter().intersects(((Player) obj).getPerimeterMid());
    }
    
    /**
     * Validates intersection with right part of player
     * @param obj
     * @return 
     */
    public boolean intersectsPlayerRight(Object obj) {
        return obj instanceof Player && getPerimeter().intersects(((Player) obj).getPerimeterRight());
    }
    
    /**
     * Validates intersection with brick
     * @param obj
     * @return 
     */
    public boolean intersectsBrick(Object obj) {
        return obj instanceof Brick && getPerimeter().intersects(((Brick) obj).getPerimeter());
    }
    
    /**
     * Loop
     */
    @Override
    public void tick() {
        ball.tick();
        //The ball moves all the time
        if (move) {
            setX(getX() + getDirX());
            setY(getY() + getDirY());
        }
        
        
        //Update direction of ball when colliding with boundaries
        if (getX() < 1) {
            dirX = -dirX;
        }
        if (getX() > game.getWidth()-(getWidth()+1)) {
            dirX = -dirX;
        }
        if (getY() < 1) {
            dirY = -dirY;
        }
        
    }

    /**
     * To render the ball in the game
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(ball.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
