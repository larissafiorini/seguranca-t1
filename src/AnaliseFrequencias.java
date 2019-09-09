import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Classe que realiza analise de frequencias para encontrar os caracteres da chave em portugues.
 * 
 * */
public class AnaliseFrequencias {
	/*
	 * Frequencias das letras no portugues
	 */
	public static double A = 14.634;
	public static double B = 1.043;
	public static double C = 3.882;
	public static double D = 4.992;
	public static double E = 12.570;
	public static double F = 1.023;
	public static double G = 1.303;
	public static double H = 0.781;
	public static double I = 6.186;
	public static double J = 0.397;
	public static double K = 0.015;
	public static double L = 2.779;
	public static double M = 4.738;
	public static double N = 4.446;
	public static double O = 9.735;
	public static double P = 2.523;
	public static double Q = 1.204;
	public static double R = 6.530;
	public static double S = 6.805;
	public static double T = 4.336;
	public static double U = 3.639;
	public static double V = 1.575;
	public static double W = 0.037;
	public static double X = 0.253;
	public static double Y = 0.006;
	public static double Z = 0.470;

	public static double[] FREQUENCIAS = { A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y,
			Z };

	private Character[] ALFABETO = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static String texto_cifrado = "";

	public AnaliseFrequencias(String tc) {
		texto_cifrado = tc;
	}

	// Divide o texto em uma matriz onde as colunas = tamanho da chave.
	// Cada coluna corresponde a um caractere que foi cifrado com um caractere da chave.
	public Map<Integer, ArrayList<Character>> divideTexto(int tamanho_chave) {

		Map<Integer, ArrayList<Character>> matriz_texto_cifrado = new HashMap<>();

		// Arredonda valor da divisao do tamanho do texto / tamanho da chave que sera o
		// numero de linhas da matriz
		int numero_linhas = (int) Math.ceil((double) texto_cifrado.length() / tamanho_chave);

		int index = 0;

		// Preenche matriz
		for (int i = 0; i < numero_linhas; i++) {

			matriz_texto_cifrado.put(i, new ArrayList<Character>());

			// Para cada coluna da linha, adiciona a letra
			for (int j = 0; j < tamanho_chave; j++) {
				if (index >= texto_cifrado.length())
					break;

				matriz_texto_cifrado.get(i).add(texto_cifrado.charAt(index));

				index++;
			}
		}

		return matriz_texto_cifrado;

//		for (Integer key : matriz_texto_cifrado.keySet()) {
//			System.out.println(matriz_texto_cifrado.get(key).toString());
//		}
	}

	// Map : <Letra, Frequencia>
	private Map<Character, Integer> frequencia_letra = new HashMap<>();
	
	public static Double chiSquare(double freq_observada, double freq_esperada) {

		return Math.pow(freq_observada - freq_esperada, 2) / freq_esperada;
	}

	// Analisa cada coluna para descobrir a letra.
	public Character descobreCaracter(String coluna) {
		// Chi-Square: qual letra do alfabeto resulta na frequencia mais proxima a
		// frequencia da lıngua portuguesa
		Map<Character, Double> chiSquare = new HashMap<>();

		String coluna_analise = coluna;

		for (int i = 1; i < ALFABETO.length; i++) {
			
			double chi = 0;
			// Busca frequencia de cada letra da coluna
			frequenciaLetra(coluna_analise);

			for (int j = 0; j < ALFABETO.length; j++) {
				// + aparece na coluna  , frequencia no portugues * tamanho da coluna
				chi += chiSquare(frequencia_letra.get(ALFABETO[j]), FREQUENCIAS[j] * coluna_analise.length());
			}
			// armazena letra e valor ChiSquare
			chiSquare.put(ALFABETO[i - 1], chi);
			// desloca letras na coluna conforme valor do caracter
			coluna_analise = deslocaLetras(ALFABETO[i], coluna);
		}

		// escolhe letra de menor valor
		// A low value for chi-square means there is a high correlation between your two sets of data
		// (frequencia no texto x frequencia no alfabeto portugues). 
		Character letra = new Character('a');
		Double valor_minimmo = new Double(Double.MAX_VALUE);
		for (Character key : chiSquare.keySet()) {
			if (valor_minimmo > chiSquare.get(key)) {
				letra = key;
				valor_minimmo = chiSquare.get(key);
			}
		}

		return letra;
	}

	// Conta a frequencia de cada letra em umacoluna de caracteres
	public void frequenciaLetra(String coluna) {
		// Inicializa o Array de frequencia de caracteres <Letra,0>
		for (int i = 0; i < ALFABETO.length; i++) {
			frequencia_letra.put(ALFABETO[i], 0);
		}
		// Calcula frequencia de cada letra na coluna
		for (int j = 0; j < coluna.length(); j++) {
			frequencia_letra.put(coluna.charAt(j), frequencia_letra.get(coluna.charAt(j)) + 1);
		}
	}

	// Decifra a coluna a partir da letra.
	public String deslocaLetras(Character c, String coluna) {

		// Coluna com deslocamento correto utilizado na cifragem. 
		StringBuilder novaColuna = new StringBuilder();
		
		// ascii
		//97 = a
		// 122 = z
		
		System.out.println("\nCARACTER: "+c+"CARACTER NO ASCII " +(int) c);
		// monta coluna da tabela de vigenere do caracter 
		ArrayList<Character> ac = new ArrayList<>();
		int k = (int) c;
		for (int i = 0; i < ALFABETO.length; i++) {
			System.out.print((char) k +",");
			ac.add((char) k);
			if(k ==122)
				k=97;
			else
				k++;
		}
		
		int index = 0;

		for (int i = 0; i < coluna.length(); i++) {

			for (int j = 0; j < ac.size(); j++) {
				if (ac.get(j) == coluna.charAt(i)) {
					index = j;
					break;
				}
			}

			novaColuna.append(ALFABETO[index]);
		}
		System.out.println("\n"+coluna);
		System.out.println(novaColuna.toString());
		return novaColuna.toString();

	}
	
	

}
