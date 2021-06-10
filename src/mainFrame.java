import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * wchang   00960978
 * Dr. Zhang @ CNU.edu
 * CS 420 Algallthms Project - kruskal's algo
 * 3 Apr 2k19
 */
@SuppressWarnings("serial")
public class mainFrame extends JFrame {

    public mainFrame() {
        setTitle("kruskal algo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        graphPanel p = new graphPanel();
        p.setBackground(Color.BLACK);
        add(p, BorderLayout.CENTER);

        JPanel btmPanel = new JPanel();
        btmPanel.setLayout(new FlowLayout());
        add(btmPanel, BorderLayout.SOUTH);
        btmPanel.setBackground(Color.DARK_GRAY);
        JButton conNode = new JButton("Connect to:");
        JLabel nodeLabel = new JLabel("node: ");
        nodeLabel.setForeground(Color.WHITE);
        JTextField nodeSelector = new JTextField(1);
        JTextField connectTo = new JTextField(6);
        JButton kruskal = new JButton("Kruskal it");
        btmPanel.add(nodeLabel);
        btmPanel.add(nodeSelector);
        btmPanel.add(conNode);
        btmPanel.add(connectTo);
        btmPanel.add(kruskal);

        conNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = nodeSelector.getText();
                Scanner scnr = null;
                try {
                    scnr = new Scanner(connectTo.getText());
                    scnr.useDelimiter("[, \n]");
                    graphNode selectedNode = null;
                    for (int i = 0; i < p.nodes.size(); i++) {
                        if (p.nodes.get(i).label.equals(sel)) {
                            selectedNode = p.nodes.get(i);
                            break;
                        }
                    }
                    if (selectedNode == null)
                        return;
                    while (scnr.hasNext()) {
                        String next = scnr.next();
                        for (int i = 0; i < p.nodes.size(); i++) {
                            graphNode gn = p.nodes.get(i);
                            if (gn.label.equals(next)) {
                                graphEdge newOne = new graphEdge(selectedNode,
                                        gn);
                                if (!p.edges.contains(newOne)) { // no dupes
                                    p.edges.add(newOne);
                                }
                            }
                        }
                    }
                }
                catch (Exception ee) {
                    System.out.println(ee.toString());
                }
                finally {
                    scnr.close();
                    nodeSelector.setText("");
                }

            }
        }); // end.conNode AL

        // -----------------------------------------------------------------------
        // Algorithm Magic
        // -----------------------------------------------------------------------
        kruskal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.edgesCompared = 0;
                if(p.edges.isEmpty())
                    return;
                p.edges.sort(null); // initialize edge set in nondecreasing order
                p.mst.clear(); // initialize set of tree edges to 0
                int encounter = 0; // initialize set size to 0
                int k = -1; // initialize processed edge count

                vtexList vList = new vtexList(); // set up disjoint sets
                for (graphNode gn : p.nodes)
                    vList.AddVtex(gn);
                while (encounter < p.nodes.size() - 1) {
                    k++;
                    // start unionizing
                    vtexLink vtsrc = vList.Find(new vtexLink(p.edges.get(k).src));
                    vtexLink vtdest = vList.Find(new vtexLink(p.edges.get(k).dest));
                    p.edgesCompared++;
                    // if not in same subset, union and add edge to MST
                    if (vtsrc != vtdest) {
                        vList.Union(vtsrc, vtdest);
                        p.mst.add(p.edges.get(k));
                        encounter++;
                    }
                    if (k == p.edges.size() - 1) // disconnected graph
                        break; // becomes a MSF, or Min-Span-Forest
                } // end.while < V-1 edges
            } //end.actPerform
        }); // end.kruskal ActListen

        JButton all = new JButton("Show ALL");
        JButton mst = new JButton("Show MST");
        btmPanel.add(all);
        btmPanel.add(mst);
        all.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                p.setHideMST();
            }
        }); // end.all
        mst.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                p.setShowMST();
            }
        }); // end.mst

        JButton delAll = new JButton("Clear the board");
        btmPanel.add(delAll);
        delAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.nodes.clear();
                p.edges.clear();
                p.mst.clear();
                p.setHideMST();
                p.edgesCompared = 0;
                setTitle("kruskal algo");
            }
        }); // end.delAll AL
        
        JButton complete = new JButton("Make complete");
        btmPanel.add(complete);
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.edges.clear();
                for(graphNode g1 : p.nodes) {
                    for(int i = p.nodes.indexOf(g1) + 1;i < p.nodes.size();i++) {
                        if(g1 == p.nodes.get(i))
                            continue;
                        p.edges.add(new graphEdge(g1,p.nodes.get(i)));
                    }
                }
            } // end.actionPerform
        }); // end.complete AL
        
        // -----------------------------------------------------------------------
        // Menu bar and fileIO
        // -----------------------------------------------------------------------
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);
        JMenu jmFile = new JMenu("File");
        jmb.add(jmFile);
        JMenuItem fileSave = new JMenuItem("Save graph");
        jmFile.add(fileSave);
        fileSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFileChooser chooser = new JFileChooser();
                        int result = chooser.showSaveDialog(mainFrame.this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();
                            PrintWriter fout = null;
                            try {
                                fout = new PrintWriter(file);
                                for(graphNode gn : p.nodes) {
                                    fout.println(gn.toString());
                                }
                                fout.println("edges");
                                for(graphEdge ge : p.edges) {
                                    fout.println(ge.src.label + ' ' + ge.dest.label);
                                }
                            } // endof.try
                            catch (Exception e) {
                                e.toString();
                            }
                            finally {
                                fout.close();
                            }
                        } // endof.if
                    } // endof.run
                }); // endof.invokelater
            }
        }); // end.fileSave AL
        
        JMenuItem fileLoad = new JMenuItem("Load graph");
        jmFile.add(fileLoad);
        fileLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                p.nodes.clear();
                p.edges.clear();
                p.mst.clear();
                p.setHideMST();
                p.edgesCompared = 0;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFileChooser chooser = new JFileChooser();
                        int result = chooser.showOpenDialog(mainFrame.this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();
                            String inputReader = file.toString();
                            File fin = new File(inputReader);
                            Scanner scnr = null;
                            try {
                                setTitle("kruskal algo " + '[' + inputReader + ']');
                                scnr = new Scanner(fin);
                                
                                // load
                                String next;
                                float xPos;
                                float yPos;
                                while (scnr.hasNext()) {
                                    next = scnr.next();
                                    if(next.contains("edges")) {
                                        break;
                                    }
                                    xPos = Float.parseFloat(next);
                                    yPos = scnr.nextFloat();
                                    p.nodes.add(new graphNode(xPos, yPos, scnr.next()));
                                } // endof.while
                                while (scnr.hasNext()) {

                                    graphNode selectedNode = null;
                                    String srcNode = scnr.next();
                                    String destNode = scnr.next();
                                    for (int i = 0; i < p.nodes.size(); i++) {
                                        
                                        if (p.nodes.get(i).label.equals(srcNode)) {
                                            selectedNode = p.nodes.get(i);
                                            break;
                                        }
                                    }
                                    graphNode targetNode = null;
                                    for (int i = 0; i < p.nodes.size(); i++) {
                                        if (p.nodes.get(i).label.equals(destNode)) {
                                            targetNode = p.nodes.get(i);
                                            break;
                                        }
                                    }
                                    p.edges.add(new graphEdge(selectedNode,targetNode));
                                } // endof.while
                            } // endof.try
                            catch (Exception e) {
                                e.toString();
                            }
                            finally {
                                scnr.close();
                            }
                        } // endof.if
                    } // endof.run
                }); // endof.invokelater
            } // end.actionPerform            
        }); // end.fileLoad AL
        

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                p.stopTimer();
                // dispose();
            }
        }); // end.WindowListener
    } // end.mainFrame constructor

    public static void main(String[] args) {
        JFrame f = new mainFrame();
        f.setVisible(true);
    }
}
