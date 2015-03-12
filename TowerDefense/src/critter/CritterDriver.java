package critter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/* 
 * SOLVIE LEE
 * 260577764
 * ASSIGNMENT #1 - ECSE 321
 * PART 3: CRITTER GROUP GENERATOR
 *
 */

/*This is the main driver class that tests the functioning of the rest of the classes.*
 */

public class CritterDriver{
	
	public static void main(String[] args) throws InterruptedException{
		
		System.out.println("Let the game begin.");

		CritterWaveGenerator a = new CritterWaveGenerator();
		
		a.generateWave();
		
		}
}





