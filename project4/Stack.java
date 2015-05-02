class Stack implements StackInterface<String> {
	public Node<String> top;

	public void push(String in) {
		Node<String> newNode = new Node<String>(in);
		newNode.under = top;
		top = newNode;
	}

	public String peek() {
		return top.info;
	}

	public String pop() throws java.util.EmptyStackException {
		if (top == null) throw new java.util.EmptyStackException();
		Node<String> temp = top;
		top = top.under;
		return temp.info;
	}

	public void clear() {
		top = null;
	}

	public boolean isEmpty() {
		return (top == null);
	}

	// I used this for debugging
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Node<String> curr = top; curr != null; curr = curr.under) {
			s.append(curr.info);
		}
		return s.reverse().toString();
	}
}