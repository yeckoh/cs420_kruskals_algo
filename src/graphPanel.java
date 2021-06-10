import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
/*
 * wchang   00960978
 * Dr. Zhang @ CNU.edu
 * CS 420 Algorithms Project - kruskal's algo
 * 31 Mar 2k19
 */
@SuppressWarnings("serial")
public class graphPanel extends JPanel {

    public ArrayList<graphNode> nodes = new ArrayList<graphNode>();
    public ArrayList<graphEdge> edges = new ArrayList<graphEdge>();
    public ArrayList<graphEdge> mst = new ArrayList<graphEdge>();

    private boolean showMST = false;
    private Timer timer;
    int edgesCompared = 0;

    public graphPanel() {
        setFocusable(true);
        setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                Character c = (char) (nodes.size() + 97);
                nodes.add(new graphNode(e.getX(), e.getY(), c.toString()));
            }
        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                graphNode gn = nodes.get(nodes.size()-1);
                gn.xx = e.getX();
                gn.yy = e.getY();
                gn.r.setRect(gn.xx-6, gn.yy-6, 14, 14);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        timer = new Timer(16, new ActionListener() { // ~60fps
            @Override
            public void actionPerformed(ActionEvent e) {

                repaint();
            }
        }); // endof.timer checking
        timer.start();

    } // endof.constr

    /**
     * stops the timer
     */
    public void stopTimer() {
        timer.stop();
    }

    /**
     * restarts the timer
     */
    public void startTimer() {
        timer.start();
    }

    public void setShowMST() {
        showMST = true;
    }

    public void setHideMST() {
        showMST = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // FontMetrics fm = g2.getFontMetrics();
        g2.drawString("edges considered: " + edgesCompared, 20, 20);
        g2.drawString("total edges: " + edges.size(), 20, 40);
        if (showMST) {
            g2.drawString("Showing MST", this.getWidth() / 2 - 40,
                    this.getHeight() - 20);
            for (graphEdge ge : mst) {
                ge.draw(g2);
            }
        }
        else {
            g2.drawString("Showing ALL", this.getWidth() / 2 - 40,
                    this.getHeight() - 20);
            for (graphEdge ge : edges) {
                ge.draw(g2);
            }
        }
        for (graphNode gn : nodes) {
            gn.draw(g2);
        }
    } // endof.paintComponent

}
