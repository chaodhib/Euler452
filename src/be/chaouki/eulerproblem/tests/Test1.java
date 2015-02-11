package be.chaouki.eulerproblem.tests;
import java.math.BigInteger;


public class Test1 {

	public static void main(String[] args) {
		System.out.println("Test avec ints:");
		int a=1;
		for(int i=0 ; i<50 ; i++){
			System.out.println("10^"+i+" ="+a);
			a*=10;
			
		}
		
		int b=1;
		for(int i=0 ; i<50 ; i++){
			System.out.println("2^"+i+" ="+b);
			b*=2;
			
		}
		
		System.out.println("-------------Test avec longs:");
		long g=1;
		for(int i=0 ; i<50 ; i++){
			System.out.println("10^"+i+" ="+g);
			g*=10;
			
		}
		
		long h=1;
		for(int i=0 ; i<50 ; i++){
			System.out.println("2^"+i+" ="+h);
			h*=2;
			
		}
		
		System.out.println("-------------Test avec BigIntegers:");
		BigInteger c=BigInteger.ONE;
		for(int i=0 ; i<50 ; i++){
			System.out.println("10^"+i+" ="+c);
			c=c.multiply(BigInteger.TEN);
			
		}
		
		int d=2147483647;
		System.out.println("d: "+ d++);
		System.out.println("d: "+ d++);
		System.out.println("d: "+ d);
		
		int e=0,f=0;
		if((2|3)==2)
			System.out.println("");
	}

}
