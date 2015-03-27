package be.chaouki.eulerproblem.tests.atp5;

import java.math.BigInteger;

import com.google.common.math.BigIntegerMath;

public class Test01 {
	private static final boolean output = true;
	private static long solutionCount = 0;
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891"); //1.2310^9
	private static final long MOD_VALUE_L=1234567891;
	
	private static BigInteger factorialSave[];

	public static void main(String[] args) {
		/*	website notation	|||	notation used in this program
		 *  m					<=>	n
		 *  n					<=>	k
		 */
		int m=60, n=m;
//		while(true){
			long debut = System.nanoTime();
			generateCombinations(m,n);
			long fin = System.nanoTime();
			System.out.println(solutionCount);
			System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms pour n="+m+" , k="+n);
			
			solutionCount=0;
			m+=1;
			n=m;
//		}
	}
	
	private static void prepFactorials(int n) {
		int limit=(int) (Math.log(n)/Math.log(2.0));
		factorialSave=new BigInteger[limit];
		for(int i=1 ; i<=limit ; i++)
			factorialSave[i-1]=BigIntegerMath.factorial(i);
	}

	public static void generateCombinations(int n, int k) {
		//eqSol doesnt contain the first element of the working vector.
		//example: if we are working on n=(X Y 0) then eqSol={Y, 0). The first element is kept in memory differently (i)
		//and its value is much more larger than a byte's capasity.
		byte eqSol[]; 
		
		// first solution, trivial (k 0 0 0 ... 0) (because product equals 1)
		solutionCount++;
		
		// first serie of solutions: (k-1 1 0...0) to (k-1 0...0 1) (because product equals 1+index_of_the_1)
		solutionCount+=(n-1)*n;
		
//		solutionCount%=MOD_VALUE_L;
		
		final int limit=(int) (Math.log(n)/Math.log(2.0));
		for(int i=2  ; i<=limit ; i++){
			//we will work on the vectors n=(k-i, ... ) <=> eqSol=(...) with the sum(eqSol_i)=i for i from 1 to eqSol.length
			
			int indMax=(int) (n/Math.pow(2, i-1));
			PartitionUser pu=new PartitionUser(indMax, k, n);
			// we start by computing the partitions of i
			// and for each partition, we generate all permutations
			// in a certain order. The order will later help us skip
			// a lot of permutations known to not be a solution.
			Test02.partition(i, pu);
			
			solutionCount+=pu.getCountT();
//			solutionCount%=MOD_VALUE_L;
			
		}
	}
		
}