package be.chaouki.eulerproblem.tests.atp5;

import java.util.Arrays;

/**from the C program found here: 
 * http://www.aconnect.de/friends/editions/computer/combinatoricode_e.html#Permutations
 * A detailed explanation of the algorithm can be found here:
 * http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
 * 
 * @author Chaouki
 *
 */
public class Test03 {
	
	private static final int GEN_TERM=0;
	private static final int GEN_NEXT=1;

	public static void main(String[] args) {
		long debut = System.nanoTime();
		byte vect[]={0,0,1,1,2};
		
		int moreSol=1;
		while(moreSol==GEN_NEXT){
			treatSolution(vect);
			moreSol=gen_perm_rep_lex_next(vect, vect.length);
		}
			
			
		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
	}

	private static void treatSolution(byte[] vect) {
		System.out.println(Arrays.toString(vect));
		
	}

	private static int gen_perm_rep_lex_next(byte vector[], int n)
	{
	int j = n - 2; //index
	int i = n - 1; //help index
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
	 return(GEN_TERM);

	//find i
	while(vector[i] <= vector[j])
	 i--;

	//increase (swap)
	temp = vector[j];
	vector[j] = vector[i];
	vector[i] = temp;

	//reverse right-hand elements
	for(j += 1, i = n - 1; j < i;  j++, i--)
	 {
	 temp = vector[j];
	 vector[j] = vector[i];
	 vector[i] = temp;
	 }

	return(GEN_NEXT);
	}
}
