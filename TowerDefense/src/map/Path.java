package map;

import java.awt.Point;

/**
 * This class allows the creation and manipulation of a Path object.
 * 
 * @author Michael Hinton
 */
public class Path {

	private Point[] path;
	private int currInd;
	private int endInd;
	private static int length;

	public Path() {
		path = new Point[1000];
		currInd = 0;
		length = 0;
	}

	public void addCoord(Point c) {
		path[currInd] = c;
		currInd = currInd + 1;
	}

	public Point getCoord(int i) {
		return path[i];
	}

	public void setEndCoord(Point c) {
		path[currInd] = c;
		endInd = currInd;
		length = currInd + 1;
	}

	public Point getEndCoord() {
		return path[endInd];
	}

	public int length() {
		return length;
	}

	public String print() {
		String s = "";
		for (int i = 0; i < length; i++) {
			s = s + "Path Cell " + i + ": " + "\t" + path[i].toString();
			if (i < length - 1) {
				s = s + "\n";
			}
		}
		return s;
	}
}
