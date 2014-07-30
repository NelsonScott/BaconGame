public class TestBacon {
	//Use to test smaller amounts of data
	public static void main(String[] args){
		AdjacencyListGraphMap<String, String> bg = new AdjacencyListGraphMap<String, String>();
		BaconHelper bh = new BaconHelper();
				
				  //Testing
	  			String[] vertices = {"Kevin Bacon", "actor1", "actor2", "actor3", "actor4", "actor5", "actor6"};
			  	String[] e0 = {"Kevin Bacon", "actor1", "movie1"};
			  	String[] e1 = {"Kevin Bacon", "actor2", "movie1"};
			  	String[] e2 = {"actor1", "actor2", "movie1"};
			  	String[] e3 = {"actor1", "actor3", "movie2"};
			  	String[] e4 = {"actor3", "actor2", "movie3"};
			  	String[] e5 = {"actor3", "actor4", "movie4"};
			  	String[] e6 = {"actor5", "actor6", "movie5"};
			  	
			  	
			  	for (int i =0; i<vertices.length; i++){
			  		bg.insertVertex(vertices[i]);
			  		System.out.println("Inserting: "+vertices[i]);
			  	}
			  	System.out.println("Finished inserting vertices");
			  	
			  		bg.insertEdge("Kevin Bacon", "actor1", "movie1");
			  		bg.insertEdge("Kevin Bacon", "actor2", "movie1");
			  		bg.insertEdge("actor1", "actor2", "movie1");
			  		bg.insertEdge("actor1", "actor3", "movie2");
			  		bg.insertEdge("actor3", "actor2", "movie3");
			  		bg.insertEdge("actor3", "actor4", "movie4");
			  		bg.insertEdge("actor5", "actor6", "movie5");
			  		
			  		System.out.println(bg.vertices());
			  		System.out.println(bg.edges());


			  		try {
						DirectedAdjListMap map = bh.BFS(bg, "Kevin Bacon");
						System.out.println(bh.findNumber("actor1", map));
						System.out.println(map.vertices());
						System.out.println(map.edges());
						System.out.println(bh.findNumber("actor1", map));
						System.out.println(map.getVertex("actor1"));

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
}	
