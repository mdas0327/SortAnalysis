package Sorting;

public class InversionCalculator {

	public double Compute(int[] data){
		if (data == null || data.length == 0)
			return 0.0;
		
		long count = 0;
		
		for (int i = 0; i < data.length; ++i) {
			for (int j = i+1; j < data.length; ++j) {
				if (data[i] > data[j])
					count++;
			}
		}
		return count / (double)(data.length * data.length);
	}
}
