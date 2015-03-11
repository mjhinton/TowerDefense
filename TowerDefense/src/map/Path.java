package map;

/**
 * This class allows the creation and manipulation of a Path object.
 * 
 * @author Michael Hinton
 */
public class Path {

	private Coord[] path;
	private int currInd;
	private int endInd;
	private int length;

	public Path() {
		path = new Coord[1000];
		currInd = 0;
		length = 0;
	}

	public void addCoord(Coord c) {
		path[currInd] = c;
		currInd = currInd + 1;
	}

	public Coord getCoord(int i) {
		return path[i];
	}

	public void setEndCoord(Coord c) {
		path[currInd] = c;
		endInd = currInd;
		length = currInd + 1;
	}

	public Coord getEndCoord() {
		return path[endInd];
	}

	public int length() {
		return length;
	}

	public String print() {
		String s = "";
		for (int i = 0; i < length; i++) {
			s = s + "Path Cell " + i + ": " + "\t" + path[i].print();
			if (i < length - 1) {
				s = s + "\n";
			}
		}
		return s;
	}
}
