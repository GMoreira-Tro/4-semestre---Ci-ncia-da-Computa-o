
public class OpenAdressingUtilities {
	public static HashtableOpenAdressing<Character> randomOpenHashGenerate(int length, HashProbings probing) throws Exception {
		int rand = (int)(Math.random() * length + 1);
		HashtableOpenAdressing<Character> hash = new HashtableOpenAdressing<Character>(length, probing);
		
		for (int i = 0; i < rand; i++) {
			hash.insert(new Item<Character>(
					(int)(Math.random() * 20),
					(char)(Math.random() * 80 + 48)) 
				);
			
		}
		return hash;
	}
	
	public static HashtableOpenAdressing<Character> randomOpenHashGenerate(int length, int doubleHashingProbingFactor) throws Exception {
		int rand = (int)(Math.random() * length);
		HashtableOpenAdressing<Character> hash = new HashtableOpenAdressing<Character>(length, doubleHashingProbingFactor);
		
		for (int i = 0; i < rand; i++) {
			hash.insert(new Item<Character>(
					(int)(Math.random() * 20),
					(char)(Math.random() * 80 + 48)) 
				);
			
		}
		return hash;
	}
}
