import java.util.HashMap;

public class Graph {
	
	private HashMap<String, Node> map = new HashMap<String,Node>();
	
	public void addNode(Node node){
		map.put(node.name, node);
	}
	
	public void oneWayLink(String start, String end){
		if(map.containsKey(start) && map.containsKey(end)){
			map.get(start).links.add(map.get(end));
		}else{
			System.out.println("Missing nodes");
		}
	}
	public void oneWayLink(String start, String end,double l ){
		if(map.containsKey(start) && map.containsKey(end)){
			Link link = new Link(map.get(end), l);
			map.get(start).listLinks.add(link);
		}else{
			System.out.println("Missing nodes");
		}
	}
	
	public void twoWayLink(String start, String end){
		oneWayLink(start,end);
		oneWayLink(end,start);
	}
	public void twoWayLink(String start, String end,double l){
		oneWayLink(start,end,l);
		oneWayLink(end,start,l);
	}
	
	public void resetAll(){
		for(Node node: map.values()){
			node.reset();
		}
	}
	
	public boolean containsNode(String name){
		return map.containsKey(name);
	}
	
	public Node getNode(String name){
		return map.get(name);
	}

}
