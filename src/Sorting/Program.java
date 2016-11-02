package Sorting;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		DataSetFactory dataSetFactory = new DataSetFactory();
		ArrayList<DataSet> datasets1 = new ArrayList<DataSet>();
		datasets1.add(dataSetFactory.CreateRandom(1000, 0, 900));
		datasets1.add(dataSetFactory.CreateRandom(2000, 0, 1000));
		datasets1.add(dataSetFactory.CreateRandom(10000, 0, 1000));
		datasets1.add(dataSetFactory.CreateRandom(15000, 0, 1000));
		
		ArrayList<DataSet> datasets2 = new ArrayList<DataSet>();
		datasets2.add(dataSetFactory.CreateNPercentReverse(5000, 0, 5000, 100.0)); // high inversion degree.
		datasets2.add(dataSetFactory.CreateNPercentReverse(5000, 0, 5000, 80.0));
		datasets2.add(dataSetFactory.CreateNPercentReverse(5000, 0, 5000, 40.0));
		datasets2.add(dataSetFactory.CreateNPercentReverse(5000, 0, 5000, 20.0));
		
		ArrayList<DataSet> datasets3 = new ArrayList<DataSet>();
		datasets3.add(dataSetFactory.CreateFromRealDataSet(1000, 0, 1000, 70.0)); 
		datasets3.add(dataSetFactory.CreateFromRealDataSet(2000, 0, 2000, 70.0));
		datasets3.add(dataSetFactory.CreateFromRealDataSet(10000, 0, 10000, 70.0));
		datasets3.add(dataSetFactory.CreateFromRealDataSet(15000, 0, 15000, 70.0));
		
		ArrayList<DataSet> datasets4 = new ArrayList<DataSet>();
		datasets4.add(dataSetFactory.CreateFromRealDataSet(1000, 0, 1000, 50.0)); 
		datasets4.add(dataSetFactory.CreateFromRealDataSet(2000, 0, 2000, 50.0));
		datasets4.add(dataSetFactory.CreateFromRealDataSet(10000, 0, 10000, 50.0));
		datasets4.add(dataSetFactory.CreateFromRealDataSet(5000, 0, 15000, 50.0));
		
		List<ArrayList<DataSet>> dataSetsGroup = new ArrayList<ArrayList<DataSet>>();
		dataSetsGroup.add(datasets1);
		dataSetsGroup.add(datasets2);
		dataSetsGroup.add(datasets3);
		dataSetsGroup.add(datasets4);
		
		SortInterface[] sorters = new SortInterface[]{
				new BubbleSort(),
				new InsertionSort(),
				new MergeSort(),
				new QuickSort(),
				new SelectionSort(),
		};
		MemoryMXBean memoryMX = ManagementFactory.getMemoryMXBean();
		
		for (ArrayList<DataSet> dataSetGroup : dataSetsGroup)
			Run(dataSetGroup, sorters, memoryMX);
		
	}

	private static void Run(List<DataSet> datasets, SortInterface[] sorters, MemoryMXBean memoryMX) {
		
		System.out.print("\n\nDataSet size,");
		System.out.print("Inversion Degree,");
		
		for (int i =0; i < sorters.length; ++i) {
			System.out.print(sorters[i].GetName()+ "(Runtime in microSec),");
		}
		
		for (int i =0; i < sorters.length; ++i) {
			System.out.print(sorters[i].GetName()+ "(Memory used in KB),");
		}

		System.out.println();
		
		for (DataSet dataset : datasets) {
			//System.out.println("Inversion degree: " + dataset.inversionDegree);
			
			double[] runTimes = new double[sorters.length];
			double[] memoryUsed = new double[sorters.length];
			for (int i = 0; i < sorters.length; ++i) {
				SortInterface sorter = sorters[i];
				//System.out.println(sorter.GetName());
				
				// Run it 5 times and average out.
				for (int k = 0; k < 5; ++k) {

					int[] clonedData = dataset.data.clone();
					memoryMX.gc();
					long startTime = System.nanoTime();
					MemoryUsage heapBefore = memoryMX.getHeapMemoryUsage();
					
					sorter.Sort(clonedData);
					
					MemoryUsage heapAfter = memoryMX.getHeapMemoryUsage();
					long endTime = System.nanoTime();
					
					runTimes[i] += (endTime - startTime) / 1000.0;
					memoryUsed[i] += heapAfter.getUsed() - heapBefore.getUsed();
				}
				runTimes[i] /= 5;
				memoryUsed[i] /= 5*100;
			}
		
			
			System.out.print(dataset.data.length + ", ");
			System.out.print(dataset.inversionDegree + ", ");
			for (int i =0; i < sorters.length; ++i) {
				System.out.print(runTimes[i] + ",");
			}
			for (int i =0; i < sorters.length; ++i) {
				System.out.print(memoryUsed[i]+ ",");
			}
			System.out.println();
		}
	}

}
