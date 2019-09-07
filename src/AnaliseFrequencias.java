import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	private static String texto_cifrado="";
	
	
	public AnaliseFrequencias(String tc) {
		this.texto_cifrado = tc;
	}

	
}
