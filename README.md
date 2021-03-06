# cs420_kruskals_algo
Kruskal finds the Minimum Spanning Forest unlike Prim, which requires a jointed graph. But will find a MST all the same

Implements linked lists to organize vertex sets (the simplest method) and a vector for the edge set. It will find the MSF if the graph is disjoint IIRC.

Also these are technically digraphs, theres just no visual distinction. It will let you connect a -> b and b -> a. The 'make complete' button does not distinguish direction, and will not create two edges

Read More: https://en.wikipedia.org/wiki/Kruskal%27s_algorithm

Click anywhere to create vertices starting from 'a'. There is no overflow control past 26 vertices but keep going if you want!

![alt text](https://i.imgur.com/8oFDiA7.png)
![alt text](https://i.imgur.com/fJyKX4C.png)

// Input: A weighted connected graph G = < V, E >

// Output: E_t, the set of edges composing a minimum spanning tree of G

sort E in nondecreasing order of the edge wrights w(π_(π_1 )) β€ β¦β€ w( π_(π_(|πΈ|) ))

    E_t βΓ; ecounter β 0 // initialize the set of tree edges and its size

    k β 0 // initialize the number of processed edges

    while ecounter < |V| -1 do

        k β k + 1

        if  E_t Union{π_(π_π )} is acyclic

        E_t β E_t βnion{π_(π_π )}; ecounter β ecounter + 1

    return E_t
