import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Criptoanalise - Cifra de Vigenere
 *
 * Este programa recebe um arquivo de entrada com um texto cifrado utilizando a
 * Cifra de Vigenere, realiza a busca pelo tamanho da chave que foi utilizada
 * para criptografar o texto, depois de encontrar o tamanho busca cada caractere
 * que compoe a chave. Ao final, decifra o texto cifrado utilizando a chave
 * encontrada e mostra o texto claro ao usuário.
 * 
 * Autora: Larissa Fiorini Martins
 *
 * Disciplina: Seguranca de Sistemas - PUCRS Data: 12.09.2019
 */

public class App {

	public static void main(String[] args) throws IOException {

		System.out.println("***Criptoanalise - Cifra de Vigenere***");

		// Le arquivo com texto cifrado
		Scanner sc = new Scanner(System.in);
		System.out.println("\nDigite o nome do arquivo com o texto cifrado: ");
		String arquivo = sc.nextLine();

		File input = new File(arquivo);
		BufferedReader in = new BufferedReader(new FileReader(input));
		String texto_cifrado = in.readLine();

		System.out.println("\nEscolha o idioma do texto. Digite: 1- portugues ou 2- ingles ");
		int idioma = sc.nextInt();

		// Classe que realiza a decifragem
		Vigenere vigenere = new Vigenere(texto_cifrado, idioma);

		int tamanho_chave;
		if (idioma == 2) {
			System.out.println("Digite o tamanho da chave: ");
			tamanho_chave = sc.nextInt();
		} else {
			// Busca tamanho da chave
			tamanho_chave = vigenere.encontraTamanhoChave();
		}

		// Busca caracteres da chave
		String chave = vigenere.encontraChave(tamanho_chave);

		// Decifra texto usando a chave encontrada
		String result = vigenere.decifra(chave);

		// Salva resultado no arquivo
		System.out.println("\nResultado salvo no arquivo: 'texto_claro.txt'");
		String outputfile = "texto_claro.txt";
		FileWriter out = new FileWriter(outputfile, false);
		out.write(result);
		out.close();
	}

}
