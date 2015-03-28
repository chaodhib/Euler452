package be.chaouki.eulerproblem.tests.atp5;

import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Tools;

public class Tests {

	public static void main(String[] args) {
		int n=400;
		byte vect2[]={1,2,3};
		do{
			System.out.println(Arrays.toString(vect2)+" "+!Tools.prodAboveLimitESShifted(vect2, n));
		} while(PermutationGenerator.gen_perm_rep_lex_next(vect2));
		
		System.out.println("--------------");
		
		byte vect[]={3,2,1};
		do{
			System.out.println(Arrays.toString(vect)+" "+!Tools.prodAboveLimitESShifted(vect, n));
		} while(PermutationGenerator.gen_perm_rep_colex_next(vect));
		
	}

}
