// This program tests and times the iterator from the BSTWithFastIterator class,
// comparing the results to those from BST.java.
// First, the program creates a BST with 10,000,000 values, 
// and a BSTWithFastIterator with the same values.
// The program displays the time required to create
// an iterator from both trees and and to iterate through the first 10 elements.
// It then displays the time required to create an iterator
// for each tree and iterate through all elements.
// Finally, it displays the time required to create an iterator
// for each tree and iterate through all elements, removing every
// fourth element.
// - Jeff Ward

import java.util.*;

public class TestBSTWithFastIterator {
	static final int NUM_VALUES = 10000000;
	public static void main(String[] args) {
		// Create BST and BSTWithFastIter
		Random rand = new Random(50);
		BST<Integer> bst = new BST<>();
		BST<Integer> bstWithFastIter = new BSTWithFastIter<>();
		long startTime = System.currentTimeMillis();
		System.out.println("Adding " + NUM_VALUES 
				+ " random values to plain BST and to BSTWithFastIter.");
		for (int i = 0; i < NUM_VALUES; i++) {
			int value = rand.nextInt();
			bst.add(value);
			bstWithFastIter.add(value);
		}
		long endTime = System.currentTimeMillis();
		double seconds = (endTime - startTime) / 1000.0;
		System.out.printf("Time to create trees:  %1.3f seconds\n", seconds);
		
		// Time the iterators
		System.out.println("Using plain BST:");
		ArrayList<ArrayList<Integer>> list1 = timeIterator(bst);
		System.out.println("Using BSTWithFastIter:");
		ArrayList<ArrayList<Integer>> list2 = timeIterator(bstWithFastIter);
		if (list1.equals(list2))
			System.out.println("Good -- Results match correctly.");
		else
			System.out.println("Error -- Results do not match.");
	}

	public static ArrayList<ArrayList<Integer>> timeIterator(BST<Integer> bst) {
		ArrayList<ArrayList<Integer>> returnValue = new ArrayList<>();
		
		// Create iterator
		long startTime = System.currentTimeMillis();
		Iterator<Integer> iter = bst.iterator();
		long endTime = System.currentTimeMillis();
		double seconds = (endTime - startTime) / 1000.0;
		System.out.printf("\tTime to create iterator:  %1.3f seconds\n", seconds);
		
		// Iterate through first few elements only
		startTime = System.currentTimeMillis();
		final int NUM_VALUES_TAKEN_AT_FIRST = 10;
		ArrayList<Integer> valuesFromIter = new ArrayList<>(NUM_VALUES_TAKEN_AT_FIRST);
		for (int i = 0; i < NUM_VALUES_TAKEN_AT_FIRST; i++)
			valuesFromIter.add(iter.next());
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000.0;
		System.out.printf("\tTime to iterate through " 
				+ NUM_VALUES_TAKEN_AT_FIRST + " values:  %1.3f seconds\n", seconds);
		returnValue.add(valuesFromIter);
		
		// Iterate through the entire list
		valuesFromIter = new ArrayList<>(NUM_VALUES);
		for (Integer i : bst)
			valuesFromIter.add(i);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000.0;
		System.out.printf("\tTime to iterate through all " 
				+ bst.size() + " values:  %1.3f seconds\n", seconds);
		returnValue.add(valuesFromIter);
		
		// Iterate through the entire list removing every fourth element
		valuesFromIter = new ArrayList<>(3 * NUM_VALUES / 4 + 1);
		int NUM_TO_ITER_THRU_WHILE_REMOVING = 1000;
		int count = 0;
		iter = bst.iterator();
		startTime = System.currentTimeMillis();
		while (count < NUM_TO_ITER_THRU_WHILE_REMOVING && iter.hasNext()) {
			iter.next();
			if (++count % 4 == 0)
				iter.remove();
		}
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000.0;
		System.out.printf("\tTime to iterate through " + NUM_TO_ITER_THRU_WHILE_REMOVING
				+ " values, removing every fourth one:  %1.3f seconds\n", seconds);
		
		startTime = System.currentTimeMillis();
		for (Integer i : bst)
			valuesFromIter.add(i);
		returnValue.add(valuesFromIter);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000.0;
		System.out.printf("\tTime to iterate through remaining " 
				+ bst.size() + " values:  %1.3f seconds\n", seconds);
			
		return returnValue;
	}
}
