import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import net.datastructures.*;

public class BaconHelper {

	//Performs Breadth First Search starting from a given index
	//Returns the shortest-path tree for the vertices
	public static DirectedAdjListMap<String, Edge> BFS(AdjacencyListGraphMap g, String v) throws Exception{
		//Initialize an empty queue to help sort through neighbors
		//And a map to fill in appropriate order of lengths 
		SLLQueue<String> theQueue = new SLLQueue<String>();
		DirectedAdjListMap<String, Edge> directedGraph = new DirectedAdjListMap<String, Edge>();

		theQueue.enqueue(v); 

		directedGraph.insertVertex(v);
		while (!theQueue.isEmpty()){
			String v2 = theQueue.dequeue();

			//Find all its edges, and find the neighbors
			Iterable i = g.incidentEdges(v2);
			Iterator iter = i.iterator();
			while (iter.hasNext()){
				Edge<String> e = (Edge) iter.next();

				Vertex<String> otherEnd = g.opposite(v2, e);

				//Add it to the graph if not already there
				if (!directedGraph.vertexInGraph((String) otherEnd.element())){
					directedGraph.insertVertex((String)otherEnd.element());
					directedGraph.insertDirectedEdge((String)otherEnd.element(), (String)v2, e);
					theQueue.enqueue((String)otherEnd.element());
				}
			}
		}
		return directedGraph;
	}
	
	public static DirectedAdjListMap<String, Edge> parseData(String movies, String actors, String actorsMovies){
		TreeMap<String, String> actorsAndID = new TreeMap<String,String>();
		TreeMap<String, String> moviesAndID = new TreeMap<String, String>();
		TreeMap<String, ArrayList<String>> pairings = new TreeMap<String, ArrayList<String>>();
		AdjacencyListGraphMap<String, String> undirected = new AdjacencyListGraphMap<String, String>();
		String line;

		//Parse the data, associate movies and actors with their IDs
		try {
			//associate movies with ID
			BufferedReader reader = new BufferedReader(new FileReader(movies));

			while ((line = reader.readLine()) != null){
				String[] movieTokens = line.split("[|]");
				String movieID = movieTokens[0];
				String movieName = movieTokens[1];
				moviesAndID.put(movieID, movieName);
			}

			//associate actors with ID
			reader = new BufferedReader(new FileReader(actors));
			while ((line = reader.readLine())!=null){
				String[] actorTokens = line.split("[|]");
				String actorID = actorTokens[0];
				String actorName = actorTokens[1];
				actorsAndID.put(actorID, actorName);
			}

			//Keep a list of which group of actors worked together 
			reader = new BufferedReader(new FileReader(actorsMovies));
			while ((line=reader.readLine())!=null){
				String[] tokens = line.split("[|]");
				String mID = tokens[0];
				String aID = tokens[1];
				if (!pairings.containsKey(moviesAndID.get(mID))){
					ArrayList<String> al = new ArrayList<String>();
					al.add(actorsAndID.get(aID));
					pairings.put(moviesAndID.get(mID), al);
				}
				else {
					ArrayList<String> t = pairings.get(moviesAndID.get(mID));
					t.add(actorsAndID.get(aID));
					pairings.put(moviesAndID.get(mID), t);
				}
			}

			//Construct the vertices and edges in the undirected graph
			for (String key: actorsAndID.keySet()){
				undirected.insertVertex(actorsAndID.get(key));
			}
			for (String key: pairings.keySet()){
				ArrayList<String> a = pairings.get(key);
				if (a.size() >1){
					for (int k = 0; k < a.size(); k++){
						for (int i=k+1; i< a.size(); i++){
							undirected.insertEdge(a.get(k), a.get(i), key);
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DirectedAdjListMap<String, Edge> directedGraph=null;
		try {
			directedGraph = BFS(undirected, "Kevin Bacon");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return directedGraph;
	}
	
	public static int findNumber(String s, DirectedAdjListMap<String,Edge> map){
		int i =0;
		String current = s;
		if (map.vertexInGraph(current)){

			while (!current.equals("Kevin Bacon")){
				Iterable iter = map.incidentEdgesOut(current);
				Iterator it = iter.iterator();
				Edge next = (Edge) it.next();
				String[] temp = next.element().toString().split("[)]");
				String movieTitle = temp[0]+")";
				System.out.println(current+" appeared in "+movieTitle+" with "+map.endVertices(next)[1]);
				current = map.endVertices(next)[1].element();
				i++;
			}


		}
		else {
			System.out.println(current+" has never worked with Kevin Bacon");
			i = -1;
		}
		return i;

	}
}