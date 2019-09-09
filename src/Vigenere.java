import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Classe que controla cada etapa da decifragem do texto.
 * 
 * */
public class Vigenere {
	
	public Vigenere() {
	}

	public static int encontraTamanhoChave(String texto_cifrado) {
		System.out.println("Utilizando Teste de Kasiski para descobrir tamanho da chave.. ");

		Kasiski kasiski = new Kasiski();
		int tamanho = kasiski.encontraTamanho(texto_cifrado);
		System.out.println("Tamanho da chave: " + tamanho);
		return tamanho;
	}

	public static String encontraChave(int tamanho_chave, String texto_cifrado) {
		System.out.println("Utilizando analise de frequencia para descobrir os caracteres da chave");

		AnaliseFrequencias f = new AnaliseFrequencias(texto_cifrado);

		// quebrar texto cifrado em conjuntos do tamanho da chave.
		// monta matriz com colunas=tamanho_chave.
		Map<Integer, ArrayList<Character>> matriz_texto_cifrado = f.divideTexto(tamanho_chave);

		StringBuilder chave = new StringBuilder();

		// Encontra o caracter da chave a partir de cada coluna da matriz
		for (int i = 0; i < tamanho_chave; i++) {
			StringBuilder coluna = new StringBuilder();

			for (int j = 0; j < matriz_texto_cifrado.size(); j++) {
				try {
					coluna.append(matriz_texto_cifrado.get(j).get(i));
				} catch (IndexOutOfBoundsException e) {
				}
			}
			chave.append(f.descobreCaracter(coluna.toString()));
		}

		System.out.println("\nChave encontrada: " + chave.toString() + "\n");

		return chave.toString();
	}

	// Sabendo qual a chave, decifra vigenere pelas cifras de cesar simples agora
	// conhecidas
	public static String decifra(String chave, String texto_cifrado) {
		StringBuilder texto_claro = new StringBuilder();
		char letra_decifrada;
		int deslocamento;

		for (int i = 0; i < texto_cifrado.length(); i++) {
			// converte para range 0-25
			deslocamento = texto_cifrado.charAt(i) - chave.charAt(i % chave.length());
			if (deslocamento < 0)
				deslocamento += 26;

			// converte para letra (ASCII)
			letra_decifrada = (char) (deslocamento + 'a');
			texto_claro.append(letra_decifrada);
		}
		System.out.println("\n\nMENSAGEM: \n" + texto_claro.toString());
		return texto_claro.toString();
	}

}
