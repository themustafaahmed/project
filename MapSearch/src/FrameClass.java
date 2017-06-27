import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
;

public class FrameClass extends JFrame {	
	
	public static void init(Graph g){
		g.addNode(new Node("A",1,0));
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
		new FrameClass("Graph");
	}

	
	JTabbedPane jt=new JTabbedPane();
	//JTable table = new JTable();
	
	public JTextArea textLog;
	JScrollPane myScroll = new JScrollPane(textLog);
	
	JPanel panel=new JPanel();
	
	JPanel topPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	JLabel Tolab = new JLabel("To");
	JLabel Fromlab = new JLabel("From");
	
	JTextField TextFieldTo = new JTextField(20);
	JTextField TextFieldFrom = new JTextField(20);

	JButton SearchButton=new JButton("Search");


	public FrameClass(String title) {
		setTitle(title);
		setLayout(new BorderLayout(5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setSize(500, 500);	

		add(LogPanel(), BorderLayout.PAGE_END);
		
		jt.setBounds(new Rectangle(0,0,500,500));
		jt.add(panel, "Graph");
		this.add(jt);
		
		panel.add(topPanel);
		panel.add(midPanel);
		panel.add(bottomPanel);
				
		topPanel.setLayout(new GridLayout(5,2));
			
		topPanel.add(Tolab);
		topPanel.add(TextFieldTo);
		topPanel.add(Fromlab);
		topPanel.add(TextFieldFrom);

		midPanel.setSize(500,60);		
		midPanel.add(SearchButton);

		SearchButton.addActionListener(new Search());
		bottomPanel.setLayout(new GridLayout(4, 1));
		
		myScroll.setSize(50, 50);
		bottomPanel.add(myScroll);
		
	}
	
	class Search implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Graph g = new Graph();
				SearchShortestPath logpath = new SearchShortestPath(g);
				init(g);
				//search(new BreadthSearch(g),TextFieldTo.getText(),TextFieldFrom.getText());
				//search(new GreedyByCoordinates(g),"E","F");
			//	search(new SearchShortestPath(g),"F","C");
				search(new SearchShortestPath(g),TextFieldTo.getText(),TextFieldFrom.getText());
				textLog.setText(logpath.Log(""));
				}
			}
	
	private JPanel LogPanel() {
		textLog = new JTextArea("Path: ");
		textLog.setRows(5);
		JScrollPane scrollPath = new JScrollPane(textLog);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Log"));
		panel.add(scrollPath, BorderLayout.PAGE_START);
		return panel;
		}
		
}