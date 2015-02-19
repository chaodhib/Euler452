package be.chaouki.eulerproblem.tests;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Tools;

import com.google.common.math.BigIntegerMath;

/** custom made method. search of the k-combinations of a n set with repetition.
 * the solutions are in the form of the solution of the diophantine equation sum(xi)=k for 1<=i<=n
 * 
 * @author Chaouki
 *
 */ 
public class Test11 {

	private static final boolean output = true;
	private static BigInteger compteur = BigInteger.ZERO;

	public static void main(String[] args) throws FileNotFoundException {
//		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
		int n = 10, k = 10;
		
		long debut = System.nanoTime();
		gen_comb_w_rep(n, k);
		long fin = System.nanoTime();
		System.out.println(compteur);
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms pour n="+n+" , k="+k);
		
	}
	
	public static void gen_comb_w_rep(int n, int k) {
		// first solution, trivial [k 0 0 0 ... 0]
		int eqSol[] = new int[n];
		eqSol[0] = k;
		for(int i=1 ; i<n ; i++)
			eqSol[i]=0;
		
		int loopCount=0;
		boolean hasMoreSol=true;
		while(hasMoreSol){
			loopCount++;
			if(loopCount>50000)
				return;
			
			doStuffWithSolution(eqSol, k);
			if(Tools.prodAboveLimitES(eqSol, eqSol.length)){
				hasMoreSol=skipUnnecessarySolutions(eqSol);
				continue;
			}
			else
				
				;
			
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
		int indA, indB, indC;
		for(indC=eqSol.length-1 ; indC>=0 ; indC--)
			if(eqSol[indC]!=0)
				break;
		for(indB=indC-1 ; indB>=0 ; indB--)
			if(eqSol[indB]!=0)
				break;
		for(indA=indB-1 ; indA>=0 ; indA--)
			if(eqSol[indA]!=0)
				break;
		if(indB==-1) // cas extreme (0 ... k ... 0). fin de la recherche car ce qui suit possède un prod superieur
			return false;
		if(indC==indB+1 && indA==-1) // cas (0, ..., k-l, l, ..., 0)
			return false;
		
		if(indB+1==indC){
			eqSol[indA]--;
			eqSol[indB]+=eqSol[indC]+1;
			eqSol[indC]=0;
		} else{
			eqSol[indB]--;
			eqSol[indB+1]=eqSol[indC]+1;
			eqSol[indC]=0;
		}
		
//		else{
//			//check if there are non null elements at the left of indA
//			int ind;
//			for(ind=indA-1 ; ind>=0 ; ind--)
//				if(eqSol[ind]!=0)
//					break;
//			if(ind==-1) //if not, the search if over
//				return false;
//		}
		
//		if(indA!=1){
//			eqSol[indA]--;
//			eqSol[indB+1]+=eqSol[indC]+1;
//			eqSol[indC]=0;
//			
//			return true;
//		}
//		else{
//			eqSol[indB]--;
//			eqSol[indB+1]+=eqSol[indC]+1;
//			eqSol[indC]=0;
//			return true;
//		}
		
		return true;
	}
	
	public static void doStuffWithSolution(int eqSol[], int k){
		
//		compteur=compteur.add(BigInteger.ONE);
		if (output) {
			System.out.println(Arrays.toString(eqSol) + " "+ Tools.prodAboveLimitES(eqSol, eqSol.length));
		}
		
		// simplif du calcul de quotient de factorielle
		BigInteger answer=BigInteger.ONE;
		for(int i=eqSol[0]+1;i<=k;i++)
			answer=answer.multiply(BigInteger.valueOf(i));
		
		// calcul des factorielles restantes
		for(int i=1; i<eqSol.length;i++) //
			answer=answer.divide(BigIntegerMath.factorial(eqSol[i])); 
		
		compteur=compteur.add(answer);
	}
}
