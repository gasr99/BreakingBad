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
public class Player extends Item {
    
    private int width;
    private int height;
    private Game game;
    
    public Player(int x, int y, int width, int height, Game game){
       super(x,y);
       this.width = width;
       this.height = height;
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
    
    @Override
    public void tick(){
        
        if(game.getKeyManager().left){
            setX(getX() - 1);
        }
        
        if(game.getKeyManager().right){
            setX(getX() + 1);
        }
        
        if(getX() + 200 >= game.getWidth()){
            setX(game.getWidth() - 201);
            
        } else if(getX() <= 0){
            setX(1);  
        }
        
        if(getY() + 80 >= game.getHeight()){
            setY(game.getHeight() - 80);
            
        } else if(getY() <= -20){
            setX(-20);  
        }
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(Assets.player, getX(), getY(), getWidht(), getHeight(), null);
    }

    private int getWidht() {
        return width;
    }
    
}
