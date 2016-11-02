package Sorting;

public class MergeSort implements SortInterface {
	private int[] mergeSpace;

	@Override
	public void Sort(int[] data) {
		
        mergeSpace = new int[data.length];
        SortRange(data, 0, data.length - 1);
    }
 
	@Override
	public String GetName() {
		return "MergeSort";
	}
	
	
    private void SortRange(int[] data, int left, int right) {
         
        if (left < right) 
        {
            int middle = (left + right) / 2;
            
            SortRange(data, left, middle);
            SortRange(data, middle + 1, right);
            
            mergeSortedSubArrays(data, left, middle, right);
        }
    }
 
    private void mergeSortedSubArrays(int[] data, int left, int middle, int right) {
 
        int rangeOneIter = left;
        int rangeOneEnd = middle;
        int rangeTwoIter = middle + 1;
        int rangeTwoEnd = right;
        int j = left;

        while(rangeOneIter <= middle && rangeTwoIter <= rangeTwoEnd) {
            if(data[rangeOneIter] <= data[rangeTwoIter]) {
                mergeSpace[j++] = data[rangeOneIter++];
            }
            else {
                mergeSpace[j++] = data[rangeTwoIter++];
            }
        }

        while(rangeOneIter <= rangeOneEnd)
            mergeSpace[j++] = data[rangeOneIter++];

        while(rangeTwoIter <= rangeTwoEnd) {
            mergeSpace[j++] = data[rangeTwoIter++];
        }

        for(int k = left; k <= right; k++) {
            data[k] = mergeSpace[k];
        }
    }	
	

}
