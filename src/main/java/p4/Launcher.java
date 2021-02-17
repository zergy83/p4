package p4;

import org.apache.log4j.BasicConfigurator;

import p4.game.Game;

/**
 * Main class for p4 p4.game
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        System.out.println("Welcome to P4 Game");

        Game myGame = new Game();
        myGame.launchNewGame();

        System.out.println("Thanks 4P laying");
    }
}
