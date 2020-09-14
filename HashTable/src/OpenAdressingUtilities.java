import java.util.*;

public class OpenAdressingUtilities {
	/**
	 * Generates a random Hashtable (won't give duplicate keys) 
	 * @param length The length of the Hash array
	 * @param probing The probing of the Hash collision
	 * @param keyMaxValue The max value that a key can have
	 * @return A Hashtable with random values
	 * @throws Exception
	 */
	public static HashtableOpenAdressing<Character> randomOpenHashGenerate(int length, HashProbings probing,
			int keyMaxValue) throws Exception {
		
		if (keyMaxValue < length) {
			System.out.println("Key max value must be greater than hash length");
			return null;
		}
		
		int rand = (int)(Math.random() * length + 1);
		HashtableOpenAdressing<Character> hash = new HashtableOpenAdressing<Character>(length, probing);
		
		List<Integer> valuesList = new ArrayList<Integer>();
		populateValuesList(valuesList, keyMaxValue);
		
		for (int i = 0; i < rand; i++) {
			int valuesListIndex = (int)(Math.random() * valuesList.size());
			
			hash.insert(new Item<Character>(
					valuesList.get(valuesListIndex),
					(char)(Math.random() * 80 + 48)) 
				);
			valuesList.remove(valuesListIndex);
		}
		return hash;
	}
	
	/**
	 * Generates a random Hashtable with double hashing probing in collisions (won't give duplicate keys)
	 * @param length The length of the Hash array
	 * @param doubleHashingProbingFactor The factor to use in double hash probing collision
	 * @param keyMaxValue The max value that a key can have
	 * @return A Hashtable with random values
	 * @throws Exception
	 */
	public static HashtableOpenAdressing<Character> randomOpenHashGenerate(int length, int doubleHashingProbingFactor,
			int keyMaxValue) throws Exception {
		
		if (keyMaxValue < length) {
			System.out.println("Key max value must be greater than hash length");
			return null;
		}
		
		int rand = (int)(Math.random() * length);
		HashtableOpenAdressing<Character> hash = new HashtableOpenAdressing<Character>(length, doubleHashingProbingFactor);
		
		List<Integer> valuesList = new ArrayList<Integer>();
		populateValuesList(valuesList, keyMaxValue);
		
		for (int i = 0; i < rand; i++) {
			int valuesListIndex = (int)(Math.random() * valuesList.size());
			
			hash.insert(new Item<Character>(
					valuesList.get(valuesListIndex),
					(char)(Math.random() * 80 + 48)) 
				);
			valuesList.remove(valuesListIndex);
		}
		return hash;
	}
	
	private static void populateValuesList(List<Integer> valuesList, int quantValues) {
		for (int i = 1; i <= quantValues; i++) {
			valuesList.add(i);
		}
	}
	
	public static void teachersHashCreation(HashtableOpenAdressing<Character> hash) throws Exception {
		hash.clear();
		
		hash.insert(new Item<Character>(7,'a'));
		hash.insert(new Item<Character>(17,'b'));
		hash.insert(new Item<Character>(36,'c'));
		hash.insert(new Item<Character>(100,'d'));
		hash.insert(new Item<Character>(106,'e'));
		hash.insert(new Item<Character>(205,'f'));
	}
}
