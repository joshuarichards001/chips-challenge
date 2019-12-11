package renderer;

import application.MainFrame;
import maze.Game;

import java.awt.*;
import java.io.File;

public class GUI extends MainFrame {
 private static Game game;
    @Override
    protected void redraw(Graphics g) {

    }

    @Override
    protected void redrawInventory(Graphics g) {

    }

   @Override
   protected void chipsLeftGraphics(Graphics g) {

   }

    @Override
    protected void timeLeft(Graphics g) {

    }

    @Override
    protected void saveGame() {

    }

    @Override
    protected void newGame() {

    }

    @Override
    protected void pauseGame() {

    }

    @Override
    protected void loadGame(File file) {

    }

    private static void setUp(){
        //game = new Game();

    }

    public static void main(String[] args){
       setUp();
    }

}
