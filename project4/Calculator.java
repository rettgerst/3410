/*  CSC3410			Spring 2015
	Gerald Gerst	ggerst1@student.gsu.edu
	Due Date: 		1‐26‐2015
	Assignment:		4, infix to postfix
	Files:			Calculator.java
					StackInterface.java
					Stack.jjava
					Node.java

	USAGE INSTRUCTIONS:
	run the program with the filename of a newline-delimited plaintext file
	containing infix expressions as the first and only argument.

	NOTES:
	there was some ambuguity as to whether * should have precedence over /.
	in regular algebra this wouldn't make a difference, but here it does,
	because of integer rounding. I opted for simplicity and gave them the same
	precedence.
*/

import java.io.*;
import java.util.Scanner;

class Calculator {
	static char[] operators = {'(',')','*','/','+','-'};

	public static void main(String[] args) {

		// initial checks

		Scanner sc;

		try {
			sc = new Scanner(new File(args[0]));

			while (true) {
				String line = sc.nextLine();

				System.out.println("\ninfix: " + line);

				StringBuilder tempLine = new StringBuilder();

				for (char c : line.toCharArray()) {
					if (c != ' ') tempLine.append("" + c);
				}

				line = tempLine.toString();

				Stack balance = new Stack();

				try {
					for (char c : line.toCharArray()) {
						if (c == '(') balance.push("(");
						else if (c == ')') balance.pop();
					}
				} catch (java.util.EmptyStackException e) {
					System.out.println("imbalanced parenthesis");
					continue;
				}

				try {
					balance.pop();
					System.out.println("inbalanced parenthesis");
					continue;
				} catch (java.util.EmptyStackException e) { }

				Stack s = new Stack();

				StringBuilder outStr = new StringBuilder();

				StringBuilder sb = new StringBuilder();

				for (String c : line.split("")) {
					if (isAnOperator(c.charAt(0))) {
						if (c.charAt(0) == ')') {
							while (!s.isEmpty() && s.peek().charAt(0) != '(') {
								sb.append(" " + s.pop());
							} s.pop();
						} else {
							if (!s.isEmpty() && higherPrecedence(s.peek().charAt(0), c.charAt(0))) {
								sb.append(" " + s.pop());
							}
							s.push(c);
							if (sb.length() != 0 && sb.charAt(sb.length()-1) != ' ') sb.append(" ");
						}
					} else {
						sb.append(c);
					}
					// System.out.println("c: " + c + ", s: " + s.toString() + ", sb: " + sb.toString());
				}

				outStr.append(sb + " ");

				while (!s.isEmpty()) {
					if (!(s.peek().charAt(0) == '(' || s.peek().charAt(0) == ')'))
						outStr.append(s.pop() + " ");
					else s.pop();
				}

				Integer outbuffer;

				try {
					outbuffer = evalPostfix(outStr.toString());
				} catch (Exception e) {
					System.out.println("bad input");
					return;
				}

				System.out.println("postfix: " + outStr);

				System.out.println("result: " + outbuffer);

				if (!sc.hasNext()) break;
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(1);
		}

		
	}

	// check if character is an operator
	private static boolean isAnOperator(char in) {
		for (char c : operators) {
			if (c == in) return true;
		} return false;
	}

	// check precedence of two operators
	private static boolean higherPrecedence(char a, char b) {
		if (a == '(' || a == ')' || b == '(' || b == ')') return false;
		return (precedenceIndex(a) < precedenceIndex(b));
	}

	// returns "precedence index" of input operator
	private static Integer precedenceIndex(char in) {
		for (int i = 0; i<operators.length; i++) {
			if (operators[i] == in) return i/2;
		} return null;
	}

	// evaluates a postfix expression
	private static Integer evalPostfix(String in) throws Exception {
		try {
			String[] postArr = in.split(" ");

			Stack st = new Stack();

			for (String s : postArr) {
				if (s.length() == 1 && isAnOperator(s.charAt(0))) {
					switch (s.charAt(0)) {
						case '+':
							st.push("" + (Integer.parseInt(st.pop()) + Integer.parseInt(st.pop())));
							break;
						case '-':
							st.push("" + (Integer.parseInt(st.pop()) - Integer.parseInt(st.pop())));
							break;
						case '*':
							st.push("" + (Integer.parseInt(st.pop()) * Integer.parseInt(st.pop())));
							break;
						case '/':
							int temp = Integer.parseInt(st.pop());
							st.push("" + (Integer.parseInt(st.pop()) / temp));
							break;
						default:
							break;
					}
				} else {
					st.push(s);
				}
			}

			Integer resultbuffer = Integer.parseInt(st.pop());

			try {
				st.pop();
				System.out.println("bad input");
				throw new Exception();
			} catch (java.util.EmptyStackException e) {

			}

			return resultbuffer;
		} catch (NumberFormatException e) {
			System.out.println("bad input");
			throw new Exception();
		}
	}
 }