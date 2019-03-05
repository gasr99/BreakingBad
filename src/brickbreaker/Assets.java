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
    public static BufferedImage pillS;
    public static BufferedImage pill[]; // to store the pill image
    public static BufferedImage bounce[];

    
    /**
    * initializing the images of the game */
    public static void init() {
    background = ImageLoader.loadImage("/images/background.jpg");
    player = ImageLoader.loadImage("/images/player.png");
    ball = ImageLoader.loadImage("/images/ball.png");
    pillS = ImageLoader.loadImage("/images/pill_1.png");
    
    pill = new BufferedImage[3];
    pill[0] = ImageLoader.loadImage("/images/pill_2.png");
    pill[1] = ImageLoader.loadImage("/images/pill_3.png");
    pill[2] = ImageLoader.loadImage("/images/pill_4.png");
    
    bounce = new BufferedImage[3];
    bounce[0] = ImageLoader.loadImage("/images/player_2.png");
    bounce[1] = ImageLoader.loadImage("/images/player_3.png");
    bounce[2] = ImageLoader.loadImage("/images/player_4.png");
    }
    
}
