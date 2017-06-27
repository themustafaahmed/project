import java.util.ArrayList;

public class Node implements INode{
	
	String name;
	int x,y;
	double weight;
	ArrayList<Node> links= new ArrayList<Node>();//forNode
	ArrayList<Link> listLinks = new ArrayList<Link>();//forLink
	//flags
	boolean tested,expanded;
	int depth;
	Node parent;
	double cost;
	
	public Node(String n){
		this.name = n;
		parent = null;
	}
	
	public Node(String n, float w){
		this.name = n;
		this.weight = w;
		parent = null;
	}
	public Node(String n, int x,int y){
		this.name = n;
		this.x =x;
		this.y = y;
		parent = null;
	}
	
	public void reset(){
		this.parent = null;
		this.tested = false;
		this.expanded = false;
		this.depth = 0;
		this.cost = 0;
	}

}//end Node
