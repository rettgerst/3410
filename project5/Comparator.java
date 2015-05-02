import java.util.Arrays;
import java.util.Random;

class Comparator extends InstrumentedSorter {

	public Comparator() {
		timer = new Timer();
	}

	public void bubbleSort(int[] array) {

		// I AM ONLY USING ARRAYS.SORT() TO VERIFY THE OUTPUT OF MY OWN SORT IMPLEMENTATIONS
		// int[] correct = array.clone(); Arrays.sort(correct);

		int j;
		boolean f = true;
		int t;

		while (f) {
			f = false;
				incrementInstructionCounter(); // for f = false
				incrementInstructionCounter(); // for j = 0
			for (j=0; j<array.length-1; j++) {
				incrementInstructionCounter(); // for j<array.length-1
				incrementInstructionCounter(); // for j++

				incrementInstructionCounter(); // for array[j]>array[j+1]
				if (array[j]>array[j+1]) {
					t = array[j];
					incrementInstructionCounter(); // for t = array[j]
					array[j] = array[j+1];
					incrementInstructionCounter(); // for array[j] = array[j+1]
					array[j+1] = t;
					incrementInstructionCounter(); // for array[j+1] = t
					f = true;
					incrementInstructionCounter(); // for f = true
				}
			}
		}

		// if (!Arrays.equals(array, correct)) {
		// 	System.out.println("WARNING: Comparator.bubbleSort() DOES NOT SORT CORRECTLY");
		// 	System.out.println(Arrays.toString(array));
		// 	System.out.println(Arrays.toString(correct));
		// 	System.exit(1);
		// };

	}

	public void selectionSort(int[] array) {

		// I AM ONLY USING ARRAYS.SORT() TO VERIFY THE OUTPUT OF MY OWN SORT IMPLEMENTATIONS
		// int[] correct = array.clone(); Arrays.sort(correct);

		int i, j, first, temp;
		incrementInstructionCounter();
		incrementInstructionCounter();
		for (i = array.length-1; i>0; i--) {
			first = 0;
			incrementInstructionCounter();

			incrementInstructionCounter();
			incrementInstructionCounter();
			for(j=1; j<=i; j++) {

				incrementInstructionCounter();
				if(array[j]>array[first]) {
					first = j;
					incrementInstructionCounter();
				}

				incrementInstructionCounter();
			}
			temp = array[first];
			incrementInstructionCounter();
			array[first] = array[i];
			incrementInstructionCounter();
			array[i] = temp;
			incrementInstructionCounter();
		}

		// if (!Arrays.equals(array, correct)) {
		// 	System.out.println("WARNING: Comparator.selectionSort() DOES NOT SORT CORRECTLY");
		// 	System.out.println(Arrays.toString(array));
		// 	System.out.println(Arrays.toString(correct));
		// 	System.exit(1);
		// };
	}

	public void insertionSort(int[] array) {

		// I AM ONLY USING ARRAYS.SORT() TO VERIFY THE OUTPUT OF MY OWN SORT IMPLEMENTATIONS
		// int[] correct = array.clone(); Arrays.sort(correct);

		int j;
		int key;
		int i;

		incrementInstructionCounter(); // for j = 1
		incrementInstructionCounter(); // for j < array.length
		for (j = 1; j < array.length; j++) {
			key = array[j];
			incrementInstructionCounter(); // for key = array[j];

			incrementInstructionCounter(); // for i = j - 1
			incrementInstructionCounter(); incrementInstructionCounter(); // for (i >= 0) && (array[i] > key)
			for(i = j - 1; (i >= 0) && (array[i] > key); i--) {
				array[i+1] = array[i];
				incrementInstructionCounter(); // for array[i+1] = array[i]
				incrementInstructionCounter(); incrementInstructionCounter(); // for (i >= 0) && (array[i] > key)
				incrementInstructionCounter(); // for i--
			}
			array[i+1] = key;
			incrementInstructionCounter(); // for array[i+1] = key

			// for j < array.length
			// for j++
		}

		// if (!Arrays.equals(array, correct)) {
		// 	System.out.println("WARNING: Comparator.selectionSort() DOES NOT SORT CORRECTLY");
		// 	System.out.println(Arrays.toString(array));
		// 	System.out.println(Arrays.toString(correct));
		// 	System.exit(1);
		// };
	}

	public void mergerSort(int[] array) {
		incrementInstructionCounter(); // for array.length <= 1
		if (array.length <= 1) return;
		int mid = array.length / 2;
		incrementInstructionCounter(); // for mid = array.length / 2
		int aSize = mid;
		incrementInstructionCounter(); // for aSize = mid
		int bSize = array.length - mid;
		incrementInstructionCounter(); // for bSize = array.length - mid
		int[] a = new int[aSize];
		incrementInstructionCounter(); // for a = new int[aSize]
		int[] b = new int[bSize];
		incrementInstructionCounter(); // for b = new int[bSize]

		incrementInstructionCounter(); // for i = 0
		incrementInstructionCounter(); // for i < mid
		for (int i = 0; i < mid; i++) {
			a[i] = array[i];
			incrementInstructionCounter(); // for a[i] = array[i]

			incrementInstructionCounter(); // for i = 0
			incrementInstructionCounter(); // for i < mid
		}

		incrementInstructionCounter(); // for i = mid
		incrementInstructionCounter(); // for i < array.length
		for (int i = mid; i < array.length; i++) {
			b[i - mid] = array[i];
			incrementInstructionCounter(); // for b[i - mid] = array[i]

			incrementInstructionCounter(); // for i = mid
			incrementInstructionCounter(); // for i < array.length
		}
		mergerSort(a); mergerSort(b);
		merge(a, b, array);
	}

	public void merge(int[] a, int[] b, int[] array) {
		int aSize = a.length;
		incrementInstructionCounter(); // for int aSize = a.length
		int bSize = b.length;
		incrementInstructionCounter(); // for int bSize = b.length
		int i = 0, j = 0, k = 0;
		incrementInstructionCounter(); incrementInstructionCounter(); incrementInstructionCounter(); // for int i = 0, j = 0, k = 0;

		incrementInstructionCounter(); incrementInstructionCounter(); // for i < aSize && j < bSize
		while (i < aSize && j < bSize) {
			incrementInstructionCounter(); // for a[i] <= b[j]
			if (a[i] <= b[j]) {
				array[k] = a[i];
				incrementInstructionCounter(); // for array[k] = a[i]
				i++; k++;
				incrementInstructionCounter(); // for i++; k++
			} else {
				array[k] = b[j];
				incrementInstructionCounter(); // for array[k] = b[j]
				k++; j++;
				incrementInstructionCounter(); // for k++; j++;
			}
			incrementInstructionCounter(); incrementInstructionCounter(); // for i < aSize && j < bSize
		}
		incrementInstructionCounter(); // for i < aSize
		while (i < aSize) {
			array[k] = a[i];
			incrementInstructionCounter(); // for array[k] = a[i]
			k++; i++;
			incrementInstructionCounter(); // k++; i++;
			incrementInstructionCounter(); // for i < aSize
		}
		incrementInstructionCounter(); // for j < aSize
		while (j < aSize) {
			array[k] = b[j];
			incrementInstructionCounter(); // for array[k] = b[j]
			k++; j++;
			incrementInstructionCounter(); // for k++; j++;k++; j++;
		}
	}

	public void quickSort(int[] array) {
		// I AM ONLY USING ARRAYS.SORT() TO VERIFY THE OUTPUT OF MY OWN SORT IMPLEMENTATIONS
		// int[] correct = array.clone(); Arrays.sort(correct);

		qSSort(array, 0, array.length-1, false);

		// if (!Arrays.equals(array, correct)) {
		// 	System.out.println("WARNING: Comparator.quickSort() DOES NOT SORT CORRECTLY");
		// 	System.out.println(Arrays.toString(array));
		// 	System.out.println(Arrays.toString(correct));
		// 	System.exit(1);
		// };
	}

	public void randomizedQuickSort(int[] array) {
		// I AM ONLY USING ARRAYS.SORT() TO VERIFY THE OUTPUT OF MY OWN SORT IMPLEMENTATIONS
		// int[] correct = array.clone(); Arrays.sort(correct);

		qSSort(array, 0, array.length-1, true);

		// if (!Arrays.equals(array, correct)) {
		// 	System.out.println("WARNING: Comparator.randomizedQuickSort() DOES NOT SORT CORRECTLY");
		// 	System.out.println(Arrays.toString(array));
		// 	System.out.println(Arrays.toString(correct));
		// 	System.exit(1);
		// };
	}

	private int qSPartition(int array[], int a, int b, boolean random) {
	  int i = a, j = b;
	  incrementInstructionCounter(); incrementInstructionCounter();

	  int t;
	  Random r = new Random(System.currentTimeMillis());
	  incrementInstructionCounter();
	  int p;

	  incrementInstructionCounter();
	  if (!random) {
		 p = array[(a + b)/2];
		 incrementInstructionCounter();
	  } else {
		p = array[a + r.nextInt(b - a)];
		incrementInstructionCounter();
	  }

	  incrementInstructionCounter();
	  while (i <= j) {
			incrementInstructionCounter();
			while (array[i] < p) {
				i++;
				incrementInstructionCounter();
				incrementInstructionCounter();
			}

			incrementInstructionCounter();
			while (array[j] > p) {
				j--;
				incrementInstructionCounter();
				incrementInstructionCounter();
			}

			incrementInstructionCounter();
			if (i <= j) {
				  t = array[i];
				  incrementInstructionCounter();
				  array[i] = array[j];
				  incrementInstructionCounter();
				  array[j] = t;
				  incrementInstructionCounter();
				  i++;
				  incrementInstructionCounter();
				  j--;
				  incrementInstructionCounter();
				  incrementInstructionCounter();
			}
			incrementInstructionCounter();
	  }
	 
	  return i;
	}

	private void qSSort(int array[], int a, int b, boolean random) {
		int i = qSPartition(array, a, b, random);
		incrementInstructionCounter();

		incrementInstructionCounter();
		if (a < i - 1)
			qSSort(array, a, i - 1, random);
		
		incrementInstructionCounter();
		if (i < b)
			qSSort(array, i, b, random);
	}

}