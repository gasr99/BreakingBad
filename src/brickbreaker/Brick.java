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
public class Brick extends Item {
    
    private int width;
    private int height;
    private Game game;
    private Animation destroy;
    
    public Brick(int x, int y, int width, int height, Game game){
        super(x,y);
        this.width = width;
        this.height = height;
        this.destroy = new Animation(Assets.pill,10);
        this.game = game;
    }

    @Override
    public void tick() {
        
        this.destroy.tick();
        
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.pillS, x, y,width, height, null);
        g.drawImage(Assets.pillS, x, y,width, height, null);
    }
    
}
