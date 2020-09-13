
public class Main {

	public static void main(String[] args) throws Exception {
		HashtableOpenAdressing<Character> hashLinear = new HashtableOpenAdressing<Character>(11, HashProbings.LINEAR_PROBING);
		HashtableOpenAdressing<Character> hashQuadratic = new HashtableOpenAdressing<Character>(11, HashProbings.QUADRATIC_PROBING);
		HashtableOpenAdressing<Character> hashDouble = new HashtableOpenAdressing<Character>(11, 7);
		
		hashLinear = OpenAdressingUtilities.randomOpenHashGenerate(hashLinear.length(), hashLinear.getProbing());
		hashLinear.print();
		
		System.out.println();
		
		hashQuadratic = OpenAdressingUtilities.randomOpenHashGenerate(hashQuadratic.length(), hashQuadratic.getProbing());
		hashQuadratic.print();
		
		System.out.println();
		
		hashDouble = OpenAdressingUtilities.randomOpenHashGenerate(hashDouble.length(), hashDouble.getDoubleHashProbingFactor());
		hashDouble.print();
	}
}
