package assign5;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SortUtilTester {

	@Test
	public void testGenerateAscending() {
		//test normal functionality
		ArrayList<Integer> testList = SortUtil.generateSortedOrder(10);
		assertEquals(10, testList.size());
		assertEquals(1, testList.get(0), 0);
		assertEquals(10, testList.get(9), 0);
		
		//test bounds case
		ArrayList<Integer> testEmpty = SortUtil.generateSortedOrder(0);
		assertEquals(0, testEmpty.size());
	}

	@Test
	public void testGenerateDescending() {
		//Test normal functionality
		ArrayList<Integer> testList = SortUtil.generateReverseSortedOrder(10);
		assertEquals(10, testList.size());
		assertEquals(10, testList.get(0), 0);
		assertEquals(1, testList.get(9), 0);
		
		//Test bounds case
		ArrayList<Integer> testEmpty = SortUtil.generateReverseSortedOrder(0);
		assertEquals(0, testEmpty.size());
	}

	@Test
	public void testGeneratePermuted(){
		//Test normal functionality
		ArrayList<Integer> testList = SortUtil.generatePermutedOrder(5);
		assertEquals(5, testList.size());
		assertTrue(testList.contains(1));
		assertTrue(testList.contains(5));
		
		//Test bounds case
		ArrayList<Integer> testEmpty = SortUtil.generatePermutedOrder(0);
		assertEquals(0, testEmpty.size());
	}
		
	@Test
	public void testQuickSort(){
		
		//Test an already sorted array list
		ArrayList<Integer> testSorted = new ArrayList<Integer>(SortUtil.generateSortedOrder(20));
		SortUtil.quicksort(testSorted);
		assertEquals(1, testSorted.get(0), 0);
		assertEquals(20, testSorted.get(19), 0);
		
		//Test a reverse sorted array list
		ArrayList<Integer> testReverse = new ArrayList<Integer>(SortUtil.generateReverseSortedOrder(20));
		SortUtil.quicksort(testReverse);
		assertEquals(1, testSorted.get(0), 0);
		assertEquals(20, testSorted.get(19), 0);
		
		//Test bounds
		ArrayList<Integer> testEmpty = new ArrayList<Integer>();
		SortUtil.quicksort(testEmpty);
		assertEquals(0, testEmpty.size());
		
		ArrayList<Integer> testOne = new ArrayList<Integer>();
		testOne.add(12);
		SortUtil.quicksort(testOne);
		assertEquals(1, testOne.size());
		assertEquals(12, testOne.get(0), 0);
		
		//Ensure functionality for a list size of two
		ArrayList<Integer> testTwo = new ArrayList<Integer>();
		testTwo.add(2);
		testTwo.add(1);
		SortUtil.quicksort(testTwo);
		assertEquals(1, testTwo.get(0), 0);
		
		//Test duplicates case
		ArrayList<Integer> testDuplicates = new ArrayList<Integer>();
		testDuplicates.add(3);
		testDuplicates.add(3);
		testDuplicates.add(4);
		testDuplicates.add(1);
		SortUtil.quicksort(testDuplicates);
		assertEquals(4, testDuplicates.size());
		assertEquals(1, testDuplicates.get(0), 0);
		assertEquals(4, testDuplicates.get(3), 0);
		
		//Test medium length array list
		ArrayList<Integer> testMedium = SortUtil.generatePermutedOrder(10);
		SortUtil.quicksort(testMedium);
		assertEquals(1, testMedium.get(0), 0);
		assertEquals(10, testMedium.get(9), 0);
		
		//Test an array list of size one
		ArrayList<Integer> testSingle = new ArrayList<Integer>();
		testSingle.add(1);
		SortUtil.quicksort(testSingle);
		assertEquals(1, testSingle.size());
		assertEquals(1, testSingle.get(0), 0);
	}
	
	
	@Test
	public void testMergeSort(){
		
		//test bounds
		ArrayList<Integer> testEmpty = new ArrayList<Integer>();
		SortUtil.mergesort(testEmpty);
		assertEquals(0, testEmpty.size());
		
		ArrayList<Integer> testOne = new ArrayList<Integer>();
		testOne.add(12);
		SortUtil.mergesort(testOne);
		assertEquals(1, testOne.size());
		assertEquals(12, testOne.get(0), 0);
		
		//Test array list of size two
		ArrayList<Integer> testTwo = new ArrayList<Integer>();
		testTwo.add(2);
		testTwo.add(1);
		SortUtil.mergesort(testTwo);
		assertEquals(1, testTwo.get(0), 0);
		
		//test duplicates
		ArrayList<Integer> testDuplicates = new ArrayList<Integer>();
		testDuplicates.add(3);
		testDuplicates.add(3);
		testDuplicates.add(4);
		testDuplicates.add(1);
		SortUtil.mergesort(testDuplicates);
		assertEquals(4, testDuplicates.size());
		assertEquals(1, testDuplicates.get(0), 0);
		assertEquals(4, testDuplicates.get(3), 0);
		
		//test a medium sized array list
		ArrayList<Integer> testMedium = SortUtil.generatePermutedOrder(10);
		SortUtil.mergesort(testMedium);
		assertEquals(1, testMedium.get(0), 0);
		assertEquals(10, testMedium.get(9), 0);
		
		//test a list of size one
		ArrayList<Integer> testSingle = new ArrayList<Integer>();
		testSingle.add(1);
		SortUtil.mergesort(testSingle);
		assertEquals(1, testSingle.size());
		assertEquals(1, testSingle.get(0), 0);
	}
	
	@Test 
	public void testQuickSortGenerics(){
		//Ensure functionality of generics
		ArrayList<Point> test = new ArrayList<Point>();
		test.add(new Point(23,23));
		test.add(new Point(231, 23));
		test.add(new Point(12, 40));
		test.add(new Point(23, 49));
		test.add(new Point(12, 1));
		SortUtil.quicksort(test);
		assertEquals(1, test.get(0).getY());
		assertEquals(49, test.get(4).getY());
		
	}
	
	@Test 
	public void testMergeSortGenerics(){
		//Ensure functionality of generics
		ArrayList<Point> test = new ArrayList<Point>();
		test.add(new Point(23,23));
		test.add(new Point(231, 23));
		test.add(new Point(12, 40));
		test.add(new Point(23, 49));
		test.add(new Point(12, 1));
		SortUtil.mergesort(test);
		assertEquals(1, test.get(0).getY());
		assertEquals(49, test.get(4).getY());
		
	}
	//Created to test generics
	class Point implements Comparable<Point>{
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getY()
		{
			return this.y;
		}
		
		//Needs to implement comparable
		@Override
		public int compareTo(Point o) {
			return this.y - o.y;
		}

		
	}

}
