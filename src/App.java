import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		
		// Le arquivo com texto cifrado
		Scanner sc = new Scanner(System.in);
		String arquivo; 
		/*System.out.println("Digite o nome do arquivo com o texto cifrado: ");
		file = sc.nextLine();*/
		arquivo="texto_cifrado.txt";
		File input = new File(arquivo);
		BufferedReader in = new BufferedReader(new FileReader(input));
		String texto_cifrado = in.readLine();
		
		Vigenere vigenere = new Vigenere();
		
		// encontrar tamanho da chave
		int tamanho_chave = vigenere.encontraTamanhoChave(texto_cifrado);
		vigenere.analiseFrequencia(tamanho_chave,texto_cifrado); 
		vigenere.decifra("avelino", texto_cifrado);
		
		// quebrar texto cifrado em conjuntos do tamanho da chave
		
		String result = "teste";
		
		String outputfile = "texto_claro.txt";
		FileWriter out = new FileWriter(outputfile, false);
		out.write(result);
		out.close();
	}

}
