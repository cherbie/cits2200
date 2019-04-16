import CITS2200.*;

public class GraphTest
{
    public static void main(String[] args)
    {
        Graph g = Graph.randomGraph(8, true, 0.8); //unweighted, directed graph
        print("Number of vertices = " + g.getNumberOfVertices());
        print("Directed = " + g.isDirected());
        print("Weighted = " + g.isWeighted());
        print(g.toString());
        print("main complete.");
    }

    /**
     * Prints to standard out.
    **/
    public static void print(String s)
    {
        System.out.println(s);
    }
}