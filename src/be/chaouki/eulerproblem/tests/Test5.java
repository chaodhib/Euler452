package be.chaouki.eulerproblem.tests;

import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Math;

public class Test5 {
	
	private static boolean output=true;
	private static int compteur=0;

	public static void main(String[] args) {
		int n=8, k=3;
		long debut= System.nanoTime();
		
		
		int vector[]=new int[k];
		for(int i=0;i<k;i++)
			vector[i]=1;
		compteur++;
		if(output){
			System.out.println(Arrays.toString(vector));
		}
		
		gen_comb_w_rep(vector, n, k);
		System.out.println(compteur);
		long fin= System.nanoTime();
		System.out.println("le calcul a pris: "+ (fin-debut)/1000000 +"ms");
	}

	public static void gen_comb_w_rep(int vector[], int n, int k)
	{
		int gen_result=Math.GEN_NEXT;
		while(gen_result==Math.GEN_NEXT){
			int j; //index
			
			//easy case, increase rightmost element
			if(vector[k - 1] < n)
			 {
			 vector[k - 1]++;
			 gen_result=Math.GEN_NEXT;
			 
			 compteur++;
			 if(output){
					System.out.println(Arrays.toString(vector));
			 }
			 continue;
			 }
		
			//find rightmost element to increase
			for(j = k - 2; j >= 0; j--)
			 if(vector[j] != n)
			  break;
		
			//terminate if all elements are n - 1
			if(j < 0)
			 return;
		
			//increase
			vector[j]++;
		
			//set right-hand elements
			for(j += 1; j < k; j++)
			 vector[j] = vector[j - 1];
		
			compteur++;
			 if(output){
					System.out.println(Arrays.toString(vector));
			 }
			gen_result=Math.GEN_NEXT;
		}
		
	}
}
