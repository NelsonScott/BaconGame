import net.datastructures.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class BaconGame {
	/*Code to play the 'Bacon Game' which finds an actor's closeness to Kevin Bacon based on their work experience
	 *@author Scott Nelson for CS 10 at Dartmouth College
	 *Uses some files downloaded from http://net3.datastructures.net/
	 *
	 */
	
	public static void main(String[] args) throws IOException{
		
		//Access to BFS and other helper methods
		BaconHelper bh = new BaconHelper();
		
		DirectedAdjListMap<String, Edge> m = bh.parseData(
				"inputs/movies.txt",
				"inputs/actors.txt" ,
				"inputs/movie-actors.txt");

		
		System.out.println("To quit the program, type return in answer to a question.");

		System.out.println("Enter the name of an actor: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=in.readLine())!=null){
			if (s.equals("")){
				System.out.println("Goodbye.");
				System.exit(0);
			}
			if (m.vertexInGraph(s)){
				System.out.println("The Bacon number of "+s+" is: "+bh.findNumber(s,m));
			}
			else {
				System.out.println(s+" was not found.");
			}
			System.out.println("Enter the name of an actor: ");

		}

	}
}


