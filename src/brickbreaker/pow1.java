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
public class pow1 extends Item{
    
    int width;
    int height;
    Game game;
    
    // PowerUP constructor
    public pow1(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
    }

    @Override
    public void tick() {
        // object will fall down;
        y = y+1;
    }
    
    public Rectangle getPerimeter() {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean intersectsPlayer(Object obj) {
        return obj instanceof Player && getPerimeter().intersects(((Player) obj).getPerimeter());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.powerUP_1, x, y, width, height, null);
    }
    
}
