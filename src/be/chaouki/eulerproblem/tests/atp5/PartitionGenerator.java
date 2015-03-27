package be.chaouki.eulerproblem.tests.atp5;

import java.util.Arrays;

/**modified version of the algorithm to generate the partition of N found here:
 * http://introcs.cs.princeton.edu/java/23recursion/Partition.java.html
 * 
 * This version uses recursivity. Therefore its probably not very efficient for high values of N.
 * But it doesnt matter here because we only need to get the partition of values up to 29 (case where n=m=10^9).
 * 
 * @author Chaouki
 *
 */
public class PartitionGenerator {
	
	public static void partition(int n, PartitionUser pu) {
		byte vect[]=new byte[n];
        partition(n, n, vect, 0, pu);
    }
	
//	public static void partitionWithOffset(int n, int offset, int base, int ind, PartitionUser pu) {
//		int vect[]=new int[n+1];
//		vect[0]=base-n;
//        partition(n, n, vect, offset, pu);
//    }
	
    private static void partition(int n, int max, byte vect[], int ind, PartitionUser pu) {
        if (n == 0) {
        	// vect nows contains the desire partition. except it may contains right side zeros 
        	// Example, one of the partitions of 6 is [4 2 0 0 0 0]. the following lines copy all 
        	// non zeros into a new vector that will be passed to the usePartition() method.
        	byte partitionLength=0;
    		while(partitionLength<vect.length && vect[partitionLength]!=0)
    			partitionLength++;
            pu.usePartition(Arrays.copyOf(vect, partitionLength), vect.length); //Treatment on the vector
            return;
        }
  
        for (byte i = (byte) Math.min(n, max); i >= 1; i--) {
        	vect[ind]=i;
            partition(n-i, i, vect, ind+1, pu);
            vect[ind]=0;
        }
    }

}
