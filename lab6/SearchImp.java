import java.lang.Object;
import CITS2200.*;

public class SearchImp implements CITS2200.Search
{
    
    /**
     * Runs a *BFS* on a given directed, unweighted graph.
     * @param g Graph to be searched
     * @param startVertex the vertex on which to start the search
     * @return an array listing the parent of each vertex in the spanning tree, or -1 is the vertex
     * is not reachable from the start vertex.
    **/
    public int[] getConnectedTree(Graph g, int startVertex)
    {
        
    }
    
    /**
     * Runs a *BFS* on a given directed, unweighted graph to find the distances of vertices from
     * the start vertex
     * @param g the Graph to eb searched
     * @param startVertex the vertex on which to statr the search
     * @result an array listing the parent of each vertex in the spann:ing tree,
     * or -1 is the vertex is not reachable from teh start vertex
    **/
    public int[] getDistances(Graph g, int startVertex)
    {
        
    }
    
    /**
     * Runs a *DFS on a given directed, unweighted graph to find the start time and finish time
     * for each vertex.
     * @param g the Graph to be searched
     * @param startVertex the vertex on which to start the search
     * @return a 2-dimensional array, where each sub-array has two elements:
     * the first is the start time, the second is the end time.
    **/
    public int[][] getTimes(Graph g, int startVertex)
    {
        
    }
}
