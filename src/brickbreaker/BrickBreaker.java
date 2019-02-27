/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

/**
 *
 * @author eugenio
 */
public class BrickBreaker {

/**
 * @param args the command line arguments 
 */

    public static void main(String[] args){
        //creating a game object
        Game g = new Game("Brick Breaker", 600,800);
        //initialize the game
        g.start();
    }
}