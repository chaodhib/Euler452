import java.math.BigInteger;


public class PE452 {
	static int m=10, n=m; // max so far :  15 et 15 en 22s
	static BigInteger compteur;
	
	static int k[];
	
	public static void main(String[] args) 
	{
//		int k[]={0,0,0,2,0,0,2,0,0,0,0,2,0,0,9};
//		System.out.println(doPart2(k));
		
		while(true)
		{
			k=new int[m];
			findSolution2();
			m+=50;
			n=m;
		}

	}
	
	static void findSolution2()
	{
		compteur=BigInteger.ZERO;
		long debut= System.nanoTime();
		doPartX(0, 1, m);
		long fin= System.nanoTime();
		System.out.print("Pour m: "+m+" et n: "+n);
		System.out.print(" La solution au problème est: "+compteur);
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
	}
	
	/*
	static void doPartX(int prec, int d)
	{
		if(d==0)
		{
			if(doPart2(k) && prec==n)
			{
//				show(k); System.out.println();
				compteur+=doPart3v2(k);
			}
				
			return;
		}
		else
		{
			for(int i=0 ; i<=n ; i++)
			{
				int somme=prec+i;
				if(somme>n)
					return;
				else
				{
					k[m-d]=i;
					doPartX(somme, d-1);
				}
			}
		}
	}
	*/
	
	static void doPartX(int sum0, int prod0, int d)
	{
		if(d==0)
		{
			//if(doPart2(k) && sum0==n)
			if(sum0==n)
			{
//				show(k); System.out.println();
				compteur=compteur.add(doPart3v2(k));
			}
				
			return;
		}
		else
		{
			for(int i=0 ; i<=n ; i++)
			{
				int sum=sum0+i;
				if(sum>n)
					return;
				else
				{
					long prod=prod0;
					for(int j=0; j<i; j++)
					{
						prod*=m-d+1;
						if(prod>m)
							return;
					}
					
					k[m-d]=i;
					doPartX(sum, (int)prod, d-1);
				}
			}
		}
	}
	
	/*
	static boolean doPart1(int k[])
	{
		int somme=0;
		for(int i=0 ; i<k.length; i++)
			somme+=k[i];
		return somme==n;
	}
	*/
	
	static boolean doPart2(int k[]) 	// calcul si l'ensemble des n-uples considérés satisfait la cdt : produit<=m
	{										// RECOIS un vecteur d'entiers de taille m
		long produit=1;
		for(int i=m-1; i>0 ;i--)
		{
			for(int j=k[i]; j>0 ; j--) 	//TODO il est peut etre possible d'optimiser cette fonction en utilisant directement pow
			{							// au lieu de cette boucle EDIT: nop
				produit*=(i+1);
				if(produit>m)
					return false;
			}
			
			/*
			
			if(k[i]==0)
				produit*=1;
			if(k[i]==1)
				produit*=(i+1);
			else
				produit*=ipow(i+1, k[i]);
			
			if(produit>m)
				return false;
				*/
		}
		
		return true;
	}
	
	static BigInteger doPart3(int k[]) // calcul le nbre de n-uples associés au vecteur k 
	{							// TODO optimisable en utilisant la simplification de division de factorielles
		BigInteger answer=factorial(n);
		for(int i=0; i<k.length;i++)
			answer=answer.divide(factorial(k[i]));
			
		
//		show(k);
//		System.out.println(" et un compte associé de: " +answer);
		return answer;
	}
	
	static BigInteger doPart3v2(int k[]) // calcul le nbre de n-uples associés au vecteur k 
	{
		// recherche de la valeur max dans k
		int max=k[0], ind=0;
		for(int i=1; max<n/2 && i<k.length; i++)
		{
			if(k[i]>max)
			{
				max=k[i];
				ind=i;
			}
		}
		
		// simplif du calcul de quotient de factorielle
		BigInteger answer=BigInteger.ONE;
		for(int i=0;i<n-max;i++)
			answer=answer.multiply(BigInteger.valueOf(n-i));
		
		// calcul des factorielles restantes
		for(int i=0; i<k.length;i++)
			if(i!=ind)
				answer=answer.divide(factorial(k[i]));//TODO: utiliser la fonctio "gamma" pour le calcul de factorielle voir web
		
//		show(k);
//		System.out.println(" et un compte associé de: " +answer);
		return answer;
	}
	
	private static void show(int[] k) 
	{
		for(int i=0; i<k.length ; i++)
			System.out.print("|"+k[i]+"|");
	}

	private static BigInteger factorial(int n2)	// n2 >=0  
	{										//TODO optimisable en utilisant le fast algo voir site web
		BigInteger ans=BigInteger.ONE;
		while(n2>1)
		{
			ans=ans.multiply(BigInteger.valueOf(n2));
			n2--;
//			System.out.println("ans :" +ans);
		}
			
		return ans;
	}
	
	static long ipow(long base, long exp) // Exponentiation by squaring
	{
//		System.out.print(base+"--"+exp);
	    long result = 1;
	    while (exp!=0)
	    {
	        if ((exp & 1)!=0)
	            result *= base;
	        exp >>= 1;
	        base *= base;
	    }
//	    System.out.println("  "+result);

	    return result;
	}
}
