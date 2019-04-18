import CITS2200.*;

public class GraphTest
{
    private static final int nvertex = 500;
    private static final double density = 0.03;

    public static void main(String[] args)
    {
        Graph g1 = Graph.randomGraph(nvertex, true, density); //unweighted, directed graph
        //infoGraph1(g1, nvertex); //display information about the unweighted, directed graph
        testGraph1(g1, nvertex); //perform test and print out the parent nodes of the spanning tree

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
        int[] parentarray = search.getConnectedTree(g, 4);
        printParentArray(parentarray);

        print("Parent + distance.");

        int[] distance = search.getDistances(g, 4);
        printDistanceArray(distance, parentarray);

        int[][] times = search.getTimes(g, 4);
        printTimesArray(times, parentarray);
    }

    private static void printTimesArray(int[][] tarr, int[] parr)
    {
        int len = parr.length;
        for(int i = 0; i < len; i++)
        {
            print(i + " --> " + parr[i] + "\tdiscovery = " + tarr[i][0] + "\tfinish = " + tarr[i][1]);
        }
    }

    private static void printDistanceArray(int[] d,int[] p)
    {
        int len = d.length;
        for(int i = 0; i < len; i++)
        {
            print(i + " --> " + p[i] + "\tdistance = " + d[i]);
        }
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