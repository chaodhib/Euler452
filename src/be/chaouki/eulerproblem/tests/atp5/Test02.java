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
public class Test02 {
	
	private final static int N=7, I=100;

	public static void main(String[] args) {
		long debut = System.nanoTime();
		partition(N);
		partitionWithOffset(N, 1);
		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
	}
	
	public static void partition(int n) {
		int vect[]=new int[n];
        partition(n, n, vect, 0);
    }
	
	public static void partitionWithOffset(int n, int offset) {
		int vect[]=new int[n+1];
		vect[0]=I-n;
        partition(n, n, vect, offset);
    }
	
    private static void partition(int n, int max, int vect[], int ind) {
        if (n == 0) {
            System.out.println(Arrays.toString(vect)); //Treatment on the vector should be placed here
            return;
        }
  
        for (int i = Math.min(n, max); i >= 1; i--) {
        	vect[ind]=i;
            partition(n-i, i, vect, ind+1);
            vect[ind]=0;
        }
    }

}
