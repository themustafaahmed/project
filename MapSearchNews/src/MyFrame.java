import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class MyFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	// frame
	private JComboBox<String> cbbBeginPoint = new JComboBox<String>();
	private JComboBox<String> cbbEndPoint = new JComboBox<String>();

	private JButton btnRunAll;

	// draw
	private JPanel drawPanel = new JPanel();
	private JButton btnPoint, btnLine, btnUpdate, btnMove,
			btnNew;
	// graph
	private MyDraw myDraw = new MyDraw();

	// log
	private JTextArea textLog;

	private int indexBeginPoint = 0, indexEndPoint = 0;


	int WIDTH_SELECT, HEIGHT_SELECT;

	MySearch dijkstra = new MySearch();

	public MyFrame(String title) {
		setTitle(title);
		setLayout(new BorderLayout(5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add content
		add(creatSelectPanel(), BorderLayout.WEST);
		add(creatPaintPanel(), BorderLayout.CENTER);
		add(creatLogPanel(), BorderLayout.PAGE_END);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private JPanel creatSelectPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelTop = new JPanel(new GridLayout(4, 1, 5, 5));
		JPanel panelBottom = new JPanel(new BorderLayout());

		JPanel panelSelectPointTemp = new JPanel(new GridLayout(1, 2, 15, 5));
		panelSelectPointTemp.setBorder(new EmptyBorder(0, 15, 0, 5));
		panelSelectPointTemp.add(cbbBeginPoint = createComboxBox("Begin"));
		panelSelectPointTemp.add(cbbEndPoint = createComboxBox("End"));
		JPanel panelSelectPoint = new JPanel(new BorderLayout());
		panelSelectPoint.setBorder(new TitledBorder("Point"));
		panelSelectPoint.add(panelSelectPointTemp);

		JPanel panelRunTemp = new JPanel(new GridLayout(1, 2, 15, 5));
		panelRunTemp.setBorder(new EmptyBorder(0, 15, 0, 5));
		panelRunTemp.add(btnRunAll = createButton("Run"));
		JPanel panelRun = new JPanel(new BorderLayout());
		panelRun.setBorder(new TitledBorder("Run"));
		panelRun.add(panelRunTemp);
		panelTop.add(panelSelectPoint);
		panelTop.add(panelRun);

		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(panelTop.getPreferredSize());
		panelBottom.add(scroll);

		panel.add(panelTop, BorderLayout.PAGE_START);
		panel.add(panelBottom, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(0, 5, 0, 0));
		WIDTH_SELECT = (int) panel.getPreferredSize().getWidth();
		HEIGHT_SELECT = (int) panel.getPreferredSize().getHeight();
		return panel;
	}

	private JPanel creatPaintPanel() {
		drawPanel.setLayout(new BoxLayout(drawPanel, BoxLayout.Y_AXIS));
		drawPanel.setBorder(new TitledBorder(""));
		drawPanel.setBackground(null);
		drawPanel.setVisible(true);
		Icon icon;
		String link = "/icon/";

		icon = getIcon(link + "iconOk.png");
		drawPanel.add(btnUpdate = createButtonImage(icon, "Update Graph"));

		icon = getIcon(link + "iconPoint.png");
		drawPanel.add(btnPoint = createButtonImage(icon, "Draw Point"));

		icon = getIcon(link + "iconLine.png");
		drawPanel.add(btnLine = createButtonImage(icon, "Draw line"));

		icon = getIcon(link + "iconMove.png");
		drawPanel.add(btnMove = createButtonImage(icon, "Move Point"));

		icon = getIcon(link + "iconNew.png");
		drawPanel.add(btnNew = createButtonImage(icon, "New graph"));	
	
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(drawPanel, BorderLayout.WEST);
		panel.add(myDraw, BorderLayout.CENTER);
		return panel;
	}

	private ImageIcon getIcon(String link) {
		return new ImageIcon(getClass().getResource(link));
	}

	private JPanel creatLogPanel() {
		textLog = new JTextArea("Path: ");
		textLog.setRows(3);
		textLog.setEditable(false);
		JScrollPane scrollPath = new JScrollPane(textLog);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Log"));
		panel.add(scrollPath, BorderLayout.PAGE_START);
		panel.setPreferredSize(new Dimension(WIDTH_SELECT * 7 / 2,
				HEIGHT_SELECT / 5));
		return panel;
	}

	// create button and add to panel
	private JButton createButton(String lable) {
		JButton btn = new JButton(lable);
		btn.addActionListener(this);
		return btn;
	}

	// create buttonImage and add to panel
	private JButton createButtonImage(Icon icon, String toolTip) {
		JButton btn = new JButton(icon);
		btn.setMargin(new Insets(0, 0, 0, 0));
		btn.addActionListener(this);
		btn.setToolTipText(toolTip);
		return btn;
	}

	// create comboBox and add to panel
	private JComboBox<String> createComboxBox(String title) {
		String list[] = { title };
		JComboBox<String> cbb = new JComboBox<String>(list);
		cbb.addActionListener(this);
		cbb.setEditable(false);
		cbb.setMaximumRowCount(5);
		return cbb;
	}

	//  Action //
	private void actionUpdate() {
		updateListPoint();
		resetDataDijkstra();
	}

	private void actionDrawPoint() {
		myDraw.setDraw(1);
	}

	private void actionDrawLine() {
		myDraw.setDraw(2);
	}

	private void actionNew() {
		myDraw.setResetGraph(true);
		myDraw.repaint();
		myDraw.init();
		updateListPoint();
		clearLog();
	}

	private void actionChoosePoint() {
		resetDataDijkstra();
		clearLog();
	}

	private void updateListPoint() {
		int size = myDraw.getData().getArrMyPoint().size();
		String listPoint[] = new String[size];
		listPoint[0] = "Begin";
		for (int i = 1; i < listPoint.length; i++) {
			listPoint[i] = String.valueOf(i);
		}

		cbbBeginPoint.setModel(new DefaultComboBoxModel<String>(listPoint));
		cbbBeginPoint.setMaximumRowCount(5);

		if (size > 1) {
			listPoint = new String[size + 1];
			listPoint[0] = "End";
			for (int i = 1; i < listPoint.length; i++) {
				listPoint[i] = String.valueOf(i);
			}
		listPoint[listPoint.length - 1]="All";
		} else {
			listPoint = new String[1];
			listPoint[0] = "End";
		}

		cbbEndPoint.setModel(new DefaultComboBoxModel<String>(listPoint));
		cbbEndPoint.setMaximumRowCount(5);
	}

	private void resetDataDijkstra() {
		dijkstra = new MySearch();
		dijkstra.setArrMyPoint(myDraw.getData().getArrMyPoint());
		dijkstra.setArrMyLine(myDraw.getData().getArrMyLine());
		dijkstra.input();
		dijkstra.processInput();
	}

	private void clearLog() {
		clearPath();
	}

	private void clearPath() {
		textLog.setText("Path : ");
	}

	private boolean checkRun() {
		int size = myDraw.getData().getArrMyPoint().size() ;//- 1
		indexBeginPoint = cbbBeginPoint.getSelectedIndex();
		indexEndPoint = cbbEndPoint.getSelectedIndex();
		if (size < 1 || indexBeginPoint == 0 || indexEndPoint == 0) {
			JOptionPane.showMessageDialog(null,
					"Error chose points or don't Update graph to chose points",
					"Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private void setBeginEndPoint() {
		myDraw.setIndexBeginPoint(indexBeginPoint);
		myDraw.setIndexEndPoint(indexEndPoint);
		dijkstra.setBeginPoint(indexBeginPoint);
		dijkstra.setEndPoint(indexEndPoint);
	}

	private void runAll() {
		if (checkRun()) {
			resetDataDijkstra();
			setBeginEndPoint();
			dijkstra.dijkstra();
			textLog.setText(dijkstra.tracePath());
			myDraw.setDrawResult(true);
			myDraw.setA(dijkstra.getA());
			myDraw.setP(dijkstra.getP());	
			myDraw.setLen(dijkstra.getLen());
			myDraw.setCheckedPointMin(dijkstra.getCheckedPointMin());
			myDraw.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// select button in paint
		if (e.getSource() == btnUpdate) {
			actionUpdate();
		}

		if (e.getSource() == btnPoint) {
			actionDrawPoint();
		}

		if (e.getSource() == btnLine) {
			actionDrawLine();
		}
		if (e.getSource() == btnMove) {
			myDraw.setDraw(3);
		}

		if (e.getSource() == btnNew) {
			actionNew();
		}
		
		// select point
		if (e.getSource() == cbbBeginPoint || e.getSource() == cbbEndPoint) {
			actionChoosePoint();
		}
		// select run
		if (e.getSource() == btnRunAll) {
			runAll();
		}
		
	}

}