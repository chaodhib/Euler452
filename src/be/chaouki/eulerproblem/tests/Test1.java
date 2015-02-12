package be.chaouki.eulerproblem.tests;
import java.math.BigInteger;

import be.chaouki.eulerproblem.utils.Tools;


public class Test1 {

	public static void main(String[] args) {
		/*
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
		
//		int e=0,f=0;
//		if((2|3)==2)
//			System.out.println("");
		
		*/
		
		
		long debut, fin; 
		int a;
		long b;
		
		// gets the thread started so the order of testing doesnt influence the results - last a few seconds
		for(int j=0;j<10000 ; j++)
		for(int i=0;i<1000000 ; i++)
			a=463543*54324354;
		
		// LONG = LONG*LONG
		debut= System.nanoTime();
		for(int j=0;j<100 ; j++)
		for(int i=0;i<1000000 ; i++)
			b=7L*54324354L;
		fin= System.nanoTime();
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
		
		// INT = INT*INT
		debut= System.nanoTime();
		for(int j=0;j<100 ; j++)
		for(int i=0;i<1000000 ; i++)
			a=7*54324354;
		fin= System.nanoTime();
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
		
		// INT = MULTIPLYEXACT(INT, INT)
		debut= System.nanoTime();
		for(int j=0;j<100 ; j++)
		for(int i=0;i<1000000 ; i++)
			a=Math.multiplyExact(7, 54324354);
		fin= System.nanoTime();
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
		
		// LONG = MULTIPLYEXACT(LONG, LONG)
		debut= System.nanoTime();
		for(int j=0;j<100 ; j++)
		for(int i=0;i<1000000 ; i++)
			b=Math.multiplyExact(7L, 54324354L);
		fin= System.nanoTime();
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
		
		
		// conclusion : multiplying ints together or longs together by the standart operator takes roughly
		// the same time. And there is a ratio x10 to use multiply(int, int) and a ratio 20x to use multiply(long, long)
		
	}

}
