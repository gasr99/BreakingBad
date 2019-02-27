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
    public static BufferedImage pill_1; // to store the pill image
    public static BufferedImage pill_2; // to store the pill image
    public static BufferedImage pill_3; // to store the pill image
    public static BufferedImage pill_4; // to store the pill image
    public static BufferedImage pill_5; // to store the pill image

    
    /**
    * initializing the images of the game */
    public static void init() {
    background = ImageLoader.loadImage("/images/background.jpg");
    player = ImageLoader.loadImage("/images/player.png");
    ball = ImageLoader.loadImage("/images/ball.png");
    pill_1 = ImageLoader.loadImage("/images/pill_1.png");
    pill_2 = ImageLoader.loadImage("/images/pill_2.png");
    pill_3 = ImageLoader.loadImage("/images/pill_3.png");
    pill_4 = ImageLoader.loadImage("/images/pill_4.png");
    pill_5 = ImageLoader.loadImage("/images/pill_5.png");
    }
    
}
