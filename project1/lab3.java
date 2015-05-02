import java.lang.StringBuilder;
import java.util.Scanner;

class lab3 {
	StringBuilder str = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		if (isPalindrome(in, 0, in.length()-1)) System.out.println("true");
		else System.out.println("false");
	}

	public static boolean isPalindrome(String s, int indexa, int indexb) {

		if (indexa == indexb) return true;

		if (s.charAt(indexa) != s.charAt(indexb)) {
			return false;
		} else if (indexb - indexa == 1) {
			return true;
		} else if (s == null) {
			return true;
		} else {
			return isPalindrome(s, indexa+1, indexb-1);
		}
	}

	// public static String writeBackward(String s, int indexa, int indexb) {

	// }
}