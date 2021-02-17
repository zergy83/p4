package p4.game;/*
 * Copyright (c) 2016 Bull/Atos.
 * All rights reserved.
 */

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * p4.game.Game Class that manages a p4.game.Game session
 *
 * @author Bull/Atos
 */
public class Game {
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    /** Current  Player*/
    private Player currentPlayer;
    /** Victory status*/
    private boolean victory;
    /** Current token to Add to Grid */
    private Case tokenToAdd;
    /** System.in scanner */
    private Scanner myScanner;

    /** player inputs */
    private int inputX;
    private int inputY;

    /** Current player score*/
    int score = 1;

    /** Game Gridlayout*/
    private GridLayout gridLayout;


    public Game() {
        //Instantiate p4.game.GridLayout and init it
        this.gridLayout = new GridLayout();
        gridLayout.initGrid();
        initScanner();
    }

    /**
     * Launch a new Game
     */
    public void launchNewGame() throws Exception {
        // main loop
        do{
            for (Player player: Player.values()){
                victory = checkVictory();
                LOGGER.debug("Victory? {}", victory);
                currentPlayer = player;
                gridLayout.displayGrid(gridLayout.buildGrid());

                // play and generate a Token
                boolean authorizedMove = false;
                while (!authorizedMove){
                    if(checkVictory()){
                        break;
                    };
                    LOGGER.debug("Victory? {}", victory);


                    int xToken = scanPlayerInput(player);
                    tokenToAdd = addToken(currentPlayer, xToken);
                    LOGGER.debug("PLAYER CHOOSE COLUMN {}", tokenToAdd.getxAxis());
                    authorizedMove = checkColumn(currentPlayer, tokenToAdd);

                    if (!authorizedMove){
                        // check grid status on Y axis
                        LOGGER.debug("You cannot play here");
                    }
                }
                }
        }while (!victory);
        LOGGER.debug("{} WINS", currentPlayer);
        closeScanner();
    }

    /**
     * Checks token can be place in this column (aka a row is available)
     * @param currentMove
     * @return
     */
    public boolean checkColumn(Player currentPlayer, Case currentMove) {
        for (int i=(gridLayout.getySize()-1); i!= -1 ; i--){
            LOGGER.debug("Check column {} row {} ", currentMove.getxAxis(), i);
            if(checkCase(currentPlayer, currentMove.getxAxis(), i)){
            return true;
            }
        }
        return false;
    }

    public boolean checkCase(Player currentPlayer, int x, int y) {
        LOGGER.debug("Testing {} // {}", x , y);
        if (gridLayout.getGrid()[x][y].getColor() == null){
            gridLayout.getGrid()[x][y] = new Case(currentPlayer);
            LOGGER.debug("Check column {} row {} color {}", x , y ,gridLayout.getGrid()[x][y].getColor());
            return true;
        }else{
            return false;
        }
    }

    public Case addToken(Player color, int xAxis){
        Case caseToAdd = new Case(color, xAxis);
        LOGGER.debug("GENERATING CASE {} {}", caseToAdd.getColor(), caseToAdd.getxAxis());
        return caseToAdd;
    }


    /**Checks victory conditions to end p4.game
     * @return
     */
    public boolean checkVictory(){
        if(checkVerticalVictory() || checkHorizontalVictory() || checkDiagVictory()){
            LOGGER.debug("{}, {}, {}", checkVerticalVictory(),checkHorizontalVictory(),checkDiagVictory());
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDiagVictory() {
        for (int x=0; x <gridLayout.getxSize(); x++) {
            for (int y = 0; y < gridLayout.getySize(); y++) {
                Player currentTokenColor = null;
                if(gridLayout.getGrid()[x][y].getColor() != null){
                    currentTokenColor = gridLayout.getGrid()[x][y].getColor();
                    if (checkMinusDiag(currentTokenColor, x ,y) || checkPlusDiag(currentTokenColor, x ,y)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkPlusDiag(final Player currentTokenColor, final int x, final int y) {

        if(x+1 < gridLayout.getxSize() && y+1<gridLayout.getySize() && gridLayout.getGrid()[x+1][y+1].getColor() != null
                && gridLayout.getGrid()[x+1][y+1].getColor().equals(currentTokenColor)){
            score++;
            if(checkScore(score)){
                return victory = true;
            }
        }
        if( !victory && x+1 < gridLayout.getxSize() && y+1<gridLayout.getySize()){
            checkPlusDiag(currentTokenColor, x+1, y+1);
        }
            score =1;
            return victory;
    }

    public boolean checkMinusDiag(final Player currentTokenColor, final int x, final int y) {
        if(x-1>-1 && y+1<gridLayout.getySize() && gridLayout.getGrid()[x-1][y+1].getColor() != null
                && gridLayout.getGrid()[x-1][y+1].getColor().equals(currentTokenColor)){
            score++;
            if(checkScore(score)){
                return victory = true;
            }
            if(x-1 >0 && y+1<gridLayout.getySize()){
                checkMinusDiag(currentTokenColor, x-1, y+1);
            }
        }
        score =1;
        return victory;
    }


    public boolean checkHorizontalVictory() {
        StringBuilder verticalLine = new StringBuilder();
        int winLine =1;
        Player previousTokenColor = null;
        // Browse for vertical or horizontal win
        for (int y=0; y<gridLayout.getySize(); y++){
            for (int x=0; x <gridLayout.getxSize(); x++){
                if(previousTokenColor != null && gridLayout.getGrid()[x][y].getColor() != null){
                    Player currentTokenColor =gridLayout.getGrid()[x][y].getColor();
                    LOGGER.debug("{} // {}", previousTokenColor, currentTokenColor);
                    if(previousTokenColor.equals(currentTokenColor)){
                        verticalLine.append(currentTokenColor +", ");
                        winLine++;
                        LOGGER.debug("SCORE {}", winLine);
                        if(checkScore(winLine)){
                            LOGGER.debug("{}", verticalLine);
                            return true;
                        };
                    } else{
                        previousTokenColor = gridLayout.getGrid()[x][y].getColor();
                        verticalLine.append(previousTokenColor +", ");
                        winLine =1;
                    }
                }else {
                    previousTokenColor = gridLayout.getGrid()[x][y].getColor();
                }
            }
            winLine = 1;
        }
        return false;
    }

    public boolean checkVerticalVictory() {
        StringBuilder verticalLine = new StringBuilder();
        int winLine =1;
        Player previousTokenColor = null;
        // Browse for vertical or horizontal win
        for (int x=0; x <gridLayout.getxSize(); x++){
            for (int y=0; y<gridLayout.getySize(); y++){
                if(previousTokenColor != null && gridLayout.getGrid()[x][y].getColor() != null){
                    Player currentTokenColor =gridLayout.getGrid()[x][y].getColor();
                    LOGGER.debug("{} // {}",previousTokenColor, currentTokenColor);
                    if(previousTokenColor.equals(currentTokenColor)){
                        verticalLine.append(currentTokenColor +", ");
                        winLine++;
                        LOGGER.debug("SCORE {}", winLine);
                        if(checkScore(winLine)){
                            LOGGER.debug("{}", verticalLine);
                            return true;
                        };
                    } else{
                        previousTokenColor = gridLayout.getGrid()[x][y].getColor();
                        verticalLine.append(previousTokenColor +", ");
                        winLine =1;
                    }
                }else {
                    previousTokenColor = gridLayout.getGrid()[x][y].getColor();
                }
            }
            winLine = 1;
        }
        return false;
    }

    public boolean checkScore(final int winLine) {
        if (winLine == 4){
            System.out.println("YOU WON");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Scans selected column by current player
     * @param currentPlayer
     * @return
     */
    public int scanPlayerInput(Player currentPlayer){
        //next player
        System.out.println(currentPlayer + " Player's turn, Select a column (1-" + (gridLayout.getxSize()) + "):\n");

            int input = 0;
                input = scan(myScanner);
                if(input>0 && input< gridLayout.getxSize()+1){
                    input--;
                    LOGGER.debug("INPUT {}", input);
                    return input;
                } else {
                    System.out.println("Try again: Select a column (1-7):");
                    return scanPlayerInput(currentPlayer);
                }
        }

        public int scan(Scanner myScanner){
        return myScanner.nextInt();
        }


    /**
     * init scanner at p4.game beginning
     */
    public void initScanner(){
        myScanner = new Scanner( System.in );
    }

    /**
     * close scannet at p4.game ending
     */
    public void closeScanner(){
        myScanner.close();
    }

    public GridLayout getGridLayout() {
        return gridLayout;
    }

    public void setGridLayout(final GridLayout gridLayout) {
        this.gridLayout = gridLayout;
    }

    public void setVictory(final boolean victory) {
        this.victory = victory;
    }
}
