import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;
import java.lang.String;
import java.lang.Exception;
import java.util.Stack;
import java.util.Iterator;

public class MyCITS2200Project implements CITS2200Project {
	//ENUMERATIONS
	enum Colour {WHITE, GREY, BLACK;} //colours representing the states of a vertex

	//FIELDS
	public  HashMap<Integer, String>                wikiLookup; //LOOKUP TABLE
	private HashMap<String, Integer>			    nodeLookup;
	private int                                     maxvd; //number of vertex descriptor (similar to file descriptor)
	public  ArrayList<LinkedList<Integer>>          edgeList;
	public  ArrayList<LinkedList<Integer>>			transEdgeList;
	public  ArrayList<Colour>					    colour;

	//CONSTRUCTOR
	public MyCITS2200Project() {
		this.wikiLookup = new HashMap<>();
		this.nodeLookup = new HashMap<>();
		this.colour = new ArrayList<>();
		this.edgeList = new ArrayList<>();
		this.transEdgeList = new ArrayList<>();
		this.maxvd = 0;
	}

	//METHODS
	/**
	* Adds an edge to the Wikipedia page graph. If the pages do not
	* already exist in the graph, they will be added to the graph.
	* @param urlFrom the URL which has a link to urlTo.
	* @param urlTo the URL which urlFrom has a link to.
	*/
	public void addEdge(String urlFrom, String urlTo) {
		//SEARCH PARENT
		Integer parentvd = this.nodeLookup.get(urlFrom);
		Integer childvd = this.nodeLookup.get(urlTo);
		
		if(parentvd == null) parentvd = this.addParent(urlFrom);
		if(childvd == null) childvd = this.addChild(urlTo);

		//TODO --> REMOVE CYCLICAL CASE

		//ADD VERTICE DESCRIPTORS TO LINKED LIST
		//CHECK IF EDGE ALREADY EXISTS
		if(!this.edgeList.get(parentvd).contains(childvd)) { //edge does not exist in LinkedList
			this.edgeList.get(parentvd).add(childvd);
		}
		if(!this.transEdgeList.get(childvd).contains(parentvd)) {
			this.transEdgeList.get(childvd).add(parentvd);
		}

		//ADD VERTEX DESCRIPTOR TO TRANSPOSED ADJACENCY LIST
		//Linked list already contains the edge
		return;
	}

	/**
	* ADD PARENT URL TO THE LOOKUP TABLE AND SET AS UNVISITED
	*/
	private Integer addParent(String urlFrom) {
		LinkedList<Integer> ll = new LinkedList<>();
		int vd = this.maxvd++;
		this.wikiLookup.put(vd, urlFrom);
		this.nodeLookup.put(urlFrom, vd);
		this.edgeList.add(vd, ll);
		this.transEdgeList.add(vd, ll);
		this.colour.add(Colour.WHITE);
		return vd;
	}
	/**
	* ADD CHILD URL TO THE LOOKUP TABLE AND SET AS UNVISITED
	*/
	private Integer addChild(String urlTo) {
		LinkedList<Integer> ll = new LinkedList<>();
		int vd = this.maxvd++;
		this.wikiLookup.put(vd, urlTo);
		this.nodeLookup.put(urlTo, vd);
		this.edgeList.add(vd, ll);
		this.transEdgeList.add(vd, ll);
		this.colour.add(Colour.WHITE);
		return vd;
	}

	/**
	 * Search lookup table to find the vertex descriptor for a particular string
	 * @param String url of wiki address
	 * @return int vertex decriptor of node/vertex
	 
	private int getVertexDescriptor(String url) throws Exception {
		int vd = this.wikiAddr.indexOf(url);
		if(vd < 0) { //vertex descriptor does not exist in lookup table
			throw new Exception("Wiki URL does not exist in lookup table and hence is not part of graph.");
		}
		return vd;
	}
	*/

	/**
	* Finds the shorest path in number of links between two pages.
	* If there is no path, returns -1.
	* 
	* @param urlFrom the URL where the path should start.
	* @param urlTo the URL where the path should end.
	* @return the length of the shorest path in number of links followed.
	*/
	public int getShortestPath(String urlFrom, String urlTo) {
		//arraylist.trimToSize();
		// bfs lab work: /Users/herbsca/OneDrive/UWA/CITS2200/cits2200/lab6/SearchImpS.java
		try {
			int startVertex = this.nodeLookup.get(urlFrom); //vertex descriptor
			int endVertex = this.nodeLookup.get(urlTo); //vertex descriptor

			MyBFS bfs = new MyBFS();
			return bfs.shortestPath(startVertex, endVertex);
		} catch(Exception e) {
				System.out.println(e.toString());
				return -1;
		}
	}

	/**
	* Finds all the centers of the page graph. The order of pages
	* in the output does not matter. Any order is correct as long as
	* all the centers are in the array, and no pages that aren't centers
	* are in the array.
	* 
	* @return an array containing all the URLs that correspond to pages that are centers.
	*/
	public String[] getCenters() {
	return new String[1];
	}

	/**
	* Finds all the strongly connected components of the page graph.
	* Every strongly connected component can be represented as an array 
	* containing the page URLs in the component. The return value is thus an array
	* of strongly connected components. The order of elements in these arrays
	* does not matter. Any output that contains all the strongly connected
	* components is considered correct.
	* 
	* @return an array containing every strongly connected component.
	*/
	public String[][] getStronglyConnectedComponents() {
		try {
			MySCC myscc = new MySCC();
			ArrayList<ArrayList<Integer>> scc = myscc.getSCC();
			Iterator<Integer> internalIt;
			Iterator<ArrayList<Integer>> externalIt = scc.iterator();
			while(externalIt.hasNext()) {
				break;
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return new String[1][1];
	}

	/**
	* Finds a Hamiltonian path in the page graph. There may be many
	* possible Hamiltonian paths. Any of these paths is a correct output.
	* This method should never be called on a graph with more than 20
	* vertices. If there is no Hamiltonian path, this method will
	* return an empty array. The output array should contain the URLs of pages
	* in a Hamiltonian path. The order matters, as the elements of the
	* array represent this path in sequence. So the element [0] is the start
	* of the path, and [1] is the next page, and so on.
	* 
	* @return a Hamiltonian path of the page graph.
	*/
	public String[] getHamiltonianPath() {
		return new String[1];
	}

	/*******************************************************************/
	private class MyBFS {
		//FIELDS
		private int[] distance; 
		private ArrayList<LinkedList<Integer>> vertexEdges;

		/**
		 * CONSTRUCTOR OF BFS CLASS
		 */
		public MyBFS() throws Exception {
			int numNodes = MyCITS2200Project.this.maxvd;
			this.distance = new int[numNodes];
			this.vertexEdges = MyCITS2200Project.this.edgeList;
		}

		/**
		 * RUN ENTIRE BFS ALGORITHM ON THE ESTABLISHED GRAPH.
		 * GARUANTEES SHORTEST PATH DISTANCE FIELD WILL BE UP-TO-DATE.
		 * @param startVertex the starting vertex of the BFS.
		 */
		public void run(int startVertex) throws Exception {

			LinkedList<Integer> q = new LinkedList<>(); //queue implementation
			LinkedList<Integer> ll; //adjacency list implementation
			Iterator<Integer> it;
			ArrayList<Colour> colour = MyCITS2200Project.this.colour; //all nodes are set as unvisited
			if(colour.size() < MyCITS2200Project.this.maxvd) throw new Exception("ALL NODES ARE NOT SET AS 'UNVISITED'.");
			int w, x;

			//BFS ALGORITHM
			this.distance[startVertex] = 0;
			q.add(startVertex);
			while(q.peek() != null) {
				w = q.pop(); //REMOVE FIRST ELEMENT IN THE QUEUE
				//FIND ADJACENT VERTICES TO w
				it = MyCITS2200Project.this.edgeList.get(w).listIterator(0); //adjacency list for the node
				while(it.hasNext()) {
					//CONNECTED OR "CHILD" OF w
					x = it.next();
					if(x == w) continue; //non-cyclical
					System.out.println(x);
					if(colour.get(x) == Colour.WHITE) { //WHITE OR NOT SEEN
						distance[x] = distance[w] + 1;
						colour.set(x, Colour.GREY); //SET TO GREY OR SEEN
						q.add(x);
					}
				}
				colour.set(w, Colour.BLACK); //SET TO BLACK
			}
		}

		/**
		 * GET THE SHORTEST PATH TO EACH NODE.
		 */
		public int[] getDistances() {
			return this.distance;
		}

		/**
		 * GET THE SHORTEST PATH FOR A SPECIFIC NODE.
		 */
		public int getDistance(int node) {
			return this.distance[node];
		}

		/**
		 * RUN A BFS OVER AN ESTABLISHED GRAPH AND EXIT AS SOON AS
		 * THE ENDING NODE IS REACHED.
		 * NOT GARUANTEED TO COMPLETELY FILL SHORTEST PATH DISTANCE ARRAY
		 * @param int startVertex is the starting vertex.
		 * @param int endVertex is the ending vertex.
		 * @return int shortest path to get to the ending vertex. 
		 */
		public int shortestPath(int startVertex, int endVertex) throws Exception {

			LinkedList<Integer> q = new LinkedList<>(); //queue implementation
			Iterator<Integer> it;
			int w, x;
			ArrayList<Colour> colour = MyCITS2200Project.this.colour; //all nodes are set as unvisited
			if(colour.size() < MyCITS2200Project.this.maxvd) throw new Exception("ALL NODES ARE NOT SET AS 'UNVISITED'.");

			//BFS ALGORITHM
			this.distance[startVertex] = 0;
			q.add(startVertex);
			while(q.peek() != null) {
				w = q.pop(); //REMOVE FIRST ELEMENT IN THE QUEUE
				//FIND ADJACENT VERTICES TO w
				it = MyCITS2200Project.this.edgeList.get(w).listIterator(0); //iterator starting at the first element
				while(it.hasNext()) {
					//CONNECTED OR "CHILD" OF w
					x = it.next();
					System.out.println(x);
					if(colour.get(x) == Colour.WHITE) { //WHITE OR NOT SEEN
						if(w == x) continue; //non-cyclical
						this.distance[x] = this.distance[w] + 1;
						if(x == endVertex) return this.distance[endVertex]; //seen end point
						colour.set(x, Colour.GREY); //SET TO GREY OR SEEN
						q.add(x);
					}
				}
				colour.set(w, Colour.BLACK); //SET TO BLACK
			}
			throw new Exception("BFS FAILED TO FIND THE END VERTEX");
		}
	}

	/*************************************************************/
	private class MySCC {
		//FIELDS
		private Stack<Integer> stack;
		private ArrayList<ArrayList<Integer>> scc; 
		private String[][] arrscc;
		private ArrayList<LinkedList<Integer>> transAdjList; //transposed adjacency list
		private ArrayList<Colour> colour;

		/**
		 * CONSTRUCTOR OF STRONGLY CONNECTED COMPONENTS (SCC) CLASS.
		 */
		public MySCC() {
			int numNodes = MyCITS2200Project.this.maxvd;
			this.stack = new Stack<>();
			this.scc = new ArrayList<>();
			this.transAdjList = new ArrayList<>();
			this.arrscc = new String[numNodes][numNodes];
			this.colour = MyCITS2200Project.this.colour;
		}

		/**
		* Recursive DFS implementation starting at the vertex descriptor specified
		* pushing to the stack 
		* Colour.WHITE indicates node has not been visited.
		* Colour.BLACK indicates node has been visited.
		* @param node int vertex decriptor
		* @param index the group id of the strongly connected components
		*/
		private void dfs(int node, int index) {
			LinkedList<Integer> ll = transAdjList.get(node); //adjacently list for vertices
			if(ll.size() <= 0) return; //NO CONNECTED NODES TO ADD TO SCC
			Iterator<Integer> it = ll.listIterator();
			//ArrayList<E> al = this.scc.get(index); //ARRAYLIST CONTAINING NODE DESCRIPTOR OF SCCs
			this.colour.set(node, Colour.BLACK);
			int count = 0;
			while(it.hasNext()) {
				int vd = it.next();
				this.scc.get(index).add(vd); //ADD NODE TO ARRAYLIST OF ARRAYLIST CONTAINING SCC's
				this.arrscc[index][count++] = MyCITS2200Project.this.wikiLookup.get(vd);
				if(colour.get(vd) == Colour.WHITE) //Colour.WHITE symbolises NOT VISITED
					this.dfs(vd, index);
			}
		}
		
		/**
		* MUTATOR METHOD TRANSPOSING THE ADJACENCY LIST OF THE KNOWN GRAPH.
		* VERY EXPENSIVE CANNOT DO THIS!!!
		*/
		private void transpose() {
			Iterator<Integer> it;
			int vd;

			int numEdges = MyCITS2200Project.this.edgeList.size();
			for(int i = 0; i < numEdges; i++) { //CYCLE THROUGH EACH VERTEX AND ITS CONNECTIONS
				if(MyCITS2200Project.this.edgeList.get(i) == null) continue; //NO DEFINED EDGES
				it = MyCITS2200Project.this.edgeList.get(i).listIterator();
				while(it.hasNext()) {
					vd = it.next();
					if(this.transAdjList.get(vd) == null) this.transAdjList.set(vd, new LinkedList<>());
					this.transAdjList.get(vd).add(i); //order of adjcency list does not matter
				}
			}
		}
		/**
		* Implements Kosaraju's algorithm performing a DFS twice on a predefined graph
		* to list all strongly connected nodes/components
		* @return array containing every strongly connected component (node descriptors)
		*/
		public ArrayList<ArrayList<Integer>> getSCC() {
			ArrayList<Colour> col = MyCITS2200Project.this.colour; //ALL NODES MARKED AS NOT VISITED
			Stack<Integer> dfsStack = new Stack<>();
			LinkedList<Integer> ll;
			Iterator<Integer> it;
			int node, x;
			//int size = MyCITS2200Project.this.maxvd;

			//NON-RECURSIVE DFS IMPLEMENTATION
			dfsStack.push(0); //starting at vertex descriptor 0
			while(dfsStack.peek() != null) {
				node = dfsStack.pop();
				if(col.get(node) == Colour.WHITE) { //Colour.WHITE symbolises not visited
					stack.push(node); //push node to stack
					col.set(node, Colour.BLACK); //Colour.BLACK symblises visited
					it = MyCITS2200Project.this.edgeList.get(node).listIterator(0); //ITERATOR OVER ADJACENCY LIST
					while(it.hasNext()) { //ADD ALL CONNECTED NODES TO DFS STACK
						x = it.next(); //CONNECTED NODE USE ITERATOR????
						if(col.get(x) == Colour.WHITE) { //NOT VISITED
							dfsStack.push(x);
						}
					}
				}
			}

			//transpose graph
			this.transpose();

			int numscc = 0; //SET NUMBER OF STRONGLY CONNECTED COMPONENTS TO ZERO
			//CALLS RECURSIVE DFS IMPLEMENTATION
			while(this.stack.peek() != null) {
				node = this.stack.pop();
				if(this.colour.get(node) == Colour.WHITE) { //NOT VISITED
					this.scc.add(new ArrayList<>()); //NEW STRONGLY CONNECTED COMPONENT
					this.dfs(node, numscc++); //RECURSIVE DFS IMPLEMENTATION
				}
			}
			return this.scc;
		}
	}

/*************************************************************************/

}
