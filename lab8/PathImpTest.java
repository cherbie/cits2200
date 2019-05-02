import CITS2200.*;

public class PathImpTest
{
    private static Graph graph;
    private static int num_vertices;

    public static void main(String[] args)
    {
        int numvertices = Integer.parseInt(args[0]);
        try
        {
            graph = Graph.readFile("sample_graph.in", true, false); //weighted & undirected
        }
        catch(Exception e)
        {
            print(e.getMessage());
            return;
        }
        num_vertices = graph.getNumberOfVertices();
        graphStats();
        findMST(graph);
    }

    /**
     * print information about the graph to standard out.
     */
    private static void graphStats()
    {
        print("Number of vertices:\t" + num_vertices);
        print("Weighted?:\t" + graph.isWeighted());
        print("Directed?:\t" + graph.isDirected());
        print(graph.toString());
    }

    /**
     * Prints to standard out the weight of the graphs minimum spanning tree.
     */
    private static void findMST(Graph g)
    {
        PathImp path = new PathImp();
        print("MST weight =\t" + path.getMinSpanningTree(g));
    }
    public static void print(String s)
    {
        System.out.println(s);
    }
}