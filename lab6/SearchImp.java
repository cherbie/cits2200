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
    public int[] getConnectedTree(Graph g, int startvertex) throws OutOfBounds
    {
        BFSImp obj = new BFSImp(g);
        obj.BFS(startvertex);
        return obj.getParentArray();
    }
    
    /**
     * Runs a *BFS* on a given directed, unweighted graph to find the distances of vertices from
     * the start vertex
     * @param g the Graph to be searched
     * @param startVertex the vertex on which to start the search
     * @return an array listing the distance of each vertex in the spanning tree,
     * or -1 is the vertex is not reachable from the start vertex
    **/
    public int[] getDistances(Graph g, int startvertex) throws OutOfBounds
    {
        BFSImp obj = new BFSImp(g);
        obj.BFS(startvertex);
        return obj.getDistanceArray();
    }
    
    /**
     * Runs a *DFS* on a given directed, unweighted graph to find the start time and finish time
     * for each vertex.
     * @param g the Graph to be searched
     * @param startVertex the vertex on which to start the search
     * @return a 2-dimensional array, where each sub-array has two elements:
     * the first is the start time, the second is the end time.
    **/
    public int[][] getTimes(Graph g, int startvertex)
    {
        DFSImp dfs = new DFSImp(g); //create abstract DFS class
        dfs.DFS(startvertex);
        return dfs.times;
    }

    public class BFSImp //ADT implementing the Breadth-First Search (BFS) Algorithm
    {
        //PUBLIC FIELDS

        //PRIVATE FIELDS
        private Colour[] colour;
        private int numvertices;
        private int[][] edgematrix;
        private int[] pi;
        private int[] distance;

        /**
         * Constructor for an abstract data type that performs a BFS on a
         * directed, unweighted graph recording the parent of each vertices
         * and the distance from the starting vertex.
         */
        public BFSImp(Graph g)
        {
            numvertices = g.getNumberOfVertices();
            edgematrix = g.getEdgeMatrix();
            colour = setVertexColour(numvertices);
            pi = iniParentArray(numvertices);
            distance = new int[numvertices];
        }

        /**
         * Runs a BFS on a given directed, unweighted graph to find the parent & distances of vertices from
         * the starting vertex
         * @param startvertex the vertex on which to start the search
         * @throws OutOfBounds if the search index is outside of the range [0, number_of_vertices)
        **/
        public void BFS(int startvertex) throws OutOfBounds
        {
            if(startvertex < 0 || startvertex >= numvertices)
                throw new OutOfBounds("starting vertex not an element of the set of vertices in graph g.");

            
            LinkedList q = new LinkedList(); //queue implementation

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
                            distance[x] = distance[w] + 1; //distance to w +1
                            pi[x] = w; //parent
                            colour[x] = Colour.GREY; //SET TO GREY
                            q.add(x);
                        }
                    }
                }
                colour[w] = Colour.BLACK; //SET TO BLACK
            }
        }

        /**
         * Accessor method for the parents of each vertices in the spanning tree of the graph.
         * @return the parent of each vertices in an int[], -1 if the vertex cannot be accessed
         * in the spanning tree.
         */
        public int[] getParentArray()
        {
            return pi;
        }

        /**
         * Accessor method for the distance of each vertices from the starting vertex of the graph
         * @return the distance of each vertex from the root of the spanning tree.
         */
        public int[] getDistanceArray()
        {
            return distance;
        }
        
        /**
        * Initialise the parent array with values of -1 to indicate no established
        * directional connections between vertices
        * @param size of integer array
        * @return int[] with values initialised to -1
        **/
        private int[] iniParentArray(int size)
        {
            int[] a = new int[size];
            for(int i = 0; i < size; i++)
                a[i] = -1;
            return a;
        }

        /**
         * Initialises the colour of the vertices to Colour.WHITE
         * @return the array of Colours for each vertices Colour[].
         */
        private Colour[] setVertexColour(int size)
        {
            Colour[] c = new Colour[size];
            for(int i = 0; i < size; i++)
            {
                c[i] = Colour.WHITE;
            }
            return c;
        }
    }


    public class DFSImp //ADT implementing the Depth-First Search (DFS) Algorithm
    {
        //PUBLIC FIELDS
        public int[][] times; //discovery and finish times of parent vertices
        
        //PRIVATE FIELDS
        private int time; //abstract time
        private Colour[] colour;
        private int[][] edgematrix;
        private int numvertices;

        /**
         * Constructor an abstract data type that performs a DFS on a
         * directed, unweighted graph recording the discovery and finish times
         * of each parent vertex.
         */
        public DFSImp(Graph g)
        {
            numvertices = g.getNumberOfVertices();
            edgematrix = g.getEdgeMatrix();
            times = new int[numvertices][2];
            colour = setVertexColour(numvertices);
            time = 0;
        }

        /**
         * Recursive DFS alorithm logging the discovery and finishing times
         * of each parent vertex in the public field DFSTime.times.
         * @param w int representing the parent vertex underexamination
         * @throws OutOfBounds if w exceeds the bounds [0, number_of_vertices)
        **/
        public void DFS(int w) throws OutOfBounds
        {
            if(w < 0 || w >= numvertices)
                throw new OutOfBounds("DFS: vertex not an element of the set of vertices in graph g.");
            colour[w] = Colour.GREY;
            times[w][0] = time; //discovery time;
            ++time;
            for(int x = 0; x < numvertices; x++)
            {
                if(colour[x] == Colour.WHITE)
                    DFS(x);
            }
            colour[w] = Colour.BLACK;
            times[w][1] = time;
            ++time;
        }

        private Colour[] setVertexColour(int size)
        {
            Colour[] c = new Colour[size];
            for(int i = 0; i < size; i++)
            {
                c[i] = Colour.WHITE;
            }
            return c;
        }
    }
}
