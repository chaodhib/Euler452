package be.chaouki.eulerproblem.tests.atp4;

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

	private static final boolean output = false;
	private static long compteur = 0;
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891");
	private static final long MOD_VALUE_L=1234567891;

	public static void main(String[] args) throws FileNotFoundException {
//		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
		int n = 10, k = n;
		
		// Warm-up so the order of testing doesnt influence the results - last a few seconds
//		for(int j=0;j<10000 ; j++)
//			for(int i=0;i<1000000 ; i++){
//				long a=463543*54324354;
//		}
		
		while(true){
			long debut = System.nanoTime();
			gen_comb_w_rep(n, k);
			long fin = System.nanoTime();
			System.out.println(compteur);
			System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms pour n="+n+" , k="+k);
			
			compteur=0;
			n+=1;
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
				hasMoreSolution=skipUnnecessaryCombinations(eqSol, currentIndex);
				reusableSolution=false;
				continue;
			}
			else{
				if(!reusableSolution)
					solutionSave=computeSolution(eqSol, k, currentIndex[1]);
					
//				compteur=(solutionSave+compteur)%MOD_VALUE_L;
				compteur+=solutionSave;
			}
			
			if(currentIndex[1]!=n-1){
				// searching for the next combination...
				int ind=currentIndex[1];// ind is the index for 1st non null element starting from the right side
				
				eqSol[ind]--;
				eqSol[ind+1]++;
				
				// General case (?,...,X,0,...,0) -> (?,...,X-1,1,...,0) where X>1
				reusableSolution=false;
				currentIndex[0]=ind;
				currentIndex[1]++;
				
				if(!Tools.prodAboveLimitES(eqSol, eqSol.length)){
					solutionSave=computeSolution(eqSol, k, currentIndex[1]);
					// apply end case (?,...,X-1,0,...,1) and see if its a solution
					eqSol[ind+1]--;
					eqSol[n-1]++;
					int start=ind+1, end;
					if(!Tools.prodAboveLimitES(eqSol, eqSol.length)){
						// bingo, a LOT of calculations and method calls can be skipped very easily.
						// basicly, from (?,...,X-1,1,...,0) to (?,...,X-1,0,...,1) in one step.
						end=n-1;
						
					} else{
						eqSol[n-1]--;
						// from this point, we know that (?,...,X-1,1,...,0) is a solution
						// but (?,...,X-1,0,...,1) is not. lets find the highest index
						// of the right sided "1" for which the vector is solution 
						// using a form of binary search.
						end=binarySearch(eqSol, ind+1, n-1);
						eqSol[end]++;
					}
					
					compteur+=(solutionSave*(long)(end-start));
//					compteur%=MOD_VALUE_L;
					reusableSolution=true;
					currentIndex[1]=end;
				}
				
				continue;
			}
			else{
				// searching for the next combination...
				int ind=currentIndex[0];// ind is the index for 2nd non null element starting from the right side
				
				// otherwise we found a new combination. 
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

	private static int binarySearch(int[] eqSol, int lo, int hi) {
		while (lo<hi-1) {
		    // the index solution should be in [lo..hi[
		    int mid = lo + (hi - lo) / 2;
		    
		    // test then put eqSol back into its original state.
		    eqSol[mid]++;
		    boolean isSolution=!Tools.prodAboveLimitES(eqSol, eqSol.length);
		    eqSol[mid]--;
		    
		    if(isSolution)
		    	lo = mid;
		    else
		    	hi = mid;
		}
		
		return lo;
	}

	private static boolean skipUnnecessaryCombinations(int eqSol[], int[] currentIndex){
		//find the first, second and third non null element starting from the right
		int indA, indB=currentIndex[0], indC=currentIndex[1]; // indA <= indB <= indC
		for(indA=indB-1 ; indA>=0 ; indA--)
			if(eqSol[indA]!=0)
				break;
		if(indB==-1) // extreme case (0 ... k ... 0). end of the search because what follows will always have a higher product.
			return false;
		if(indC==indB+1 && indA==-1) // case with (0, ..., k-l, l, ..., 0)
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
	
	public static long computeSolution(int eqSol[], int k, int indMax){
		// This function calculates all the possible permutations of the k-tuple associated
		// with a n-tuple given in parameter (eqSol). Example, the n-uple (2, 1) (n=2 here)
		// which is to be understood as 2 ONE and 1 TWO 
		// is associated with (1,1,2) but also (1,2,1) and (2,1,1) (k=3 here).
		// The formula used is sol=k!/prod(n_i!).
		
//		//DEBUG
//		compteur=compteur.add(BigInteger.ONE);

		// It can proven that the highest element (n_i) in esQol will always be eqSol[0]
		// because of the condition on the product: eqSol[0]_minimum = k - LN(n)/LN(2).
		// => eqSol[0]_minimum > k/2 which makes eqSol[0]_minimum the highest element of eqSol
		// which makes eqSol[0] itself the highest element of eqSol. 
		// This can be used to simplify the calculation of k!.
		BigInteger answer=BigInteger.ONE;
		for(int i=eqSol[0]+1;i<=k;i++)
			answer=answer.multiply(BigInteger.valueOf(i));
		
		// Computing of the remaining factorials.
		for(int i=1; i<=indMax;i++) //
			if(eqSol[i]>1)
				answer=answer.divide(BigIntegerMath.factorial(eqSol[i]));
		
//		System.out.println(Arrays.toString(Arrays.copyOf(eqSol, 25))+" "+answer.mod(MOD_VALUE).longValue());
//		System.out.println(Arrays.toString(Arrays.copyOf(eqSol, 25))+" "+answer.longValue());
		
//		return answer.mod(MOD_VALUE).longValue();
		return answer.longValue();
	}
	
	public static void display(int eqSol[]){
		if (output) {
			System.out.println(Arrays.toString(eqSol) + " "+ Tools.prodAboveLimitES(eqSol, eqSol.length));

		}
	}
}
