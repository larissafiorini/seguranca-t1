import java.util.*;

public class Kasiski2 {
	/*
	 * 
	 * - encontrar conjuntos repetidos de letras no texto - Depois de encontrar as
	 * seqüências repetidas, obtenha uma contagem do espaçamento entre as
	 * seqüências. - Obtenha fatores de espaçamento - Pega menor fator
	 * 
	 * 
	 * 
	 */

	private static int tamanho_chave = 0;
	private static int distancias[] = new int[200];
	private static int tamanho_palavra = 120;

	public int encontraTamanho(String texto_cifrado) {

		System.out.println(texto_cifrado.length());

		String comparador_strings = "";

		int cont = 0;
		for (int i = 0; i < (texto_cifrado.length() - tamanho_palavra); i++) {

			// procura trechos de string q se repetem pelo texto
			comparador_strings = texto_cifrado.substring(i, i + tamanho_palavra);

			for (int j = i + 1; j < (texto_cifrado.length() - tamanho_palavra); j++) {

				if (comparador_strings.equals(texto_cifrado.substring(j, j + tamanho_palavra)) && cont < 200) {
					// System.out.println(comparador_strings + " >> "+texto_cifrado.substring(j, j +
					// tamanho_palavra));
					// System.out.println("j: "+ j+ " i: "+i + " j-i: " + (j-i));
					distancias[cont] = j - i;
					cont++;
				}
			}
		}

		for (int i = 0; i < distancias.length; i++) {
			System.out.println("distancia[" + i + "] = " + distancias[i]);

		}
		Map<Integer, Integer> sums = new HashMap<>();

		int[] arr = { 10, 10, 10, 20, 30, 30 };
		arr = distancias;
		Map<Integer, Integer> m = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			if (!m.containsKey(arr[i]))
				m.put(arr[i], 1);
			else if (m.containsKey(arr[i])) {
				m.put(arr[i], m.get(arr[i]) + 1);
			}
		}

		int maiorvalor = 0;

		for (Integer key : m.keySet()) {
			if (key > 0) {
				System.out.println("key: " + key + " value: " + m.get(key));
				if (m.get(key) > maiorvalor)
					maiorvalor = m.get(key);
			}
		}
		int segundomaior = 0;
		for (Integer key : m.keySet()) {
			if (key > 0) {
				System.out.println("key: " + key + " value: " + m.get(key));
				if (m.get(key) != maiorvalor) {
					if (m.get(key) > segundomaior) {
						segundomaior = m.get(key);
					}
				}
			}
		}
		ArrayList<Integer> arrayfinal = new ArrayList<Integer>();
		System.out.println("maiorvalor: " + maiorvalor);
		for (Integer key : m.keySet()) {
			if (m.get(key) == maiorvalor || m.get(key) == segundomaior) {
				System.out.println("\nMAIOR: " + key + " " + m.get(key));

				int n = key;
				for (int i = 1; i <= n; i++) {
					if (n % i == 0) {
						System.out.printf("%d ", i);
						arrayfinal.add(i);}
				}
				System.out.println("\n");
			}
		}
		Collections.sort(arrayfinal);
		System.out.println("PRINTANDO ARRAYLIST");
		for (Integer i : arrayfinal) {
			System.out.print(i+",");
		}
		
		
		//encontra valor que mais se repete -> vai ser tamanho da chave

	
		Map<Integer, Integer> m2 = new HashMap<>();

		for (Integer i : arrayfinal) {
			if (!m2.containsKey(i))
				m2.put(i, 1);
			else if (m2.containsKey(i)) {
				m2.put(i, m2.get(i) + 1);
			}
		}
		int maior=0;
		int maiorkey = 0;
		for (Integer key : m2.keySet()) {
			if (key > 1) {
				System.out.println("key: " + key + " value: " + m2.get(key));
				if( m2.get(key) > maior) {
					maior = m2.get(key) ;
					maiorkey=key;
				}
			}
		}
		System.out.println("tamanho da chave agora: "+ maiorkey);
		
		
		// pega varias maiores 
		maior=0;
		int[] maioreskeys = new int[20];
		int p=0;
		for (Integer key : m2.keySet()) {
			if (key > 1) {
				System.out.println("key: " + key + " value: " + m2.get(key));
				if( m2.get(key) >= maior) {
					maior = m2.get(key) ;
					maioreskeys[p]=key;
					p++;
				}
			}
		}
		for (int i = 0; i < maioreskeys.length; i++) {
			if(maioreskeys[i]>0)
				System.out.println("tamanho da chave agora: "+ maioreskeys[i]);
		}
		
//		maiorvalor = 1;
//
//		for (Integer key : m.keySet()) {
//			if (key > 0) {
//				System.out.println("key: " + key + " value: " + m.get(key));
//				if (m.get(key) > maiorvalor)
//					maiorvalor = m.get(key);
//			}
//		}
//		System.out.println("Provavel chave: "+maiorvalor);

		
		// fatores de espacamento

		tamanho_chave = alg_euclidiano(distancias[0], distancias[1]);

		if (distancias.length > 2) {
			for (int k = 2; k < distancias.length; k++) {
				tamanho_chave = alg_euclidiano(tamanho_chave, distancias[k]);
				System.out.println("nas dist: " + tamanho_chave);
			}
		}
		return tamanho_chave;
	}

	// Euclidean algorithm : greatest common divisor of two numbers
	public static int alg_euclidiano(int p, int q) {
//    	if (q>0) {
//    	System.out.println("p: "+p+" q: "+q );
//    	System.out.println("p % q: "+p % q);}
//    	
//    	
		if (q == 0)
			return p;
		else
			return alg_euclidiano(q, p % q);
	}
}
