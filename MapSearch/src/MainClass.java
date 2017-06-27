
public class MainClass {
	
	public static void init(Graph g){
		g.addNode(new Node("A",0,0));
		g.addNode(new Node("B",2,0));
		g.addNode(new Node("C",0,3));
		g.addNode(new Node("D",2,2));
		g.addNode(new Node("E",12,0));
		g.addNode(new Node("F",1,25));
		g.addNode(new Node("W",2,3));
		
		g.twoWayLink("A", "B",2);
		g.twoWayLink("A", "C",1);
		g.twoWayLink("B", "D",4);
		g.twoWayLink("B", "E",5);
		g.twoWayLink("B", "F",12);
		g.twoWayLink("C", "F",12);
		g.twoWayLink("E", "F",1);
		g.twoWayLink("A", "F",8);
		
	}
	
	public static void search(ISearch s, String start, String end){
		if(s.hasPath(start, end)){
			System.out.println("HAVE A PATH!!!");
		}else{
			System.out.println("HAVE NOT A PATH!!!");
		}
	}
	public static void main(String[] args) {
		Graph g = new Graph();
		init(g);
		//search(new BreadthSearch(g),"A","F");
		//search(new GreedyByCoordinates(g),"E","F");
		search(new SearchShortestPath(g),"F","C");

	}

}

//algorithm dijkstra java 27.04