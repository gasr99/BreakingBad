/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author eugenio
 */
public class Game implements Runnable {

    private BufferStrategy bs; // to have several buffers when displaying
    private Graphics g; // to paint objects
    private Display display; // to display in the game
    private String title; //title of the window
    private int width; // width of the window
    private int height; // height of the window
    private Thread thread; // thread to create the game
    private boolean running; //to set the game
    private Player player; //to use a player
    private Ball ball; // to use a ball
    private LinkedList<Brick> bricks;
    private KeyManager keyManager; // to manage the keyboard
    private int timer = 1; // timer for every collision of ball
    private int destroy = 1;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        bricks = new LinkedList<Brick>();
    }

    @Override
    public void run() {
        init();

        int fps = 50;

        double timeTick = 100000000 / fps;

        double delta = 0;

        long now;

        long lastTime = System.nanoTime();

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;

            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player = new Player(getWidth() / 2 - 100, getHeight() - 100, 200, 40, this);
        ball = new Ball((player.getX() + player.getWidth() / 2 - 25), (player.getY() - 51), 50, 50, this);
        spawnBricks();
        display.getJframe().addKeyListener(keyManager);
    }

    /**
     * Spawns the bricks for the game
     */
    private void spawnBricks() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                bricks.add(new Brick(100 * i, 50 * j + 50, 100, 50, this));
            }
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {

        keyManager.tick();
        
        // Restart Game
        if(keyManager.restart){
            bricks.clear();
            player.x = getWidth() / 2 - 100;
            player.y = getHeight() - 100;
            ball.x = player.x + player.getWidth()/2 -25;
            ball.y = player.y - 51;
            spawnBricks();
            keyManager.space = false;
            keyManager.restart = false;
        }
        
        // If space is pressed once, starts ball movement
        if(keyManager.space) {
            ball.setMove(true);
        }

        // If game is not in pause, it runs
        if (!keyManager.pause) {
            player.tick();
            ball.tick();
        }

        // Validate collisions betwen ball and player
        if (timer > 0) {
           // Collision with left part of the bar
            if (ball.intersectsPlayerLeft(player)) {
                timer--;
                ball.setDirY(-1 * ball.getDirY());
                ball.setDirX(ball.getDirX()-1);
            }
        }
        if (timer > 0) {
            // Collision with right part of bar
            if (ball.intersectsPlayerRight(player)) {
                timer--;
                ball.setDirY(-1 * ball.getDirY());
                ball.setDirX(ball.getDirX()+1);
            }
        }
        if (timer > 0) {
            // Collision with middle of the bar
            if (ball.intersectsPlayerMid(player)) {
                timer--;
                ball.setDirY(-1 * ball.getDirY());
            }
        }
        
        timer = 1;
        
        // Validate collision with bricks
        for(int i = 0; i < bricks.size(); i++){
            
            Brick brick = bricks.get(i);
            
            if(ball.intersectsBrick(brick)){
                if(ball.getX()+ball.getWidth()-1 <= brick.getPerimeter().x || ball.getX()+1 >= brick.getPerimeter().x+brick.getPerimeter().width){
                    ball.setDirX(ball.getDirX()*-1);
                } else {
                    ball.setDirY(ball.getDirY()*-1);
                }
                
                if(destroy != 0){
                    bricks.remove(i);
                    destroy = 0;
                }
            }
            
            
            
         } 
        
        destroy = 1;

    }

    private void render() {
        //gets buffer strategy from the displya
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null we define one with 3 buffers to display images
        of the game, if not null, then we display every image of the game 
        but after clearing the Rectangle, getting the graphic object from the 
        buffer strategy element
        show the graphic and dispose it to trash the system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            if(ball.getY() > height){
                g = bs.getDrawGraphics();
                g.drawImage(Assets.gameover,0,0, width, width, null);
                bs.show();
                g.dispose();
            } else {
                g = bs.getDrawGraphics();
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player.render(g);
                ball.render(g);
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    brick.tick();
                    brick.render(g);
                }
                bs.show();
                g.dispose();
            }
        }
    }

    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

}
