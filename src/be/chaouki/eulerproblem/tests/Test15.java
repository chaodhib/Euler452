package be.chaouki.eulerproblem.tests;

public class Test15 {
	
	public static void main(String[] args) {
		long debut = System.nanoTime();
		int vect[]={2,1,1,0,0};
		permutation(vect);
		long fin = System.nanoTime();
		System.out.println("le calcul a pris: " + (fin - debut) / 1000000+ "ms");
	}

	private static void permutation(int[] vect) {
		
		
	}
}
