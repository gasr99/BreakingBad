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
public class Player extends Item {
    
    private int width;
    private int height;
    private Game game;
    private Animation bounce;
    
    public Player(int x, int y, int width, int height, Game game){
       super(x,y);
       this.width = width;
       this.height = height;
       this.bounce = new Animation(Assets.bounce, 300);
       this.game = game;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public Rectangle getPerimeterLeft() {
        //Assuming width 200
        return new Rectangle(getX(), getY(), 60, getHeight());
    }
    
    public Rectangle getPerimeterMid() {
        //Assuming width 200
        return new Rectangle(getX() + 60, getY(), 80, getHeight());
    }
    
    public Rectangle getPerimeterRight() {
        //Assuming width 200
        return new Rectangle(getX() + 140, getY(), 60, getHeight());
    }
    
    @Override
    public void tick(){
        
        this.bounce.tick();
        
        if(game.getKeyManager().left){
            setX(getX() - 2);
        }
        
        if(game.getKeyManager().right){
            setX(getX() + 2);
        }
        
        //Movement constraints
        if(getX() + 100 >= game.getWidth()){
            setX(game.getWidth() - 101);
            
        } else if(getX() + 100 <= 0){
            setX(-99);  
        }
        
        if(getY() + 80 >= game.getHeight()){
            setY(game.getHeight() - 80);
            
        } else if(getY() <= -20){
            setX(-20);  
        }
    }
    
    @Override
    public void render(Graphics g){
        // Insert IF for collision with ball to play bounce animation
        g.drawImage(bounce.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
