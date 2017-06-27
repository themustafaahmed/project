
import java.util.ArrayList;


public class MySearch {
	private int a[][];
	private int[] len, p;
	private int[][] logLen, logP;
	private boolean[] checkedPointMin; 
	private int infinity, size = 0;
	private ArrayList<MyPoint> arrMyPoint = new ArrayList<MyPoint>();
	private ArrayList<MyLine> arrMyLine = new ArrayList<MyLine>();
	private int beginPoint = 0, endPoint = 0;
	private String path = "";

	public MySearch() {
	}

	public void input() {
		infinity = 1;
		size = arrMyPoint.size();
		a = new int[size][size];
		len = new int[size];
		p = new int[size];
		checkedPointMin = new boolean[size];

		for (int i = 1; i < arrMyLine.size(); i++) {
			a[arrMyLine.get(i).getIndexPointA()][arrMyLine.get(i)
					.getIndexPointB()] = arrMyLine.get(i).getCost();

			infinity += arrMyLine.get(i).getCost();//draw red line
		}
	}
	
	
//za da ne vzeme 0
	public void processInput() {
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (a[i][j] == 0 && i != j) {
					a[i][j] = infinity;
				}
			}
		}
	}

	private void initValue() {
		logLen = new int[size][size];
		logP = new int[size][size];
		for (int i = 1; i < size; i++) {
			len[i] = infinity;
			checkedPointMin[i] = false;
			p[i] = 0;
		}
		logLen[0] = len;
		logP[0] = p;
		len[beginPoint] = 0;
	}

	public int dijkstra() {
		initValue();
		int i = 1, k = 0;
	
		while (checkContinue(k)) {
			for (i = 1; i < size; i++)
				if (!checkedPointMin[i] && len[i] < infinity)
					break;
			if (i >= size)
				break;
			for (int j = 1; j < size; j++)
				if (!checkedPointMin[j] && len[i] > len[j])
					i = j;

			checkedPointMin[i] = true;
			for (int j = 1; j < size; j++) {
				if (!checkedPointMin[j] && len[i] + a[i][j] < len[j]) {
					len[j] = len[i] + a[i][j];
					p[j] = i;

				}
				logLen[k][j] = len[j];
				logP[k][j] = p[j];
			}
			k++;
		}
		return len[endPoint];
	}


	private boolean checkContinue(int k) {
		if (endPoint != -1) {
			return (!checkedPointMin[endPoint]);
		}
		return (k < arrMyPoint.size() - 1);
	}

	public String tracePath() {
		path = "";
		if (endPoint > 0 && len[endPoint] < infinity) {
			int i = endPoint;
			while (i != beginPoint) {
				path = " --> " + i + path;
				i = p[i];
			}
			path = "The cost from " + beginPoint + " to " + endPoint + " is "
					+ len[endPoint] + "\t" + "Path : " + i + path;
		} else if (endPoint == -1) {
			for (int i = arrMyPoint.size() - 1; i >= 1; i--) {
				int j = i;
				if (len[i] < infinity) {
					while (j != beginPoint) {
						path = " --> " + j + path;
						j = p[j];
					}
					path = "The cost from " + beginPoint + " to " + i + " is "
							+ len[i] + "\t" + "Path : " + j + path;

				} else {
					path = "Can't go from " + beginPoint + " to " + i + path;
				}
				if (i > 1) {
					path = "\n" + path;
				}
			}
		} else {
			path = "Can't go from " + beginPoint + " to " + endPoint;
		}
		return path;
	}

	public int[][] getLogLen() {
		return logLen;
	}

	public void setLogLen(int[][] logLen) {
		this.logLen = logLen;
	}

	public int[][] getLogP() {
		return logP;
	}

	public void setLogP(int[][] logP) {
		this.logP = logP;
	}

	public boolean[] getCheckedPointMin() {
		return checkedPointMin;
	}

	public void setCheckedPointMin(boolean[] checkedPointMin) {
		this.checkedPointMin = checkedPointMin;
	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}

	public int[] getLen() {
		return len;
	}

	//public void setLen(int[] len) {
	//	this.len = len;
	//}

	public int[][] getA() {
	return a;
	}

	public void setA(int[][] a) {
		this.a = a;
	}

	public int getBeginPoint() {
		return beginPoint;
	}

	public void setBeginPoint(int beginPoint) {
		this.beginPoint = beginPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

	public ArrayList<MyPoint> getArrMyPoint() {
		return arrMyPoint;
	}

	public void setArrMyPoint(ArrayList<MyPoint> arrMyPoint) {
		this.arrMyPoint = arrMyPoint;
	}

	public ArrayList<MyLine> getArrMyLine() {
		return arrMyLine;
	}

	public void setArrMyLine(ArrayList<MyLine> arrMyLine) {
		this.arrMyLine = arrMyLine;
	}
}
