/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.LinkedList;


/**
 *
 * @author eugenio
 */
public class SaveLoad {

    private Game game;
    
    SaveLoad(Game game) {
        this.game = game;
    }

    void saveGame() {
        try {
            //Creates new file to save the game data
            FileWriter fw = new FileWriter("save.txt");
            //Saves every value of every object in the game
            fw.write(String.valueOf(game.getPlayer().getX()) + "\n");
            fw.write(String.valueOf(game.getPlayer().getY()) + "\n");
            
            fw.write(String.valueOf(game.getBalls())+ "\n");
            for (int i = 0; i < game.getBalls(); i++) {
                fw.write(String.valueOf(game.balls.get(i).getX()) + "\n");
                fw.write(String.valueOf(game.balls.get(i).getY()) + "\n");
                fw.write(String.valueOf(game.balls.get(i).getDirX()) + "\n");
                fw.write(String.valueOf(game.balls.get(i).getDirY()) + "\n");
            }
            
            fw.write(String.valueOf(game.getBricks()) + "\n");
            for (int i = 0; i < game.getBricks(); i++) {
                fw.write(String.valueOf(game.bricks.get(i).getX()) + "\n");
                fw.write(String.valueOf(game.bricks.get(i).getY()) + "\n");
            }
            
            fw.write(String.valueOf(game.powUP1.size()) + "\n");
            for (int i = 0; i < game.powUP1.size(); i++) {
                fw.write(String.valueOf(game.powUP1.get(i).getX()) + "\n");
                fw.write(String.valueOf(game.powUP1.get(i).getY()) + "\n");
            }
            
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loadGame() {
        try {
            //Loads file where every value of the game was saved
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            //Read every value in the file so it can be loaded
            game.getPlayer().setX(Integer.parseInt(br.readLine()));
            game.getPlayer().setY(Integer.parseInt(br.readLine()));
            // Loads balls from previous save
            int balls = Integer.parseInt(br.readLine());
            game.balls.clear();
            for(int i = 0; i < balls; i++){
                // Reads data
                int x = Integer.parseInt(br.readLine());
                int y = Integer.parseInt(br.readLine());
                int DirX = Integer.parseInt(br.readLine());
                int DirY = Integer.parseInt(br.readLine());
                // Creates objects from data
                game.balls.addFirst(new Ball(x,y,30,30,game));
                game.balls.getFirst().setDirX(DirX);
                game.balls.getFirst().setDirY(DirY);
                game.balls.getFirst().setMove(true);
            }
            // Loads bricks from previous save
           game.bricks.clear();
            int bricks = Integer.parseInt(br.readLine());
            for (int i = 0; i < bricks; i++){
                // Read data
                int x = Integer.parseInt(br.readLine());
                int y = Integer.parseInt(br.readLine());
                // create object from data
                game.bricks.addFirst(new Brick(x,y,50,50,game));
            }
            // Load power ups from previous game
            game.powUP1.clear();
            int powerUPs = Integer.parseInt(br.readLine());
            for(int i = 0; i < powerUPs;i++){
                // Read data
                int x = Integer.parseInt(br.readLine());
                int y = Integer.parseInt(br.readLine());
                // create object from data
                game.powUP1.addFirst(new pow1(x,y,50,50,game));
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
