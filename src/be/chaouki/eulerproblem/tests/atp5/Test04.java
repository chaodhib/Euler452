package be.chaouki.eulerproblem.tests.atp5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asperen.permutations.PermutationsIterator;

public class Test04 {

	public static void main(String[] args) {
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
//		list.add(4);
//		treat(list);
		
		PermutationsIterator<Integer> pi=new PermutationsIterator<Integer>(list);
		while(pi.hasNext())
			treat(pi.next());
	}

	private static void treat(List<Integer> list) {
		System.out.println(list);
		
	}

}
