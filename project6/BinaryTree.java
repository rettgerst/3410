/*  CSC3410			Spring 2015
	Gerald Gerst	ggerst1@student.gsu.edu
	Due Date: 		4‐27‐2015
	Assignment:		6, binary sort tree
	Files:			BinaryTree.java
					Dictionary.java
					Entry.java
					Node.java
					TestDict.java
					TreeDictionary.java
*/

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Stack;

// the binary tree class
// the class must implement the treeDictionary and Iterable interfaces
public class BinaryTree<K extends Comparable<K>, V> 
			implements TreeDictionary<K, V>, Iterable<Entry<K, V>> {

	// class members
	private int size;
	Node<K, V> root;
	private String iterationOrder;
	
	public BinaryTree() {
		this.iterationOrder = "preorder";
		// to be implemented
	}
	
	// private inner classes for preorder, inorder, postorder, and 
	// breath first order traversals
	// PreOrder class given as example:
	private class PreOrder implements Iterator<Entry<K, V>> {

		private Node<K, V> nextNode;

		private Stack<Node<K, V>> s = new Stack<Node<K, V>>();

		PreOrder() {
			s.add(root);
		}

		@Override
		public boolean hasNext() {
			return !s.isEmpty();
		}

		@Override
		public Entry<K, V> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			nextNode = s.pop();
			if (nextNode.rightChild != null) s.push(nextNode.rightChild);
			if (nextNode.leftChild != null) s.push(nextNode.leftChild);
			return nextNode.entry;
		}
	}

	@Override
	public void makeEmpty() {
		size = 0;
		root = null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(K k, V v) {
		// throw new UnsupportedOperationException("Not implemented yet");

		Node<K, V> newNode = new Node<K, V>(new Entry<K, V>(k, v));

		if (isEmpty()) {
			root = newNode;
			size++;
			return;
		}

		Node<K, V> selectedNode = root;

		while (true) {
			// if (selectedNode != null) System.out.println("currently selected node is: " + selectedNode.entry);
			// else System.out.println("current node is null");

			// System.out.print("comparing " + selectedNode.entry.key + " with " + newNode.entry.key + "... ");
			int c = newNode.entry.compareTo(selectedNode.entry);

			if (c < 0) {
				// System.out.println("c < 0");
				if (selectedNode.leftChild == null) {
					// System.out.println("selected node had no left child");
					selectedNode.leftChild = newNode;
					newNode.parent = selectedNode;
					// System.out.println("inserted!!!\n");
					break;
				} else {
					// System.out.println("moving to " + selectedNode.leftChild);
					selectedNode = selectedNode.leftChild;
				}
			} else if (c == 0) {
				System.out.println("ignoring duplicate key");
				return;
			} else { // c > 0
				// System.out.println("c > 0");
				if (selectedNode.rightChild == null) {
					// System.out.println("selected node had no right child");
					selectedNode.rightChild = newNode;
					newNode.parent = selectedNode;
					// System.out.println("inserted!!!\n");
					break;
				} else {
					// System.out.println("moving to right child");
					selectedNode = selectedNode.rightChild;
				}
			}
		}

		size++;
	}

	@Override
	public V find(K key) {
		Node<K, V> val = recurseFind(key, root);
		if (val == null) return null;
		return val.entry.value();
	}

	private Node<K, V> recurseFind(K k, Node<K, V> n) {
		int c = k.compareTo(n.entry.key());
		if (n == null || n.entry.key() == k) {
			return n;
		} else if (c < 0 && n.leftChild != null) {
			return recurseFind(k, n.leftChild);
		} else if (c > 0 && n.rightChild != null) {
			return recurseFind(k, n.rightChild);
		} else {
			return null;
		}
	}

	private int getDepth(K key) {
		return recurseDepth(key, root);
	}

	private int recurseDepth(K k, Node<K, V> n) {
		int c = k.compareTo(n.entry.key());
		if (n == null || n.entry.key() == k) {
			return 1;
		} else if (c < 0) {
			return recurseDepth(k, n.leftChild) + 1;
		} else {
			// if greater than
			return recurseDepth(k, n.rightChild) + 1;
		}
	}
	
	@Override
	public HashSet<K> getKeys() {
		HashSet<K> keys = new HashSet<K>();
		for (Entry<K, V> e : this) {
			keys.add(e.key());
		}

		return keys;
	}
	
	@Override
	public List<V> getValues() {
		ArrayList<V> values = new ArrayList<V>();
		for (Entry<K, V> e : this) {
			values.add(e.value());
		}

		return values;
	} 
	
	@Override
	public Set<Entry<K, V>> getEntries() {
		HashSet<Entry<K, V>> entries = new HashSet<Entry<K, V>>();
		for (Entry<K, V> e : this) {
			entries.add(e);
		}

		return entries;
	}
	
	@Override
	public void defaultIterator(String order) {
		switch (order) {
			case "bfs":
			case "inorder":
			case "preorder":
				iterationOrder = order;
				break;
			default:
				System.out.println("unrecognized iteration order");
				break;
		}
	}
	
	@Override
	public Iterator<Entry<K, V>> preorder() {
		this.iterationOrder = "preorder";
		return new PreOrder();
	}
	
	@Override
	public Iterator<Entry<K, V>> inorder() {
		this.iterationOrder = "inorder";
		return new Inorder();
	}

	private class Inorder implements Iterator<Entry<K, V>> {

		Inorder() {
			downAndLeft();
		}

		Node<K, V> next = root;

		public Entry<K, V> next() {
			Node<K, V> returnNode = next;
			// System.out.println("returnNode = " + returnNode.entry);

			if (next.rightChild != null) {
				next = next.rightChild;
				downAndLeft();
				return returnNode.entry;
			} else {
				while (true) {
					// System.out.println("next.parent = " + next.parent.entry);
					if (next.parent == null) {
						// System.out.println("next.parent == null");
						next = null;
						return returnNode.entry;
					} else if (next.parent.leftChild == next) {
						// System.out.println("next.parent.leftChild == next");
						next = next.parent;
						return returnNode.entry;
					}
					// System.out.println("outside");
					next = next.parent;
				}
			}
		}

		public boolean hasNext() {
			return next != null;
		}

		private void downAndLeft() {
			while (next.leftChild != null) {
				next = next.leftChild;
			}
		}
	}
	
	@Override
	public Iterator<Entry<K, V>> postorder() {
		this.iterationOrder = "postorder";
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public Iterator<Entry<K, V>> breathFirst() {
		this.iterationOrder = "bfs";
		return new BreathFirst();
	}

	private class BreathFirst implements Iterator<Entry<K, V>> {
		Queue<Node<K, V>> q;
		Node<K, V> nextNode = root;

		BreathFirst() {
			q = new LinkedList<Node<K, V>>();
			q.add(root);
		}
		@Override
		public Entry<K, V> next() {
			nextNode = q.remove();
			if (nextNode.leftChild != null) q.add(nextNode.leftChild);
			if (nextNode.rightChild != null) q.add(nextNode.rightChild);
			return nextNode.entry;
		}

		@Override
		public boolean hasNext() {
			return !q.isEmpty();
		}
	}
	
	@Override
	public Iterator<Entry<K, V>> iterator() {
		switch (iterationOrder) {
			case "inorder": return inorder();
			case "preorder": return preorder();
			case "postorder": return postorder();
			case "bfs": return breathFirst();
			default:
				System.out.println("unrecognized iterationOrder");
				break;
		}

		return inorder();
	}
	
	@Override
	public void remove(K key) {
		Node<K, V> n = recurseFind(key, root);

		if (n == null) {
			System.out.println("node not found");
			return;
		}

		else if (n == root && root.isLeaf()) {

		} else if ((n.leftChild != null && n.rightChild == null) || 
					(n.leftChild == null && n.rightChild != null)) {
			size--;
			if (n.parent.leftChild == n) {
				if (n.leftChild != null) {
					n.parent.leftChild = n.leftChild;
					n.leftChild.parent = n.parent;
				} else if (n.rightChild != null) {
					n.parent.leftChild = n.rightChild;
					n.rightChild.parent = n.parent;
				}
			} else if (n.parent.rightChild == n) {
				if (n.leftChild != null) {
					n.parent.rightChild = n.leftChild;
					n.leftChild.parent = n.parent;
				} else if (n.rightChild != null) {
					n.parent.rightChild = n.rightChild;
					n.rightChild.parent = n.parent;
				}
			}
		} else if (n.isLeaf()) {
			size--;
			if (n.parent.leftChild == n) n.parent.leftChild = null;
			else if (n.parent.rightChild == n) n.parent.rightChild = null;
		} else if (n.leftChild != null && n.rightChild != null) {
			size--;
			Node<K, V> temp = n.rightChild;

			while (temp.leftChild != null) {
				temp = temp.leftChild;
			}

			temp.leftChild = n.leftChild;
			temp.leftChild.parent = temp;
			temp.parent = n.parent;

			if (n == root) {
				root = temp;
			} else {
				if (n.parent.leftChild == n) {
					n.parent.leftChild = temp;
				} else {
					n.parent.rightChild = temp;
				}
			}
		}
	}
	
	@Override
	public int height() {
		return heightOfSubTree(root)+1;

		// throw new UnsupportedOperationException("Not implemented yet");
	}

	private int heightOfSubTree(Node<K, V> n) {
		int height = 0;

		Node<K, V> selectedNode = n;

		if (selectedNode.leftChild != null &&
			heightOfSubTree(selectedNode.leftChild)+1 > height)
			height = heightOfSubTree(selectedNode.leftChild)+1;
		if (selectedNode.rightChild != null &&
			heightOfSubTree(selectedNode.rightChild)+1 > height)
			height = heightOfSubTree(selectedNode.rightChild)+1;

		return height;
	}
	
	@Override
	public void prettyPrint() {
		recursePrettyPrint(root);
	}

	private void recursePrettyPrint(Node<K, V> n) {
		if (n != null) {
			recursePrettyPrint(n.leftChild);
			int offset = getDepth(n.entry.key());
			StringBuilder hyphen = new StringBuilder();
			for (int i = 0; i < offset; i++) {
				hyphen.append("-----");
			}
			System.out.println(hyphen.toString() + n.entry);
			recursePrettyPrint(n.rightChild);
		} else return;
	}
}
