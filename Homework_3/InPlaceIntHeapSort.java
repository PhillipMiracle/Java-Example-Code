//Phillip Miracle
//CSC 364-001
//Performs an in-place heap sort of an array

public class InPlaceIntHeapSort
{
	public static void heapSort(int[] array)
	{
		int n = array.length - 1;

		// Part I: Turn the array into a max-heap
		for (int i = n; i >= 0; i--)
		{
			siftUp(array, i);
		}
		// Part II: Repeatedly extract the max element from the heap
		for (int i = n; i >= 0; i--)
		{
			swap(array, 0, i);
			siftDown(array, 0, i);
		}
		//Used to deal with cases where the first two elements wouldn't be swapped
		if (array[0] > array[1])
			swap(array, 0, 1);
	}

	public static void siftUp (int array[], int start)
	{
		//Variable Creation
		int max = start;
		int left = 2 * start + 1;
		int right = 2 * start + 2;

		//Determines if left child is larger than the parent
		if (left < array.length)
		{
			if(array[left] > array[start])
			{
				max = left;
			}
		}
		//Determines if right child is larger than the parent
		if (right < array.length)
		{
			if (array[right] > array[max])
			{
				max = right;
			}
		}
		//if the highest node isn't the parent node then swap and sift up
		if (max != start)
		{
			swap(array, start, max);
			siftUp(array, max);
		}
	}

	public static void siftDown (int array[], int start, int end)
	{
		//Variable Creation
		int max = start;
		int left = 2 * start + 1;
		int right = 2 * start + 2;

		//Stops before last element sent to back
		if (left < end && right < end)
		{
			//Determines if left child is larger than the parent
			if (left < array.length)
			{
				if (array[left] > array[start])
				{
					max = left;
				}
			}
			//Determines if right child is larger than the parent
			if (right < array.length)
			{
				if (array[right] > array[max])
				{
					max = right;
				}
			}

			//if the highest node isn't the parent node then swap and sift down
			if (max != start)
			{
				swap(array, start, max);
				siftDown(array, max, end);
			}
		}
	}

	//Swaps the elements in the array at the given indexes
	private static void swap(int[] array, int x, int y)
	{
		int temp;
		temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
}


