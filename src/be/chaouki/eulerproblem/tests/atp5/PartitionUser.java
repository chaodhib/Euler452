package be.chaouki.eulerproblem.tests.atp5;

import java.math.BigInteger;
import java.util.Arrays;

import com.google.common.math.BigIntegerMath;

import be.chaouki.eulerproblem.utils.Tools;

public class PartitionUser {
	
//	public static BigInteger count;
	private long countT;
	private int count;

	private int n ,k;
	private long solution;
	private byte[] eqSol;
	
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891");
	private static final long MOD_VALUE_L=1234567891;
	
	private static final boolean OUTPUT=true;
	
	public PartitionUser(int size, int k, int n){
//		count=BigInteger.ZERO;
		countT=0;
		eqSol=new byte[size];
		this.n=n;
		this.k=k;
	}

	public void usePartition(byte[] partitionVector, int I) {
		if(OUTPUT) System.out.println(Arrays.toString(partitionVector)+"---NEW PARTITION----");
		count=0;
//		System.out.println(Arrays.toString(partitionVector));
		
		// if partitionVector=[a, b, c, d], the first step is to get all permutations of this same set.
		// meaning: [b, a, c ,d], [b, c, a, d], [b, c, d, a], etc...
		// the algorithm used here MUST support repetitions (for instance, a=b).
		
		do{
			if(OUTPUT) System.out.println(Arrays.toString(partitionVector)+"---NEW PERMUTATION----");
			if(partitionVector.length>eqSol.length)
				break;
			//copy the partition into the eqSol vector
			for(int i=0; i<partitionVector.length ; i++)
				eqSol[i]=partitionVector[i];
			
			findPermuts((byte)partitionVector.length, 0);
		} while(Test03.gen_perm_rep_colex_next(partitionVector));
		
		if(count>0){
			if(OUTPUT) System.out.println(Arrays.toString(partitionVector)+", count="+count);
			countT+=(computeSolution(partitionVector, I, k)*count);
			
//			countT+=(computeSolution(partitionVector, I, k, partitionLength-1)*(count%MOD_VALUE_L))%MOD_VALUE_L;
//			countT=countT%MOD_VALUE_L;
		}
	}

	private void findPermuts(byte length, int startInd) {
		int saveStartInd=startInd;
//		byte save[]=Arrays.copyOfRange(eqSol, startInd, startInd+length);
		while(isSolution(eqSol) && length>0){
			// treatment
			if(length==1){
//				count=count.add(BigInteger.ONE);
				count++;
				if(OUTPUT) System.out.println(Arrays.toString(eqSol)+" "+Tools.prodAboveLimitESShifted(eqSol, n));
			}
			findPermuts((byte) (length-1), startInd+1);
			
			// check if there is one more shift to the right possible
			if(startInd+length+1>eqSol.length)
				break;
			// if so, do it
			for(int i=startInd+length ; i>startInd ; i--)
				eqSol[i]=eqSol[i-1];
			eqSol[startInd]=0;
			startInd++;
		}
		// after the final shift, restore the state
		for(int i=0 ; saveStartInd!=startInd && i<length ; i++){
			eqSol[saveStartInd+i]=eqSol[startInd+i];
			eqSol[startInd+i]=0;
		}
		
	}
	
	private boolean isSolution(byte vect[]){
		return !Tools.prodAboveLimitESShifted(eqSol, n);
//		return true;
	}
	
	public static long computeSolution(byte partitionVector[], int I, int k){
		// This function calculates all the possible permutations of the k-tuple associated
		// with a n-tuple given in parameter (eqSol). Example, the n-uple (2, 1) (n=2 here)
		// which is to be understood as 2 ONE and 1 TWO 
		// is associated with (1,1,2) but also (1,2,1) and (2,1,1) (k=3 here).
		// The formula used is sol=k!/prod(n_i!).
		
//		//DEBUG
//		compteur=compteur.add(BigInteger.ONE);

		// It can proven that the highest element (n_i) in eqSol will always be eqSol[0]
		// because of the condition on the product: eqSol[0]_minimum = k - LN(n)/LN(2).
		// => eqSol[0]_minimum > k/2 which makes eqSol[0]_minimum the highest element of eqSol
		// which makes eqSol[0] itself the highest element of eqSol. 
		// This can be used to simplify the calculation of k!.
		BigInteger answer=BigInteger.ONE;
		for(int i=k;I>0;I--, i--)
			answer=answer.multiply(BigInteger.valueOf(i));
		
		// Computing of the remaining factorials.
		for(int i=0; i<partitionVector.length;i++){
			if(partitionVector[i]<=0)
				throw new AssertionError();
			answer=answer.divide(BigIntegerMath.factorial(partitionVector[i]));
		}
		
		if(OUTPUT) System.out.println(Arrays.toString(partitionVector)+"--"+answer.mod(MOD_VALUE).longValue());
//		return answer.mod(MOD_VALUE).longValue();
		return answer.longValue();
	}

	public long getCountT() {
		return countT;
	}

}
