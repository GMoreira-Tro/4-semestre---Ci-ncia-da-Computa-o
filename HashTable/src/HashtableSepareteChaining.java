import java.util.LinkedList;

public class HashtableSepareteChaining<E> implements IHashtable<E>{

	/**
	 * The linked list of itens array
	 */
	private LinkedList<Item<E>>[] itensLinkedListArray;
	
	private int numElements;
	public int getNumElements() {
		return this.numElements;
	}
	
	@SuppressWarnings("unchecked")
	public HashtableSepareteChaining(int length) {
		itensLinkedListArray = new LinkedList[length];
		this.numElements = 0;
		
		for(int i = 0; i < length; i++) {
			itensLinkedListArray[i] = new LinkedList<Item<E>>();
		}
	}
	
	@Override
	public Item<E> delete(int key) {
		int modularPosition = key % length();
		int index = 0;
		
		//Se a posição que iria na primeira inserção for nula, significa que não há a chave na Hash
		for(Item<E> item : itensLinkedListArray[modularPosition]) {
			if(item.getKey() == key) {
				numElements--;
				return itensLinkedListArray[modularPosition].remove(index);
			}
			index++;
		}
		return null;
	}

	@Override
	public int insert(Item<E> item) throws Exception {
		int modularPosition = item.getKey() % length();
		int linkedListIndex = 0;
		
		try {
			Item<E> node = itensLinkedListArray[modularPosition].get(linkedListIndex);
			
			while(node != null) {
				if(item.getKey() == node.getKey())
					throw new DuplicatedKeyException();
				
				node = itensLinkedListArray[modularPosition].get(++linkedListIndex);
			}
			
			node = item;
		}
		catch(IndexOutOfBoundsException e) {
			itensLinkedListArray[modularPosition].addFirst(item);
		}
		numElements++;
		return modularPosition;
	}

	@Override
	public Item<E> searchItemByKey(int key) {
		int modularPosition = key % length();
		
		//Se a posição que iria na primeira inserção for nula, significa que não há a chave na Hash
		for(Item<E> item : itensLinkedListArray[modularPosition]) {
			if(item.getKey() == key)
				return item;
		}
		return null;
	}

	@Override
	public void print() {
		int i = 1, j = 0;
		
		System.out.println("{");
		for(LinkedList<Item<E>> linkedList : itensLinkedListArray) {
			if(!linkedList.isEmpty())
				System.out.println(i + "º linked list");
			for(Item<E> item : linkedList) {
				if(item != null) {
					if(!item.isDeleted) {
						System.out.println("Key: " + item.getKey() + " | Value: " + item.getValue()
						+ " | Linked list index: " + j);
					}
				}
				j++;
			}
			if(!linkedList.isEmpty())
				System.out.println();
			i++;
			j = 0;
		}
		System.out.println("}");
	}
	
	public int length() {
		return itensLinkedListArray.length;
	}
	
	@SuppressWarnings("unchecked")
	public void clear() {
		numElements = 0;
		itensLinkedListArray = new LinkedList[length()];
		
		for(int i = 0; i < length(); i++) {
			itensLinkedListArray[i] = new LinkedList<Item<E>>();
		}
	}

}
