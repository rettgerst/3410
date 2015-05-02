import java.util.Random;
public abstract class InstrumentedSorter {
	protected int instructionCounter; // instruction counter
	protected Timer timer; // refer to the tutorial link

	public void resetInstructionCounter() {
		instructionCounter = 0;
	}

	public void incrementInstructionCounter() {
		instructionCounter++;
	}

	public int getInstructionCounter() {
		return instructionCounter;
	}

	public int[] createRandomArray(int size) {
		Random r = new Random();
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = r.nextInt(500);
		return array;
	}

	public abstract void bubbleSort(int[] array);
	public abstract void selectionSort(int[] array);
	public abstract void insertionSort(int[] array);
	public abstract void mergerSort(int[] array);
	public abstract void quickSort(int[] array);
	public abstract void randomizedQuickSort(int[] array);
}