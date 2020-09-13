
public class HashtableOpenAdressing<E> implements IHashtable<E>{

	/**
	 * The probing to be used by Hash
	 */
	private HashProbings probing;
	public HashProbings getProbing() {
		return probing;
	}

	/**
	 * The internal array of itens
	 */
	private Item<E>[] itensArray;
	/**
	 * If the probing was double hash, this integer will be its factor
	 */
	private int doubleHashProbingFactor;
	public int getDoubleHashProbingFactor() {
		return doubleHashProbingFactor;
	}

	/**
	 * The number of elements inserted in Hash
	 */
	private int numElements;
	public int getNumElements() {
		return numElements;
	}

	@SuppressWarnings("unchecked")
	public HashtableOpenAdressing(int length, HashProbings probing) {
		if(probing == HashProbings.DOUBLE_PROBING || length < 1)
			throw new IllegalArgumentException();
		
		this.itensArray = new Item[length];
		this.probing = probing;
		this.numElements = 0;
	}
	
	@SuppressWarnings("unchecked")
	public HashtableOpenAdressing(int length, int doubleHashProbingFactor) throws Exception {
		if(!verifyPrimeNumber(doubleHashProbingFactor))
			throw new NotPrimeParameterException();
		
		if(doubleHashProbingFactor >= length)
			throw new DoubleHashingProbingGreaterOrEqualThanHashLengthException();
		
		if(length < 1)
			throw new IllegalArgumentException();
		
		this.itensArray = new Item[length];
		this.doubleHashProbingFactor = doubleHashProbingFactor;
		this.probing = HashProbings.DOUBLE_PROBING;
		this.numElements = 0;
	}

	@Override
	public Item<E> delete(int key) {
		Item<E> deletedItem = searchItemByKey(key);
		deletedItem = null;
		
		return deletedItem;
	}

	@Override
	public int insert(Item<E> item) throws FullHashException {
		if(numElements == length())
			throw new FullHashException();
		
		int modularPosition = item.getKey() % length();
		
		if(probing == HashProbings.LINEAR_PROBING) {
			
			while(itensArray[modularPosition] != null) {
				modularPosition = (modularPosition + 1) % length();
			}
		}
		
		else if(probing == HashProbings.QUADRATIC_PROBING) {
			int collisionTimes = 0;
			int startPosition = modularPosition;
			
			while(itensArray[modularPosition] != null) {
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*collisionTimes) % length();
			}
		}
		
		else {
			int collisionTimes = 0;
			int startPosition = modularPosition;
			int secondaryFunction = doubleHashProbingFactor - (item.getKey() % doubleHashProbingFactor);
			
			while(itensArray[modularPosition] != null) {
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*secondaryFunction) % length();
			}
		}
		
		itensArray[modularPosition] = item;
		numElements++;
		return modularPosition;
	}

	@Override
	public Item<E> searchItemByKey(int key) {
		int modularPosition = key % length();
		
		if(probing == HashProbings.LINEAR_PROBING) {
			int count = 1;
			
			while(itensArray[modularPosition] == null) {
				if(count == length())
					return null;
				
				count++;
				modularPosition = (modularPosition + 1) % length();
			}
		}
		
		else if(probing == HashProbings.QUADRATIC_PROBING) {
			int collisionTimes = 0;
			int startPosition = modularPosition;
			
			while(itensArray[modularPosition] == null) {
				if(collisionTimes == length())
					return null;
							
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*collisionTimes) % length();
			}
		}
		
		else {
			int collisionTimes = 0;
			int startPosition = modularPosition;
			int secondaryFunction = doubleHashProbingFactor - (key % doubleHashProbingFactor);
			
			while(itensArray[modularPosition] == null) {
				if(collisionTimes == length())
					return null;
				
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*secondaryFunction) % length();
			}
		}
		
		return itensArray[modularPosition];
	}

	@Override
	public void print() {
		System.out.println("{");
		for(Item<E> item : itensArray) {
			if(item != null)
				System.out.println("Key: " + item.getKey() + " | Value: " + item.getValue());
		}
		System.out.println("}");
	}
	
	public int length() {
		return itensArray.length;
	}
	
	private boolean verifyPrimeNumber(int number) {
		if ((number > 2 && (number&1) == 0) || number < 2)
			return false;
		
		int sqrootNumber = (int) Math.sqrt(number);
		
		for (int i = 3; i <= sqrootNumber; i+=2) {
			if(number % i == 0)
				return false;
		}
		
		return true;
	}
	
}
