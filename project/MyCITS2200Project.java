/**
* UNIT: Data Structures & Algorithms (CITS2200)
* @author Clayton Herbst (22245091@student.uwa.edu.au)
*/

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

public class MyCITS2200Project implements CITS2200Project {

	//FIELDS
	private HashMap<Integer, String>                wikiLookup; //LOOKUP TABLE
	private HashMap<String, Integer>			    nodeLookup; //REVERSE LOOKUP TABLE
	private int                                     maxvd; //NUMBER OF VERTEX DESCRIPTORS (SIMILAR TO FILE DESCRIPTORS)
	private ArrayList<LinkedList<Integer>>          edgeList;
	private ArrayList<LinkedList<Integer>>			transEdgeList;
	private boolean[][]								adjMatrix;

	//CONSTRUCTOR
	public MyCITS2200Project() {
		this.wikiLookup = new HashMap<>();
		this.nodeLookup = new HashMap<>();
		this.edgeList = new ArrayList<>();
		this.transEdgeList = new ArrayList<>();
		this.adjMatrix = new boolean[20][20]; //QUESTION SPECIFIED NODE RESTRICTED TO 20 FOR HAMILTONIAN PATHS
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
		Integer parentvd = this.nodeLookup.get(urlFrom); //SEARCH FOR 
		Integer childvd = this.nodeLookup.get(urlTo);
		
		if(parentvd == null) parentvd = this.addParent(urlFrom);
		if(childvd == null && !urlFrom.equals(urlTo)) childvd = this.addChild(urlTo);
		else if(childvd == null) childvd = parentvd; //CYCLICAL EDGE

		if(this.edgeList.get(parentvd) == null) //IF LINKED LIST HAS NOT BEEN INITIALISED
			this.edgeList.set(parentvd, new LinkedList<>()); 

		//ADD VERTEX DESCRIPTORS TO ADJACENCY LIST
		if(!this.edgeList.get(parentvd).contains(childvd)) //EDGE DOES NOT EXIST IN LINKED LIST
			this.edgeList.get(parentvd).add(childvd);

		if(parentvd < 20 || childvd < 20) //BOUNDS OF HAMILTONIAN IMPLEMENTATION
			this.adjMatrix[parentvd][childvd] = true; //ADD EDGE TO ADJACENCY MATRIX

		if(this.transEdgeList.get(childvd) == null) //IF LINKED LIST HAS NOT BEEN INITIALISED
			this.transEdgeList.set(childvd, new LinkedList<>());

		//ADD VERTEX DESCRIPTORS TO TRANSPOSED ADJACENCY LIST
		if(!this.transEdgeList.get(childvd).contains(parentvd))
			this.transEdgeList.get(childvd).add(parentvd);

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
		return vd;
	}

	/**
	* Finds the shorest path in number of links between two pages.
	* @param urlFrom the URL where the path should start.
	* @param urlTo the URL where the path should end.
	* @return the length of the shorest path in number of links followed, otherwise returns -1.
	*/
	public int getShortestPath(String urlFrom, String urlTo) {
		if(urlFrom.equals(urlTo)) return 0;
		else {
			int startVertex = this.nodeLookup.get(urlFrom); //vertex descriptor
			int endVertex = this.nodeLookup.get(urlTo); //vertex descriptor	
			MyBFS bfs = new MyBFS();
			return bfs.shortestPath(startVertex, endVertex);
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
		GraphCenters gc = new GraphCenters();
		return gc.getCenters();
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
		MySCC myscc = new MySCC();
		return myscc.getSCC();
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
		DPHamiltonian hamImp = new DPHamiltonian(this.maxvd);
		return hamImp.hamiltonianPath();
	}

	/*******************************************************************/

	private class MyBFS {

		/**
		* CONSTRUCTOR OF BREADTH-FIRST SEARCH CLASS IMPLEMENTATION
		*/
		public MyBFS() {}

		/**
		* Run a BFS over an established graph and exit as soon as the ending node is reached.
		* Not garuanteed to have a shortest path defined between the two specified vertices.
		* @param int startVertex is the starting vertex.
		* @param int endVertex is the ending vertex.
		* @return the shortest path to get to the ending vertex or -1 if the shortest path does not exist.
		*/
		public int shortestPath(int startVertex, int endVertex) {
			int[] distance = new int[MyCITS2200Project.this.maxvd]; //DISTANCE OF NODES FROM ROOT NODE IN BFS-TREE
			LinkedList < Integer > q = new LinkedList < > (); //FIRST-IN FIRST-OUT QUEUE IMPLEMENTATION
			Iterator < Integer > it; //ITERATOR THROUGH ALL ADJACENT NODES
			BitSet visited = new BitSet(MyCITS2200Project.this.maxvd); //MARK VERTEICES AS UNVISITED
			int node, x;

			// -- BFS ALGORITHM --
			distance[startVertex] = 0; //SET ROOT NODE IN BFS-TREE'S DISTANCE TO ZERO
			q.add(startVertex);
			visited.set(startVertex);
			while (q.peek() != null) {
				node = q.pop(); //REMOVE FIRST ELEMENT IN THE QUEUE
				if (MyCITS2200Project.this.edgeList.get(node) == null) continue; //LEAF IN BFS-TREE
				// --FIND ADJACENT VERTICES TO NODE--
				it = MyCITS2200Project.this.edgeList.get(node).listIterator(0); //iterator starting at the first element
				while (it.hasNext()) {
					x = it.next(); //CONNECTED OR "CHILD" OF NODE
					if (!visited.get(x)) { //NOT VISITED
						if (node == x) continue; //NON-CYCLIC GRAPH
						distance[x] = distance[node] + 1;
						if (x == endVertex) return distance[endVertex]; //SEEN DESTINATION VERTEX
						visited.set(x);
						q.add(x);
					}
				}
			}
			return -1;
		}
	}

	/*************************************************************/

	private class MySCC {

		//FIELDS
		private BitSet visited;

		/**
		 * CONSTRUCTOR OF STRONGLY CONNECTED COMPONENTS (SCC) CLASS.
		 */
		public MySCC() {
			this.visited = new BitSet(MyCITS2200Project.this.maxvd);
		}

		/**
		* Recursive DFS implementation starting at the vertex descriptor specified.
		* count is incremented and array containing all strongly connected components updated.
		* @param node int vertex descriptor.
		* @param arrstr list of all nodes in DFS/strongly connected component
		*/
		private void dfs(int node, LinkedList<String> arrstr) {
			if(MyCITS2200Project.this.transEdgeList.get(node) == null) return; //NO CONNECTED NODES TO ADD TO SCC GROUP
			Iterator<Integer> it = MyCITS2200Project.this.transEdgeList.get(node).listIterator(0);
			System.out.println("TRANS EDGE LIST: " + node + "\t");
			
			arrstr.add(MyCITS2200Project.this.wikiLookup.get(node)); //ADD WIKI URL TO SCC LIST
			this.visited.set(node);

			while(it.hasNext()) {
				int vd = it.next();
				System.out.println("NEXT=\t" + vd + "COLOUR=\t" + this.visited.get(vd));
				if(!this.visited.get(vd)) //Colour.WHITE symbolises NOT VISITED
					this.dfs(vd, arrstr);
			}
		}
		
		/**
		* Implements Kosaraju's algorithm performing a DFS twice on a predefined graph
		* inorder to find and list all strongly connected components.
		* @return array containing every strongly connected component (node descriptors)
		*/
		public String[][] getSCC() {
			BitSet iterVisited = new BitSet(MyCITS2200Project.this.maxvd); //ALL NODES ARE SET AS UNVISITED
			String[][] arrscc = new String[MyCITS2200Project.this.maxvd][];
			LinkedList<Integer> queue = new LinkedList<>(); //QUEUE TO RETRACE ORDER OF FIRST DFS
			Stack<Integer> dfsStack = new Stack<>(); //STACK FOR ITERATIVE DFS
			Iterator<Integer> it;
			Integer node, x;

			// -- NON-RECURSIVE DFS IMPLEMENTATION --
			node = 0;
			dfsStack.push(node); //STARTING AT VERTEX DESCRIPTOR 0
			while(!dfsStack.empty()) {
				node = dfsStack.pop();
				System.out.println("Node popped " + node);
				if(!iterVisited.get(node)) { //NOT VISITED
					System.out.println("node not seen");
					queue.add(node);
					iterVisited.set(node);
					if(MyCITS2200Project.this.edgeList.get(node) == null) continue;
					it = MyCITS2200Project.this.edgeList.get(node).listIterator(0); //ITERATOR OVER ADJACENCY LIST
					while(it.hasNext()) { //ADD ALL CONNECTED NODES TO DFS STACK
						x = it.next(); //CONNECTED NODE USE ITERATOR????
						System.out.println("1st DFS adjacency item = " + x);
						if(!iterVisited.get(x)) //NOT VISITED
							dfsStack.push(x);
					}
				}
				else System.out.println("ELSE: " + iterVisited.get(node));
			}

			// -- TRANSPOSITION HANDLED IN MyCITS2200Project.addEdge() --
			
			//CALLS RECURSIVE DFS IMPLEMENTATION
			LinkedList<String> scc = new LinkedList<>();
			int numscc = 0; //SET NUMBER OF STRONGLY CONNECTED COMPONENTS TO ZERO
			int count = 0;
			System.out.println("REACHED 2nd DFS");
			while(!queue.isEmpty()) {
				node = queue.remove();
				System.out.println("2nd DFS :: NODE=\t" + node + " COLOUR=\t" + this.visited.get(node));
				if(!this.visited.get(node)) { //NOT VISITED
					this.dfs(node, scc); //RECURSIVE DFS IMPLEMENTATION
					arrscc[numscc] = new String[scc.size()];
					while(!scc.isEmpty()) arrscc[numscc][count++] = scc.remove(); //REMOVE HEAD FROM LIST OF STRONGLY CONNECTED COMPONENTS
					count = 0;
					numscc++;
					System.out.println();
				}
			}

			// -- TIDY RETURN VALUE --
			String[][] retscc = new String[numscc][];
			System.arraycopy(arrscc, 0, retscc, 0, numscc);
			return retscc;
		}
	}

/*************************************************************************/

	public class DPHamiltonian {
		
		//FIELDS
		private int 							MAXNODES; //NUMBER OF NODES
		private ArrayList<BitSet> 				dp;
		private boolean[][] 				 	adj; //ADJACENCT MATRIX
		//Array of bitsets?
		
		/**
		* Constructor for hamiltonian path class implementation.
		*/
		public DPHamiltonian(int numNodes) {
			this.MAXNODES = numNodes;
			this.dp = new ArrayList<>(numNodes); //INITIALISE VERTICES
			this.adj = MyCITS2200Project.this.adjMatrix; //SHALLOW COPY OF ADJACENCY MATRIX
			this.initialiseBitSet();
		}

		/**
		* CREATE THE BIT-SET MASK FOR EACH GRAPH NODE.
		*/
		private void initialiseBitSet() {
			for(int i = 0; i < MAXNODES; i++)
				this.dp.add(i, new BitSet(1<<MAXNODES)); //CREATE THE "MASK"
		}

		/**
		* Dynamic programming algorithm using a bottom-up approach.
		* @return String[] containing the 'hamiltonian walk'. If no hamiltonian path exists an empty array is returned. 
		*/
		private String[] hamiltonianPath() {
			String[] path = new String[MAXNODES];
			
			for(int i = 0; i < this.MAXNODES; i++)
				this.dp.get(i).set(1 << i); //SET THE NODE BITS IN MASK

			// -- SET BITS IN MASK --
			for(int i = 0; i < (1<<this.MAXNODES); i++) { //CYCLE THROUGH EACH MASK/SUBSET OF THE VERTICES
				System.out.println("NEXT MASK -- i =\t" + i);
				for(int j = 0; j < this.MAXNODES; j++) {
					if((i & (1 << j)) != 0) { //if the jth bit is set in i
						System.out.println("bs.get(j) is true. j =\t" + j);
						for(int k = 0; k < this.MAXNODES; k++) {
							System.out.println("k =\t" + k + " adjacency:\t" + this.adj[k][j]);
							if( ((i & (1 << k)) != 0) && this.adj[k][j] && k != j && this.dp.get(k).get((i ^ (1 << j)))) {
								this.dp.get(j).set(i);
								break;
							}
						}
					}
				}
			}

			// -- ESTABLISH IF PATH EXIST && DERIVE THE HAMILTONIAN WALK --
			for(int i = 0; i < this.MAXNODES; i++) {
				System.out.println("Bit value at [" + i + "][" + ((1<<this.MAXNODES) -1) + "] = " + this.dp.get(i).get((1<<this.MAXNODES) -1));
				if(this.dp.get(i).get((1<<this.MAXNODES) - 1)) { //HAMILTONIAN PATH DOES EXIST
					BitSet seen = new BitSet(this.MAXNODES);
					int count = this.MAXNODES-1; //start at last path position
					int j = 0;
					int prev, mask, maskxor;
					path[count--] = MyCITS2200Project.this.wikiLookup.get(i);
					seen.set(i); //MARK AS SEEN
					mask = (1 << this.MAXNODES) - 1;
					// -- FIND PREVIOUS NODE ... ie walking backwards --
					while(seen.cardinality() < this.MAXNODES && count >= 0) {
						prev = seen.nextClearBit(j);
						maskxor = mask ^ (1 << i);
						System.out.println("prev=\t" + prev + " i=\t" + i);
						if(prev > (this.MAXNODES - 1)) {
							j = 0; //START SEARCH FOR CLEAR BIT AT START
							continue;
						}

						if(dp.get(i).get(mask) == dp.get(prev).get(maskxor) && this.adj[prev][i]) { //PREVIOUS NODE
							path[count--] = MyCITS2200Project.this.wikiLookup.get(prev);
							seen.set(prev);
							i = prev;
							mask = maskxor;
							continue;
						}
						j = (++j%this.MAXNODES); //CYCLICAL SEARCH FOR CLEAR BIT
					}
					return path;
				}
			}
			return new String[0];
		}
	}

	/**********************************************/

	public class GraphCenters {

		/**
		* Constructor
		*/
		public GraphCenters() {}

		/**
		* Perform a BFS on a given vertex.
		* @return the eccentricity associated with the vertex
		* @param startVertex, the vertex assessed
		* @param distance, array for managing distances
		*/
		private int bfs(int startVertex, int[] distance) {
			LinkedList<Integer> q = new LinkedList<>(); //queue implementation
			Iterator<Integer> it;
			BitSet visited = new BitSet(MyCITS2200Project.this.maxvd);
			int node, x;

			// -- BFS ALGORITHM --
			System.out.println("Start Vertex: " + startVertex + "\n");
			distance[startVertex] = 0;
			int max = 0;
			visited.set(startVertex);
			q.add(startVertex);
			while(q.peek() != null) {
				node = q.remove(); //REMOVE FIRST ELEMENT IN THE QUEUE
				// -- FIND ADJACENT VERTICES --
				if(MyCITS2200Project.this.edgeList.get(node) == null) continue;
				it = MyCITS2200Project.this.edgeList.get(node).listIterator(0); //adjacency list for the node
				while(it.hasNext()) { //CONNECTED OR "CHILD" OF w
					x = it.next();
					if(x == node) continue; //non-cyclical
					if(!visited.get(x)) { //NOT VISITED
						distance[x] = distance[node] + 1;
						if(distance[x] > max) {
							System.out.println("MAX DISTANCE FOR x = " + x + " is => " + distance[x]);
							max = distance[x];
						}
						visited.set(x);
						q.add(x);
					}
				}
			}
			return max;
		}

		public String[] getCenters() {
			int numNodes = MyCITS2200Project.this.maxvd;
			int min = Integer.MAX_VALUE; //INITIALISE ECCENTRICITY TO MAXIMUM
			BitSet minEcc = new BitSet(numNodes); //SET OF MINIMUM ECCENTRICITIES
			
			// -- FIND SET OF MINIMUM ECCENTRICITIES --
			for(int i = 0; i < numNodes; i++) {
				int max = this.bfs(i, new int[numNodes]);
				System.out.println("NODE = " + i + " MAX = " + max);
				if(min > max) { //MAX ECCENTRICITY IS NEW MINIMUM
					minEcc.clear();
					minEcc.set(i);
					min = max;
				}
				else if(min == max) //ADD TO MINIMUM SET
					minEcc.set(i);
			}

			int size = minEcc.cardinality(); //NUMBER OF ELEMENTS IN SET OF MINIMUM ECCENTRICITIES
			if(size <= 0) return new String[0]; //EMPTY ARRAY

			int len = minEcc.length(); //GREATEST VERTEX DESCRIPTOR IN SET OF MINIMUM ECCENTRICITIES 
			String[] centers = new String[size];
			int j = 0;
			int count = 0;
			j = minEcc.nextSetBit(j);
			while(j<len&& count < size) {
				System.out.println("COUNT = " + count + " J = " + j);
				centers[count++] = MyCITS2200Project.this.wikiLookup.get(j);
				minEcc.set(j, false); //REMOVED FROM SET -- AVOID DOUBLE ENTRY
				j = minEcc.nextSetBit(j+1);
			}
			return centers;
		}
	}
}
