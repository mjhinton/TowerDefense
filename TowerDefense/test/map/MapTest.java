package map;

import static org.junit.Assert.*;

<<<<<<< HEAD
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakePathCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakePathStartCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakePathEndCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeSceneryCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testToggle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOffMapExit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveMap() {
		fail("Not yet implemented");
	}
=======
import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

	private Map testMap1, testMap2, testMap3, testMap4;
	
	@Before
	public void setUp() throws Exception {
	testMap1 = Map.getPackagedMap("15x15map");
	testMap2 = new Map();
	testMap3 = Map.getPackagedMap("15x15_test2_complex_map");
	testMap4 = Map.getPackagedMap("15x15_test1_simple_map");
	}

	@After
	public void tearDown() throws Exception {
	testMap1 = null;
	testMap2 = null;
	testMap3 = null;
	testMap4 = null;
	}

	@Test
	public void testMakePathCell() {
	//test valid selection (scenery cell)
	assertTrue(testMap2.makePathCell(5, 5));
	//test invalid selection
	assertFalse(testMap2.makePathCell(30,4));
	//test that the cell is an instance of PathCell
	assertTrue(testMap2.getCell(new Point (5,5)) instanceof PathCell);
	//test that a different cell is an instance of SceneryCell;
	assertTrue(testMap2.getCell(new Point (6,5)) instanceof SceneryCell);
	//test that 
	
	}

	@Test
	public void testMakePathStartCell() {
	//test valid selection when it is already a path start cell
	assertTrue(testMap1.makePathStartCell(1,0));
	//test valid selection when it is a scenery cell
	assertTrue(testMap1.makePathStartCell(14, 0));
	//test valid selection when it is a pathEndCoord
	assertTrue(testMap1.makePathStartCell(11,14));
	//test invalid selection
	assertFalse(testMap1.makePathStartCell(5, 5));
	//test selection outside of map
	assertFalse(testMap1.makePathStartCell(40,40));
	//test that (11,14) is the start cell now
	assertTrue(testMap1.getCell(new Point(11,14)) instanceof PathStartCell);	
	//test that the previous ones are no longer path start cells
	assertFalse(testMap1.getCell(new Point(1,0)) instanceof PathStartCell);	
	//test that path start coord has been set
	assertTrue(testMap1.getPathStartCoord().getX()==11 &&testMap1.getPathStartCoord().getY()==14);
	//and that path has been set to null;
	assertTrue(testMap1.getPath() == null);
	}
	
	@Test
	public void testMakePathEndCell() {
	//test valid selection when it is already a path End cell
	assertTrue(testMap1.makePathEndCell(11,14));
	//test valid selection when it is a scenery cell
	assertTrue(testMap1.makePathEndCell(14, 0));
	//test valid selection when it is a pathStartCoord
	assertTrue(testMap1.makePathEndCell(1,0));
	//test invalid selection
	assertFalse(testMap1.makePathEndCell(5, 5));
	//test selection outside of map
	assertFalse(testMap1.makePathEndCell(40,40));
	//test that (1,0) is the end cell now
	assertTrue(testMap1.getCell(new Point(1,0)) instanceof PathEndCell);	
	//test that the previous ones are no longer path start cells
	assertFalse(testMap1.getCell(new Point(14,0)) instanceof PathEndCell);
	//test that path end coord has been set
	assertTrue(testMap1.getPathEndCoord().getX()==1 &&testMap1.getPathEndCoord().getY()==0);
	//test that path has been set to null
	assertTrue(testMap1.getPath() == null);
	}

	@Test
	public void testMakeSceneryCell() {
	//test valid selections, already scenery cell
	assertTrue(testMap1.makeSceneryCell(5, 5));
	//test valid selection, path cell
	assertTrue(testMap1.makeSceneryCell(4,1));
		//path start cell
	assertTrue(testMap1.makeSceneryCell(1,0));
		//test that start coord is set to null
	assertTrue(testMap1.getPathStartCoord()==null);
		//path end cell
	assertTrue(testMap1.makeSceneryCell(11,14));
		//test that end coord is set to null
	assertTrue(testMap1.getPathEndCoord()==null);
	//test invalid selection
	assertFalse(testMap1.makePathCell(30,4));
	//test that the cell is an instance of SceneryCell
	assertTrue(testMap1.getCell(new Point (5,5)) instanceof SceneryCell);
	//test that a different cell is an instance of PathCell;
	assertTrue(testMap1.getCell(new Point (1,3)) instanceof PathCell);
	//test that the path has been set to null
	assertTrue(testMap1.getPath() ==null);
	
	}

	@Test
	public void testInitPath() {
	//fail, path, but no start or end cell.
	for (int i=0; i<testMap2.getWidth()-1; i++){
		testMap2.makePathCell(i, 5);
	}
	//fail, no start cell
	testMap2.makePathEndCell(14, 5);
	assertFalse(testMap2.initPath());
	//fail, no end cell
	testMap2.makePathStartCell(14,5);
	assertFalse(testMap2.initPath());
	//fail test broken path
	testMap1.makeSceneryCell(9,4);
	assertFalse(testMap1.initPath());
	//fail test diagonally connected path
	testMap1.makePathCell(9,4);
	testMap1.makeSceneryCell(9,6);
	assertFalse(testMap1.initPath());
	//fail test too many connections path
	testMap1.makePathCell(9,6);
	testMap1.makePathCell(4,10);
	assertFalse(testMap1.initPath());
	//test complex but intializable path
	testMap3.initPath();
	
	}

	@Test
	public void testCheckPath() {
	//check that it is a path
	assertTrue(testMap1.checkPath(new Point(9,4)));
	//fail it is not a path
	assertFalse(testMap1.checkPath(new Point(0,0)));
	}

	@Test
	public void testToggle() {
	//check toggle from scenery to path
	Point c = new Point(0,0);
	testMap1.toggle(0,0);
	assertTrue(testMap1.getCell(c) instanceof PathCell);
	//check toggle from path to path end cell
	testMap1.toggle(0, 0);
	assertTrue(testMap1.getCell(c) instanceof PathEndCell);
	//check toggle from path end cell to path start cell
	testMap1.toggle(0, 0);
	assertTrue(testMap1.getCell(c) instanceof PathStartCell);
	//check toggle from path start cell to scenery cell
	testMap1.toggle(0,0);
	assertTrue(testMap1.getCell(c) instanceof SceneryCell);
	//check toggle from path cell to scenery cell
	c.setLocation(1, 1);
	testMap1.toggle(1, 1);
	assertTrue(testMap1.getCell(c) instanceof SceneryCell);
	
	}

	@Test
	public void testGetOffMapExit() {
	//test that it returns a null p when path is null
	assertEquals("No path, should return null", null, testMap2.getOffMapExit());
	//test that it returns the correct point
	assertEquals("End coordinate y pos = 14, point p should be set to x, 15", new Point(11,15) , testMap1.getOffMapExit());
	
	}
	
	@Test
	public void testPrint(){
	
	for (int i=0; i<testMap2.getWidth()-1; i++){
			testMap2.makePathCell(i, 5);
	}	
	testMap2.makePathStartCell(0,5);
	testMap2.makePathEndCell(14,5);
		
	assertEquals("two equivalent maps should print the same thing", testMap4.print(), testMap2.print());
	assertNotEquals("two different maps should not print the same thing", testMap1.print(), testMap2.print());
	}

>>>>>>> branch 'master' of https://github.com/mjhinton/TowerDefense

}
