// The Entries in the BinaryTree are <key, value> pairs
// The key (K) type must be Comparable
// Entry must impelement Comparable
public class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {

	protected K key;
	protected V value;

	protected Entry(K k, V v) {
		key = k;
		value = v;
	}

	public K key() {
		return key;
	}

	public V value() {
		return value;
	}
	
	// override toString method
	// toString method should print the entry as:
	// <key,value>
	@Override
	public String toString() {
		return String.format("<%s,%s>", key, value);
	}
	
	// override compareTo method for Entry
	@Override
	public int compareTo(Entry<K, V> other) {
		return this.key.compareTo(other.key());
	}
}
