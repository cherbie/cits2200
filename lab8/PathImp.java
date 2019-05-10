import CITS2200.*;
import java.util.PriorityQueue;

public class PathImp implements CITS2200.Path
{
    private MSTImp imp; //sub-class implementation of mst and shortest path algorithms

    /**
     * Constructor.
     */
    public PathImp() { this.imp = new MSTImp(); };

    /**
     * @return int the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found.
     * @param g the graph to be searched. Assume that an edge weight of 0 signifies that the edge does not exist.
     * @return the weight of the minimum spanning tree, or -1 if there is no spanning tree.
     */
    public int getMinSpanningTree(Graph g)
    {
        imp.defineGraph(g);
        return imp.minSpanningTree();
    }

     /**
     * @return int[] of the diistances from the start vertex to each of the vertices in the graph.
     * @param g the Graph to be searched. Assume than an edge weight of 0 signifies that the edge does not exist.
     * @param source the vertex on which to start the search
     * @return an array listing the distance to each vertex in the single source shortest path problem,
     *  or -1 if the vertex i snot reachable from the source
     */
     public int[] getShortestPaths(Graph g, int vertex)
     {
        imp.defineGraph(g);
        return imp.shortestPaths(vertex);
     }

/* ------------------------------------------- */

    public class MSTImp
    {
        private Graph graph;
        private int vertices; //number of vertices
        public MST mst;

        /**
         * Contructor for the Prim Algorithm implementation of MST
         * @throws IllegalValue when the graph is directed or not weighted
         */
        public MSTImp() {}

        public void defineGraph(Graph g)
        {
            this.graph = g;
            this.vertices = g.getNumberOfVertices();
            this.mst = new MST(vertices);
        }

        /**
         * Use of heap to implement prim's algorithm.
         * @return int the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found.
         * @return the weight of the minimum spanning tree, or -1 if there is no spanning tree.
         */
        public int minSpanningTree()
        {
            PriorityQueueLinked<Integer> p_queue = new PriorityQueueLinked<Integer>();

            mst.setNodeKey(0, 0); //set root node key value to zero
            int node, x; //current "window" of node being considered
            int sum = 0; //running sum of weighted MST

            p_queue.enqueue(0, 0);

            while(!p_queue.isEmpty()) //not all elements have been visit
            {
                node = p_queue.dequeue(); //dequeue vertex 0 --> marked as seen

                if(mst.nodeVisited(node)) continue; //if already visited skip.
                mst.setNodeVisited(node, 1);
                sum += mst.getNodeKey(node); //increment mininum spanning tree weight

                for(x = 0; x < vertices; x++)
                {
                    if(!mst.nodeVisited(x)) //not visited
                    {
                        int weight = graph.getWeight(node, x); 
                        if(weight > 0 && weight < mst.getNodeKey(x)) //unvisited node is child with minimum weight
                        {
                            mst.setNodeKey(x, weight);
                            mst.setNodeParent(x, node);
                            p_queue.enqueue(x, weight);
                        }
                    }
                }
            }
            if(sum <= 0) 
                return -1; //indicate no minimum spanning tree present
            return sum;
        }

        public int[] shortestPaths(int startVert) 
        {
            PriorityQueueLinked<Integer> p_queue = new PriorityQueueLinked<Integer>();

            mst.setShortestPath(startVert, 0);
            mst.setNodeKey(startVert, 0); //set root node key value to zero
            int node; //current "window" of node being considered
            int[] distances = new int[this.vertices];

            p_queue.enqueue(startVert, 0);
            while(!p_queue.isEmpty()) {
                node = p_queue.dequeue();
                if(mst.nodeVisited(node)) continue; //visited
                mst.setNodeVisited(node, 1);
                distances[node] = mst.getShortestPath(node);
                for(int x = 0; x < this.vertices; x++) {
                    if(!mst.nodeVisited(x)) { //not visited
                        int weight = graph.getWeight(node, x);
                        int priority = distances[node] + weight;
                        if(weight > 0 && priority < mst.getShortestPath(x)) {
                            mst.setShortestPath(x, priority);
                            p_queue.enqueue(x, priority);
                        }
                    }
                }
            }
            return distances;
        }
    }

/* ----------------------------------- */

    public class MST
    {
        private int[][] nodes; //storing node information.
        private int[] shortestPaths;
        private int mstWeight;

        /**
         * Constructor of minimum spanning tree ADT
         */
        public MST(int V)
        {
            this.nodes = new int[V][3];
            this.shortestPaths = new int[V];
            this.mstWeight = 0;

            for(int i = 0; i < V; i++) {
                this.setNodeParent(i, -1); //undefined parent
                this.setNodeKey(i, Integer.MAX_VALUE);
                this.setShortestPath(i, Integer.MAX_VALUE);
                this.setNodeVisited(i, -1);
            }
        }

        /**
         * Set the minimum spanning trees weight
         */
        public void setMSTWeight(int val) 
        {
           this.mstWeight = val;
        }

        /**
         *
        */
        public int getMSTWeight() 
        {
            return this.mstWeight;
        }
        /**
         * @return the nodes respective key value
         */
        public int getNodeKey(int node)
        {
            return this.nodes[node][0];
        }

        /**
         *
         */
        public void setShortestPath(int node, int val) {
            this.shortestPaths[node] = val;
        }
        
        /**
         * @return int[] of all shortest paths.
         */
        public int[] getShortestPaths() {
            return this.shortestPaths;
        }

        /**
         * @return int of specific shortest path
         */
        public int getShortestPath(int node) {
            return this.shortestPaths[node];
        }
        /**
         * Sets the nodes respective key value
         */
        public void setNodeKey(int node, int val)
        {
            this.nodes[node][0] = val;
        }
        
        /**
         * val of -1 indicated not visited.
         * val of 1 indicates visited.
         */
        public void setNodeVisited(int node, int val)
        {
            this.nodes[node][2] = val;
        }

        /**
         * returns true if the node has been visited. false otherwise.
         */
        public boolean nodeVisited(int node)
        {
            return this.nodes[node][2] > 0;
        }
        /**
         * Node descriptor is set as parent. 
         * -1 if the node is the root
         */
        public void setNodeParent(int node, int parent)
        {
            this.nodes[node][1] = parent;
        }

        /**
         * @return the nodes respective parent
         */
        public int getNodeParent(int node)
        {
            return this.nodes[node][1];
        }
    }

/* ------------------------------------- */

    public class PriorityQueueLinked<E>
    {
        private Link<E> front;
        
        /**
        * Constructor for implementation of CITS2200.PriorityQueueLinked
        * Empty priority queue created.
        */
        public PriorityQueueLinked()
        {
            this.front = null;
        }

        /**
        * Tests whether the queue is empty.
        * @return true if the queue is empty, otherwise returns false.
        */
        public boolean isEmpty()
        {
            return front == null;
        }

        /**
        * Insert an item at the back into the queue with a given priority.
        * Smaller priority values prioritised.
        * @param a the item to insert
        * @param priority the priority of the elements ( > 0 )
        * @throws IllegalValue if the priority is not in a valid range
        */
        public void enqueue(E a, int p) throws IllegalValue
        {
            if( isEmpty() || p < front.priority)
            {
                front = new Link<E>(a, p, front);
            }
            else
            {
                Link<E> window = front;
                while( window.next != null && window.next.priority < p ) //lowest priority
                {
                    window = window.next;
                }
                window.next = new Link(a, p, window.next); //insert Link at the end
            }
        }

        /**
        * Examine the element at the front of the queue 
        * Front -- the element with the highest priority that has been in the queue the longest.
        * @return the first iteem
        * @throws Underflow if the queue is empty
        */
        public E examine() throws Underflow
        {
            if(isEmpty())
                throw new Underflow("examine: p-queue is empty.");
            return front.element;
        }

        /**
        * Remove the item at the front of the queue
        * Front -- the element with the highest priority that has been in the queue the longest.
        * @throws Underflow if the queue is empty
        */
        public E dequeue() throws Underflow
        {
            if(isEmpty())
                throw new Underflow("dequeue: p-queue is empty.");
            E temp = (E) front.element;
            front = front.next;
            return temp;
        }

        public class Link<E>
        {
            public E element;
            public int priority;
            public Link<E> next;

            /**
            * Constructor for Link including priorities.
            */
            public Link(E e, int p, Link<E> n)
            {
                this.element = e;
                this.priority = p;
                this.next = n;
            }
        }
    }
}