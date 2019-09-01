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
	 * 1- encontra tamanho da chave 2- encontra caracteres da chave
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
		texto_cifrado = "abcdabcdabcdabcdbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		tamanho_chave = 4;
		String key = "";
		System.out.println("tamanho texto-cifrado: " + texto_cifrado.length());

		ArrayList<String> linhas = new ArrayList<String>();
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

		// verifica quais caracteres aparecem mais em cada posicao possivel da chave
		// testando pro primeiro caracter da chave primeiro
		String subtext = linhas.get(0);

		// Loop para calcular a frequência
		// ASCII:
		// 97 = a
		// 122 = z
		int frequencias[] = new int[26];
		int c = 0;
		for (int j = 97; j <= 122; j++) {
			int frequencia = 0;

			for (int k = 0; k < subtext.length(); k++) {
				if (subtext.charAt(k) == (char) j)
					frequencia++;
			}
			System.out.println(" asc[" + j + "] = " + frequencia);
			frequencias[c] = frequencia;
			c++;
		}
		int maior_frequencia = 0;
		// 26=numero de letras do alfabeto
		for (int i = 0; i < 26; i++) {
			if (frequencias[i] > frequencias[maior_frequencia])
				maior_frequencia = i;
		}
		System.out.println("maior_frequencia: "+maior_frequencia);

	}
}
