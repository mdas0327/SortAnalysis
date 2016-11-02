package Sorting;

public class QuickSort implements SortInterface {

	@Override
	public void Sort(int[] data) {
		
		SortRange(data, 0, data.length - 1);
		
	}

	@Override
	public String GetName() {
		return "QuickSort";
	}
	
	// rangeStart and rangeEnd are inclusive.
	private void SortRange(int[] data, int rangeStart, int rangeEnd) 
	{
		if (rangeEnd <= rangeStart) 
			return;
		
		int rangeMid = partition(data, rangeStart, rangeEnd);
		SortRange(data, rangeStart, rangeMid-1);
		SortRange(data, rangeMid+1, rangeEnd);
	}

	private int partition(int[] data, int rangeStart, int rangeEnd) 
	{
		int left = rangeStart;
		int right = rangeEnd + 1;
		int v = data[rangeStart];
		while (left < right) { 

			// get smaller element than pivot from left.
			while (data[++left] < v)
				if (left == rangeEnd) break;

			// get higher element than pivot from right.
			while (v < data[--right])
				if (right == rangeStart) break;      

			if (left < right) {
				// swap at left and right.
				int temp = data[left];
				data[left] = data[right];
				data[right] = temp;
			}
		}

		// swap first element with pivot position.
		int temp = data[rangeStart];
		data[rangeStart] = data[right];
		data[right] = temp;

		return right;
	}
}
