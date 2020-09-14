
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
		int arrayIndex = searchArrayIndexByKey(key);
		if(arrayIndex == -1)
			return null;
		
		Item<E> deletedItem = itensArray[arrayIndex];
		itensArray[arrayIndex] = null;
		this.numElements--;
		
		return deletedItem;
	}

	@Override
	public int insert(Item<E> item) throws Exception {
		if(numElements == length())
			throw new FullHashException();
		
		int modularPosition = item.getKey() % length();
		
		if(probing == HashProbings.LINEAR_PROBING) {
			
			while(itensArray[modularPosition] != null) {
				if(item.getKey() == itensArray[modularPosition].getKey())
					throw new DuplicatedKeyException();
				
				modularPosition = (modularPosition + 1) % length();
			}
		}
		
		else if(probing == HashProbings.QUADRATIC_PROBING) {
			int collisionTimes = 0;
			int startPosition = modularPosition;
			
			while(itensArray[modularPosition] != null) {
				if(item.getKey() == itensArray[modularPosition].getKey())
					throw new DuplicatedKeyException();
				
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*collisionTimes) % length();
			}
		}
		
		else {
			int collisionTimes = 0;
			int startPosition = modularPosition;
			int secondaryFunction = doubleHashProbingFactor - (item.getKey() % doubleHashProbingFactor);
			
			while(itensArray[modularPosition] != null) {
				if(item.getKey() == itensArray[modularPosition].getKey())
					throw new DuplicatedKeyException();
				
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
		
		//Se a posição que iria na primeira inserção for nula, significa que não há a chave na Hash
		if(itensArray[modularPosition] == null)
			return null;
		
		if(probing == HashProbings.LINEAR_PROBING) {
			int count = 1, maxCollisionTimes = length() - 1;
			
			while(itensArray[modularPosition].getKey() != key) {
				if(count == maxCollisionTimes)
					return null;
				
				count++;
				modularPosition = (modularPosition + 1) % length();
				
				//Se a posição que iria na próxima inserção for nula, significa que não há a chave na hash
				if(itensArray[modularPosition] == null)
					return null;
			}
		}
		
		else if(probing == HashProbings.QUADRATIC_PROBING) {
			int collisionTimes = 0, maxCollisionTimes = length() - 1;
			int startPosition = modularPosition;
			
			while(itensArray[modularPosition].getKey() != key) {
				if(collisionTimes == maxCollisionTimes)
					return null;
							
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*collisionTimes) % length();
				
				//Se a posição que iria na próxima inserção for nula, significa que não há a chave na hash
				if(itensArray[modularPosition] == null)
					return null;
			}
		}
		
		else {
			int collisionTimes = 0, maxCollisionTimes = length() - 1;
			int startPosition = modularPosition;
			int secondaryFunction = doubleHashProbingFactor - (key % doubleHashProbingFactor);
			
			while(itensArray[modularPosition].getKey() != key) {
				if(collisionTimes == maxCollisionTimes)
					return null;
				
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*secondaryFunction) % length();
				
				//Se a posição que iria na próxima inserção for nula, significa que não há a chave na hash
				if(itensArray[modularPosition] == null)
					return null;
			}
		}
		
		return itensArray[modularPosition];
	}
	
	private int searchArrayIndexByKey(int key) {
		int modularPosition = key % length();
		
		//Se a posição que iria na primeira inserção for nula, significa que não há a chave na Hash
		if(itensArray[modularPosition] == null)
			return -1;
		
		if(probing == HashProbings.LINEAR_PROBING) {
			int count = 1, maxCollisionTimes = length() - 1;
			
			while(itensArray[modularPosition].getKey() != key) {
				if(count == maxCollisionTimes)
					return -1;
				
				count++;
				modularPosition = (modularPosition + 1) % length();
				
				//Se a posição que iria na próxima inserção for nula, significa que não há a chave na hash
				if(itensArray[modularPosition] == null)
					return -1;
			}
		}
		
		else if(probing == HashProbings.QUADRATIC_PROBING) {
			int collisionTimes = 0, maxCollisionTimes = length() - 1;
			int startPosition = modularPosition;
			
			while(itensArray[modularPosition].getKey() != key) {
				if(collisionTimes == maxCollisionTimes)
					return -1;
							
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*collisionTimes) % length();
				
				//Se a posição que iria na próxima inserção for nula, significa que não há a chave na hash
				if(itensArray[modularPosition] == null)
					return -1;
			}
		}
		
		else {
			int collisionTimes = 0, maxCollisionTimes = length() - 1;
			int startPosition = modularPosition;
			int secondaryFunction = doubleHashProbingFactor - (key % doubleHashProbingFactor);
			
			while(itensArray[modularPosition].getKey() != key) {
				if(collisionTimes == maxCollisionTimes)
					return -1;
				
				collisionTimes++;
				modularPosition = (startPosition + collisionTimes*secondaryFunction) % length();
				
				//Se a posição que iria na próxima inserção for nula, significa que não há a chave na hash
				if(itensArray[modularPosition] == null)
					return -1;
			}
		}
		
		return modularPosition;
	}

	@Override
	public void print() {
		int i = 0;
		
		System.out.println("{");
		for(Item<E> item : itensArray) {
			if(item != null)
				System.out.println("Key: " + item.getKey() + " | Value: " + item.getValue()
				+ " | Array index: " + i);
			i++;
		}
		System.out.println("}");
	}
	
	public int length() {
		return itensArray.length;
	}
	
	@SuppressWarnings("unchecked")
	public void clear() {
		numElements = 0;
		itensArray = new Item[length()];
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
