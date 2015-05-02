/*  CSC3410			Spring 2015
	Gerald Gerst	ggerst1@student.gsu.edu
	Due Date: 		4-9‐2015
	Assignment:		5, sorting algorithms
	Files:			project5.java
					Comparator.java
					InstrumentedSorter.java
					Timer.java
					sampleoutput.txt

	USAGE INSTRUCTIONS:
	run project5.java with no arguments. program will generate a result summary table as output.
	please be patient. it takes a few minutes even on my quad-core Xeon processor.

	the program compiles and runs fine on both my laptop and desktop (both Macs) with Java SE 8 installed.
	if you have trouble please make sure you are using the latest stable Java version.
*/

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;

class project5 {
	public static void main(String[] args) {

		Comparator c = new Comparator();

		String[] sortnames = new String[]{"bubbleSort","selectionSort","insertionSort","mergerSort","quickSort","randomizedQuickSort"};

		Method[] sorts = new Method[6];

		for (int i = 0; i < 6; i++) {
			try {
				sorts[i] = (c.getClass().getMethod(sortnames[i], int[].class));
			} catch (NoSuchMethodException e) {
				System.exit(1);
			}
		}

		System.out.println("┌────────────────────┬────────────────────────────────────────────┐");

		// for arrays of size 1000-10000
		for (int size = 1; size <= 10; size += 1) {

			if (size != 1) System.out.println("├────────────────────┼─────────────┴──────────┴─────────┴─────────┤");
			System.out.printf("│                    │           %20s             │\n", "array size: " + (size*1000));
			System.out.println("│                    ├────────────────────────┬───────────────────┤");
			System.out.printf("│%-19s │ %22s │ %17s │\n","", "instruction count", "run time (ms)");
			System.out.println("├────────────────────┼─────────────┬──────────┼─────────┬─────────┤");
			System.out.println("│sorting algorithm   │      μ      │    σ     │    μ    │    σ    │");
			System.out.println("├────────────────────┼─────────────┼──────────┼─────────┼─────────┤");

			// iterate over every sort method
			try {
				for (int i = 0; i < 6; i++) {

					Integer[] instructions = new Integer[10*20];
					Integer[] times = new Integer[10*20];
					int index = 0;

					for (int j = 0; j<10; j++) {
						int[] randomArray = c.createRandomArray(size*1000);

						for (int k = 0; k<20; k++) {
							int[] x = randomArray.clone();

							c.resetInstructionCounter();
							c.timer.start();
							sorts[i].invoke(c, x);
							c.timer.end();
							instructions[index] = (c.getInstructionCounter());
							// System.out.println("instructions: " + instructions[index]);
							times[index] = ((int) c.timer.getTotalTime());
							// System.out.println("time: " + times[index]);
							// Thread.sleep(10);

							index++;
						}
					}

					// System.out.println("performing " + sortnames[i] + " with array size " + (size*1000));
					double icountmean = mean(instructions);
					double icountstdev = stdev(instructions, icountmean);

					double timesmean = mean(times);
					double timesstdev = stdev(times, timesmean);

					System.out.printf("│%-19s │ %10.2fk │ %7.2fk │ %7.2f │ %7.2f │\n", sortnames[i], icountmean/1000, icountstdev/1000, timesmean, timesstdev);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		System.out.println("└────────────────────┴─────────────┴──────────┴─────────┴─────────┘");
	}

	public static double mean(Integer[] in){
		double sum = 0;

		for (Integer i : in) {
			sum += i;
		}

		return sum/in.length;
	}

	public static double stdev(Integer[] in, double mean){
		double sumOfSquaredDiffs = 0;

		for (Integer i : in) sumOfSquaredDiffs += Math.pow((i - mean), 2);

		return Math.sqrt(sumOfSquaredDiffs/in.length);
	}
}