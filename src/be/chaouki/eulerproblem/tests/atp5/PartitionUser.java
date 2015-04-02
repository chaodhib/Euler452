package be.chaouki.eulerproblem.tests.atp5;

import java.math.BigInteger;
import java.util.Arrays;

import com.google.common.math.BigIntegerMath;

import be.chaouki.eulerproblem.utils.Math;
import be.chaouki.eulerproblem.utils.Tools;

public class PartitionUser {
	
//	public static BigInteger count;
	private long countT;
	private long count;

	private int n ,k;
	private int I;
	private byte[] eqSol;
	
	private static final BigInteger MOD_VALUE=new BigInteger("1234567891");
	private static final long MOD_VALUE_L=1234567891;
	
	public static BigInteger factorialSave[];
	
	public PartitionUser(int size, int I, int k, int n){
		countT=0;
		eqSol=new byte[size];
		this.I=I;
		this.n=n;
		this.k=k;
	}

	/**	if partitionVector=[a, b, c, d], the first step is to get all permutations of this same set.
		meaning: [b, a, c ,d], [b, c, a, d], [b, c, d, a], etc...
		the algorithm used here MUST support repetitions (for instance, a=b).
	 * 
	 * @param partitionVector
	 * @param I
	 */
	public void usePartition(byte[] partitionVector) {
		if(Launcher.OUTPUT) System.out.println(Arrays.toString(partitionVector)+"---NEW PARTITION----");
		
		if(!isSolution(partitionVector))
			return;
		count=0;
		
		do{
			if(Launcher.OUTPUT) System.out.println(Arrays.toString(partitionVector)+"---NEW PERMUTATION----");
			if(partitionVector.length>eqSol.length)
				break;
			//copy the partition into the eqSol vector
			for(int i=0; i<partitionVector.length ; i++)
				eqSol[i]=partitionVector[i];
			//and clean the right side of the vector unaffected by the copy of partitionVector
//			for(int i=partitionVector.length ; i<eqSol.length ; i++)
//				eqSol[i]=0;
			if(isSolution(partitionVector))
				findPermuts((byte)partitionVector.length, 0);
		} while(PermutationGenerator.gen_perm_rep_colex_next(partitionVector)); 
		// generate next permutation in colexicographic order & test whether or not it's a solution
		
		/// clean up of eqSol for later method calls on the same PartitionUser object
		for(int i=0 ; i<partitionVector.length ; i++)
			eqSol[i]=0;
		
		// update of the total count
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

	/** This method needs to receive eqSol in a solution state.
	 * 
	 * @param length
	 * @param startInd
	 */
	public void findPermuts(byte length, int startInd) { //TODO: put private back after testing
		if(!isSolution())
			throw new AssertionError();
		
		if(eqSol.length==length){
			count++;
			return;
		}
		
		byte movingValues[]=new byte[length];
		for(int i=0 ; i<length ; i++){
			movingValues[i]=eqSol[i+startInd];
			eqSol[i+startInd]=0;
		}
		// apply last possible shift
		applyValueTransfer(eqSol.length-length, movingValues);
		
		// test if solution
		boolean isSolution=isSolution();
		desapplyValueTransfer(eqSol.length-length, length);
		
		// if solution, bingo, a lot of steps can be skipped. we can update the solution count and return.
		if(isSolution){
			int lengthMovableValues=eqSol.length-startInd;
			long addCount=0;
			
			for(int i=lengthMovableValues-length+1 ; i>0 ; i--)
				addCount+=i*(i+1);
			addCount/=2L;

//			if(Launcher.USE_MODULO)
//				addCount%=MOD_VALUE_L;
			count+=addCount;
			if(Launcher.USE_MODULO)
				count%=MOD_VALUE_L;
			
			applyValueTransfer(startInd, movingValues); //restore state
			return;
		}
		
		// if not solution, we use a binary search to find the highest ind for which eqSol is solution
		int highestSolutionInd=binarySearch(startInd, eqSol.length-length, movingValues);
		applyValueTransfer(startInd, movingValues); //restore state
		// and we operate as usual
		if(length==1){
			long addCount=highestSolutionInd-startInd+1;
//			if(Launcher.USE_MODULO)
//				addCount%=MOD_VALUE_L; 
			count+=addCount;
			
			if(Launcher.USE_MODULO)
				count%=MOD_VALUE_L;
			
			return;
			
		} else{
			int saveStartInd=startInd;
			while(startInd<=highestSolutionInd){
				findPermuts((byte) (length-1), startInd+1);
				
				// check if it is possible to shift one more time to the right 
				if(startInd+length+1>eqSol.length)
					break;
				// if so, do it
				for(int i=startInd+length ; i>startInd ; i--)
					eqSol[i]=eqSol[i-1];
				eqSol[startInd]=0;
				startInd++;
			}
			// after the final shift, restore the initial state of eqSol
			if(saveStartInd!=startInd){
				for(int i=0 ; i<length ; i++){
					eqSol[saveStartInd+i]=eqSol[startInd+i];
					eqSol[startInd+i]=0;
				}
			}
		}
	}
	
	/**
	 * eqSol must be received in a "neutral" state. the state before a transformation
	 * @param lo
	 * @param hi
	 * @return
	 */
	private int binarySearch(int lo, int hi, byte v[]) {
		while (lo<hi-1) {
		    // the index solution should be in [lo..hi[
		    int mid = lo + (hi - lo) / 2;
		    
		    // test then put eqSol back into its original state.
		    applyValueTransfer(mid, v);
		    boolean isSolution=isSolution();
		    desapplyValueTransfer(mid, v.length);
		    
		    if(isSolution)
		    	lo = mid;
		    else
		    	hi = mid;
		}
		
		return lo;
	}
	
	private void applyValueTransfer(int startInd, byte v[]){
		for(int i=0 ; i<v.length ; i++)
			eqSol[startInd+i]=v[i];
	}
	private void desapplyValueTransfer(int startInd, int length){
		for(int i=0 ; i<length ; i++)
			eqSol[startInd+i]=0;
	}
	
	private boolean isSolution(){
		return !Tools.prodAboveLimitESShifted(eqSol, n);
	}
	
	private boolean isSolution(byte vector[]){
		return !Tools.prodAboveLimitESShifted(vector, n);
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

	//TODO: to be removed after debugging
	public byte[] getEqSol() {
		return eqSol;
	}

	public void setEqSol(byte[] eqSol) {
		this.eqSol = eqSol;
	}

}
