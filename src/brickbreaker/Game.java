/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Math.random;
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
    public LinkedList<Ball> balls; // to use a ball
    public LinkedList<Brick> bricks;
    private KeyManager keyManager; // to manage the keyboard
    private int destroy = 1;
    public LinkedList<pow1> powUP1;
    public SaveLoad saveload;
    

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
        saveload = new SaveLoad(this);
    }

    @Override
    public void run() {
        init();

        int fps = 30;

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
        player = new Player(getWidth() / 2 - 100, getHeight() - 100, 200, 86, this);
        balls = new LinkedList<>();
        balls.add(new Ball((player.getX() + player.getWidth() / 2 - 25), (player.getY() - 31), 30, 30, this));
        powUP1 = new LinkedList<>();
        spawnBricks();
        
        display.getJframe().addKeyListener(keyManager);
    }

    /**
     * Spawns the bricks for the game
     */
    private void spawnBricks() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                bricks.add(new Brick(50 * i + 10 * i + 5, 50 * j + 50, 50, 50, this));
            }
        }
    }
    
    private void power1(){
        
        balls.add(new Ball((player.getX() + player.getWidth() / 2 - 25), (player.getY() - 31), 30, 30, this));
        balls.getLast().setMove(true);
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {

        keyManager.tick();

        // Restarts Game with all initial values
        if (keyManager.restart) {
            bricks.clear();
            powUP1.clear();
            balls.clear();
            player.x = getWidth() / 2 - 100;
            player.y = getHeight() - 100;
            balls.add(new Ball((player.getX() + player.getWidth() / 2 - 25), (player.getY() - 31), 30, 30, this));
            spawnBricks();
            keyManager.space = false;
            balls.getFirst().setMove(false);
            keyManager.restart = false;
        }
        
        if(keyManager.save){
            saveload.saveGame();
        }
        
        if(keyManager.load){
            saveload.loadGame();
        }
        
        if (!balls.isEmpty()) {
            if (keyManager.space) {
                balls.getFirst().setMove(true);
            }
        }

        // If game is not in pause, it runs
        if (!keyManager.pause) {
            player.tick();
            for (int i = 0; i < powUP1.size(); i++) {
                pow1 power = powUP1.get(i);
                power.tick();
                if (power.intersectsPlayer(player)) {
                    power1();
                    powUP1.remove(i);
                }
                if (power.getY() > height) {
                    powUP1.remove(i);
                }
            }
            for (Ball b : balls) {
                b.tick();
                // Validate collisions betwen ball and player

                // Collision with left part of the bar
                if (b.intersectsPlayerLeft(player)) {
                    if (b.getX() + b.getWidth() - 2  <= player.getPerimeterLeft().x && b.getY() + b.getHeight() > player.getPerimeterLeft().y) {
                    b.setDirX(-b.getDirX()); //changes direction (bounce)
                    b.setX(player.getPerimeterLeft().x - b.getWidth() - 2);
                } //intersects from the top
                else /*(ball.getX() + ball.getWidth() <= player.getPerimeterLeft().x + player.getPerimeterLeft().width && ball.getY() + ball.getHeight() <= player.getPerimeterLeft().y) */{
                    b.setY(player.getPerimeterLeft().y - b.getHeight() - 1);
                    b.setDirY(-b.getDirY());
                    b.setDirX((b.getDirX() - 1 < -2) ? -2 : b.getDirX() - 1);
                }
                }

                if (b.intersectsPlayerRight(player)) {
                    if (b.getX() + 2 >= player.getPerimeterRight().x + player.getPerimeterRight().width && b.getY() + b.getHeight() > player.getPerimeterRight().y) {
                    b.setDirX(-b.getDirX());
                    b.setX(player.getPerimeterRight().x + player.getPerimeterRight().width + 2);
                } else {
                    b.setY(player.getPerimeterRight().y - b.getHeight() - 1);
                    b.setDirY(-b.getDirY());
                    b.setDirX((b.getDirX() + 1 > 2) ? 2 : b.getDirX() + 1);
                }
                }
                if (b.intersectsPlayerMid(player)) {
                    b.setY(player.getPerimeterMid().y - b.getHeight() - 1);
                    b.setDirY(-b.getDirY());
                }

                // Validate collision with bricks
                for (int i = 0; i < bricks.size(); i++) {

                    Brick brick = bricks.get(i);
                    if (destroy != 0) {

                        if (b.intersectsBrick(brick)) {
                            if (b.getX() + b.getWidth() - 1 <= brick.getPerimeter().x || b.getX() + 1 >= brick.getPerimeter().x + brick.getPerimeter().width) {
                                b.setDirX(-b.getDirX());
                            } else {
                                b.setDirY(-b.getDirY());
                            }

                            if (destroy != 0) {
                                int cond = (int) Math.floor(Math.random() * 101);
                                if (cond < 10) {
                                    powUP1.add(new pow1(brick.getX(), brick.getY(), 50, 50, this));
                                }
                                bricks.remove(i);

                                destroy = 0;
                            }
                        }
                    }
                    destroy = 1;
                }
            }
                 

//            if (ball.intersectsBrick(brick)) {
//                // Collides from left
//                if (ball.getX() + ball.getWidth() - 1 <= brick.getPerimeter().x ) {
//                    ball.setX(brick.getPerimeter().x - 1);
//                    ball.setDirX(-ball.getDirX());
//                }
//                // Collides from right
//                else if (ball.getX() + 1 >= brick.getPerimeter().x + brick.getPerimeter().width) {
//                    ball.setX(brick.getPerimeter().x + brick.getPerimeter().width + 1);
//                    ball.setDirX(-ball.getDirX());
//                }
//                // Collision from bottom
//                else if (ball.getY() + 1 >= brick.getPerimeter().y + brick.getPerimeter().height) {
//                    ball.setY(brick.getPerimeter().y + brick.getPerimeter().height + 1);
//                    ball.setDirY(-ball.getDirY());
//                }else {
//                    ball.setY(brick.getPerimeter().y + 1);
//                    ball.setDirY(-ball.getDirY());
//                }
//
//                if (destroy != 0) {
//                    bricks.remove(i);
//                    destroy = 0;
//                }
//            }        
            }
            
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
            for(int i = 0; i < balls.size(); i++){
                if(balls.get(i).y > height)
                    balls.remove(i);
            }
            if(balls.isEmpty() || bricks.isEmpty()){
                g = bs.getDrawGraphics();
                g.drawImage(Assets.gameover, 0, 0, width, height, null);
                bs.show();
                g.dispose();
            } else {
                g = bs.getDrawGraphics();
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player.render(g);
                for(Ball b: balls){
                    b.render(g);
                }
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    brick.tick();
                    brick.render(g);
                }
                for(int i = 0; i < powUP1.size();i++){
                    pow1 power = powUP1.get(i);
                    power.render(g);
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
    
    Player getPlayer(){
        return player;
    }
    
    int getBalls(){
        return balls.size();
    }
    
    int getBricks(){
        return bricks.size();
    }
    
    int getpowUP(){
        return powUP1.size();
    }

}
