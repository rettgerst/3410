// this really should be in Stack.java

class Node<T> {
	public final T info;
	public Node<T> under;

	public Node(T in) {
		info = in;
	}
}