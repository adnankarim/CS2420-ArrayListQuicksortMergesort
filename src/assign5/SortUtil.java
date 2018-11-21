package assign5;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class provides a mergesort sorting algorithm that invokes insertion sort
 * once a small enough group is passed in and a quicksort algorithm. The class
 * also provides methods for generating a sorted list, a reverse sorted list,
 * and a permuted list.
 * 
 * @author Jordan Hensley, Romney Doria jHensley doria assignment 5 CS 2420 -
 *         Fall 2015 9/30/2015
 *
 */
public class SortUtil {

	// Presumes to run median pivot selection for quicksort
	private static String myPivot = "median";

	/**
	 * This driver method takes in an arrayList of comparable types and returns
	 * a sorted version of the list.
	 * 
	 * @param list
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
		// Sends the list and endpoints to the method that actually invokes the
		// mergesort
		mergesort(list, new ArrayList<T>(), 0, list.size() - 1);
	}

	/**
	 * This method is the recursive part of the mergesort that breaks down the
	 * list and invokes mergesort again on the smaller lists.
	 * 
	 * The implementation of this mergesort is from Data Structures and
	 * Algorithms Fourth Edition by Mark Weiss p.364-365. This textbook uses a
	 * standard array, but has been adapted to use an ArrayList.
	 * 
	 * @param list
	 * @param temp
	 * @param low
	 * @param high
	 */
	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list, ArrayList<T> temp, int low,
			int high) {
		// List is already sorted
		if (low >= high)
			return;

		// Invoke insertion sort on the desired size of group
		if ((high - low) <= 50) {
			insertionSort(list, low, high);
			return;
		}

		int center = (low + high) / 2;
		// Use recursion on the smaller lists
		mergesort(list, new ArrayList<T>(), low, center);
		mergesort(list, new ArrayList<T>(), center + 1, high);
		merge(list, new ArrayList<T>(), low, center, high);
	}

	/**
	 * This method sorts the broken down list into a temporary array and reads
	 * the sorted section of the list into the original.
	 * 
	 * @param list
	 * @param temp
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> temp, int low, int mid,
			int high) {

		int startToMid = low;
		int midToEnd = mid + 1;

		while (startToMid <= mid && midToEnd <= high) {
			// Sort the elements while adding them to the temporary arrayList
			if (list.get(startToMid).compareTo(list.get(midToEnd)) >= 0)
				temp.add(list.get(midToEnd++));
			else
				temp.add(list.get(startToMid++));
		}

		while (startToMid <= mid)
			temp.add(list.get(startToMid++));

		while (midToEnd <= high)
			temp.add(list.get(midToEnd++));

		// Add the elements back to the original list
		for (int i = 0; i < temp.size(); i++)
			list.set(low + i, temp.get(i));

	}

	/**
	 * This driver method takes in an arrayList and sorts its elements using a
	 * quicksort algorithm
	 * 
	 * @param list
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {

		// List is already sorted if empty or has only one element.
		if (list.isEmpty() || list.size() == 1)
			return;
		// Deal with a list if it is only of length 2
		else if (list.size() == 2) {
			if (list.get(0).compareTo(list.get(1)) > 0)
				swap(list, 0, 1);
			return;
		} else
			// Otherwise invoke the quicksort algorithm
			quicksort(list, 0, list.size() - 1, myPivot);
	}

	/**
	 * This method takes in the original list and the end points of the sublist
	 * to be sorted. The method selects a pivot and sorts the elements of the
	 * sublist based on the pivot and then recurses down the list leaving the
	 * list sorted.
	 * 
	 * The implementation of this code was referenced from Nataraja Gootooru's
	 * web page java2novice.com. The original code was meant to deal with
	 * array's, but this has been altered for array's lists.
	 * 
	 * @param list
	 * @param low
	 * @param high
	 * @param s
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list, int low, int high, String s) {

		// Pointer variables
		int i = low;
		int j = high;

		// Get the correct pivot
		T pivot = list.get(pivotSelect(s, list, low, high));

		while (i <= j) {
			// Find elements to be swapped
			while (list.get(i).compareTo(pivot) < 0)
				i++;
			while (list.get(j).compareTo(pivot) > 0)
				j--;
			if (i <= j) {
				swap(list, i, j);
				i++;
				j--;
			}
		}
		// Call method again on unsorted portions of array
		if (low < j)
			quicksort(list, low, j, s);
		if (i < high)
			quicksort(list, i, high, s);

	}

	/**
	 * This method returns an index for a desired pivot either "first",
	 * "random", or "median". If neither of these are input the program will
	 * return a -1 which will invoke an IndexOutOfBounds exception.
	 * 
	 * @param s
	 * @param list
	 * @param low
	 * @param high
	 * @return
	 */
	private static <T extends Comparable<? super T>> int pivotSelect(String s, ArrayList<T> list, int low, int high) {
		// Initialize pivot index
		int pivot = -1;
		switch (s) {

		case "first": {
			pivot = low;
			break;
		}
		case "random": {
			Random rng = new Random();
			// Select a random in bounds integer.
			pivot = rng.nextInt(low + (high - low));
			break;
		}
		case "median": {

			int middle = (low + high) / 2;

			// decipher which index is the median and return it.
			if (list.get(middle).compareTo(list.get(low)) > 0 && list.get(middle).compareTo(list.get(high)) > 0) {

				if (list.get(low).compareTo(list.get(high)) > 0)
					pivot = low;
				else
					pivot = high;
			}

			else if (list.get(low).compareTo(list.get(middle)) > 0 && list.get(low).compareTo(list.get(high)) > 0) {
				if (list.get(middle).compareTo(list.get(high)) > 0)
					pivot = middle;
				else
					pivot = high;
			}

			else {
				if (list.get(low).compareTo(list.get(middle)) > 0)
					pivot = low;
				else
					pivot = middle;
			}
			break;

		}
		}
		return pivot;
	}

	/**
	 * Takes in a list and two indexes and swaps the values at those indexes.
	 * 
	 * @param list
	 * @param a
	 * @param b
	 */
	private static <T> void swap(ArrayList<T> list, int a, int b) {
		T temp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, temp);
	}

	/**
	 * A generic method that performs an insertion sort on the input array, by
	 * order of the input comparator
	 * 
	 * @param item
	 * @param comp
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int low, int high) {

		// List is sorted
		if (list.size() <= 1)
			return;

		for (int i = low; i <= high; i++) {
			T element = list.get(i);
			int j;
			// Run insertion sort and compare using the input comparator.
			for (j = i - 1; j >= low && list.get(j).compareTo(element) > 0; j--) {
				list.set(j + 1, list.get(j));
			}
			list.set(j + 1, element);
		}
	}

	/**
	 * 
	 * @param size
	 * @return An ascending ArrayList of integers from 1 to size
	 */
	public static ArrayList<Integer> generateSortedOrder(int size) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			list.add(i);
		}
		return list;

	}

	/**
	 * 
	 * @param size
	 * @return A permuted ArrayList of integers containing number 1 to size
	 */
	public static ArrayList<Integer> generatePermutedOrder(int size) {

		// First create a sorted list
		ArrayList<Integer> list = generateSortedOrder(size);
		Random rng = new Random(size);

		if (size == 0)
			return new ArrayList<Integer>();

		if (size == 1)
			return generateSortedOrder(1);

		// For every element swap it with a random one.
		for (int i = 0; i < list.size(); i++) {
			int index = rng.nextInt(size - 1);
			// Do the swap
			int temp = list.get(index);
			list.set(index, list.get(i));
			list.set(i, temp);
		}
		return list;

	}

	/**
	 * 
	 * @param size
	 * @return An ArrayList containing numbers 1 - size in descending order.
	 */
	public static ArrayList<Integer> generateReverseSortedOrder(int size) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = size; i >= 1; i--) {
			list.add(i);
		}
		return list;

	}
}
