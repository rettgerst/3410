// usage instructions are provided at runtime.
// this is an interface class for the Set class. the Set class holds set data and performs set algebra.

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static LinkedList<Set> allSets;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		allSets = new LinkedList<Set>();

		printCommands();

		while (true) try {

			System.out.print("Please enter the number of the command > ");

			switch (Integer.parseInt(sc.nextLine())) {
				
				// add
				case 1:
					String setName;

					outercase1: while (true) {
						System.out.print("Please enter a name: ");
						setName = sc.nextLine();
						break;
					}

					if (setName.equalsIgnoreCase("cancel")) throw new UserCanceledOperationexception();

					// see the very bottom of this file if you're confused

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
				case 3: {
					getSetFromName().print();
					System.out.println();
					break;
				}

				// sort a set (not in-place) and print the results
				case 4: {
					Set.sort(getSetFromName()).print();
					System.out.println();
					break;
				}

				// check for membership
				case 5: {
					Set s = getSetFromName();

					Integer intToCheckFor;

					// the while loop is to catch NumberFormatException
					while (true) try {
						System.out.print("Enter an integer: ");
						String intString = sc.nextLine();
						if (intString.equalsIgnoreCase("cancel")) throw new UserCanceledOperationexception();
						intToCheckFor = Integer.parseInt(intString);
						break;
					} catch (NumberFormatException e) {
						System.out.println("That was not an integer!");
					}

					System.out.println(Set.checkMembership(s, intToCheckFor));
					System.out.println();
					break;
				}

				// check for subset
				case 6: {
					System.out.println("We will be checking if the second set is a subset of the first set.");

					Set[] bothSets = getTwoSetsFromNames();
					System.out.println(Set.checkSubset(bothSets[0], bothSets[1]));
					System.out.println();

					continue;
				}

				// check if a set is sorted
				case 7: {
					System.out.println(Set.isSorted(getSetFromName()));
					System.out.println();
					break;
				}

				// print out the results of a union of two sets
				case 8: {
					Set[] bothSets = getTwoSetsFromNames();
					Set resultOfUnion = Set.union(bothSets[0], bothSets[1]);
					resultOfUnion.print();
					System.out.println();

					continue;
				}

				// print out the results of an intersection
				case 9: {
					Set[] bothSets = getTwoSetsFromNames();
					Set resultOfIntersection = Set.intersection(bothSets[0], bothSets[1]);
					resultOfIntersection.print();
					System.out.println();

					continue;
				}

				// subtraction
				case 10: {
					System.out.println("We will be subtracting the second set from the first set.");

					Set[] bothSets = getTwoSetsFromNames();
					Set resultOfSubtraction = Set.subtraction(bothSets[0], bothSets[1]);

					resultOfSubtraction.print();
					System.out.println();

					continue;
				}

				// print commands again
				case 11:
					printCommands();
					break;

				// exit the program
				case 12:
					System.out.println("Bye!\n");
					return;

				// in case input was not < 1 || > 7
				default:
					System.out.println("Bad input\n");
					break;
			}
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Bad input");
		} catch (UserCanceledOperationexception e) {
			System.out.println();
			continue;
		}
	}

	// if I've had a single good idea in my life (debatable) then it would be the next three methods
	// they make a lot of the work easier and save a lot of time I would have spent fixing little mistakes

	private static Set getSetFromName() throws UserCanceledOperationexception {
		Set set1 = null;

		while (set1 == null) try {
			System.out.print("Enter name: ");
			String setInputName1 = sc.nextLine();
			set1 = getSetFromName(setInputName1);
		} catch (NullPointerException e) {
			continue;
		}

		return set1;
	}

	private static Set getSetFromName(String in) throws UserCanceledOperationexception {
		if (in.equalsIgnoreCase("cancel")) throw new UserCanceledOperationexception();
		for (Set s : allSets) {
			if (s.getName().equalsIgnoreCase(in)) return s;
		}
		System.out.println("Couldn't find a set with that name");
		return null;
	}

	private static Set[] getTwoSetsFromNames() throws UserCanceledOperationexception {
		Set set1 = null;
		Set set2 = null;

		while (set1 == null) try {
			System.out.print("Enter name of first set: ");
			String setInputName1 = sc.nextLine();
			set1 = getSetFromName(setInputName1);
		} catch (NullPointerException e) {
			continue;
		}

		while (set2 == null) try {
			System.out.print("Enter name of second set: ");
			String setInputName2 = sc.nextLine();
			set2 = getSetFromName(setInputName2);
		} catch (NullPointerException e) {
			continue;
		}

		return new Set[] {set1, set2};
	}

	private static void printCommands() {
		System.out.print(
			  "1)  Create a set" +
			"\n2)  Print the list of created sets" +
			"\n3)  Print a set" +
			"\n4)  Print sorted (NEW)" +
			"\n5)  Membership check (NEW)" +
			"\n6)  Subset check (NEW)" +
			"\n7)  Sorted check (NEW)" +
			"\n8)  Union" +
			"\n9)  Intersection" +
			"\n10) Subtraction" +
			"\n11) Print commands again" +
			"\n12) Exit\n\n" +
			  "Enter \"cancel\" during an operation to go back to the command input\n\n");
	}

	// now this was totally unnecessary, but I think it was a good learning experience

	// this is my solution for the cases where the user might accidentally
	// enter a value at the wrong prompt or accidentally open an operation that
	// requires two sets when they didn't have >=2 sets yet

	// which I did *a lot* while working on the project as a whole

	// this technically means that the user can't create a set with the name "cancel"

	// also if I had more time it would probably be more efficient to subclass Scanner
	// and override the nextLine() method to throw UserCanceledOperationexception
	// if it matches the string "cancel", instead of what I have above
	// but this implementation works fine as-is and was already unnecessary

	private static class UserCanceledOperationexception extends Exception {
		public UserCanceledOperationexception() {}
	}
}