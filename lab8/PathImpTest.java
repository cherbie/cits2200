import CITS2200.*;

public class PathImpTest
{
    Graph graph;
    public static void main(String[] args)
    {
        int numvertices = Integer.parseInt(args[0]);
        try
        {
            graph = Graph.readFile("sample_graph.in", true, false);
        }
        catch(Exception e)
        {
            print(e.getMessage());
            return;
        }
    }

    public static void print(String s)
    {
        System.out.println(s);
    }
}