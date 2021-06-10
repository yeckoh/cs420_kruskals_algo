/*
 * wchang   00960978
 * Dr. Zhang @ CNU.edu
 * CS 420 Algorithms Project - kruskal's algo
 * 31 Mar 2k19
 */
public class vtexLink {

    graphNode g;
    vtexLink nxt;
    public vtexLink() {
    }
    public vtexLink(graphNode _g) {
        g = _g;
    }
    public vtexLink nex() {
        return nxt;
    }
    public graphNode getNode() {
        return g;
    }
    public void linkTo(vtexLink link) {
        nxt = link;
    }
}
