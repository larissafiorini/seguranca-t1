public class Kasiski {

	private static int tamanho_chave = 0;
	private static int distancias[] = new int[100];
	private static int tamanho_palavra = 7;
	

	public int encontraTamanho(String texto_cifrado) {

		System.out.println(texto_cifrado.length());

		String comparador_strings = "";
		

		int cont = 0;
		for (int i = 0; i < (texto_cifrado.length() - tamanho_palavra); i++) {

			/*
			 * 0  0+6=6
			
			1  1+6=7
			2  2+6=8*/
		
			// procura trechos de string q se repetem pelo texto
			comparador_strings = texto_cifrado.substring(i, i + tamanho_palavra);

			for (int j = i + 1; j < texto_cifrado.length() - tamanho_palavra; j++) {
				if (comparador_strings.equals(texto_cifrado.substring(j, j + tamanho_palavra)) && cont < 100) {
					//System.out.println(comparador_strings + " >> "+texto_cifrado.substring(j, j + tamanho_palavra));
					//System.out.println("j: "+ j+ " i: "+i + " j-i: " + (j-i));
					distancias[cont] = j - i;
					cont++;
				}
			}
		}

		tamanho_chave = alg_euclidiano(distancias[0], distancias[1]);
		System.out.println("distancias[0]: "+distancias[0]+" distancias[1]: "+distancias[1]);
		System.out.println("tamanho_chave: "+ tamanho_chave);

		if (distancias.length > 2) {
			for (int k = 2; k < distancias.length; k++) {
				tamanho_chave = alg_euclidiano(tamanho_chave, distancias[k]);
				System.out.println("nas dist: "+tamanho_chave);
			}
		}
		return tamanho_chave;
	}
	
	//  Euclidean algorithm : greatest common divisor of two numbers
    public static int alg_euclidiano(int p, int q) {
        if (q == 0) return p;
        else return alg_euclidiano(q, p % q);
    }

}