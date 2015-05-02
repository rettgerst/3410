import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Set {
	String name;
	List<Integer> values;

	public Set(String name) {
		this.name = name;
		values = new ArrayList<Integer>();
	}

	// add a single integer to the set
	// handles duplicate checking on its own
	public void add(int in) {
		// System.out.println("add(int) got " + in);
		if (values.isEmpty()) {
			values.add(in);
			return;
		}
		for(int i: values) {
			if (i == in) {
				// System.out.println(in + " is aleady in set " + name);
				return;
			}
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

	// removes item at specified index
	public void remove(int i) {
		values.remove(i);
	}

	// takes two sets as input and returns the union of both sets
	public static Set union(Set set1, Set set2) {
		Set tempSet = new Set("results of union");

		for (int i = 0; i<set1.size(); i++) tempSet.add(set1.get(i));
		for (int i = 0; i<set2.size(); i++) tempSet.add(set2.get(i));

		return tempSet;
	}

	// takes two sets as input and returns the intersection of both sets
	public static Set intersection(Set set1, Set set2) {
		Set tempSet = new Set("results of intersection");

		for (int i = 0; i<set1.size(); i++) {
			for (int j = 0; j<set2.size(); j++) {
				if (set1.get(i) == set2.get(j)) tempSet.add(set1.get(i));
			}
		}

		return tempSet;
	}

	// takes two sets as input, and returns second input subtracted from first input
	public static Set subtraction(Set set1, Set set2) {
		Set tempSet = new Set("results of subtraction");

		for (int i = 0; i<set1.size(); i++) {
			tempSet.add(set1.get(i));
		}

		for (int i = tempSet.size()-1; i>=0; i--) {
			for (int j = set2.size()-1; j>=0; j--) {
				if (i >= tempSet.size() || j >= set2.size()) continue;
				if (tempSet.get(i) == set2.get(j)) tempSet.remove(i);
			}
		}

		return tempSet;

	}
}

