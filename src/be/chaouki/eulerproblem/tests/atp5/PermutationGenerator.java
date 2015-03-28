package be.chaouki.eulerproblem.tests.atp5;

import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Tools;

/**from the C program found here: 
 * http://www.aconnect.de/friends/editions/computer/combinatoricode_e.html#Permutations
 * A detailed explanation of the algorithm can be found here:
 * http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
 * 
 * @author Chaouki
 *
 */
public class PermutationGenerator {

	/*
	private static int count;

	public static void main(String[] args) {
		long debut = System.nanoTime();
//		byte vect[]={0,0,0,2,1,2};
		byte vect[]={2,1,2,0,0,0};

		do{
			treatSolution(vect, vect.length+1);
//		} while(gen_perm_rep_lex_next(vect));
		} while(gen_perm_rep_colex_next(vect));

		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
		System.out.println("count: "+count);
	}

	private static void treatSolution(byte[] vect, int lim) {
//		System.out.println(Arrays.toString(vect) +" "+ aboveLimitReverseM(vect, lim) +" "+Tools.prodAboveLimitES(vect, lim));
		System.out.println(Arrays.toString(vect));
		count++;
	}

		private static boolean aboveLimitReverseM(byte vector[], int lim){
		long prod=1;
//		for(int i=eqSol.length-1 ; i>0 ; i--){
		for(int i=vector.length-1 ; i>=0 ; i--){
			for(int j=0 ; j<vector[i] ; j++){
				prod*=vector.length+1-i;
				if(prod>lim)
					return true;
			}
		}
		return false;
	}
	 */

	/**from http://www.aconnect.de/friends/editions/computer/combinatoricode_e.html
	 * method to generate permutations.
	 * 
	 * @param vector
	 * @return
	 */
	public static boolean gen_perm_rep_lex_next(byte vector[])
	{
		int j = vector.length - 2; //index
		int i = vector.length - 1; //help index
		byte temp;      //auxiliary element

		//find rightmost element to increase
		while(j >= 0)
		{
			if(vector[j] < vector[j + 1])
				break;

			j--;
		}

		//terminate if all elements are in decreasing order
		if(j < 0)
			return false;

		//find i
		while(vector[i] <= vector[j])
			i--;

		//increase (swap)
		temp = vector[j];
		vector[j] = vector[i];
		vector[i] = temp;

		//reverse right-hand elements
		for(j += 1, i = vector.length - 1; j < i;  j++, i--)
		{
			temp = vector[j];
			vector[j] = vector[i];
			vector[i] = temp;
		}

		return true;
	}

	/** I modified the method above to generate permutations in the 
	 * 	colexicographic order. Repetitions in the vector paramter must be supported.
	 * 
	 * @param vector
	 * @return
	 */
	public static boolean gen_perm_rep_colex_next(byte vector[])
	{
		int j = vector.length - 2; //index
		int i = vector.length - 1; //help index
		byte temp;      //auxiliary element

		//find rightmost element to increase
		while(j >= 0)
		{
			if(vector[j] > vector[j + 1])
				break;

			j--;
		}

		//terminate if all elements are in decreasing order
		if(j < 0)
			return false;


		//find i
		while(vector[i] >= vector[j])
			i--;

		//increase (swap)
		temp = vector[j];
		vector[j] = vector[i];
		vector[i] = temp;

		//reverse left-hand elements
		//reverse right-hand elements
		for(j += 1, i = vector.length - 1; j < i;  j++, i--)
		{
			temp = vector[j];
			vector[j] = vector[i];
			vector[i] = temp;
		}

		return true;
	}


}
