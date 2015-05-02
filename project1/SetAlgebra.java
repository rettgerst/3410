// usage instructions are provided at runtime.
// this is an interface class for the Set class. the Set class holds set data and performs set algebra.

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SetAlgebra {
	static List<Set> allSets;
	static Scanner sc = new Scanner(System.in);


	public static void main(String[] args) {

		allSets = new ArrayList<Set>();

		while (true) try {
			System.out.print(
			  "1) Create a set." +
			"\n2) Print the list of created sets" +
			"\n3) Print a set" +
			"\n4) Union" +
			"\n5) Intersection" +
			"\n6) Subtraction" +
			"\n7) Exit" +
			"\nPlease enter the number of the command > ");

			switch (sc.nextInt()) {
				// add
				case 1:
					// flush the scanner
					sc.nextLine();

					String setName;

					outercase1: while (true) {
						System.out.print("please enter a name: ");
						setName = sc.nextLine();
						for (Set s : allSets) if (s.getName().equalsIgnoreCase(setName)) {
							System.out.println("a set with that name already exists!");
							continue outercase1;
						}
						break;
					}

					Set set = new Set(setName);

					System.out.println("Please enter values separated by a space, or nothing for an empty set");
					set.add(sc.nextLine());

					allSets.add(set);

					System.out.println();
					continue;

				// print all
				case 2:
					for (Set s: allSets)
						s.print();
					System.out.println();
					continue;

				// print specific
				case 3:
					sc.nextLine();
					System.out.print("enter a name: ");
					String setInputName = sc.nextLine();
					for (Set s: allSets)
							if (s.getName().equalsIgnoreCase(setInputName))
									s.print();
					System.out.println();
					continue;

				// print out the results of a union of two sets
				// makes use of the fact that Set.add() handles duplicates by itself

				// this case and following cases are enclosed in brackets to alleviate
				// conflicts of scope with the other cases which reuse the same variable names.
				case 4: {
					sc.nextLine();

					Set[] bothSets = getTwoSetsFromNames();
					Set resultOfUnion = Set.union(bothSets[0], bothSets[1]);
					resultOfUnion.print();
					System.out.println();

					continue;
				}

				// print out the results of an intersection
				case 5: {
					sc.nextLine();

					Set[] bothSets = getTwoSetsFromNames();
					Set resultOfIntersection = Set.intersection(bothSets[0], bothSets[1]);
					resultOfIntersection.print();
					System.out.println();

					continue;
				}

				// this was the most complicated case

				// first, we iterate backwards, so that removing an item will not change index
				// locations of future items.

				// second I need to check the iterator against the size of the set to avoid a
				// null pointer error.
				case 6: {
					sc.nextLine();

					System.out.println("\nwe will be subtracting the second set from the first set.\n");

					Set[] bothSets = getTwoSetsFromNames();
					Set resultOfSubtraction = Set.subtraction(bothSets[0], bothSets[1]);

					resultOfSubtraction.print();
					System.out.println();

					continue;
				}

				// exit the program
				case 7:
					System.out.println("bye!");
					return;

				// in case input was not < 1 || > 7
				default:
					System.out.print("bad input");
					continue;
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("bad input");
		}
	}

	private static Set getSetFromName(String in) {
		for (Set s : allSets) {
			if (s.getName().equalsIgnoreCase(in)) return s;
		}
		return null;
	}

	private static Set[] getTwoSetsFromNames() {
		Set set1, set2;

		while (true) {
			System.out.print("enter name of first set: ");
			set1 = getSetFromName(sc.nextLine());
			if (set1 == null) System.out.println("couldn't find a set with that name");
			else break;
		}

		while (true) {
			System.out.print("enter name of second set: ");
			set2 = getSetFromName(sc.nextLine());
			if (set2 == null) System.out.println("couldn't find a set with that name");
			else break;
		}

		return new Set[] {set1, set2};
	}
}