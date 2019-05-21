import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;
import java.lang.String;
import java.lang.Exception;

public class MyCITS2200Project implements CITS2200Project {
	//ENUMERATIONS
	enum Colour {WHITE, GREY, BLACK;} //colours representing the states of a vertex

	//FIELDS
	public  ArrayList<String>                     wikiAddr; //LOOKUP TABLE
	private int                                   maxvd; //number of vertice descriptor (similar to file descriptor)
	public  HashMap<Integer, LinkedList<Integer>> edgeList;

	//CONSTRUCTOR
	public MyCITS2200Project() {
		this.wikiAddr = new ArrayList<>();
		this.edgeList = new HashMap<>();
		this.maxvd = 0;
	}

	//METHODS
	/**
	* Adds an edge to the Wikipedia page graph. If the pages do not
	* already exist in the graph, they will be added to the graph.
	* 
	* @param urlFrom the URL which has a link to urlTo.
	* @param urlTo the URL which urlFrom has a link to.
	*/
	public void addEdge(String urlFrom, String urlTo) {
		//SEARCH PARENT
		int parentvd = this.wikiAddr.indexOf(urlFrom);
		int childvd = this.wikiAddr.indexOf(urlTo);

		//TODO --> REMOVE CYCLICAL CASE
		if(parentvd < 0) { //vertex does not exist in lookup table
			parentvd = this.maxvd;
			this.wikiAddr.add(parentvd, urlFrom);
			this.edgeList.put(parentvd, new LinkedList<>());
			this.maxvd++;
		}
		else if(!this.edgeList.containsKey(parentvd)) { //does contain a linked list already
			//does not have a linkedlist mapped
			this.edgeList.put(parentvd, new LinkedList<>());
		}
		if(childvd < 0) { //vertex does not exist in lookup table
			childvd = this.maxvd;
			this.wikiAddr.add(childvd, urlTo);
			this.maxvd++;
		}

		//ADD VERTICE DESCRIPTORS TO LINKED LIST
		//CHECK IF EDGE ALREADY EXISTS
		if(!edgeList.get(parentvd).contains(childvd)) { //edge does not exist in LinkedList
			edgeList.get(parentvd).add(childvd);
		}
		//Linked list already contains the edge
		return;
	}

	/**
	 * Search lookup table to find the vertex descriptor for a particular string
	 * @param String url of wiki address
	 * @return int vertex decriptor of node/vertex
	 */
	private int getVertexDescriptor(String url) throws Exception {
		int vd = this.wikiAddr.indexOf(url);
		if(vd < 0) { //vertex descriptor does not exist in lookup table
			throw new Exception("Wiki URL does not exist in lookup table and hence is not part of graph.");
		}
		return vd;
	}

	private class MyBFS<E> {
		//FIELDS
		private int[] distance; 
		private HashMap<Integer, LinkedList<Integer>> vertexEdges;
		private Colour[] colour;

		/**
		 * CONSTRUCTOR OF BFS CLASS
		 */
		public MyBFS() {
			int numNodes = MyCITS2200Project.this.maxvd;
			this.distance = new int[numNodes];
			this.vertexEdges = MyCITS2200Project.this.edgeList;
			this.setColour(numNodes); //
		}

		private void setColour(int size) {
			this.colour = new Colour[size];
			for(int i = 0; i < size; i++) {
				this.colour[i] = Colour.WHITE;
			}
		}

		/**
		 * RUN ENTIRE BFS ALGORITHM ON THE ESTABLISHED GRAPH.
		 * GARUANTEES SHORTEST PATH DISTANCE FIELD WILL BE UP-TO-DATE.
		 * @param startVertex the starting vertex of the BFS.
		 */
		public void run(int startVertex) {

			LinkedList<Integer> q = new LinkedList<>(); //queue implementation
			LinkedList<Integer> ll;
			int w, x;

			//BFS ALGORITHM
			distance[startVertex] = 0;
			q.add(startVertex);
			while(q.peek() != null) {
				w = (int) q.remove(); //REMOVE FIRST ELEMENT IN THE QUEUE
				//FIND ADJACENT VERTICES TO w
				ll = MyCITS2200Project.this.edgeList.get(w); //adjacency list for the node
				if(ll == null) continue; //NO VALUE MAPPED TO KEY
				System.out.println(ll.toString());
				while(ll.peek() != null) {
					//CONNECTED OR "CHILD" OF w
					x = (int) ll.remove();
					System.out.println(x);
					if(colour[x] == Colour.WHITE) { //WHITE OR NOT SEEN
						if(x == w) continue; //non-cyclical
						distance[x] = distance[w] + 1;
						colour[x] = Colour.GREY; //SET TO GREY OR SEEN
						q.add(x);
					}
				}
				colour[w] = Colour.BLACK; //SET TO BLACK
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
		public int getDistances(int node) {
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
			LinkedList<Integer> ll;
			int w, x;

			//BFS ALGORITHM
			distance[startVertex] = 0;
			q.add(startVertex);
			while(q.peek() != null) {
				w = (int) q.remove(); //REMOVE FIRST ELEMENT IN THE QUEUE
				//FIND ADJACENT VERTICES TO w
				ll = MyCITS2200Project.this.edgeList.get(w); //adjacency list for the node
				if(ll == null) continue; //NO VALUE MAPPED TO KEY
				System.out.println(ll.toString());
				while(ll.peek() != null) {
					//CONNECTED OR "CHILD" OF w
					x = (int) ll.remove();
					System.out.println(x);
					if(colour[x] == Colour.WHITE) { //WHITE OR NOT SEEN
						if(x == w) continue; //non-cyclical
						distance[x] = distance[w] + 1;
						if(x == endVertex) return distance[endVertex]; //seen end point
						colour[x] = Colour.GREY; //SET TO GREY OR SEEN
						q.add(x);
					}
				}
				colour[w] = Colour.BLACK; //SET TO BLACK
			}
			throw new Exception("BFS FAILED TO FIND THE END VERTEX");
		}
	}
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
			int startVertex = getVertexDescriptor(urlFrom); //vertex descriptor
			int endVertex = getVertexDescriptor(urlTo); //vertex descriptor

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
}