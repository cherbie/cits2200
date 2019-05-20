import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;
import java.lang.String;

public class MyCITS2200Project implements CITS2200Project {
    //FIELDS
	public ArrayList<String> wikiAddr; //LOOKUP TABLE
	private int maxvd; //number of vertice descriptor (similar to file descriptor)
	private HashMap< Integer, LinkedList<Integer> > edgeList;
	
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
	 * Finds the shorest path in number of links between two pages.
	 * If there is no path, returns -1.
	 * 
	 * @param urlFrom the URL where the path should start.
	 * @param urlTo the URL where the path should end.
	 * @return the legnth of the shorest path in number of links followed.
	 */
	public int getShortestPath(String urlFrom, String urlTo) {
		//arraylist.trimToSize();
		return 0;
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