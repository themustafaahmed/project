import java.util.ArrayList;

public class BreadthSearch implements ISearch{

	Graph graph;
	ArrayList<Node> queue = new ArrayList<Node>();
	public BreadthSearch(Graph g){
		this.graph = g;
	}

	@Override
	public boolean hasPath(String start, String end) {
		
		if(!graph.containsNode(start) || !graph.containsNode(end)){
			return false;
		}
		graph.resetAll();
		
		//ArrayList<Node> queue = new ArrayList<Node>();
		Node temp;
		queue.add(graph.getNode(start));
		
		while(!queue.isEmpty()){
			
			temp = queue.get(0);
			System.out.println("\n" + temp.name + " , " + temp.depth);
			if(temp.name.equals(end)){
				GraphUtil.printPathByDepth(end,graph);
				return true;
			}
			queue.remove(0);
			temp.tested = true;
			if(!temp.expanded){
				for(Node node : temp.links){
					if(!node.tested && !queue.contains(node)){
						//System.out.print(node.name + " | ");
						node.depth = temp.depth +1;
						queue.add(node);
					}
				}
				temp.expanded = true;
			}
			//printQueue(queue);
				
		}//end while
		return false;
	}//end hasPath
	//Node node;
	public void printQueue(ArrayList<Node> arr){	
		//String path = "";
		//ArrayList<Node> arr = new ArrayList<Node>();
		for(Node node : arr){
			System.out.print(node.name + ",");
		}
		
	//return path;
	}
	
	String path = "";
	//Node temp;
	public void Log(){
		printQueue(queue);
		//return path;
	}
	
}
