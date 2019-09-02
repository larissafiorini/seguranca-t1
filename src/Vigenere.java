import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vigenere {

	// indice de coincidencia para o portugues (probabilidade de dois elementos
	// aleat�rios de x serem identicos)
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

	public static ArrayList<String> cifrasCesar(int tamanho_chave, String texto_cifrado) {

		// monta cada linha com presenca em um caracter da chave, avancando pelo tamanho
		// da chave em cada index
		// texto_cifrado = "abcdabcdabcdabcdbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		// tamanho_chave = 4;
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

//		// outra forma
//		ArrayList<String> resultado = new ArrayList<String>();
//		StringBuilder[] sbb = new StringBuilder[tamanho_chave];
//		
//		for (int i = 0; i < tamanho_chave; i++)
//			sbb[i] = new StringBuilder();
//		
//		for (int i = 0; i < texto_cifrado.length(); i++)
//			sbb[i % tamanho_chave].append(texto_cifrado.charAt(i));
//		
//		for (StringBuilder s : sbb) {
////			System.out.println(s.toString());
//			resultado.add(s.toString());
//		}
		return linhas;
	}

	// encontrar caracteres da chave
	public static void analiseFrequencia(int tamanho_chave, String texto_cifrado) {
		ArrayList<String> linhas = cifrasCesar(tamanho_chave, texto_cifrado);

		// verifica quais caracteres aparecem mais em cada posicao possivel da chave
		// testando pro primeiro caracter da chave primeiro
		// String subtext = linhas.get(1);
		StringBuilder key_final = new StringBuilder();
		StringBuilder key_final2 = new StringBuilder();

		for (String linha : linhas) {
			String subtext = linha;

			// Loop para calcular a frequ�ncia
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
			System.out.println("maior_frequencia: " + maior_frequencia);
			// letra mais frequente portugues: a
			System.out.println((char) (maior_frequencia + 97) + " eh o a");
			System.out.println(maior_frequencia + 97);
			System.out.println((char) (maior_frequencia + 97));

			// 'E' is the maxChar
			// Console.WriteLine(string.Format("E is probably: {0} - {1}", maxChar,
			// maxFreq));
			int testa = (char) 'a' - (char) (maior_frequencia + 97);
			System.out.println("testa:" + testa);

			// Console.WriteLine(string.Format("Key would be: {0}", (char) (maxChar - 'e' +
			// 'A')));
			int keyyy = ((char) (maior_frequencia + 97) - (char) 'a') + 97;
			System.out.println("keyyy:" + keyyy);
			System.out.println("keyyy:" + (char) keyyy);
			key_final.append((char) keyyy);

			// each one needs 26 tries to decrypt, so a key length of 5 gives us a 26^5
			// cases to try
			// If, for example, in the first group, the letter �s� is the most repeated one,
			// we know that �e� is replaced by �s� and hence the key is �s� � �e� = �m�.
			char mais_repete = (char) (maior_frequencia + 97);

			int letra1 = (int) mais_repete - (int) 'a';
			if (letra1 < 0)
				letra1 += 26;
			System.out.println(letra1);
			System.out.println(letra1 + 97);

			int letra2 = (int) mais_repete - (int) 'a';
			letra2 += 97;

			System.out.println("possivel letra da chave: " + (char) letra2);
			key_final2.append((char) letra2);
		}
		System.out.println("KEYFINAL 1: " + key_final.toString());
		System.out.println("KEYFINAL 2: " + key_final2.toString());
	}

	// sabendo qual a chave, decifra vigenere pelas cifras de cesar simples agora
	// conhecidas
	public static void decifra(String chave, String texto_cifrado) {
		int tamanho_chave = chave.length();
		int d;
		char decifrada;
		StringBuilder texto_claro = new StringBuilder();

		for (int i = 0; i < texto_cifrado.length(); i++) {

			d = texto_cifrado.charAt(i) - chave.charAt(i % tamanho_chave);

			if (d < 0)
				d += 26;

			decifrada = (char) (d + 'a');
			texto_claro.append(decifrada);
		}
		System.out.println("\n\nMENSAGEM: \n");
		System.out.println(texto_claro.toString());

	}

}
