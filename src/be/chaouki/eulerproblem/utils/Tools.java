package be.chaouki.eulerproblem.utils;

import java.math.BigInteger;

public class Tools {

	public static int pow(int a, int b) // puissance d'entiers : a^b avec a et b possitif
	{							// fonction pas necessairement tres efficace
		if(a==0 & b==0)
			return -1;
		int solution=1;
		for(int i=0; i<b ; i++)
			solution*=a;
		return solution;
	}
	
	public static long ipow(long base, long exp) // Exponentiation by squaring
	{
	    long result = 1;
	    while (exp!=0)
	    {
	        if ((exp & 1)!=0)
	            result *= base;
	        exp >>= 1;
	        base *= base;
	    }

	    return result;
	}
	
	public static boolean prodAboveLimit(int k[], int lim){
		long prod=k[0];
		for(int i=1 ; i<k.length ; i++){
			prod*=k[i];
			if(prod>lim)
				return true;
		}
		return false;
	}
	
	public static boolean prodAboveLimitES(int[] eqSol, int lim) {
		long prod=1;
//		for(int i=eqSol.length-1 ; i>0 ; i--){
		for(int i=1 ; i<eqSol.length ; i++){
			for(int j=0 ; j<eqSol[i] ; j++){
				prod*=i+1;
				if(prod>lim)
					return true;
			}
		}
		return false;
	}
	
	public static String scientificFormatBigInteger(BigInteger a){
		if(a.compareTo(new BigInteger("10"))==-1)
			return a.toString()+".000 e0";
		else if(a.compareTo(new BigInteger("100"))==-1)
			return a.toString().charAt(0)+"."+a.toString().substring(1, 2)+"00 e"+(a.toString().length()-1);
		else if(a.compareTo(new BigInteger("1000"))==-1)
			return a.toString().charAt(0)+"."+a.toString().substring(1, 3)+"0 e"+(a.toString().length()-1);
		
//		if(a.compareTo(new BigInteger("1000"))==-1)
//			return a.toString();
		else
			return a.toString().charAt(0)+"."+a.toString().substring(1, 4)+" e"+(a.toString().length()-1);
	}
	
	public static int[] getCaractericVector(int v[], int n){
		int k[]=new int[n];
		for(int i=1 ; i<= n ; i++){
			int compteur=0;
			for(int j=0 ; j<v.length ; j++){
				if(v[j]==i)
					compteur++;
			}
			k[i-1]=compteur;
		}
		return k;
	}



}
