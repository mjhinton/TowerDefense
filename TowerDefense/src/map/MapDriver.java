package map;

/**
 * This class tests the functionality in the map package, specifically the Map
 * class.
 * 
 * @author Michael Hinton
 */
public class MapDriver {

	public static void main(String[] args) {
		// Test different constructors to generate same 40 x 40 map with only
		// scenery elements.
		// 40 x 40 is also the default size.
		System.out
				.println("Test constructors, init(), print(), getName(), changeName().");
		System.out
				.println("Also, verify that different constructors can construct same map.");
		Map testMap1 = new Map("testMap1");
		String[] testArrayBlankMap = ReadWriteTxtFile
				.readTxtFileAsStringArray("testMaps/blankMap.txt");
		Map testMap2 = new Map("testMap2", 40, testArrayBlankMap);
		Map testMap3 = new Map("testMap3", 40, 40);
		Map testMap4 = new Map();
		// testMap4 should be given a random name since name was passed as an
		// input argument.
		// Read the random name then change it to 'testMap4'
		System.out.println("testMap4 randomly generated name: "
				+ testMap4.getName());
		testMap4.changeName("testMap4");
		System.out.println("testMap4 changed name: " + testMap4.getName());
		// Output the maps as strings for comparison
		String stringTestMap1 = testMap1.print();
		String stringTestMap2 = testMap2.print();
		String stringTestMap3 = testMap3.print();
		String stringTestMap4 = testMap4.print();
		// Now print the contents of testMap1
		System.out.println(stringTestMap1);
		// Compare the maps to testMap1 to make sure they are all the same
		if (stringTestMap1.equals(stringTestMap2))
			System.out.println("Case 1: pass");
		if (stringTestMap1.equals(stringTestMap3))
			System.out.println("Case 2: pass");
		if (stringTestMap1.equals(stringTestMap4))
			System.out.println("Case 3: pass");

		// Now work with testMap1 on new set of tests
		System.out.println();
		System.out
				.println("Test makePathCell(), makePathStartCell(), makePathEndCell(), and makeSceneryCell().");
		// Test valid placements of paths
		if (testMap1.makePathCell(1, 0))
			System.out.println("Case 4: pass");
		if (testMap1.makePathStartCell(0, 0))
			System.out.println("Case 5: pass");
		if (testMap1.makePathEndCell(2, 0))
			System.out.println("Case 6: pass");
		// Display map
		System.out.println(testMap1.print());
		// Try invalid placement of start, normal and end paths
		if (!(testMap1.makePathCell(40, 0)))
			System.out.println("Case 7: pass");
		if (!(testMap1.makePathStartCell(1, 1)))
			System.out.println("Case 8: pass");
		if (!(testMap1.makePathEndCell(25, 25)))
			System.out.println("Case 9: pass");
		// Try making start and end paths at valid new location, and verify that
		// only one of each can exist by analyzing printed map
		if (testMap1.makePathStartCell(0, 20))
			System.out.println("Case 10: pass");
		if (testMap1.makePathEndCell(15, 0))
			System.out.println("Case 11: pass");
		System.out.println(testMap1.print());
		// Overwrite paths with scenery
		if (testMap1.makeSceneryCell(15, 0) && testMap1.makeSceneryCell(0, 20)
				&& testMap1.makeSceneryCell(1, 0))
			System.out.println("Case 12: pass");
		// Map should now be blank
		System.out.println(testMap1.print());

		// Now construct valid and invalid paths and try to initialize them
		System.out.println();
		System.out.println("Test initPath(), checkPath() and getPath().");
		// Construct valid path along left side of map, print map and initialize
		// path
		testMap1.makePathStartCell(0, 0);
		for (int i = 1; i < 39; i++) {
			testMap1.makePathCell(i, 0);
		}
		testMap1.makePathEndCell(39, 0);
		System.out.println(testMap1.print());
		if (testMap1.initPath())
			System.out.println("Case 13: pass");
		// Use getPath() to verify the path isn't set to null
		if (testMap1.getPath() != null)
			System.out.println("Case 14: pass");
		// Use the print method of the Path class
		System.out.println(testMap1.getPath().print());
		// Use checkPath() to verify that one of the cells on the path is a path
		// cell
		if (testMap1.checkPath(new Coord(5, 0)))
			System.out.println("Case 15: pass");
		// Now replace that path cell with scenery and retry
		testMap1.makeSceneryCell(5, 0);
		if (!(testMap1.checkPath(new Coord(5, 0))))
			System.out.println("Case 16: pass");
		// Check that path has been set back to null
		if (testMap1.getPath() == null)
			System.out.println("Case 17: pass");
		// Print map and test that initialize path no longer works
		System.out.println(testMap1.print());
		if (!(testMap1.initPath()))
			System.out.println("Case 18: pass");

		// Test a more complex map path
		System.out.println();
		System.out.println("Validate a more complex map.");
		String[] testArrayComplexMap = ReadWriteTxtFile
				.readTxtFileAsStringArray("testMaps/complexMap.txt");
		Map testMap5 = new Map("testMap5", 40, testArrayComplexMap);
		System.out.println(testMap5.print());
		if (testMap5.initPath())
			System.out.println("Case 19: pass");

		// Test map of a different size
		System.out.println();
		System.out.println("Validate a map of a different size.");
		Map testMap6 = new Map("testMap6", 15, 3);
		testMap6.makePathStartCell(0, 5);
		testMap6.makePathCell(1, 5);
		testMap6.makePathEndCell(2, 5);
		System.out.println(testMap6.print());
		if (testMap6.initPath())
			System.out.println("Case 20: pass");

	}

}
