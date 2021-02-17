package p4.game;

import org.junit.Assert;
import org.junit.Test;

/**
 * [Enter type description here].
 *
 * @author Bull/Atos
 */
public class GridLayoutTest {

    @Test
    public void initGridTest() {
        GridLayout gridLayout = new GridLayout();

        //method call
        gridLayout.initGrid();

        for (int y = 0; y < gridLayout.getySize(); y++) {
            for (int x = 0; x < gridLayout.getxSize(); x++) {
                Assert.assertTrue(gridLayout.getGrid()[x][y] instanceof Case);
                Assert.assertEquals(null, gridLayout.getGrid()[x][y].getColor());
            }
        }
    }
    @Test
      public void showGridTest(){
        final StringBuilder control = new StringBuilder("--------------\n" +
                "R| | | | | | |\n" +
                "--------------\n" +
                "R| | | | | | |\n" +
                "--------------\n" +
                "R| | | | | | |\n" +
                "--------------\n" +
                "R| | | | | | |\n" +
                "--------------\n" +
                " | | | | | | |\n" +
                "--------------\n" +
                " | | | | | | |\n" +
                "--------------\n");
            GridLayout gridLayout = new GridLayout();
            gridLayout.initGrid();
            gridLayout.getGrid()[0][0].setColor(Player.BLACK);
            gridLayout.getGrid()[0][1].setColor(Player.BLACK);
            gridLayout.getGrid()[0][2].setColor(Player.BLACK);
            gridLayout.getGrid()[0][3].setColor(Player.BLACK);
            gridLayout.getGrid()[0][0].setColor(Player.RED);
            gridLayout.getGrid()[0][1].setColor(Player.RED);
            gridLayout.getGrid()[0][2].setColor(Player.RED);
            gridLayout.getGrid()[0][3].setColor(Player.RED);

            System.out.println(gridLayout.buildGrid());


            Assert.assertEquals(control, gridLayout.buildGrid());
        }
    }

