/**
 * A P4 case
 */
public class Case {

    private Player color;
    private int xAxis;
    private int yAxis;

    public Player getColor() {
        return color;
    }

    public void setColor(Player color) {
        this.color = color;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        if (xAxis <0 || xAxis>6){
            System.out.println("ERROR");
        } else {
            this.xAxis = xAxis;
        }
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        if (yAxis <0 || yAxis>6){
            System.out.println("ERROR");
        } else {
            this.yAxis = yAxis;
        }
    }

    public Case(Player color, int xAxis) {
        this.color = color;
        this.xAxis = xAxis;
    }

    public Case() {
    }
}
