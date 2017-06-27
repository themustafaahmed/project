import java.util.ArrayList;

public class SearchShortestPath implements ISearch{

	Graph graph;
	String path = "";
	public SearchShortestPath(Graph g){
		this.graph = g;
	}

	
	@Override
	public boolean hasPath(String start, String end) {
		
		if(!graph.containsNode(start) || !graph.containsNode(end)){
			return false;
		}
		graph.resetAll();
		
		ArrayList<Node> queue = new ArrayList<Node>();
		Node temp;
		queue.add(graph.getNode(start));
		
		while(queue.isEmpty()){
			
			temp = queue.get(0);
			GraphUtil.printNodeInfo(temp);

			queue.remove(0);
			temp.tested = true;
			
			if(!temp.expanded){ //ako temp ne e razsiren
				for(Link link: temp.listLinks){
					Node rNode = link.relatedNode;
					
						GraphUtil.setParentCost(temp, link);
						queue.add(0,rNode);
						
				}//for each Linjk from temp
				temp.expanded = true;
			}//end if
			
		}//end while for queue
		if(graph.getNode(end).parent == null){
			return false;
		}else{
			
			path ="Cost: " + graph.getNode(end).cost;
			System.out.println("Cost: " + graph.getNode(end).cost);//print path
		//	Node startNode = graph.getNode(start);
		//	Node stopNode = graph.getNode(end);
			return true;
		}
		
		
	}
	
	
	public String Log(String path){
		this.path = path;
		return null;
	}
	

}
