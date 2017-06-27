import java.util.ArrayList;

public class GraphUtil {
	
	public static void setParentCost(Node p, Link l){
		double newCost = p.cost + l.pathLenght;
		Node rNode = l.relatedNode;
		
		if(rNode.parent == null || rNode.cost > newCost){
			rNode.parent = p;
			rNode.cost = newCost;	
		}		
	}
	
	public static void printNodeInfo(Node n){
		String parentName;
		if(n.parent == null){
			parentName = "No parent";
		}else{
			parentName = n.parent.name;
		}
		System.out.print("Node name:"+ n.name + ", Cost: " + n.cost + ", Parent: " +parentName);
	}
	
	public static void printPathByDepth(String name,Graph graph){
		Node temp = graph.getNode(name);
		System.out.println(name);
		if(temp.depth == 0){
			return;
		}
		for(Node node : temp.links){
			if(node.depth == (temp.depth-1)){
				printPathByDepth(node.name,graph);
				break;
			}
		}
	}
	
	public static ArrayList<Node> sortQueueWeight(ArrayList<Node> queue, Node node){
		
		boolean isNotAdded = true;
		
			for(int i=0;i<queue.size();i++){
				if(node.weight < queue.get(i).weight){
					queue.add(i,node);
					isNotAdded = false;
					break;
				}
			}//loop over queue
			if(isNotAdded) queue.add(node);

		return queue;
	}
	
	public static void calcDistance(Node start,Node end){
		start.weight = Math.sqrt(Math.pow(start.x - end.x, 2)
				+ Math.pow(start.y - end.y, 2));
	}
	
	public static void pathPrint(Node stopNode, Node goalNode){
		if(!(goalNode.name == stopNode.name)){
			System.out.println(goalNode.name+ "<-");
			pathPrint(stopNode,goalNode.parent);
		}else{
			System.out.println(stopNode.name + "|" + "\n");
			return;
		}
	}

}
