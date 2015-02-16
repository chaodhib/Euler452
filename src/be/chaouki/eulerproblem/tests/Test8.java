package be.chaouki.eulerproblem.tests;

import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Tools;

/** custom made method. search of the k-combinations of a n set with repetition.
 * the solutions are in the form of the solution of the diophantine equation sum(xi)=k for 1<i<n
 * attempt at optimising by removing the search on the vector and for that, I use two index values
 * of the first 2 non-null elements (starting at the right)
 * 
 * @author Chaouki
 *
 */
public class Test8 {

	private static boolean output = true;
	private static int compteur = 0;

	public static void main(String[] args) {
		int n = 4, k = 8;
		long debut = System.nanoTime();

//		gen_comb_wo_rep(n, k);
		gen_comb_w_rep(n, k);
		System.out.println(compteur);
		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
	}

	public static void gen_comb_w_rep(int n, int k) {
		// first solution, trivial [k 0 0 0 ... 0]
		int idxA=0, idxB=0;
		int eqSol[] = new int[n];
		eqSol[0] = k;
		for(int i=1 ; i<n ; i++)
			eqSol[i]=0;
		doStuffWithSolution(eqSol);
		
		
		while(true){
			if(idxB!=n-1){
				eqSol[idxB]--;
				if(eqSol[idxB]>0)
					idxA=idxB;
				eqSol[++idxB]++;
				
				doStuffWithSolution(eqSol);
				continue;
			}
			else{
				// search for second non null element starting from the right side
				int ind;
				for(ind=n-2 ; ind>=0 ; ind--)
				{
					if(eqSol[ind]!=0)
						break;
				}
				// end of the combinaitons
				if(ind==-1)
					return;
				
				//otherwise we found a new solution
				eqSol[ind]--;
				eqSol[ind+1]=eqSol[n-1]+1;
				if(ind+1!=n-1)
					eqSol[n-1]=0;
				
				doStuffWithSolution(eqSol);
				continue;
			}
		}
	}
	
	public static void doStuffWithSolution(int eqSol[]){
		compteur++;
		if (output) {
			System.out.println(Arrays.toString(eqSol));
		}
	}
}
