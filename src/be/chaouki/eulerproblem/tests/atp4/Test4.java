package be.chaouki.eulerproblem.tests.atp4;

import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Math;

public class Test4 {

	public static void main(String[] args) {
		int n=100, k=99;
		
		int vector[]=new int[k];
		for(int i=0;i<k;i++)
			vector[i]=0;
		System.out.println(Arrays.toString(vector));

		int gen_result=Math.GEN_NEXT;
		while(gen_result==Math.GEN_NEXT){
			gen_result=Math.gen_comb_rep_lex_next(vector, n, k);
			System.out.println(Arrays.toString(vector));
		}
	}

}
