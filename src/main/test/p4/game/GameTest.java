package p4.game;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * [Enter type description here].
 *
 * @author Bull/Atos
 */

public class GameTest {
        @Test
        public void checkColumnTest(){
            Game game = new Game();
            game.getGridLayout().initGrid();
            Case caseMock = new Case();
            caseMock.setxAxis(0);

            Assert.assertEquals(true,game.checkColumn(Player.RED, caseMock));

            for (int y =0; y<game.getGridLayout().getySize(); y++){
                game.getGridLayout().getGrid()[0][y] = new Case(Player.BLACK);
            }
            Assert.assertEquals(false, game.checkColumn(Player.RED, caseMock));
        }

        @Test
        public void checkCaseTest(){
            Game game = new Game();
            game.getGridLayout().initGrid();
            Assert.assertEquals(true, game.checkCase(Player.RED, 0,0));
            game.getGridLayout().getGrid()[0][1] = new Case(Player.BLACK);
            Assert.assertEquals(false, game.checkCase(Player.RED, 0,1));
        }

    @Test
    public void addTokenTest(){
        Game game = new Game();
        game.getGridLayout().initGrid();
        Assert.assertTrue(game.addToken(Player.BLACK, 0) instanceof Case);
        Assert.assertEquals(Player.BLACK, game.addToken(Player.BLACK, 0).getColor());
        Assert.assertEquals(0, game.addToken(Player.BLACK, 0).getxAxis());
    }

    @Test
    public void checkVictoryTest(){
            Game gameMock = new Game();
            Assert.assertEquals(false, gameMock.checkVictory());
    }

    @Test
    public void checkVictoryTest2(){
        Game gameMock = new Game(){
            @Override
            public boolean checkDiagVictory() {
                return true;
            }
        };
        Game gameMock2 = new Game(){
            @Override
            public boolean checkHorizontalVictory() {
                return true;
            }
        };
        Game gameMock3 = new Game(){
            @Override
            public boolean checkVerticalVictory() {
                return true;
            }
        };
        Assert.assertEquals(true, gameMock.checkVictory());
        Assert.assertEquals(true, gameMock2.checkVictory());
        Assert.assertEquals(true, gameMock2.checkVictory());
    }

    @Test
    public void checkDiagVictoryTest(){
        GridLayout gridLayout = new GridLayout();
        gridLayout.initGrid();
        gridLayout.getGrid()[0][0].setColor(Player.BLACK);
        gridLayout.getGrid()[1][1].setColor(Player.BLACK);
        gridLayout.getGrid()[2][2].setColor(Player.BLACK);
        gridLayout.getGrid()[3][3].setColor(Player.BLACK);

        Game game = new Game();
        game.setGridLayout(gridLayout);

        Assert.assertTrue(game.checkPlusDiag(Player.BLACK,0,0));
    }

    @Test
    public void checkDiagVictoryTest2(){
        GridLayout gridLayout = new GridLayout();
        gridLayout.initGrid();
        gridLayout.getGrid()[5][0].setColor(Player.BLACK);
        gridLayout.getGrid()[4][1].setColor(Player.BLACK);
        gridLayout.getGrid()[3][2].setColor(Player.BLACK);
        gridLayout.getGrid()[2][3].setColor(Player.BLACK);

        Game game = new Game();
        game.setGridLayout(gridLayout);

        Assert.assertTrue(game.checkMinusDiag(Player.BLACK,5,0));
    }

    @Test
    public void checkHorizontalVictoryTest(){
        GridLayout gridLayout = new GridLayout();
        gridLayout.initGrid();
        gridLayout.getGrid()[0][0].setColor(Player.BLACK);
        gridLayout.getGrid()[1][0].setColor(Player.BLACK);
        gridLayout.getGrid()[2][0].setColor(Player.BLACK);
        gridLayout.getGrid()[3][0].setColor(Player.BLACK);

        Game game = new Game();
        game.setGridLayout(gridLayout);

        Assert.assertTrue(game.checkHorizontalVictory());
    }

    @Test
    public void checkVerticalVictoryTest(){
        GridLayout gridLayout = new GridLayout();
        gridLayout.initGrid();
        gridLayout.getGrid()[0][0].setColor(Player.BLACK);
        gridLayout.getGrid()[0][1].setColor(Player.BLACK);
        gridLayout.getGrid()[0][2].setColor(Player.BLACK);
        gridLayout.getGrid()[0][3].setColor(Player.BLACK);

        Game game = new Game();
        game.setGridLayout(gridLayout);

        Assert.assertTrue(game.checkVerticalVictory());
    }

    @Test
    public void checkScoreTest(){
        Game game = new Game();

        Assert.assertEquals(false, game.checkScore(0));
        Assert.assertEquals(true, game.checkScore(4));
    }

    @Test
    public void scanPlayerInputTest(){
        Game game = new Game(){
            @Override
            public int scan(Scanner myScanner){
                return 1;
            }
        };

        //Method call
        Assert.assertEquals(0, game.scanPlayerInput(Player.RED));
    }

    @Test
    public void scanPlayerInputTest2(){
        Game game = new Game(){
            @Override
            public int scan(Scanner myScanner){
                return 45;
            }
        };

        //Method call
        Assertions.assertThrows(StackOverflowError.class , () ->game.scanPlayerInput(Player.RED) );
    }
}
