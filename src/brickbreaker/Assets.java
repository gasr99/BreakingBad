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
    public static BufferedImage ball[]; // to store the ball image
    public static BufferedImage pillS;
    public static BufferedImage pill[]; // to store the pill image
    public static BufferedImage bounce[];

    
    /**
    * initializing the images of the game */
    public static void init() {
    background = ImageLoader.loadImage("/images/background.jpg");
    player = ImageLoader.loadImage("/images/player.png");
    gameover = ImageLoader.loadImage("/images/gameover.jpeg");
    
    ball = new BufferedImage[9];
    ball[0] = ImageLoader.loadImage("/images/ball1.png");
    ball[1] = ImageLoader.loadImage("/images/ball2.png");
    ball[2] = ImageLoader.loadImage("/images/ball3.png");
    ball[3] = ImageLoader.loadImage("/images/ball4.png");
    ball[4] = ImageLoader.loadImage("/images/ball5.png");
    ball[5] = ImageLoader.loadImage("/images/ball6.png");
    ball[6] = ImageLoader.loadImage("/images/ball7.png");
    ball[7] = ImageLoader.loadImage("/images/ball8.png");
    ball[8] = ImageLoader.loadImage("/images/ball9.png");
    
    
    pillS = ImageLoader.loadImage("/images/pill_1.png");
    
    pill = new BufferedImage[3];
    pill[0] = ImageLoader.loadImage("/images/pill_2.png");
    pill[1] = ImageLoader.loadImage("/images/pill_3.png");
    pill[2] = ImageLoader.loadImage("/images/pill_4.png");
    
    bounce = new BufferedImage[4];
    bounce[0] = ImageLoader.loadImage("/images/player1.png");
    bounce[1] = ImageLoader.loadImage("/images/player2.png");
    bounce[2] = ImageLoader.loadImage("/images/player3.png");
    bounce[3] = ImageLoader.loadImage("/images/player4.png");
    }
    
}
