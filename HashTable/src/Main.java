
public class Main {

	/**
	 * Defini que o valor máximo das keys seria 20 para meus testes de geração de Hashs randômicas, mas qualquer coisa só mudar na constante
	 */
	static final int MaxKeyValue = 20;
	
	public static void main(String[] args) throws Exception {
		//Como se declara normalmente. Embora fiz funções que retornam já Hashs, deixei as declarações aqui pra expô-las
		HashtableOpenAdressing<Character> hashLinear = new HashtableOpenAdressing<Character>(11, HashProbings.LINEAR_PROBING);
		HashtableOpenAdressing<Character> hashQuadratic = new HashtableOpenAdressing<Character>(11, HashProbings.QUADRATIC_PROBING);
		HashtableOpenAdressing<Character> hashDouble = new HashtableOpenAdressing<Character>(11, 7);
		
		//Primeira bateria de testes
		/*System.out.println("Gerando 3 Hashs aleatórias para ver primeiro:\n");
		System.out.println("Hash Linear");
		hashLinear = OpenAdressingUtilities.randomOpenHashGenerate(hashLinear.length(), hashLinear.getProbing(), MaxKeyValue);
		hashLinear.print();
		
		System.out.println();
		
		System.out.println("Hash Quadrática");
		System.out.println("ATENÇÃO! EXISTE A POSSIBILIDADE DA HASH QUADRÁTICA DAR TESTE CIRCULAR DE COLISÃO!");
		hashQuadratic = OpenAdressingUtilities.randomOpenHashGenerate(hashQuadratic.length(), hashQuadratic.getProbing(), MaxKeyValue);
		hashQuadratic.print();
		
		System.out.println();
		
		System.out.println("Hash Dupla");
		hashDouble = OpenAdressingUtilities.randomOpenHashGenerate(hashDouble.length(), hashDouble.getDoubleHashProbingFactor(), MaxKeyValue);
		hashDouble.print();
		*/
		System.out.println("\nAgora recriando as 3 hashs com os valores pedidos pela professora:\n");
		OpenAdressingUtilities.teachersHashCreation(hashLinear);
		OpenAdressingUtilities.teachersHashCreation(hashQuadratic);
		OpenAdressingUtilities.teachersHashCreation(hashDouble);
		
		System.out.println("Hash Linear");
		hashLinear.print();
		System.out.println("\nHash Quadrática");
		hashQuadratic.print();
		System.out.println("\nHash Dupla");
		hashDouble.print();
		
		System.out.println("\nHora de algumas buscas:\n");
		System.out.println("Buscas na Hash linear:\n");
		System.out.println("Elemento da chave 100: " + hashLinear.searchItemByKey(100).getValue());
		System.out.println("Elemento da chave 205: " + hashLinear.searchItemByKey(205).getValue());
		System.out.println("Elemento da chave 7: " + hashLinear.searchItemByKey(7).getValue());
		System.out.println("Elemento da chave 36: " + hashLinear.searchItemByKey(36).getValue());
		System.out.println("Elemento da chave 17: " + hashLinear.searchItemByKey(17).getValue());
		System.out.println("Elemento nulo da chave 206: " + hashLinear.searchItemByKey(206));
		System.out.println("Elemento nulo da chave 0: " + hashLinear.searchItemByKey(0));
		System.out.println("Elemento da chave 106: " + hashLinear.searchItemByKey(106).getValue());
		
		System.out.println("\nBuscas na Hash Quadrática:\n");
		System.out.println("Elemento da chave 100: " + hashQuadratic.searchItemByKey(100).getValue());
		System.out.println("Elemento da chave 205: " + hashQuadratic.searchItemByKey(205).getValue());
		System.out.println("Elemento da chave 7: " + hashQuadratic.searchItemByKey(7).getValue());
		System.out.println("Elemento da chave 36: " + hashQuadratic.searchItemByKey(36).getValue());
		System.out.println("Elemento da chave 17: " + hashQuadratic.searchItemByKey(17).getValue());
		System.out.println("Elemento nulo da chave 206: " + hashQuadratic.searchItemByKey(206));
		System.out.println("Elemento nulo da chave 0: " + hashQuadratic.searchItemByKey(0));
		System.out.println("Elemento da chave 106: " + hashQuadratic.searchItemByKey(106).getValue());
		
		System.out.println("\nBuscas na Hash Dupla:\n");
		System.out.println("Elemento da chave 100: " + hashDouble.searchItemByKey(100).getValue());
		System.out.println("Elemento da chave 205: " + hashDouble.searchItemByKey(205).getValue());
		System.out.println("Elemento da chave 7: " + hashDouble.searchItemByKey(7).getValue());
		System.out.println("Elemento da chave 36: " + hashDouble.searchItemByKey(36).getValue());
		System.out.println("Elemento da chave 17: " + hashDouble.searchItemByKey(17).getValue());
		System.out.println("Elemento nulo da chave 206: " + hashDouble.searchItemByKey(206));
		System.out.println("Elemento nulo da chave 0: " + hashDouble.searchItemByKey(0));
		System.out.println("Elemento da chave 106: " + hashDouble.searchItemByKey(106).getValue());
		
		System.out.println("\nHora da tão temida deleção:\n");
		System.out.println("Deleções na Hash Linear:\n");
		System.out.println("Removendo o elemento da chave 205: " + hashLinear.delete(205).getValue());
		System.out.println("Removendo o elemento da chave 36: " + hashLinear.delete(36).getValue());
		System.out.println("Removendo o elemento que nem tem da chave 16: " + hashLinear.delete(16));
		System.out.println("Inserindo agora o elemento da chave 16 na posição " + hashLinear.insert(new Item<Character>(16, 'X')));
		System.out.println("Vamo printar de novo:\n");
		hashLinear.print();
		
		// Mais randoms testes pra ti sora
		/*
		hashDouble.clear();
		hashDouble.insert(new Item<Character>(7,'a'));
		hashDouble.insert(new Item<Character>(18,'X'));
		hashDouble.insert(new Item<Character>(29,'X'));
		hashDouble.insert(new Item<Character>(40,'X'));
		hashDouble.delete(7);
		hashDouble.delete(40);
		System.out.println(hashDouble.searchItemByKey(29).getKey());
		*/
		
	}
}
