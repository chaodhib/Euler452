package be.chaouki.eulerproblem.tests.atp4;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Arrays;

import com.google.common.math.BigIntegerMath;

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
		
		boolean hasMoreSol=true;
		while(hasMoreSol){
			if(Tools.prodAboveLimitES(eqSol, eqSol.length)){
				hasMoreSol=skipUnnecessarySolutions(eqSol);
				continue;
			}
			else
				doStuffWithSolution(eqSol);
			
			if(eqSol[n-1]==0){
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
					if(eqSol[n-1]==k){
						hasMoreSol=false;
						continue;
					}
					else
						throw new IllegalStateException();
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
	
	private static boolean skipUnnecessarySolutions(int eqSol[]){
		//find the first and second non null element starting from the right
		int indA, indB;
		for(indB=eqSol.length-1 ; indB>=0 ; indB--)
			if(eqSol[indB]!=0)
				break;
		for(indA=indB-1 ; indA>=0 ; indA--)
			if(eqSol[indA]!=0)
				break;
		if(indA==-1) // cas extreme (0 ... k ... 0). fin de la recherche car ce qui suit possède un prod superieur
			return false;
		
		eqSol[indA]--;
		eqSol[indA+1]=eqSol[indB]+1;
		if(indA+1!=indB)
			eqSol[indB]=0;
		
		return true;
	}
	
	public static void doStuffWithSolution(int eqSol[]){
		compteur++;
		if (output) {
			System.out.println(Arrays.toString(eqSol) + " "+ Tools.prodAboveLimitES(eqSol, eqSol.length));
		}
	}
}
