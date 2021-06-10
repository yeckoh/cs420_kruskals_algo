import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/*
 * wchang   00960978
 * Dr. Zhang @ CNU.edu
 * CS 420 Algorithms Project - kruskal's algo
 * 31 Mar 2k19
 */
public class graphNode extends Point2D {

    float xx;
    float yy;
    String label = "X";
    Rectangle2D r;

    public graphNode(float x, float y) {
        xx = x;
        yy = y;
        r = new Rectangle2D.Float(x - 6, y - 6, 14, 14);
    }

    public graphNode(float x, float y, String l) {
        xx = x;
        yy = y;
        label = l;
        r = new Rectangle2D.Float(x - 6, y - 6, 14, 14);
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.DARK_GRAY);
        //g2.setColor(Color.CYAN);
        g2.setColor(graphEdge.dkgry);
        g2.fill(r); // bkgrnd
        g2.setColor(graphEdge.pmpkn);
        g2.drawString(label, xx - 3, yy + 6); // text
    }

    @Override
    public double getX() {
        return xx;
    }

    @Override
    public double getY() {
        return yy;
    }

    @Override
    public void setLocation(double x, double y) {
        xx = (float) x;
        yy = (float) y;
    }

    public int getLabel() {
        return label.toCharArray()[0] - 97;
    }
    
    @Override
    public String toString() {
        return this.xx + " " + this.yy + " " + this.label;
    }
}
