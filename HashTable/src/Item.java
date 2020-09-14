
public class Item<E> {
	/**
	 * Flag to see if the item was deleted or not
	 */
	public boolean isDeleted;
	/**
	 * Read only key of item
	 */
	private int key;
	private E value;

	public int getKey() {
		return key;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public Item(int key, E value) {
		this.key = key;
		this.value = value;
		this.isDeleted = false;
	}
}
