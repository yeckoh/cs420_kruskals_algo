import java.util.ArrayList;
/*
 * wchang   00960978
 * Dr. Zhang @ CNU.edu
 * CS 420 Algorithms Project - kruskal's algo
 * 31 Mar 2k19
 */
public class vtexList {

    ArrayList<vtexLink> vtlinks = new ArrayList<vtexLink>();
    ArrayList<vtexLink> vtlastLink = new ArrayList<vtexLink>();
    ArrayList<Integer> vtsizes = new ArrayList<Integer>();
    
    public vtexList() {
        // do nothing
    }

    public void Union(vtexLink a, vtexLink b) {
        vtexLink start;
        int startInd = vtlinks.indexOf(a);
        int startSize = vtsizes.get(startInd);
        vtexLink end;
        int endInd = vtlinks.indexOf(b);
        int endSize = vtsizes.get(endInd);
        // union by size: start of bigger to end of smaller
        if (startSize < endSize) {
            end = vtlastLink.get(startInd);
            start = b;
            vtsizes.set(startInd, startSize + endSize);
            vtlastLink.set(startInd, vtlastLink.get(endInd));
            vtsizes.remove(endInd);
            vtlastLink.remove(endInd);
        }
        else {
            end = vtlastLink.get(endInd);
            start = a;
            vtsizes.set(endInd, startSize + endSize);
            vtlastLink.set(endInd, vtlastLink.get(startInd));
            vtsizes.remove(startInd);
            vtlastLink.remove(startInd);
        }
        end.linkTo(start);
        vtlinks.remove(start);
    } // end.Union

    public vtexLink Find(vtexLink l) {
        vtexLink start;
        vtexLink vtl;
        for (int i = 0; i < vtlinks.size(); i++) {
            start = vtlinks.get(i);
            if (start.g == l.g)
                return start;
            else {
                vtl = start;
                while (vtl.nxt != null) {
                    vtl = vtl.nex();
                    if (vtl.g == l.g)
                        return start;
                }
            }
        } // should never return null really
        return null;
    } // end.Find
    
    public void AddVtex(graphNode g) {
//        int i = 0;
//        for (graphNode gn : p.nodes) {
//            vList.vtlinks.add(new vtexLink(gn));
//            vList.vtsizes.add(1);
//            vList.vtlastLink.add(vList.vtlinks.get(i++));
//        }
        vtlinks.add(new vtexLink(g));
        vtsizes.add(1);
        vtlastLink.add(vtlinks.get(vtlinks.size()-1));
    }
}
