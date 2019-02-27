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
    public static BufferedImage player; // to store the player image
    public static BufferedImage ball; // to store the ball image
    public static BufferedImage pill; // to store the pill image
    
    /**
    * initializing the images of the game */
    public static void init() {
    background = ImageLoader.loadImage("/images/background.jpg");
    player = ImageLoader.loadImage("/images/player.png");
    ball = ImageLoader.loadImage("/images/ball.png");
    pill = ImageLoader.loadImage("/images/pill.png");
    }
    
}
