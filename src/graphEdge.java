import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/*
 * wchang   00960978
 * Dr. Zhang @ CNU.edu
 * CS 420 Algorithms Project - kruskal's algo
 * 31 Mar 2k19
 */
public class graphEdge extends Line2D implements Comparable<graphEdge> {
    static Color red = new Color(188, 55, 55);
    static Color purple = new Color(126, 11, 188);
    static Color pmpkn = new Color(198, 106, 59);
    static Color blu = new Color(14, 70, 90);
    static Color grn = new Color(59, 198, 78);
    static Color dkgry = new Color(30, 30, 30);
    graphNode src;
    graphNode dest;
    Integer weight;

    // weight by pixel euclidean dist
    public graphEdge(graphNode s, graphNode d) {
        src = s;
        dest = d;
        super.setLine(s, d);
        weight = (int) src.distance(dest);
    }
    
    // override weight
    public graphEdge(graphNode s, graphNode d, int w) {
        src = s;
        dest = d;
        super.setLine(s, d);
        weight = w;
    }

    @Override
    public Rectangle2D getBounds2D() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getX1() {
        return src.getX();
    }

    @Override
    public double getY1() {
        return src.getY();
    }

    @Override
    public Point2D getP1() {
        return src;
    }

    @Override
    public double getX2() {
        return dest.getX();
    }

    @Override
    public double getY2() {
        return dest.getY();
    }

    @Override
    public Point2D getP2() {
        return dest;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {

    }

    public void draw(Graphics2D g2) {

        int xxx = (int) (src.getX() + dest.getX()) / 2;
        int yyy = (int) (src.getY() + dest.getY()) / 2 + 4;
        //g2.setColor(Color.PINK);
        g2.setColor(graphEdge.blu);
        g2.draw(this);
        //g2.setColor(Color.GREEN);
        g2.setColor(graphEdge.red);
        g2.drawString(weight.toString(), xxx, yyy);

    }

    @Override
    public int compareTo(graphEdge o) {
        if (this.weight - o.weight == 0)
            return this.src.label.compareTo(o.src.label);
        return this.weight - o.weight;
    }
}
