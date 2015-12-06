package assign5;

import java.util.ArrayList;

public class Timing {
	
	public static void main(String[] args) {
		
		// Do 10000 lookups and use the average running time.
		int timesToLoop = 500;
		
		// For each problem size n . . .
		for (int n = 2000; n <= 40000; n += 2000) {
			// Setup of n size for testing
			long startTime, midpointTime, stopTime;
			
			ArrayList<Integer> original = SortUtil.generatePermutedOrder(n);
			ArrayList<Integer> temp = new ArrayList<Integer>(original);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			// Run the methods for testing
			for (int i = 0; i < timesToLoop; i++) {
				SortUtil.quicksort(original);
				original = new ArrayList<Integer>(temp);
			}

			midpointTime = System.nanoTime();

			// Time it takes to run loop
			for (int i = 0; i < timesToLoop; i++) {
				original = new ArrayList<Integer>(temp);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(averageTime);
		}

	}

}
