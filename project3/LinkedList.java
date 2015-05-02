// welcome to the hackiest class in human history

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class LinkedList<T> implements Iterable<T> {
	protected Node<T> head, tail;

	// constructor
	public LinkedList() {
		head = tail = null;
	}

	// check if the list is empty
	public boolean isEmpty() {
		return head == null;
	}

	// add to head (front of the list)
	public void addToHead(T el) {
		Node<T> newNode = new Node<T>(el);

		if (isEmpty()) {
			head = tail = newNode;
			return;
		}

		head.prev = newNode;
		newNode.next = head;
		head = newNode;
	}

	// add to tail (end of the list)
	public void addToTail(T el) {
		Node<T> newNode = new Node<T>(el);

		if (isEmpty()) {
			head = tail = newNode;
			return;
		}

		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
	}

	// convenience method for adding to tail
	// in case order doesn't matter
	public void add(T el) {
		addToTail(el);
	}

	// delete feom head
	public T deleteFromHead() {
		T el = head.info;

		if (!isEmpty() && head.next != null) head = head.next;
		else head = tail = null;
		return el;
	}

	// delete from tail
	public T deleteFromTail() { 
		 T el = tail.info;

		if (!isEmpty() && tail.prev != null) tail = tail.prev;
		else tail = head = null;
		return el;
	}

	// delete node containing el
	// this ended up being more useful than I thought!
	public void delete(T el) {
		if (isEmpty()) return;

					  if (head.info.equals(el)) deleteFromHead();
		if (!isEmpty() && tail.info.equals(el)) deleteFromTail();

		for (Node<T> curr = head; curr != null; curr = curr.next) {
			if (curr == null) return;
			if (curr.info.equals(el)) curr.remove();
		}
	}

	// print all items in the list
	public void printAll() {
		for (T info : this)
			System.out.println(info);
	}

	// check if an item is in the list
	public boolean isInList(T el) {
		for (T item : this) {
			if (item.equals(el)) return true;
		}
		return false;
	}

	// get an item at index i
	public T get(int i) {
		int counter = 1;
		for (T item : this) {
			if (counter == i) return item;
			counter++;
		}
		// if we've reached here we must not have returned
		throw new NullPointerException();
	}

	public int size() {
		int i = 0;
		for (T item : this) {
			i++;
		}
		return i;
	}

	public LinkedListIterator iterator() {
	  return new LinkedListIterator();
	}

	class LinkedListIterator implements Iterator<T> {

		boolean hasNextHackFlag = true;

		Node<T> curr = head;
		public boolean hasNext() {
			if (head == null) return false;
			return hasNextHackFlag;
		}

		public T next() {
			if (!hasNextHackFlag) throw new NoSuchElementException();
			if (curr.next == null) hasNextHackFlag = false;
			T data = curr.info;
			curr = curr.next;
			return data;
		}
	}
}