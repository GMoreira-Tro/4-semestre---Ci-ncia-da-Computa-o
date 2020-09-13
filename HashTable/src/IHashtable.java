
public interface IHashtable<E> {
	/**
	 * Delete an item from Hashtable
	 * @param key The item's key
	 * @return The deleted item
	 */
	public Item<E> delete(int key);
	/**
	 * Insert an item on Hashtable
	 * @param item The item to insert
	 * @return The position of the item on Hashtable
	 * @throws FullHashException 
	 */
	public int insert (Item<E> item) throws FullHashException;
	/**
	 * Search an item with a given key
	 * @param key The key to search an item with it
	 * @return The item with the key
	 */
	public Item<E> searchItemByKey(int key);
	/**
	 * Print the Hashtable
	 */
	public void print();
}
