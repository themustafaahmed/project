import java.util.ArrayList;

public class GreedyByCoordinates implements ISearch{
	
	Graph graph;
	ArrayList<Node> queue = new ArrayList<Node>();

	public GreedyByCoordinates(Graph g){
		this.graph = g;
	}

	@Override
	public boolean hasPath(String start, String end) {
		if(!graph.containsNode(start) || !graph.containsNode(end))
		return false;
		
		graph.resetAll();
		
		//ArrayList<Node> queue = new ArrayList<Node>();
		Node temp;
		queue.add(graph.getNode(start));
		
		while(!queue.isEmpty()){
			
			temp = queue.get(0);
			System.out.println("\n" + temp.name + " , " + temp.depth);
			if(temp.name.equals(end)){
				return true;
			}
			queue.remove(0);
			temp.tested = true;
			if(!temp.expanded){
				for(Node node : temp.links){
					if(!node.tested && !queue.contains(node)){
						GraphUtil.calcDistance(node, graph.getNode(end));
						GraphUtil.sortQueueWeight(queue, node);
					}
				}
				temp.expanded = true;
			}
			//printQueue(queue);
				
		}//end while
		return false;
	}//end hasPath
	public void printQueue(ArrayList<Node> arr){	

		for(Node node : arr){
			System.out.print(node.name + ",");
		}
	}
		
	
}
