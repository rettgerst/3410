class Node<T> {
	public Node<T>	prev;
	public T		info;
	public Node<T> 	next;

	// constructor
	public Node() {
		prev = next = null;
	}

	// other constructor
	public Node(T el) {
		super();
		info = el;
	}

	// remove node and return data
	public T remove() {
		T data = info;
		if (next != null)
			next.prev = prev;
		if (prev != null)
			prev.next = next;
		
		return data;
	}
}