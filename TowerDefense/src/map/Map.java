package map;

/**
 * This class allows the creation and manipulation of a Map object.
 * 
 * @author Michael Hinton
 */
public class Map {

	private int width;
	private int height;
	private MapCell[][] cells;
	private String mapName;
	private Coord pathStartCoord;
	private Coord pathEndCoord;
	private Path path;

	final static private int maxWidth = 40;
	final static private int maxHeight = 40;

	public Map(String inpMapName, int inpWidth, int inpHeight) {
		if (inpWidth > maxWidth || inpHeight > maxHeight) {
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
		if (inpWidth > maxWidth) {
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
				if (c == PathCell.charID) {
					this.makePathCell(i, j);
				} else if (c == PathStartCell.charID) {
					this.makePathStartCell(i, j);
				} else if (c == PathEndCell.charID) {
					this.makePathEndCell(i, j);
				} else if (c == SceneryCell.charID) {
					// Do nothing. Default is scenery cell
				} else {
					throw new IllegalArgumentException(
							"Invalid map cell element at (" + i + "," + j
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

		this.cells = new MapCell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
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
				s = s + cells[i][j].print();
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
	 * @param row
	 *            row of the map being changed
	 * @param col
	 *            column of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makePathCell(int row, int col) {
		try {
			Coord c = new Coord(row, col);
			this.cells[row][col] = new PathCell();
			if (c.equals(pathStartCoord)) {
				pathStartCoord = null;
			}
			if (c.equals(pathEndCoord)) {
				pathEndCoord = null;
			}
			path = null;
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
	 * @param row
	 *            row of the map being changed
	 * @param col
	 *            column of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makePathStartCell(int row, int col) {
		// Check that coordinates are on the edge of the map and remove any
		// previous start path
		try {
			Coord c = new Coord(row, col);
			if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (cells[i][j] instanceof PathStartCell) {
							cells[i][j] = new SceneryCell();
						}
					}
				}
				if (c.equals(pathEndCoord)) {
					pathEndCoord = null;
				}
				this.cells[row][col] = new PathStartCell();
				this.pathStartCoord = c;
				path = null;
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
	 * @param row
	 *            row of the map being changed
	 * @param col
	 *            column of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makePathEndCell(int row, int col) {
		// Check that coordinates are on the edge of the map and remove any
		// previous end path
		try {
			Coord c = new Coord(row, col);
			if ((row == 0 || row == height - 1 || col == 0 || col == width - 1)
					&& row >= 0 && row < height && col >= 0 && col < width) {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (cells[i][j] instanceof PathEndCell) {
							cells[i][j] = new SceneryCell();
						}
					}
				}
				if (c.equals(pathStartCoord)) {
					pathStartCoord = null;
				}
				this.cells[row][col] = new PathEndCell();
				this.pathEndCoord = c;
				path = null;
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
	 * @param row
	 *            row of the map being changed
	 * @param col
	 *            column of the map being changed
	 * @return true if valid change and change was made
	 */
	public boolean makeSceneryCell(int row, int col) {
		try {
			Coord c = new Coord(row, col);
			this.cells[row][col] = new SceneryCell();
			if (c.equals(pathStartCoord)) {
				pathStartCoord = null;
			}
			if (c.equals(pathEndCoord)) {
				pathEndCoord = null;
			}
			path = null;
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
		Coord prev = null;
		Coord curr = pathStartCoord;
		Coord next = null;
		Coord[] check = new Coord[4];
		Boolean flag = true;
		int numNewPathTouching;
		// Attempt to follow path one element at a time and add the path
		// coordinates to the path object along the way.
		while (flag) {
			numNewPathTouching = 0;
			next = null;
			// Path connections can only be up, down, left or right, not
			// diagonally.
			check[0] = new Coord(curr.row() - 1, curr.col());
			check[1] = new Coord(curr.row(), curr.col() - 1);
			check[2] = new Coord(curr.row() + 1, curr.col());
			check[3] = new Coord(curr.row(), curr.col() + 1);
			for (int i = 0; i < 4; i++) {
				if (checkPath(check[i]) && !(check[i].equals(prev))) {
					numNewPathTouching = numNewPathTouching + 1;
					next = check[i];
				}
			}
			if (next == null && !(curr.equals(pathEndCoord))) {
				System.err.println("Path broken at " + curr.print());
				path = null;
				flag = false;
				return false;
			} else if (numNewPathTouching > 1) {
				System.err.println("Path has too many connections at "
						+ curr.print());
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
										+ curr.print());
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

	public boolean checkPath(Coord c) {

		try {
			if (this.cells[c.row()][c.col()] instanceof PathCell) {
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
}
