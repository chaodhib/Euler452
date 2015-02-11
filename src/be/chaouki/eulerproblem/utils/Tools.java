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

}
