package be.chaouki.eulerproblem.tests.atp5;

import java.math.BigInteger;
import java.util.Arrays;

import com.google.common.math.BigIntegerMath;

import be.chaouki.eulerproblem.utils.Tools;

public class PartitionUser {
	
//	public static BigInteger count;
	private long countT;
	private long count;

	private int n ,k;
	private long solution;
	private byte[] eqSol;
	
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891");
	private static final long MOD_VALUE_L=1234567891;
	
	public PartitionUser(int size, int k, int n){
		countT=0;
		eqSol=new byte[size];
		this.n=n;
		this.k=k;
	}

	public void usePartition(byte[] partitionVector, int I) {
		if(Launcher.OUTPUT) System.out.println(Arrays.toString(partitionVector)+"---NEW PARTITION----");
		count=0;
//		System.out.println(Arrays.toString(partitionVector));
		
		// if partitionVector=[a, b, c, d], the first step is to get all permutations of this same set.
		// meaning: [b, a, c ,d], [b, c, a, d], [b, c, d, a], etc...
		// the algorithm used here MUST support repetitions (for instance, a=b).
		
		do{
			if(Launcher.OUTPUT) System.out.println(Arrays.toString(partitionVector)+"---NEW PERMUTATION----");
			if(partitionVector.length>eqSol.length)
				break;
			//copy the partition into the eqSol vector
			for(int i=0; i<partitionVector.length ; i++)
				eqSol[i]=partitionVector[i];
			//and clean the right side of the vector unaffected by the copy of partitionVector
			for(int i=partitionVector.length ; i<eqSol.length ; i++)
				eqSol[i]=0;
			
			findPermuts((byte)partitionVector.length, 0);
		} while(PermutationGenerator.gen_perm_rep_colex_next(partitionVector)); // generate next permutation 
																				// in colexicographic order
		
		if(count>0){
			if(Launcher.OUTPUT) System.out.println(Arrays.toString(partitionVector)+", count="+count);
			
			if(Launcher.USE_MODULO){
				countT+=(computeSolution(partitionVector, I, k)*(count%MOD_VALUE_L))%MOD_VALUE_L;
				countT=countT%MOD_VALUE_L;
			} else{
				countT+=(computeSolution(partitionVector, I, k)*count);
			}
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
				if(Launcher.OUTPUT) System.out.println(Arrays.toString(eqSol)+" "+Tools.prodAboveLimitESShifted(eqSol, n));
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
	}
	
	public static long computeSolution(byte partitionVector[], int I, int k){
		BigInteger answer=BigInteger.ONE;
		for(int i=k;I>0;I--, i--)
			answer=answer.multiply(BigInteger.valueOf(i));
		
		// Computing of the remaining factorials.
		for(int i=0; i<partitionVector.length;i++){
			if(partitionVector[i]<=0)
				throw new AssertionError();
			answer=answer.divide(BigIntegerMath.factorial(partitionVector[i]));
		}
		
		if(Launcher.OUTPUT) {
			if(Launcher.USE_MODULO)
				System.out.println(Arrays.toString(partitionVector)+"--"+answer.mod(MOD_VALUE).longValue());
			else
				System.out.println(Arrays.toString(partitionVector)+"--"+answer.longValue());
		}
		
		if(Launcher.USE_MODULO)
			return answer.mod(MOD_VALUE).longValue();
		else 
			return answer.longValue();
	}

	public long getCountT() {
		return countT;
	}

}
