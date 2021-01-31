import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for p4 game
 */
public class Launcher {
    //set a grid
    static Case[][] grid = new Case[7][6];
    static Player currentPlayer;
    static int victory=0;
    static Case currentToken;

    //Player input
    static int inputX;
    static int inputY;

    List<Case> cases = new ArrayList<>();

    public static void main(String[] args) {

        //init grid
        for (int y=0; y<5; y++){
            for (int x=0; x<6; x++){
                grid[x][y] = new Case();
            }
        }


        // main loop
        do{
            for (Player player: Player.values()){
                currentPlayer = player;
                showGrid(grid);

               // play and generate a Token
               currentToken= scanPlayerInput(player);
               // check grid status on Y axis
               for (int i=5; i==0 ; i--){
                   if (grid[currentToken.getxAxis()][i] !=null){
                       grid[currentToken.getyAxis()][i] = currentToken;
                   }
               }
                showGrid(grid);
            }
        }while (victory==0);
        System.out.println("YES YOU WIN" + currentPlayer);
    }

    private static void showGrid (Case[][]grid){
        StringBuilder currentLine= new StringBuilder();
        currentLine.append("-------------");
        System.out.println(currentLine);
        currentLine.setLength(0);

        for (int y=0; y<5; y++){
            for (int x=0; x<6; x++){
                if (grid[x][y].getColor() != null){
                    if (grid[x][y].getColor().equals(Player.BLACK)){
                        currentLine.append("|B");
                    } else{
                        currentLine.append("|R");
                    }
                } else {
                    currentLine.append("| ");
                }
            }
            currentLine.append("|");
            System.out.println(currentLine);
            currentLine.setLength(0);
            currentLine.append("-------------");
            System.out.println(currentLine);
            currentLine.setLength(0);
        }
    }

    private static Case addToken(Player color, int yAxis){
        Case caseToAdd = new Case(color, yAxis);
        return caseToAdd;
    }

    private static boolean checkGrid(Case caseToAdd){
        if(grid[caseToAdd.getxAxis()][caseToAdd.getyAxis()] != null){
            System.out.println("program error");
            return false;
        }else return true;
    }

    private static boolean checkVictory(){
        return false;
    }

    public static Case scanPlayerInput(Player currentPlayer){
        //next player
        System.out.println("Player" + currentPlayer + "Select a column (1-7)");

        try ( Scanner scanner = new Scanner( System.in ) ) {
            int input = scanner.nextInt();
            input = input--;

           return addToken(currentPlayer, input);
        }
    }


}