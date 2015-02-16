package be.chaouki.eulerproblem.tests;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Tools;

/** custom made method. search of the k-combinations of a n set with repetition.
 * the solutions are in the form of the solution of the diophantine equation sum(xi)=k for 1<i<n
 * 
 * @author Chaouki
 *
 */
public class Test9 {

	private static boolean output = true;
	private static int compteur = 0;

	public static void main(String[] args) throws FileNotFoundException {
//		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
		int n = 8, k = 3;
		long debut = System.nanoTime();

//		gen_comb_wo_rep(n, k);
		gen_comb_w_rep(n, k);
		System.out.println(compteur);
		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
		
	}

	public static void gen_comb_w_rep(int n, int k) {
		// first solution, trivial [k 0 0 0 ... 0]
		int eqSol[] = new int[n];
		eqSol[0] = k;
		for(int i=1 ; i<n ; i++)
			eqSol[i]=0;
		
		while(true){
			if(eqSol[n-1]==0){
//				if(Tools.prodAboveLimitES(eqSol, eqSol.length)){
//					//find the first and second non null element starting from the right
//					int indA, indB;
//					for(indB=n-2 ; indB>=0 ; indB--)
//						if(eqSol[indB]!=0)
//							break;
//					for(indA=indB-1 ; indB>=0 ; indB--)
//						if(eqSol[indB]!=0)
//							break;
//					if(indA==-1)
//						indA=indB;
//					eqSol[indA]--;
//					eqSol[indA]--;
//					
//					
//					continue;
//				}
//				else
					doStuffWithSolution(eqSol);

				// searching for the next combination...
				// search for first non null element starting from the right side
				int ind;
				for(ind=n-2 ; ind>=0 ; ind--)
				{
					if(eqSol[ind]!=0)
						break;
				}
				eqSol[ind]--;
				eqSol[ind+1]++;
				
				continue;
			}
			else{
				doStuffWithSolution(eqSol);
				
				// searching for the next combination...
				// search for second non null element starting from the right side
				int ind;
				for(ind=n-2 ; ind>=0 ; ind--)
				{
					if(eqSol[ind]!=0)
						break;
				}
				// no more next combinations
				if(ind==-1){
					if(eqSol[n-1]==k)
						return;
					else
						throw new RuntimeException();
				}
				
				//otherwise we found a new solution
				eqSol[ind]--;
				eqSol[ind+1]=eqSol[n-1]+1;
				if(ind+1!=n-1)
					eqSol[n-1]=0;
				
				
				continue;
			}
		}
	}
	
	public static void doStuffWithSolution(int eqSol[]){
		compteur++;
		if (output) {
			System.out.println(Arrays.toString(eqSol) + " "+ Tools.prodAboveLimitES(eqSol, eqSol.length));
		}
	}
}
