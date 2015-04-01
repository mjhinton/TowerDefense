package map;

import java.awt.Graphics;
import java.awt.Point;

import common.Subject;

/**
 * This class allows the creation and manipulation of a Map object.
 * 
 * @author Michael Hinton
 */
public class Map extends Subject {

	private int width;
	private int height;
	private MapCell[][] cells;
	private String mapName;
	private Point pathStartCoord;
	private Point pathEndCoord;
	private Path path;

	final static private int MAX_WIDTH = 40;
	final static private int MAX_HEIGHT = 40;
	final public static int CELL_PIXEL_SIZE = 20;

	public Map(String inpMapName, int inpWidth, int inpHeight) {
		if (inpWidth > MAX_WIDTH || inpHeight > MAX_HEIGHT) {
			throw new IllegalArgumentException("Size too big.");
		}
		this.width = inpWidth;
		this.height = inpHeight;
		this.mapName = inpMapName;

		this.init();
	}

	public Map(String inpMapName, int inpWidth, String[] inpMap) {
		// Gives ability to create map from String array
		// If start and / or end path given, assumes only one of each is given
		if (inpWidth > MAX_WIDTH) {
			throw new IllegalArgumentException("Size too big.");
		}
		this.width = inpWidth;
		this.height = inpMap.length;
		this.mapName = inpMapName;

		this.init();

		String line = null;
		char c;
		for (int i = 0; i < this.height; i++) {
			line = inpMap[i];
			if (line.length() != width) {
				throw new IllegalArgumentException("Invalid width on line " + i
						+ ".");
			}
			for (int j = 0; j < this.width; j++) {
				c = line.charAt(j);
				if (c == PathCell.CHAR_ID) {
					this.makePathCell(j, i);
				} else if (c == PathStartCell.CHAR_ID) {
					this.makePathStartCell(j, i);
				} else if (c == PathEndCell.CHAR_ID) {
					this.makePathEndCell(j, i);
				} else if (c == SceneryCell.CHAR_ID) {
					// Do nothing. Default is scenery cell
				} else {
					throw new IllegalArgumentException(
							"Invalid map cell element at (" + j + "," + i
									+ ").");
				}
			}
		}
	}

	public Map(String inpMapName) {
		this.width = 40;
		this.height = 40;
		this.mapName = inpMapName;

		this.init();
	}

	public Map() {
		this.width = 40;
		this.height = 40;
		int randNum = 1000 + (int) (Math.random() * 999);
		this.mapName = "map" + randNum;

		this.init();
	}

	/**
	 * Initializes the cells in the map to scenery cell objects.
	 */
	private void init() {
		this.pathStartCoord = null;
		this.pathEndCoord = null;
		this.path = null;

		this.cells = new MapCell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells[i][j] = new SceneryCell();
			}
		}
	}

	public String getName() {
		return this.mapName;
	}

	public void changeName(String inpName) {
		this.mapName = inpName;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public String print() {
		String s = "";
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				s = s + cells[j][i].print();
			}
			if (i < this.height - 1) {
				s = s + "\n";
			}
		}
		return s;
	}

	/**
	 * Changes the given cell to a path cell.
	 * 
	 * @param x
	 *            column of the map being changed
	 * @param y
	 *            row of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makePathCell(int x, int y) {
		try {
			Point c = new Point(x, y);
			this.cells[x][y] = new PathCell();
			if (c.equals(pathStartCoord)) {
				pathStartCoord = null;
			}
			if (c.equals(pathEndCoord)) {
				pathEndCoord = null;
			}
			path = null;
			this.notifyObservers();
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Changes the given cell to a start path cell. Also erases any previous
	 * start path cells.
	 * 
	 * @param x
	 *            column of the map being changed
	 * @param y
	 *            row of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makePathStartCell(int x, int y) {
		// Check that coordinates are on the edge of the map and remove any
		// previous start path
		try {
			Point c = new Point(x, y);
			if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (cells[i][j] instanceof PathStartCell) {
							cells[i][j] = new SceneryCell();
						}
					}
				}
				if (c.equals(pathEndCoord)) {
					pathEndCoord = null;
				}
				this.cells[x][y] = new PathStartCell();
				this.pathStartCoord = c;
				path = null;
				this.notifyObservers();
				return true;
			} else {
				System.err
						.println("Starting Path Cell must be placed on the edge of the map.");
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return false;
		}

	}

	/**
	 * Changes the given cell to a end path cell. Also erases any previous end
	 * path cells.
	 * 
	 * @param x
	 *            column of the map being changed
	 * @param y
	 *            row of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makePathEndCell(int x, int y) {
		// Check that coordinates are on the edge of the map and remove any
		// previous end path
		try {
			Point c = new Point(x, y);
			if ((y == 0 || y == height - 1 || x == 0 || x == width - 1)
					&& y >= 0 && y < height && x >= 0 && x < width) {
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (cells[i][j] instanceof PathEndCell) {
							cells[i][j] = new SceneryCell();
						}
					}
				}
				if (c.equals(pathStartCoord)) {
					pathStartCoord = null;
				}
				this.cells[x][y] = new PathEndCell();
				this.pathEndCoord = c;
				path = null;
				this.notifyObservers();
				return true;
			} else {
				System.err
						.println("Ending Path Cell must be placed on the edge of the map.");
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return false;
		}

	}

	/**
	 * Changes the given cell to a scenery cell.
	 * 
	 * @param x
	 *            column of the map being changed
	 * @param y
	 *            row of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makeSceneryCell(int x, int y) {
		try {
			Point c = new Point(x, y);
			this.cells[x][y] = new SceneryCell();
			if (c.equals(pathStartCoord)) {
				pathStartCoord = null;
			}
			if (c.equals(pathEndCoord)) {
				pathEndCoord = null;
			}
			path = null;
			this.notifyObservers();
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Attempts to build the path. Verifies if path is valid.
	 * 
	 * @return true if valid path and path is initialized
	 */
	public boolean initPath() {
		if (pathStartCoord == null) {
			System.err
					.println("Cannot initiate path because start cell does not exist.");
			return false;
		}
		if (pathEndCoord == null) {
			System.err
					.println("Cannot initiate path because end cell does not exist.");
			return false;
		}
		path = new Path();
		Point prev = null;
		Point curr = pathStartCoord;
		Point next = null;
		Point[] check = new Point[4];
		Boolean flag = true;
		int numNewPathTouching;
		// Attempt to follow path one element at a time and add the path
		// coordinates to the path object along the way.
		while (flag) {
			numNewPathTouching = 0;
			next = null;
			// Path connections can only be up, down, left or right, not
			// diagonally.
			check[0] = new Point(curr.x - 1, curr.y);
			check[1] = new Point(curr.x, curr.y - 1);
			check[2] = new Point(curr.x + 1, curr.y);
			check[3] = new Point(curr.x, curr.y + 1);
			for (int i = 0; i < 4; i++) {
				if (checkPath(check[i]) && !(check[i].equals(prev))) {
					numNewPathTouching = numNewPathTouching + 1;
					next = check[i];
				}
			}
			if (next == null && !(curr.equals(pathEndCoord))) {
				System.err.println("Path broken at " + curr.toString());
				path = null;
				flag = false;
				return false;
			} else if (numNewPathTouching > 1) {
				System.err.println("Path has too many connections at "
						+ curr.toString());
				path = null;
				flag = false;
				return false;
			} else {
				if (curr.equals(pathEndCoord)) {
					if (next == null) {
						path.setEndCoord(curr);
						flag = false;
						return true;
					} else {
						System.err
								.println("Path end has too many connections at "
										+ curr.toString());
						path = null;
						flag = false;
						return false;
					}
				} else {
					path.addCoord(curr);
					prev = curr;
					curr = next;
				}
			}
		}
		return false;
	}

	/**
	 * Verifies that the cell at the coordinate is a path cell.
	 * 
	 * @param c
	 *            coordinate of the cell to be checked
	 * @return true if cell is a Path
	 */
	public boolean checkPath(Point c) {

		try {
			if (this.cells[c.x][c.y] instanceof PathCell) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	public Path getPath() {
		return path;
	}

	public MapCell getCell(Point c) {
		try {
			return cells[c.x][c.y];
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return null;
		}
	}

	public void paintMap(Graphics g) {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				g.drawImage(cells[i][j].getImage(), j * CELL_PIXEL_SIZE, i
						* CELL_PIXEL_SIZE, null);
			}
		}

	}
}
