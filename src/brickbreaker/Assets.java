/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.image.BufferedImage;

/**
 *
 * @author eugenio
 */
public class Assets {
    
    public static BufferedImage background; // to store background image 
    public static BufferedImage gameover;
    public static BufferedImage player; // to store the player image
    public static BufferedImage ball;
    public static BufferedImage bag;
    public static BufferedImage powerUP_1;
    public static BufferedImage powerUP_2;

    
    /**
    * initializing the images of the game */
    public static void init() {
    //background = ImageLoader.loadImage("/images/streetbackground2.png");
    background = ImageLoader.loadImage("/images/fondo.png");
    player = ImageLoader.loadImage("/images/playerbill.jpeg");
    gameover = ImageLoader.loadImage("/images/gameover.jpeg");
    bag = ImageLoader.loadImage("/images/bag.png");
    ball = ImageLoader.loadImage("/images/bluesky.png");
    powerUP_1 = ImageLoader.loadImage("/images/pow1.png");
    powerUP_2 = ImageLoader.loadImage("/images/pow2.png");
    }
    
}