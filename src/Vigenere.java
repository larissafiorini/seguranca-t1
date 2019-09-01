import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Vigenere {

	// indice de coincidencia para o portugues (probabilidade de dois elementos
	// aleatórios de x serem identicos)
	private static final double index_of_coincidence = 0.072723;
	private static final int min_key_length = 2;
	private static final int max_key_length = 25;

	public Vigenere() {
	}

	/*
	 * 1- encontra tamanho da chave 
	 * 2- encontra caracteres da chave
	 * 
	 * 
	 */

	public static int encontraTamanhoChave(String texto_cifrado) {
		// indice de coincidencia

		// kasiski
		Kasiski kasiski = new Kasiski();
		int tamanho = kasiski.encontraTamanho(texto_cifrado);
		System.out.println("tamanho> " + tamanho);
		return tamanho;
	}

	// encontrar caracteres da chave
	public static void analiseFrequencia(int tamanho_chave, String texto_cifrado) {

		// monta cada linha com presenca em um caracter da chave, avancando pelo tamanho
		// da chave em cada index
		texto_cifrado = "abcdabcdabcdabcd";
		tamanho_chave = 4;
		String key = "";
		System.out.println("tamanho texto-cifrado: "+texto_cifrado.length());
		
		ArrayList<String> linhas= new ArrayList<String>();
		int index = 0;
		while (index < tamanho_chave) {
			StringBuilder sb = new StringBuilder();

			for (int i = index; i < texto_cifrado.length(); i = i + tamanho_chave) {
				sb.append(texto_cifrado.charAt(i));
			}
			linhas.add(sb.toString());
			index++;
		}

		for (String linha : linhas) {
			System.out.println(linha);
		}

	}
}
