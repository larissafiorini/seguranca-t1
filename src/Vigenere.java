import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public static double chiSquare(int[] observedFreq, double[] expectedFreq) {
		double chi = 0;
		int sum = 0;
		for (int freq : observedFreq) {
			sum += freq;
		}
		for (int i = 0; i < 26; i++) {
			chi += Math.pow(((1.0 * observedFreq[i] / sum) - (expectedFreq[i] / 100.0)), 2.0)
					/ (expectedFreq[i] / 100.0);
		}
		return chi;

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
				System.out.println((char) j + " asc[" + j + "] = " + frequencia);
				frequencias[c] = frequencia;
				c++;
			}

			int maior_frequencia = 0;
			// 26=numero de letras do alfabeto
			for (int i = 0; i < 26; i++) {
				if (frequencias[i] > frequencias[maior_frequencia])
					maior_frequencia = i;
			}

			int deslocamento = maior_frequencia;// 0-26
			// somar com 1 letra da tabela ascII('a'=97)
			int letra_que_eh_o_a = deslocamento + 97;
			letra_que_eh_o_a = (char) letra_que_eh_o_a;
			deslocamento = letra_que_eh_o_a - 'a';
			System.out.println("DESLOCAMENTO: " + deslocamento);

			Utils utils = new Utils();

			double[] expected = new double[26];
			for (int i = 0; i < 26; i++) {
				// length * P(i)
				expected[i] = (linha.length() * (utils.FREQUENCIES[i] / 100));
			}

			double[] expectedCharacterCounts = expected;

			int[] counts = new int[26];
			for (char ccc : linha.toCharArray())
				counts[ccc - 'a']++;

			double tempFitness = 0.0;
			double fitness = Integer.MAX_VALUE;
			int shift = 0;
			for (int i = 0; i < 26; i++) {
				// ((Ci - Ei)^2)/Ei
				tempFitness += Math.pow(counts[i] - expectedCharacterCounts[i], 2) / expectedCharacterCounts[i];

				if (tempFitness > fitness) {
					fitness = tempFitness;
					shift = i;
				}

			}
			System.out.println("\nSHIFT: " + shift);
			char letra_chave = (char) (deslocamento + 'a');
			System.out.println("LETRA_CHAVE: " + letra_chave);

			int letra2 = (deslocamento + 'a');
			// int letra2 = (shift + 97);
			// FALTA IMPLEMENTAR: chiSquareAgainstEnglish(String ciphertext) ciphertext=CADA
			// LINHA DO ARRAYLIST

//			//LETRA Q CORRESPONDE AO 'A' DO PORTUGUES
//			//(char)(97+maior_frequencia)
//			
//			// A DO PORTUGUES - DESLOCAMENTO = DESLOCAMENTO FINAL DA CIFRA
//			System.out.println("MAIOR FREQ: "+maior_frequencia);
//
//			System.out.println("\n DESLOC.::: "+ ((maior_frequencia - 97)+97));
//			System.out.println("\n .::: "+ (char)((maior_frequencia - 97)+97));
//			
//			System.out.println("maior_frequencia: " + maior_frequencia);
//			// letra mais frequente portugues: a
//			System.out.println((char) (maior_frequencia + 97) + " eh o a");
//			System.out.println(maior_frequencia + 97);
//			System.out.println((char) (maior_frequencia + 97));
//
//			// 'E' is the maxChar
//			// Console.WriteLine(string.Format("E is probably: {0} - {1}", maxChar,
//			// maxFreq));
//			int testa = (char) 'a' - (char) (maior_frequencia + 97);
//			System.out.println("testa:" + testa);
//
//			// Console.WriteLine(string.Format("Key would be: {0}", (char) (maxChar - 'e' +
//			// 'A')));
//			int keyyy = ((char) (maior_frequencia + 97) - (char) 'a') + 97;
//			System.out.println("keyyy:" + keyyy);
//			System.out.println("keyyy:" + (char) keyyy);
//			key_final.append((char) keyyy);
//
//			// each one needs 26 tries to decrypt, so a key length of 5 gives us a 26^5
//			// cases to try
//			// If, for example, in the first group, the letter “s” is the most repeated one,
//			// we know that “e” is replaced by “s” and hence the key is “s” – “e” = “m”.
//			char mais_repete = (char) (maior_frequencia + 97);
//
//			int letra1 = (int) mais_repete - (int) 'a';
//			if (letra1 < 0)
//				letra1 += 26;
//			System.out.println("LETRA1:::::::::::: "+letra1);
//			System.out.println("LETRA1+97:::::::::::: "+(letra1 + 97));
//			System.out.println("LETRA1+97:::::::::::: "+(char)(letra1 + 97));
//
//			int letra2 = (int) mais_repete - (int) 'a';
//			letra2 += 97;
//
//			System.out.println("possivel letra da chave: " + (char) letra2);
			key_final2.append((char) letra2);
		}
		System.out.println("KEYFINAL 1: " + key_final.toString());
		System.out.println("\n\nKEYFINAL 2: " + key_final2.toString());
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
