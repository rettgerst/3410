// this is mostly the same as last time
// except I added some methods and use LinkedList

import java.util.StringTokenizer;
import java.util.Iterator;

class Set {
	String name;
	LinkedList<Integer> values;

	// constructor
	public Set(String name) {
		this.name = name;
		values = new LinkedList<Integer>();
	}

	// add a single integer to the set
	// handles duplicate checking on its own
	public void add(int in) {
		if (values.isEmpty()) {
			values.add(in);
			return;
		}
		for(int i: values) {
			if (i == in) return;
		}
		values.add(in);
	}

	// take user input, tokenize, iterate and add to set
	public void add(String in) {
		StringTokenizer st = new StringTokenizer(in);
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			try {
				add(Integer.parseInt(token));
			} catch (NumberFormatException e) {
				System.out.println(token + " is not an integer");
			}
		}
	}

	// returns a string containing both the name and contents of the set
	public String toString() {
		String setString = name + ": ";

		if (values.isEmpty()) return setString + "empty";

		for (int i: values) {
			setString += i + " ";
		}

		return setString;
	}

	// convenience method that prints the output of toString() console
	public void print() {
		System.out.println(this.toString());
	}

	// returns only the name of the set
	public String getName() {
		return name;
	}

	// returns the number of items in the set
	public int size() {
		return values.size();
	}

	// gets item at specified index
	public int get(int i) {
		return values.get(i);
	}

	// static methods

	// takes two sets as input and returns the union of both sets
	public static Set union(Set set1, Set set2) {
		Set tempSet = new Set("results of union");

		for (int i : set1.values) tempSet.add(i);
		for (int i : set2.values) tempSet.add(i);

		return tempSet;
	}

	// takes two sets as input and returns the intersection of both sets
	public static Set intersection(Set set1, Set set2) {
		Set tempSet = new Set("results of intersection");

		for (int i : set1.values)
			for (int j : set2.values)
				if (i == j) tempSet.add(i);

		return tempSet;
	}

	// takes two sets as input, and returns second input subtracted from first input
	public static Set subtraction(Set set1, Set set2) {
		Set tempSet = new Set("results of subtraction");

		for (int i : set1.values)
			tempSet.add(i);

		for (int i : set2.values)
			tempSet.values.delete(i);

		return tempSet;
	}

	// check if sorted
	public static boolean isSorted(Set input) {
		Integer last = null;
		for (Integer i : input.values) {
			if (last != null && i < last) return false;
			last = i;
		} return true;
	}

	// sort a set (not in-place) and return a sorted version
	public static Set sort(Set input) {
		// first convert the set into an array se we can set by indices
		int[] setArray = new int[input.size()];
		int counter = 0;
		for (Integer i : input.values) {
			setArray[counter] = i;
			counter++;
		}

		// now we do selection sort
		for (int i = 0; i < setArray.length - 1; i++) {
			for (int j = i + 1; j < setArray.length; j++) {
				if (setArray[i] > setArray[j]) {
					int temp = setArray[i];
					setArray[i] = setArray[j];
					setArray[j] = temp;
				}
			}
		}

		// and convert the array back into a set
		Set tempSet = new Set("results of sort");
		for (int i : setArray) {
			tempSet.add(i);
		}

		return tempSet;
	}

	// check for membership
	public static boolean checkMembership(Set inSet, Integer inInt) {
		for (Integer i : inSet.values)
			if (inInt == i) return true;
		return false;
	}

	// check for subset
	public static boolean checkSubset(Set seta, Set setb) {
		for (Integer i : setb.values)
			if (!checkMembership(seta, i)) return false;
		return true;
	}
}