import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;
import java.lang.String;
import java.lang.Exception;
import java.util.Stack;
import java.util.Iterator;
import java.lang.Enum;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.lang.Boolean;

enum Colour {WHITE, GREY, BLACK;} //colours representing the states of a vertex

public class MyCITS2200Project implements CITS2200Project {
	//ENUMERATIONS

	//FIELDS
	public  HashMap<Integer, String>                wikiLookup; //LOOKUP TABLE
	private HashMap<String, Integer>			    nodeLookup;
	private int                                     maxvd; //number of vertex descriptor (similar to file descriptor)
	public  ArrayList<LinkedList<Integer>>          edgeList;
	public  ArrayList<LinkedList<Integer>>			transEdgeList;
	private Boolean[][]								adjMatrix;
	public  ArrayList<Colour>						colour;

	//CONSTRUCTOR
	public MyCITS2200Project() {
		this.wikiLookup = new HashMap<>();
		this.nodeLookup = new HashMap<>();
		this.colour = new ArrayList<>();
		this.edgeList = new ArrayList<>();
		this.transEdgeList = new ArrayList<>();
		this.adjMatrix = new Boolean[20][20]; //QUESTION SPECIFIED NODE RESTRICTED TO 20
		this.maxvd = 0;
	}

	/**
	* Makes a deep copy of any Java object that is passed.
	*/
	public static Object deepCopy(Object object) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
			outputStrm.writeObject(object);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
			return objInputStream.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		if(this.edgeList.get(parentvd) == null) {
			this.edgeList.set(parentvd, new LinkedList<>());
		}
		if(!this.edgeList.get(parentvd).contains(childvd)) { //edge does not exist in LinkedList
			this.edgeList.get(parentvd).add(childvd);
		}
		System.out.println(childvd);

		this.adjMatrix[parentvd][childvd] = Boolean.TRUE;

		if(this.transEdgeList.get(childvd) == null) {
			System.out.println("true");
			this.transEdgeList.set(childvd, new LinkedList<>());
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
		this.edgeList.add(vd, null);
		this.transEdgeList.add(vd, null);
		this.colour.add(vd, Colour.WHITE);
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
		this.edgeList.add(vd, null);
		this.transEdgeList.add(vd, null);
		this.colour.add(vd, Colour.WHITE);
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
			return myscc.getSCC();
		} catch(Exception e) {
			e.printStackTrace();
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
		DPHamiltonian ham = new DPHamiltonian(this.maxvd);
		System.out.println("DOES A HAMILTONIAN PATH EXIST:\n\t" + Boolean.toString(ham.pathExists()));
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
			//LinkedList<Integer> ll; //adjacency list implementation
			Iterator<Integer> it;
			ArrayList<Colour> col = (ArrayList<Colour>) MyCITS2200Project.deepCopy(MyCITS2200Project.this.colour); //all nodes are set as unvisited
			if(col.size() < MyCITS2200Project.this.maxvd) throw new Exception("ALL NODES ARE NOT SET AS 'UNVISITED'.");
			int w, x; //better name??

			//BFS ALGORITHM
			this.distance[startVertex] = 0;
			q.add(startVertex);
			while(q.peek() != null) {
				w = q.pop(); //REMOVE FIRST ELEMENT IN THE QUEUE
				//FIND ADJACENT VERTICES TO w
				if(MyCITS2200Project.this.edgeList.get(w) == null) continue;
				it = MyCITS2200Project.this.edgeList.get(w).listIterator(0); //adjacency list for the node
				while(it.hasNext()) {
					//CONNECTED OR "CHILD" OF w
					x = it.next();
					if(x == w) continue; //non-cyclical
					System.out.println(x);
					if(Colour.WHITE.equals(col.get(x))) { //WHITE OR NOT SEEN
						distance[x] = distance[w] + 1;
						col.set(x, Colour.GREY); //SET TO GREY OR SEEN
						q.add(x);
					}
				}
				col.set(w, Colour.BLACK); //SET TO BLACK
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
			ArrayList<Colour> col = (ArrayList<Colour>) MyCITS2200Project.deepCopy(MyCITS2200Project.this.colour); //all nodes are set as unvisited
			if(colour.size() < MyCITS2200Project.this.maxvd) throw new Exception("ALL NODES ARE NOT SET AS 'UNVISITED'.");

			//BFS ALGORITHM
			this.distance[startVertex] = 0;
			q.add(startVertex);
			while(q.peek() != null) {
				w = q.pop(); //REMOVE FIRST ELEMENT IN THE QUEUE
				//FIND ADJACENT VERTICES TO w
				if(MyCITS2200Project.this.edgeList.get(w) == null) continue;
				it = MyCITS2200Project.this.edgeList.get(w).listIterator(0); //iterator starting at the first element
				while(it.hasNext()) {
					//CONNECTED OR "CHILD" OF w
					x = it.next();
					System.out.println(x);
					if(Colour.WHITE.equals(col.get(x))) { //WHITE OR NOT SEEN
						if(w == x) continue; //non-cyclical
						this.distance[x] = this.distance[w] + 1;
						if(x == endVertex) return this.distance[endVertex]; //seen end point
						col.set(x, Colour.GREY); //SET TO GREY OR SEEN
						q.add(x);
					}
				}
				col.set(w, Colour.BLACK); //SET TO BLACK
			}
			throw new Exception("BFS FAILED TO FIND THE END VERTEX");
		}
	}

	/*************************************************************/
	private class MySCC {
		//FIELDS
		private ArrayList<ArrayList<String>> scc; 
		private String[][] arrscc;
		private ArrayList<Colour> colour;

		/**
		 * CONSTRUCTOR OF STRONGLY CONNECTED COMPONENTS (SCC) CLASS.
		 */
		public MySCC() {
			int numNodes = MyCITS2200Project.this.maxvd;
			this.scc = new ArrayList<>();
			this.arrscc = new String[numNodes][numNodes];
			this.colour = (ArrayList<Colour>) MyCITS2200Project.deepCopy(MyCITS2200Project.this.colour); //all nodes are set as unvisited
		}

		/**
		* Recursive DFS implementation starting at the vertex descriptor specified
		* pushing to the stack 
		* Colour.WHITE indicates node has not been visited.
		* Colour.BLACK indicates node has been visited.
		* @param node int vertex decriptor
		* @param index the 'group id' of the strongly connected components
		* @param count the element id within the strongly connected component.
		*/
		private void dfs(int node, int index, int count) {
			LinkedList<Integer> ll = MyCITS2200Project.this.transEdgeList.get(node); //transposed adjacently list for vertices
			if(ll == null) return; //NO CONNECTED NODES TO ADD TO SCC
			Iterator<Integer> it = ll.listIterator(0);
			System.out.println("TRANS EDGE LIST: " + node + "\t");
			this.arrscc[index][count] = MyCITS2200Project.this.wikiLookup.get(node);

			this.colour.set(node, Colour.BLACK);
			while(it.hasNext()) {
				int vd = it.next();
				System.out.println("NEXT=\t" + vd + "COLOUR=\t" + this.colour.get(vd));
				if(Colour.WHITE.equals(this.colour.get(vd))) //Colour.WHITE symbolises NOT VISITED
					this.dfs(vd, index, count+1);
			}
		}
		
		/**
		* Implements Kosaraju's algorithm performing a DFS twice on a predefined graph
		* to list all strongly connected nodes/components
		* @return array containing every strongly connected component (node descriptors)
		*/
		public String[][] getSCC() {
			ArrayList<Colour> col = (ArrayList<Colour>) MyCITS2200Project.deepCopy(MyCITS2200Project.this.colour); //all nodes are set as unvisited
			System.out.println(col.get(1));
			LinkedList<Integer> queue = new LinkedList<>(); //QUEUE TO RETRACE ORDER OF FIRST DFS
			Stack<Integer> dfsStack = new Stack<>();
			Iterator<Integer> it;
			Integer node, x;
			//int size = MyCITS2200Project.this.maxvd;

			//NON-RECURSIVE DFS IMPLEMENTATION
			node = new Integer(0);
			dfsStack.push(node); //starting at vertex descriptor 0
			while(!dfsStack.empty()) {
				node = dfsStack.pop();
				System.out.println("Node popped " + node);
				if(Colour.WHITE.equals(col.get(node))) { //Colour.WHITE symbolises not visited
					System.out.println("node not seen");
					queue.add(node); //push node to stack
					col.set(node, Colour.BLACK); //Colour.BLACK symblises visited
					if(MyCITS2200Project.this.edgeList.get(node) == null) continue;
					it = MyCITS2200Project.this.edgeList.get(node).listIterator(0); //ITERATOR OVER ADJACENCY LIST
					while(it.hasNext()) { //ADD ALL CONNECTED NODES TO DFS STACK
						x = it.next(); //CONNECTED NODE USE ITERATOR????
						System.out.println("1st DFS adjacency item = " + x);
						if(Colour.WHITE.equals(col.get(x))) { //NOT VISITED
							dfsStack.push(x);
						}
					}
				}
				else System.out.println("ELSE: " + col.get(node));
			}

			//transposition handled in MyCITS2200Projct.addEdge()

			int numscc = 0; //SET NUMBER OF STRONGLY CONNECTED COMPONENTS TO ZERO
			//CALLS RECURSIVE DFS IMPLEMENTATION
			System.out.println("REACHED 2nd DFS");
			while(!queue.isEmpty()) {
				node = queue.remove();
				System.out.println("2nd DFS :: NODE=\t" + node + " COLOUR=\t" + this.colour.get(node));
				if(Colour.WHITE.equals(this.colour.get(node))) { //NOT VISITED
					this.dfs(node, numscc, 0); //RECURSIVE DFS IMPLEMENTATION
					numscc++;
					System.out.println();
				}
			}
			return this.arrscc;
		}
	}

/*************************************************************************/

	public class DPHamiltonian {
		private int 							MAXNODES;
		private ArrayList<BitSet> 				dp;
		private Boolean[][] 				 	adj;
		//Array of bitsets?
		
		public DPHamiltonian(int numNodes) {
			this.MAXNODES = numNodes;
			this.dp = new ArrayList<>(numNodes); //INITIALISE VERTICES
			this.adj = MyCITS2200Project.this.adjMatrix;
		}

		private void initialiseBitSet() {
			for(int i = 0; i < MAXNODES; i++) {
				this.dp.add(i, new BitSet(1<<MAXNODES)); //CREATE THE "MASK"
			}
		}

		private boolean pathExists() {
			this.initialiseBitSet();
			for(int i = 0; i < this.MAXNODES; i++) {
				this.dp.get(i).set(i); //SETS THE BIT SPECIFIED TO true
			}
			BitSet bs; //bitsetj;
			for(int i = 0; i < (1<<this.MAXNODES); i++) { //CYCLE THROUGH EACH MASK/SUBSET OF THE VERTICES
				ByteBuffer bb = ByteBuffer.allocate(1 << this.MAXNODES);
				bb.putInt(i);
				bs = BitSet.valueOf(bb);
				for(int j = 0; j < this.MAXNODES; j++) {
					//bitsetj = BitSet.valueOf(bb);
					if(bs.get(j)) { //If jth bit is set in i (WHICH OF THE VERTICES ARE PRESENT IN THE SUBSET) // i & (1<<j)
						for(int k = 0; k < this.MAXNODES; k++) {
							if(bs.get(1 << k) && this.adj[k][j] && k != j) {
								if(this.dp.get(k).get(j)) { //dp[k][i ^ (1<<j)]
									this.dp.get(j).set(i);
									break;
								}
							}
						}
					}
				}
			}

			for(int i = 0; i < this.MAXNODES; i++) {
				if(this.dp.get(i).get((1<<this.MAXNODES) - 1)) {
					return true;
				}
			}
			return false;
		}
	}
}
