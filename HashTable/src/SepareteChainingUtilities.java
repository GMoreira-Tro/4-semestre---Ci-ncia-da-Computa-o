import java.util.ArrayList;
import java.util.List;

public class SepareteChainingUtilities {
	
	public static HashtableSepareteChaining<Character> randomSepareteChainingHashGenerate(int length, int elementsToInput, int maxKeyValue) 
			throws Exception {
		List<Integer> possibleKeys = new ArrayList<Integer>();
		populateValuesList(possibleKeys, maxKeyValue);
		
		HashtableSepareteChaining<Character> hash = new HashtableSepareteChaining<Character>(length);
		
		for(int i = 0; i < elementsToInput; i++) {
			int valuesListIndex = (int)(Math.random() * possibleKeys.size());
			
			hash.insert(new Item<Character>(
			valuesListIndex,
			(char)(Math.random() * 80 + 48)) 
			);
			
			possibleKeys.remove(valuesListIndex);
		}
		
		return hash;
	}
	
	private static void populateValuesList(List<Integer> valuesList, int quantValues) {
		for (int i = 1; i <= quantValues; i++) {
			valuesList.add(i);
		}
	}
	
	public static void teachersHashCreation(HashtableSepareteChaining<Character> hash) throws Exception {
		hash.clear();
		
		hash.insert(new Item<Character>(7,'a'));
		hash.insert(new Item<Character>(17,'b'));
		hash.insert(new Item<Character>(36,'c'));
		hash.insert(new Item<Character>(100,'d'));
		hash.insert(new Item<Character>(106,'e'));
		hash.insert(new Item<Character>(205,'f'));
	}
}
