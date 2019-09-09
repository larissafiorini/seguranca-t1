import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

public class IndexCoincidencia {

	private double index_coincidence_portugues = 0.072723;

	private static String texto_cifrado = "";

	public IndexCoincidencia(String tc) {
		texto_cifrado = tc;
	}
//	//calcula index pra cada coluna
//	public void calcula(String texto_cifrado, Map<Character, Integer> frequencia_letra) {
//		System.out.println(texto_cifrado.length());
//
//		double N = frequencia_letra.get(ALFABETO[i]);
//		double ic = 1/(N*(N-1));
//		
//		ic - index_coincidence_portugues
//		
//	}
//}

	public void calcula() {
		AnaliseFrequencias f = new AnaliseFrequencias(texto_cifrado);
		// Map : <Letra, Frequencia>
		Map<Character, Integer> frequencia_letra = new HashMap<>();
		Map<Integer, Double> compara_indices = new HashMap<>();

		// Inicializa o Array de frequencia de caracteres <Letra,0>
		for (int i = 0; i < f.getALFABETO().length; i++) {
			frequencia_letra.put(f.getALFABETO()[i], 0);
		}

		// Para numeros diferentes de colunas ateh o tamanho do texto (nCol = chave)
		for (int nCol =1; nCol < 15; nCol++) {

			// Para cada coluna, conta a frequencia de cada letra e armazena
			// Calcula o indexOfCoincidence() de cada coluna e adicona em um array
			List<Double> freq_coluna = new ArrayList<>();

			// Separa o texto em linhas e colunas de acordo
			// int nOfLines = buildMatrix(nOfColumns);
			int nLinhas = (int) Math.ceil((double) texto_cifrado.length() / nCol);

			Map<Integer, ArrayList<Character>> matriz_texto_cifrado = f.divideTexto(nCol);

			// Encontra o caracter da chave a partir de cada coluna da matriz
			for (int i = 0; i < nCol; i++) {
				StringBuilder coluna = new StringBuilder();

				for (int j = 0; j < matriz_texto_cifrado.size(); j++) {
					try {
						coluna.append(matriz_texto_cifrado.get(j).get(i));
					} catch (IndexOutOfBoundsException e) {
					}
				}
			}

			for (int i = 0; i < nCol; i++) {
				for (int j = 0; j < nLinhas; j++) {
					try {
						matriz_texto_cifrado.get(j).get(i);
					} catch (IndexOutOfBoundsException e) {
						break;
					}
					frequencia_letra.put(matriz_texto_cifrado.get(j).get(i),
							frequencia_letra.get(matriz_texto_cifrado.get(j).get(i)) + 1);
				}
				// Calcula IndexOfCoincidence da coluna e poe resultado no array de Key
				double ic = 0;
				int sum = 0;
				for (int k = 0; k < f.getALFABETO().length; k++) {
					sum += frequencia_letra.get(f.getALFABETO()[k]);
				}
				for (int m = 0; m < f.getALFABETO().length; m++) {
					double cima = frequencia_letra.get(f.getALFABETO()[m])
							* (frequencia_letra.get(f.getALFABETO()[m]) - 1);
					double baixo = sum * (sum - 1);
					ic += cima / baixo;
					frequencia_letra.put(f.getALFABETO()[m], 0);
				}
				freq_coluna.add(ic);
			}

			// Calcula a media do indice de frequencia das colunas e cria objeto Key
			double somaIOC = 0;
			for (Double IOC : freq_coluna) {
				somaIOC += IOC;
			}
			double media = somaIOC / freq_coluna.size();

			double diferencia_indices = 0.0;
			// Adiciona possivel chave no array de chaves possiveis, passando o possivel
			// tamanho da chave e a media do indice de coicidencia calculado para ordenacao
			if (media > 0) {
				// listOfKeys.add(new Key(nOfColumns, avg));//keyLength, indexOfCoincidence
				if (media >= index_coincidence_portugues) {
					diferencia_indices = media - index_coincidence_portugues;
				} else {
					diferencia_indices = index_coincidence_portugues - media;
				}
				// keyLength = nOfColumns
				// difIndexOfCoincidence
				// indexOfCoincidence=avg
				System.out.println("Chave: " + nCol + " indexOfCoincidence" + media
						+ " ic quem tem maior vira chave final: " + diferencia_indices);
				
				compara_indices.put(nCol, diferencia_indices);


			}

		}
		double menor_ic =Double.MAX_VALUE;
		int key_final=0;
		for (Integer key : compara_indices.keySet()) {
			System.out.println("key: "+key+" ic: "+ compara_indices.get(key).toString());
			if( compara_indices.get(key) < menor_ic) {
				menor_ic=compara_indices.get(key);
				key_final=key;
			}
		}
		System.out.println("Tamanho de chave final por indice de coincidencia: "+key_final);

	}
}