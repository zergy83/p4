package p4.game;

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

    public Case(Player color, int xAxis) {
        this.color = color;
        this.xAxis = xAxis;
    }

    public Case(Player color) {
        this.color = color;
    }

    public Case() {
    }

    public void setxAxis(final int xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(final int yAxis) {
        this.yAxis = yAxis;
    }
}
