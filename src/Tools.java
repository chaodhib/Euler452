
public class Tools {
	

	private static int testfonction(int a, int b) 
	{
		for(int i=0;i<10000;i++)
		{
			pow(a,b);
		}
		return pow(a,b);
		
	}

	static int pow(int a, int b) // puissance d'entiers : a^b avec a et b possitif
	{							// fonction pas nécessairement tres efficace
		if(a==0 & b==0)
			return -1;
		int solution=1;
		for(int i=0; i<b ; i++)
			solution*=a;
		return solution;
	}
	
	

}
