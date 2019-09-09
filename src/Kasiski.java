/*
 * 
 * Classe que realiza o teste de Kasiski para encontrar o tamanho da chave. 
 * 
 * */

public class Kasiski {

	private static int tamanho_chave = 0;
	private static int distancias[] = new int[200];
	private static int tamanho_palavra = 12;
	

	public int encontraTamanho(String texto_cifrado) {

		System.out.println(texto_cifrado.length());

		String comparador_strings = "";

		int cont = 0;
		for (int i = 0; i < (texto_cifrado.length() - tamanho_palavra); i++) {
	
			// procura trechos de string q se repetem pelo texto
			comparador_strings = texto_cifrado.substring(i, i + tamanho_palavra);

			for (int j = i + 1; j < texto_cifrado.length() - tamanho_palavra; j++) {
				if (comparador_strings.equals(texto_cifrado.substring(j, j + tamanho_palavra)) && cont < 100) {
					distancias[cont] = j - i;
					cont++;
				}
			}
		}

		tamanho_chave = alg_euclidiano(distancias[0], distancias[1]);

		if (distancias.length > 2) {
			for (int k = 2; k < distancias.length; k++) {
				tamanho_chave = alg_euclidiano(tamanho_chave, distancias[k]);
				System.out.println("nas dist: "+tamanho_chave);
			}
		}
		return tamanho_chave;
	}
	
	// Algoritmo Euclidiano: maior divisor comum de dois números
    public static int alg_euclidiano(int p, int q) {
        if (q == 0) return p;
        else return alg_euclidiano(q, p % q);
    }

}