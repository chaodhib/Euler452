package be.chaouki.eulerproblem.tests;

import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Math;
import be.chaouki.eulerproblem.utils.Tools;

/** Use the same algorithm of utils.Math.
 *  I just refactored the method into a while loop.
 * 
 * @author Chaouki
 *
 */
public class Test5 {

	private static boolean output = true;
	private static int compteur = 0;

	public static void main(String[] args) {
		int n = 8, k = 3;
		long debut = System.nanoTime();

//		gen_comb_wo_rep(n, k);
		gen_comb_w_rep(n, k);
		System.out.println(compteur);
		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
	}

	public static void gen_comb_w_rep(int n, int k) {
		int vector[] = new int[k];
		for (int i = 0; i < k; i++)
			vector[i] = 1;
		compteur++;
		if (output) {
			System.out.println(Arrays.toString(vector)+ " "+ Arrays.toString(Tools.getCaractericVector(vector, n))+" "+Tools.prodAboveLimit(vector, n));
		}
		
		int gen_result = Math.GEN_NEXT;
		while (gen_result == Math.GEN_NEXT) {
			int j; // index

			// easy case, increase rightmost element
			if (vector[k - 1] < n) {
				vector[k - 1]++;
				gen_result = Math.GEN_NEXT;

				compteur++;
				if (output) {
					System.out.println(Arrays.toString(vector)+ " "+ Arrays.toString(Tools.getCaractericVector(vector, n))+" "+Tools.prodAboveLimit(vector, n));
				}
				continue;
			}

			// find rightmost element to increase
			for (j = k - 2; j >= 0; j--)
				if (vector[j] != n)
					break;

			// terminate if all elements are n - 1
			if (j < 0)
				return;

			// increase
			vector[j]++;

			// set right-hand elements
			for (j += 1; j < k; j++)
				vector[j] = vector[j - 1];

			compteur++;
			if (output) {
				System.out.println(Arrays.toString(vector)+ " "+ Arrays.toString(Tools.getCaractericVector(vector, n))+" "+Tools.prodAboveLimit(vector, n));
			}
			gen_result = Math.GEN_NEXT;
		}

	}

	public static void gen_comb_wo_rep(int n, int k) {
		int vector[] = new int[k];
		for (int i = 0; i < k; i++)
			vector[i] = i;
		compteur++;
		if (output) {
			System.out.println(Arrays.toString(vector));
		}
		
		int gen_result = Math.GEN_NEXT;
		while (gen_result == Math.GEN_NEXT) {
			int j; //index

			//easy case, increase rightmost element
			if(vector[k - 1] < n - 1)
			{
				vector[k - 1]++;
				gen_result = Math.GEN_NEXT;

				compteur++;
				if (output) {
					System.out.println(Arrays.toString(vector));
				}
				continue;
			}

			//find rightmost element to increase
			for(j = k - 2; j >= 0; j--)
				if(vector[j] < n - k + j)
					break;

			//terminate if vector[0] == n - k
			if(j < 0)
				return;

			//increase
			vector[j]++;

			//set right-hand elements
			while(j < k - 1)
			{
				vector[j + 1] = vector[j] + 1;
				j++;
			}

			compteur++;
			if (output) {
				System.out.println(Arrays.toString(vector));
			}
			gen_result = Math.GEN_NEXT;
		}
	}


}
