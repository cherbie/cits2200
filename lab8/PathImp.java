import CITS2200.*;

public class PathImp implements CITS2200.Path
{
    private CITS2200.Graph graph;
    private PrimImp prim; //Prim implementation object

    /**
     * Constructor 
     */
    public PathImp(Graph g)
    {
        this.graph = g;
        this.prim = new PrimImp(g);

    }

    //METHODS

    /**
     * @return int the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found.
     * @param g the graph to be searched. Assume that an edge weight of 0 signifies that the edge does not exist.
     * @return the weight of the minimum spanning tree, or -1 if there is no spanning tree.
     */
    public int getMinimumSpanningTree(Graph g)
    {

    }

    public class PrimImp
    {
        public Graph graph;
        private int[][] matrix;
        private int vertices; //number of vertices

        public PrimImp(Graph g)
        {
            this.graph = g;
            this.matrix = g.getEdgeMatrix();
            this.vertices = g.getNumberOfVertices();
        }

        public boolean isValid()
        {
            if(!graph.isDirected() && graph.isWeighted())
                return true;
            return false;
        }

        /**
        * @return int the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found.
        * @return the weight of the minimum spanning tree, or -1 if there is no spanning tree.
        */
        public int minSpanningTreeWeight()
        {
            int[] visited = int[vertices]; // 0: not_visited, 1: visited
            int numEdge = 0;
            const int LARGE_NUM = 9999; //large assumed starting weight
            int x, y, min;
            int sum = -1;

            while(numEdge < (vertices -1) )
            {
                x = 0;
                y = 0;
                min = LARGE_NUM;
                visited[0] = 1; //start at vertex 0 and indicate it has been visited

                for(int i = 0; i < vertices; i++)
                {
                    if( visited[i] )
                    {
                        for(int j = 0; j < vertices; j++)
                        {
                            int weight = graph.getWeight(i,j);
                            if(visited[j]==0 && weight!=0 ) //all connected vertices not visited.
                            {
                                if(min > weight)
                                {
                                    min = weight; //new minimum edge
                                    //two vertices forming the minimum
                                    x = i;
                                    y = j;

                                }
                            } 
                        }
                    }
                }
                visited[y] = 1;
                numEdge++;
            }
            return sum;
        }
    }

    /**
     * @return int[] of the diistances from the start vertex to each of the vertices in the graph.
     * @param g the Graph to be searched. Assume than an edge weight of 0 signifies that the edge does not exist.
     * @param source the vertex on which to start the search
     * @return an array listing the distance to each vertex in the single source shortest path problem,
     *  or -1 if the vertex i snot reachable from the source
     */
     public int getShortestPath(Graph G, int vertex)
     {

     }

}