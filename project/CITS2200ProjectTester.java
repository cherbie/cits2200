import java.io.*;
import java.util.*;

/**
 * NOTE: CHANGE ALL REFERENCE TO MYCITS2200PROJECT TO THE INTERFACE CLASS CITS2200PROJECT FOR TEST
 */

public class CITS2200ProjectTester {
	private MyCITS2200Project myProj;

	public static void loadGraph(MyCITS2200Project project, String path) {
		// The graph is in the following format:
		// Every pair of consecutive lines represent a directed edge.
		// The edge goes from the URL in the first line to the URL in the second line.
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			while (reader.ready()) {
				String from = reader.readLine();
				String to = reader.readLine();
				System.out.println("Adding edge from " + from + " to " + to);
				project.addEdge(from, to);
			}
		} catch (Exception e) {
			System.out.println("There was a problem:");
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		// Change this to be the path to the graph file.
		String pathToGraphFile = "./example_graph.in";
		// Create an instance of your implementation.
		MyCITS2200Project proj = new MyCITS2200Project();
		// Load the graph into the project.
		loadGraph(proj, pathToGraphFile);

		/** TESTING **/
		printLookupTable(proj.wikiAddr);
		printAdjacencyList(proj.edgeList, 2);
	}

	public static void print(String s) {
		System.out.println(s);
	}

	public static void printLookupTable(ArrayList<String> al) {
		int size = al.size();
		for(int i = 0; i < size; i++) {
			System.out.println("vertex descriptor:\t" + i + "\taddress:\t" + al.get(i));
		}
	}

	public static void printAdjacencyList(HashMap<Integer, LinkedList<Integer>> map, int node) {
		LinkedList<Integer> ll = map.get(node);
		int size = ll.size();
		System.out.println("NODE - " + node);
		for(int i = 0; i < size; i++) {
			System.out.println("edge:\t" + ll.get(i));
		}
	}
}