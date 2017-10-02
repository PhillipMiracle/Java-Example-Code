// This program times two different ways of finding the k-th smallest
// element in an AVL tree.
// The first approach simply calls next on an iterator until the
// k-th element is reached.  This approach runs in O(n) time.
// The second approach calls a find method on the tree.
// If the tree has size values stored on each node that specify
// how many elements are in the subtree rooted at the node,
// then the find method can run in O(log n) time.
// The program also creates a sorted array that contains the
// elements from the tree, and uses this to check that
// the results returned from the two approaches are in fact correct.
// - Jeff Ward

import java.util.*;

public class TestFindKthSmallest {
  public static void main(String[] args) {
  	final int NUM_VALUES = 20000;
	final int BOUND = Integer.MAX_VALUE;
	final int NUM_TESTS = NUM_VALUES;
	Random rand = new Random(50);
	
	// Create an array with NUM_VALUES distinct Integer elements.
	Integer[] treeElements = new Integer[NUM_VALUES];
	HashSet<Integer> hashSet = new HashSet<Integer>();
	for (int i = 0; i < treeElements.length; i++) {
		while (true) {
			Integer elt = rand.nextInt(BOUND);
			if (hashSet.add(elt)) {
				treeElements[i] = elt;
				break;
			}
		}
	}
		
	// Put elements into tree.
	System.out.println("Creating AVL tree with " + treeElements.length + " elements.\n");
    AVLTree<Integer> tree = new AVLTree<Integer>(treeElements);

	// Create sorted array in order to check results.
	Integer[] treeElementsSorted = treeElements.clone();
	Arrays.sort(treeElementsSorted);
	
	// Create collection of k values.
	int[] kValues = new int[NUM_TESTS];
	for (int i = 0; i < kValues.length; i++)
		kValues[i] = rand.nextInt(tree.getSize()) + 1;
  	
  	// Run tests using iterators.
  	System.out.println("Starting " + NUM_TESTS + " tests using iterators.");
    Integer[] resultsFromIterator = new Integer[NUM_TESTS];
	long startTime = System.currentTimeMillis();
	for (int i = 0; i < NUM_TESTS; i++) {
		Iterator<Integer> iter = tree.iterator();
		int k = kValues[i];
		for (int j = 1; j <= k - 1; j++)
			iter.next();
		resultsFromIterator[i] = iter.next();
	}
	long endTime = System.currentTimeMillis();
	double seconds = (endTime - startTime) / 1000.0;
	System.out.printf("Runtime using iterators:  %1.3f seconds\n", seconds);
	
	System.out.print("Checking results ... ");
	for (int i = 0; i < NUM_TESTS; i++) {
		Integer resultFromIterator = resultsFromIterator[i];
		Integer resultFromSortedArray = treeElementsSorted[kValues[i] - 1];
		if (!resultFromIterator.equals(resultFromSortedArray)) {
			System.out.println("Error:");
			System.out.println("When searching for the k-th element with k = " + kValues[i]);
			System.out.println("The result from the iterator is " + resultFromIterator);
			System.out.println("but the result from the sorted array is " + resultFromSortedArray);
		}
	}
	System.out.println("okay.\n");
	
	// Run tests using find method.
  	System.out.println("Starting " + NUM_TESTS + " tests using find method.");
    Integer[] resultsFromFind = new Integer[NUM_TESTS];
	startTime = System.currentTimeMillis();
	for (int i = 0; i < NUM_TESTS; i++) {
		resultsFromFind[i] = tree.find(kValues[i]);
	}
	endTime = System.currentTimeMillis();
	seconds = (endTime - startTime) / 1000.0;
	System.out.printf("Runtime using find method:  %1.3f seconds\n", seconds);
	
	System.out.print("Checking results ... ");
	for (int i = 0; i < NUM_TESTS; i++) {
		Integer resultFromFind = resultsFromFind[i];
		Integer resultFromSortedArray = treeElementsSorted[kValues[i] - 1];
		if (!resultFromFind.equals(resultFromSortedArray)) {
			System.out.println("Error:");
			System.out.println("When searching for the k-th element with k = " + kValues[i]);
			System.out.println("The result from the find method is " + resultFromFind);
			System.out.println("but the result from the sorted array is " + resultFromSortedArray);
		}
	}
	System.out.println("okay.");
  }
}
