import CITS2200.*;

public class GraphTest
{
    public static void main(String[] args)
    {
        Graph g1 = Graph.randomGraph(8, true, 0.5); //unweighted, directed graph
        infoGraph1(g1, 8); //display information about the unweighted, directed graph
        testGraph1(g1, 8); //perform test and print out the parent nodes of the spanning tree

    }

    private static void infoGraph1(Graph g, int numvertices)
    {
        print("Number of vertices = " + g.getNumberOfVertices());
        print("Directed = " + g.isDirected());
        print("Weighted = " + g.isWeighted());
        print(g.toString());
        print("main complete.");
    }

    private static void testGraph1(Graph g, int numvertices)
    {
        SearchImp search = new SearchImp();
        int[] parentarray = search.getConnectedTree(g, 6);
        printParentArray(parentarray);
    }

    private static void printParentArray(int[] a)
    {
        int len = a.length;
        for(int i = 0; i < len; i++)
        {
            print(i + " --> " + a[i]);
        }
    }

    /**
     * Prints to standard out.
    **/
    private static void print(String s)
    {
        System.out.println(s);
    }
}