import CITS2200.*;
import java.util.LinkedList; //java implementation of queue

public class SearchImp implements CITS2200.Search
{
    enum Colour { WHITE, GREY, BLACK;} //states of a vertex

    /**
     * Runs a *BFS* on a given directed, unweighted graph.
     * @param g Graph to be searched
     * @param sv the vertex on which to start the search (starting vertex)
     * @return an array listing the parent of each vertex in the spanning tree, or -1 is the vertex
     * is not reachable from the start vertex.
    **/
    public int[] getConnectedTree(Graph g, int startingvertex)
    {
        //GRAPH INFORMATION
        int[][] edgematrix = g.getEdgeMatrix();
        int numvertices = g.getNumberOfVertices();
        
        //BFS ADT's
        LinkedList q = new LinkedList(); //queue implementation
        Colour[] colour = setVertexColour(numvertices);
        int[] pi = initialiseParentArray(numvertices); //parent or predecessor array

        //BFS ALGORITHM
        q.add(startingvertex);
        while(q.peek() != null) //NOT EMPTY
        {
            int w = (int) q.remove(); //REMOVE FIRST ELEMENT IN THE QUEUE
            //FIND ADJACENT VERTICES TO w
            for(int x = 0; x < numvertices; x++)
            {
                if(edgematrix[w][x] == 1) //CONNECTED OR "CHILD" OF w
                {
                    if(colour[x] == Colour.WHITE) //WHITE
                    {
                        pi[x] = w;
                        colour[x] = Colour.GREY; //SET TO GREY
                        q.add(x);
                    }
                }
            }
            colour[w] = Colour.BLACK; //SET TO BLACK
        }
        return pi;
    }

    /**
     * Initialise the parent array with values of -1 to indicate no established
     * directional connections between vertices
     * @param size of integer array
     * @return int[] with values initialised to -1
    **/
    private static int[] initialiseParentArray(int size)
    {
        int[] a = new int[size];
        for(int i = 0; i < size; i++)
        {
            a[i] = -1;
        }
        return a;
    }

    /**
     * Sets the colour of all elements in an array to Colour.WHITE
     * @return Colour[] with all elements coloured Colour.WHITE
     * @param size integer size of array
    **/
    private static Colour[] setVertexColour(int size)
    {
        Colour[] c = new Colour[size];
        for(int i = 0; i < size; i++)
        {
            c[i] = Colour.WHITE;
        }
        return c;
    }
    
    /**
     * Runs a *BFS* on a given directed, unweighted graph to find the distances of vertices from
     * the start vertex
     * @param g the Graph to be searched
     * @param startVertex the vertex on which to start the search
     * @return an array listing the distance of each vertex in the spanning tree,
     * or -1 is the vertex is not reachable from the start vertex
    **/
    public int[] getDistances(Graph g, int startvertex)
    {
        //GRAPH INFORMATION
        int[][] edgematrix = g.getEdgeMatrix();
        int numvertices = g.getNumberOfVertices();
        
        //BFS ADT's
        LinkedList q = new LinkedList(); //queue implementation
        Colour[] colour = setVertexColour(numvertices);
        int[] pi = initialiseParentArray(numvertices); //pd[i][0] = parent array | pd[i][1] = distance
        int[] dist = new int[numvertices];

        //BFS ALGORITHM
        q.add(startvertex);
        while(q.peek() != null) //NOT EMPTY
        {
            int w = (int) q.remove(); //REMOVE FIRST ELEMENT IN THE QUEUE
            //FIND ADJACENT VERTICES TO w
            for(int x = 0; x < numvertices; x++)
            {
                if(edgematrix[w][x] == 1) //CONNECTED OR "CHILD" OF w
                {
                    if(colour[x] == Colour.WHITE) //WHITE
                    {
                        dist[x] = dist[w] + 1; //distance to w +1
                        pi[x] = w; //parent
                        colour[x] = Colour.GREY; //SET TO GREY
                        q.add(x);
                    }
                }
            }
            colour[w] = Colour.BLACK; //SET TO BLACK
        }
        return dist;
    }

    private static int[][] initialiseParDisArray(int size)
    {
        int[][] a = new int[size][2];
        for(int i = 0; i < size; i++)
        {
            a[i][0] = -1;
        }
        return a;
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
        return new int[1][1];
    }
}
