package map;

/**
 * This class allows the creation of simple 2D coordinate objects.
 * 
 * @author Michael Hinton
 */
public class Coord {

	private int row;
	private int col;

	public Coord(int inpRow, int inpCol) {
		this.row = inpRow;
		this.col = inpCol;
	}

	public int row() {
		return this.row;
	}

	public int col() {
		return this.col;
	}

	public boolean equals(Coord c) {
		if (c == null) {
			return false;
		}
		if (c.row() == this.row && c.col() == this.col) {
			return true;
		} else {
			return false;
		}
	}

	public String print() {
		return "(row = " + row + ", col = " + col + ")";
	}
}
