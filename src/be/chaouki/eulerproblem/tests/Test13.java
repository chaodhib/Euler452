package be.chaouki.eulerproblem.tests;

import java.math.BigInteger;

public class Test13 {
	private static final boolean output = false;
	private static long solutionCount = 0;
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891");
	private static final long MOD_VALUE_L=1234567891;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void generateCombinations(int n, int k) {
		// first solution, trivial (k 0 0 0 ... 0)
		int eqSol[] = new int[n];
		eqSol[0] = k;
		for(int i=1 ; i<n ; i++)
			eqSol[i]=0;
		solutionCount++;
		
		int limit=(int) (Math.log(n)/Math.log(2.0));
		for(int i=1  ; i<=limit ; i++){
			eqSol[0]=k-i;
			
			int indMax=(int) (n/Math.pow(2, i-1));
			// we start by computing the partitions of i
			
			// and for each partition, we generate all permutations
			// in a certain order. The order will later help us skip
			// a lot of permutations known to not be a solution.
		}
	}
		
}
