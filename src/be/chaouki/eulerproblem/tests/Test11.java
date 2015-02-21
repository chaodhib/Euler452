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
	private static long compteur = 0;
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891");

	public static void main(String[] args) throws FileNotFoundException {
//		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
		int n = 512, k = n;
		
		// Warm-up so the order of testing doesnt influence the results - last a few seconds
//		for(int j=0;j<10000 ; j++)
//			for(int i=0;i<1000000 ; i++){
//				long a=463543*54324354;
//		}
		
//		while(true)
		{
			
			long debut = System.nanoTime();
			gen_comb_w_rep(n, k);
			long fin = System.nanoTime();
			System.out.println(compteur);
			System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms pour n="+n+" , k="+k);
			
			compteur=0;
			n*=2;
			k=n;
		}
	}
	
	public static void gen_comb_w_rep(int n, int k) {
		// first solution, trivial (k 0 0 0 ... 0)
		int eqSol[] = new int[n];
		eqSol[0] = k;
		for(int i=1 ; i<n ; i++)
			eqSol[i]=0;
		
		int loopCount=0;//DEBUG
		
		int currentIndex[]={0, 0}; //currentIndex0 <= currentIndex1
		boolean hasMoreSolution=true, reusableSolution=false;
		long solutionSave = 0;
		while(hasMoreSolution){
//			//DEBUG
//			loopCount++;
//			if(loopCount>1000)
//				return;
//			//DEBUG
			
			//DEBUG
//			if(eqSol[0]==506 && eqSol[1]==2 && eqSol[2]==4)
//				System.out.println("debug");
			
			//DEBUG
//			for(int i=0 ; i<eqSol.length ; i++)
//				if(eqSol[i]<0)
//					throw new AssertionError();
			
			display(eqSol);
			if(Tools.prodAboveLimitES(eqSol, eqSol.length)){
				hasMoreSolution=skipUnnecessarySolutions(eqSol, currentIndex);
				reusableSolution=false;
				continue;
			}
			else{
				if(!reusableSolution)
					solutionSave=doStuffWithSolution(eqSol, k, currentIndex[1]);
					;
					
				compteur=(solutionSave+compteur)%1234567891;
			}
			
			if(currentIndex[1]!=n-1){
				// searching for the next combination...
				int ind=currentIndex[1];// ind is the index for first non null element starting from the right side
				
				eqSol[ind]--;
				eqSol[ind+1]++;
				
				if(eqSol[ind]==0){ // (?,...,1,0,...,0) -> (?,...,0,1,...,0) Case where just a 1 is shifted to the right
					reusableSolution=true;
					currentIndex[1]++; //the second index is shifted by one position to the right
				}
				else{ // General case (?,...,X,0,...,0) -> (?,...,X-1,1,...,0) where X>1
					reusableSolution=false;
					currentIndex[0]=ind;
					currentIndex[1]++;
				}
				
				continue;
			}
			else{
				// searching for the next combination...
				int ind=currentIndex[0];// ind is the index for second non null element starting from the right side
				
				if(ind==-1){ // then this was the very last combination: (0, ..., 0, k)
					if(eqSol[n-1]==k){
						hasMoreSolution=false;
						continue;
					}
					else
						throw new IllegalStateException();
				}
				
				// otherwise we found a new solution. 
				//(?,...,X,...,Y) or (?,...,X,Y) -> (?,...,X-1,Y+1,...,0) and (?,...,X-1,Y+1) respectively
				eqSol[ind]--;
				eqSol[ind+1]=eqSol[n-1]+1;
				if(ind+1!=n-1){
					eqSol[n-1]=0;
					currentIndex[1]=ind+1; // keeping the indexes updated
				}
				
				// reusability of the solution
				reusableSolution=false;
				
				continue;
			}
		}
	}
	
	private static boolean skipUnnecessarySolutions(int eqSol[], int[] currentIndex){
		//find the first, second and third non null element starting from the right
		int indA, indB=currentIndex[0], indC=currentIndex[1]; // indA <= indB <= indC
		for(indA=indB-1 ; indA>=0 ; indA--)
			if(eqSol[indA]!=0)
				break;
		if(indB==-1) // cas extreme (0 ... k ... 0). fin de la recherche car ce qui suit possède un prod superieur
			return false;
		if(indC==indB+1 && indA==-1) // cas (0, ..., k-l, l, ..., 0)
			return false;
		
		if(indB+1==indC){
			eqSol[indA]--;
			eqSol[indA+1]=eqSol[indB]+eqSol[indC]+1;
			eqSol[indC]=0;			
			if(indA+1!=indB)
				eqSol[indB]=0;	
			
			// keeping indexes updated
			if(eqSol[indA]>0)
				currentIndex[0]=indA;
			else{
				for(currentIndex[0]=indA-1 ; currentIndex[0]>=0 ; currentIndex[0]--)
					if(eqSol[currentIndex[0]]!=0)
						break;
			}
			currentIndex[1]=indA+1;
			
		} else{
			eqSol[indB]--;
			eqSol[indB+1]=eqSol[indC]+1;
			eqSol[indC]=0;
			
			// keeping indexes updated
			currentIndex[0]=(eqSol[indB]>0)?indB:indA;
			currentIndex[1]=indB+1;
		}
		
		return true;
	}
	
	public static long doStuffWithSolution(int eqSol[], int k, int indMax){
		
//		compteur=compteur.add(BigInteger.ONE);
//		if (output) {
//			System.out.println(Arrays.toString(eqSol) + " "+ Tools.prodAboveLimitES(eqSol, eqSol.length));
//		}
		
		// simplif du calcul de quotient de factorielle. 
		// Il est prouvable que le plus grand element dans eqSol serra toujours eqSol[0] à cause
		// de la condition sur le produit. eqSol[0] minimum egal k - LN(n)/LN(2).
		BigInteger answer=BigInteger.ONE;
		for(int i=eqSol[0]+1;i<=k;i++)
			answer=answer.multiply(BigInteger.valueOf(i));
		
		// calcul des factorielles restantes
		for(int i=1; i<=indMax;i++) //
			if(eqSol[i]>1)
				answer=answer.divide(BigIntegerMath.factorial(eqSol[i]));
		
		return answer.mod(MOD_VALUE).longValue();
	}
	
	public static void display(int eqSol[]){
		if (output) {
			System.out.println(Arrays.toString(eqSol) + " "+ Tools.prodAboveLimitES(eqSol, eqSol.length));
		}
	}
}
