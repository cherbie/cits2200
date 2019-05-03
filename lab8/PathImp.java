import CITS2200.*;

public class PathImp implements CITS2200.Path
{
    /**
     * Constructor.
     */
    public PathImp() {};

    /**
     * @return int the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found.
     * @param g the graph to be searched. Assume that an edge weight of 0 signifies that the edge does not exist.
     * @return the weight of the minimum spanning tree, or -1 if there is no spanning tree.
     */
    public int getMinSpanningTree(Graph g)
    {
        try 
        {
            PrimImp prim = new PrimImp(g);ß
            return prim.minSpanningTree();
        } 
        catch (Exception e)
        {
            return -1;
        }
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
         return new int[10];
     }

/* ------------------------------------------- */


/* ------------------------------------------- */

    public class PrimImp
    {
        private Graph graph;
        private int vertices; //number of vertices
        public static final int LARGE_VALUE = 999;
        private MST mst;

        /**
         * Contructor for the Prim Algorithm implementation of MST
         * @throws IllegalValue when the graph is directed or not weighted
         */
        public PrimImp(Graph g) throws IllegalValue
        {
            this.graph = g;
            this.vertices = g.getNumberOfVertices();
            this.mst = new MST(vertices); //construct empty minimum spanning tree
            if(!isValid())
                throw new IllegalValue("PrimImp: Graph neededs to be weighted & undirected.");
        }
        
        /**
         * Weighted, undirected graphs are implemented using Prim's algorithm.
         * @return true if valid graph, false otherwise
         */
        private boolean isValid()
        {
            if(!graph.isDirected() && graph.isWeighted())
                return true;
            return false;
        }

        /**
         * Use of heap to implement prim's algorithm
         * @return int the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found.
         * @return the weight of the minimum spanning tree, or -1 if there is no spanning tree.
         */
        public int minSpanningTree()
        {
            PriorityQueueLinked p_queue = new PriorityQueueLinked();
            //this.initPriorityQueue(p_queue, vertices);

            mst.setNodeKey(0, 0); //set root node key value to zero
            int node, x; //current "window" of node being considered
            int sum = 0; //running sum of weighted MST

            p_queue.enqueue(0, 0);

            while(!p_queue.isEmpty()) //not all elements have been visit
            {
                node = (int) p_queue.dequeue(); //dequeue vertex 0 --> marked as seen

                if(mst.nodeVisited(node)) continue; //if already visited skip.
                System.out.println("sum:\t" + sum + "node:\t" + node + "val:\t" + mst.getNodeKey(node));
                mst.setNodeVisited(node, 1);
                sum += (int) mst.getNodeKey(node); //increment mininum spanning tree weight
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
            {
                return -1; //indicate no minimum spanning tree present
            }
            return sum;
        }

        /**
         * Construct a priority queue containing all vertices of the classes graph
         * and set the values to a large number.
         * @return initialised PriorityQueueLinked object
         * @param PriorityQueueLinked p
         * @param int number of vertices in graph
         */
        private void initPriorityQueue(PriorityQueueLinked p, int v)
        {
            for(int i = 1; i < v; i++)
            {
                p.enqueue(i, this.LARGE_VALUE);
            }
            p.enqueue(0,0); //starting at node 0
        }
    }

/* ----------------------------------- */

    public class MST
    {
        public int[][] nodes; //storing node information.

        /**
         * Constructor of minimum spanning tree ADT
         */
        public MST(int V)
        {
            nodes = new int[V][3];
            for(int i = 0; i < V; i++)
            {
                this.setNodeParent(i, -1); //undefined parent
                this.setNodeKey(i, PrimImp.LARGE_VALUE);
                this.setNodeVisited(i, -1);
            }
        }

        /**
         * @return the nodes respective key value
         */
        public int getNodeKey(int node)
        {
            return this.nodes[node][0];
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
            nodes[node][2] = val;
        }

        /**
         * returns true if the node has been visited. false otherwise.
         */
        public boolean nodeVisited(int node)
        {
            return nodes[node][2] > 0;
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

    public class PriorityQueueLinked<E> implements CITS2200.PriorityQueue<E>
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
                while( window.next != null && window.next.priority <= p ) //lowest priority
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

        /**
        * Return a DAT.iterator to examine all the elements in the priority queue
        * @return an iterator pointing to before the first item
        */
        public Iterator<E> iterator()
        {
            return new PQueueIterator<E>();
        }

        public class PQueueIterator<A> implements CITS2200.Iterator<A>
        {
            private Link<E> current;

            public PQueueIterator()
            {
                current = PriorityQueueLinked.this.front;
            }

            /**
            * Tests if there is a next item to return
            * @return true if and only if there is a next item 
            */
            public boolean hasNext()
            {
                return current != null;
            }

            /**
            * Returns the next element and moves the iterator to the next position
            * @return the next element in the collection
            * @throws OutOfBounds if there is no next element
            */
            public A next() throws OutOfBounds
            {
                if(!this.hasNext())
                    throw new OutOfBounds("next: queue is empty.");
                A temp = (A) current.element;
                current = current.next;
                return temp;
            }
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