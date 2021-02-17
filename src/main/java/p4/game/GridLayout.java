package p4.game;

/**
 * Class to generate a Grid Layout for P4
 *
 * @author Bull/Atos
 */
public class GridLayout {

    private final int xSize = 7;
    private final int ySize = 6;

    private final Case[][] grid = new Case[xSize][ySize];


    /**
     * Populate Grid with Case instance without color
     */
    public void initGrid(){
        for (int y=0; y<ySize; y++){
            for (int x=0; x<xSize; x++){
                this.grid[x][y] = new Case();
            }
        }
    }

    /**
     * Show grid
     */
    public StringBuilder buildGrid() {
        StringBuilder gridLayout = new StringBuilder();
        gridLayout.append("--------------\n");

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                StringBuilder line = new StringBuilder();
                if (grid[x][y].getColor() != null) {
                    if(grid[x][y].getColor().equals(Player.RED)){
                        line.append("R");
                    }
                    if(grid[x][y].getColor().equals(Player.BLACK)){
                        line.append("B");
                    }
                } else {
                    line.append(" ");
                }
                line.append("|");
                gridLayout.append(line);
            }
            gridLayout.append("\n");
            gridLayout.append("--------------\n");
        }
        return gridLayout;
    }

    public void displayGrid(StringBuilder grid){
        System.out.println(grid);
        grid.setLength(0);
    }

    public Case[][] getGrid() {
        return grid;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

}
