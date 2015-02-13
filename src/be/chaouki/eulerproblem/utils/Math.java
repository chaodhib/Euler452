package be.chaouki.eulerproblem.utils;

public class Math {
	
	public final static int GEN_NEXT=0;
	public final static int GEN_TERM=1;
	public final static int GEN_EMPTY=2;
	public final static int GEN_ERROR=3;
	
	/**based on the algorithm written in C available here: 
	 * http://www.aconnect.de/friends/editions/computer/combinatoricode_e.html
	 * 
	 * @param vector
	 * @param n
	 * @param k
	 * @return
	 */
	public static int gen_comb_rep_lex_next(int vector[], int n, int k)
	{
		int j; //index
	
		//easy case, increase rightmost element
		if(vector[k - 1] < n - 1)
		 {
		 vector[k - 1]++;
		 return(GEN_NEXT);
		 }
	
		//find rightmost element to increase
		for(j = k - 2; j >= 0; j--)
		 if(vector[j] != n - 1)
		  break;
	
		//terminate if all elements are n - 1
		if(j < 0)
		 return(GEN_TERM);
	
		//increase
		vector[j]++;
	
		//set right-hand elements
		for(j += 1; j < k; j++)
		 vector[j] = vector[j - 1];
	
		return(GEN_NEXT);
	}
}
