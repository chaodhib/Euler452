package be.chaouki.eulerproblem.tests.atp4;

import java.util.Arrays;

public class Test2 {
	private static int o=6, l=2; // l== nombre d'élements à choisir parmis o éléments ordonnés.
	private static int k[]=new int[o];
	private static long compteur=0;
	
	 public static void main(String[] args) {
		
		for(int i=0; i<o ; i++)
			k[i]=i+1;
		System.out.println(Arrays.toString(k));
		long x[]=new long[1];
		x[0]=Long.parseUnsignedLong("0111", 2);
		
		boolean cdt=true;
		while(compteur<20)
			cdt=next_combination(x);
		System.out.println(compteur);
	}

	 /**
	  * Le but de cette methode est d'énumerer tous les manieres de sélectionner l élements 
	  * parmis o éléments distints et discernables
	  * Ces éléments sont les nombres 1 à o
	  * De plus, il est  nécessaire de ne PAS prendre en compte l'ordre de choix de ces élements.
	  * C'est à dire: prendre l'élément 1 puis l'élément 4 revient à la meme chose que prendre l'élement 4 puis 1
	  */
	 static boolean next_combination(long xA[]){ // assume x has form x'01^a10^b in binary
		 long u = xA[0] & -xA[0]; // extract rightmost bit 1; u =  0'00^a10^b
		 long v = u + xA[0]; // set last non-trailing bit 0, and clear to the right; v=x'10^a00^b
		 if (v==0) // then overflow in v, or x==0
		   return false; // signal that next k-combination cannot be represented
		 xA[0] = v +(((v^xA[0])/u)>>2); // v^x = 0'11^a10^b, (v^x)/u = 0'0^b1^{a+2}, and x <- x'100^b1^a
		 System.out.println(Long.toUnsignedString(xA[0], 2));
		 compteur++;
		 return true; // successful completion
	}
}
