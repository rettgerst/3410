class ackermann {
	public static void main(String[] args) {
		System.out.println(ackermann(2,4,""));
	}

	private static int ackermann(int m, int n, String indent) {
		if (m == 0) {
			System.out.println(indent + "A(" + m + "," + n + ") returns 1");
			return 1;
		} else if (m > 0 && n == 0) {
			System.out.println(indent + "A(" + m + "," + n + ") calls A(" + (m-1) + "," + 1 + ")");

			return ackermann(m-1,n,indent + "    ");
		} else if (m > 0 && n > 0) {
			System.out.println(indent + "A(" + m + "," + n + ") calls A(" + (m-1) + ", A(" + m + "," + (n-1) + "))");

			return ackermann(m-1,ackermann(m,n-1,indent + "    "),indent + "    ");
		} else {
			System.out.println("encountered unprecedented case");
			return 0;
		}
	}
}