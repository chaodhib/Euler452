
public class PE452 {
	final static int m=10, n=30; // max so far : 10 et 9
	static int compteur, a;
	
	public static void main(String[] args) 
	{
		
//		createAndShow.process(m,  n);
//		findSolution();
		
		/*
		int a=13, b=5;
		long debut= System.nanoTime();
		for(int i=0;i<10000;i++)
		{
			Math.pow(a, b);
		}
		System.out.println("La solution du calcul est: "+ Math.pow(a, b));
		long fin= System.nanoTime();
		System.out.println("Le calcul a prit: "+ (fin-debut)/1000000 +"ms");
		
		debut= System.nanoTime();
		System.out.println("La solution du calcul est: "+ testfonction(a, b));
		fin= System.nanoTime();
		System.out.println("Le calcul a prit: "+ (fin-debut)/1000000 +"ms");
		*/
		
		/*
		int k[]={0,0,2,0};
		System.out.println(doPart2(k));
		*/
		
		System.out.println("factoriel de 0 :"+factorial(0));
		findSolution2();
//		int k[]={19,0,0,0,0,0,0,0,0,1};
//		doPart3(k);
	}
	
	private static int testfonction(int a, int b) 
	{
		for(int i=0;i<10000;i++)
		{
			pow(a,b);
		}
		return pow(a,b);
		
	}

	static void findSolution()
	{
		compteur=0;
		//a=0;
		long debut= System.nanoTime();
		traiterRecursivement2(1, n);
		long fin= System.nanoTime();
		System.out.println("La solution au problème est: "+compteur);
		System.out.println("Le calcul a prit: "+ (fin-debut)/1000000 +"ms");
	}
	
	static void traiterRecursivement2(int prec, int d)
	{
		if(d==0)
		{
			compteur++;
			return;
		}
		else
		{
			for(int i=0 ; i<m ; i++)
			{
				int produit=prec*(i+1);
				if(produit>m)
					return;
				else
				{
					//System.out.println("La solution au probleme est: "+compteur);
					traiterRecursivement2(produit, d-1);
				}
			}
		}
	}

	static void traiterRecursivement(byte[][]mat, int y0, int yL)
	{
		final int xL=(int)(Math.log10(yL)/Math.log10(m));
		
		for(int i=0 ; i<m ; i++)
		{
			for(int j=y0+i*yL/m; j!=y0+(i+1)*yL/m ;j++)
				mat[j][n-xL]=(byte)(i+1);
		}
			
		if(yL==m)
			return;
		else
		{
			for(int i=0;i<m;i++)
				traiterRecursivement(mat, y0+i*yL/m, yL/m);
		}
	}
	
	static void findSolution2()
	{
		int k[]=new int[m];
		compteur=0;
		long debut= System.nanoTime();
		doPartX(0, m, k); // <================== VERIFIER PARAMETRES
		long fin= System.nanoTime();
		System.out.println("La solution au problème est: "+compteur);
		System.out.println("Le calcul a prit: "+ (fin-debut)/1000000 +"ms");
	}
	
	static void doPartX(int prec, int d, int k[])
	{
		if(d==0)
		{
			if(doPart2(k) && prec==n)
				compteur+=doPart3(k);
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
					doPartX(somme, d-1, k);
				}
			}
		}
	}
	
	static void traiterParts()
	{
		int k[]=new int[m];
		compteur=0;
		
		for(int i=0; i< ipow(n+1,m); i++)
		{
			;
		}
	}
	
	
	
	static boolean doPart1(int k[])
	{
		int somme=0;
		for(int i=0 ; i<k.length; i++)
			somme+=k[i];
		return somme==n;
	}
	
	static boolean doPart2(int k[]) 	// calcul si l'ensemble des n-uples considérés satisfait la cdt : produit<=m
	{										// RECOIS un vecteur d'entiers de taille m
		int produit=1;
		for(int i=m-1; i>0 ;i--)
		{
			for(int j=k[i]; j>0 ; j--) 	//TODO il est peut etre possible d'optimiser cette fonction en utilisant directement pow
			{							// au lieu de cette boucle
				produit*=(i+1);
				if(produit>m)
					return false;
			}
		}
		
		return true;
	}
	
	static long doPart3(int k[]) // calcul le nbre de n-uples associés au vecteur k 
	{							// TODO optimisable en utilisant la simplification de division de factorielles
		long answer=factorial(n);
		for(int i=0; i<k.length;i++)
			answer/=factorial(k[i]);
			
		
		show(k);
		System.out.println("  et un compte associe de :" +answer);
		return answer;
	}
	
	private static void show(int[] k) 
	{
		for(int i=0; i<k.length ; i++)
			System.out.print("|"+k[i]+"|");
	}

	private static long factorial(int n2)	// n2 >=0  
	{										//TODO optimisable en utilisant le fast algo voir site web
		long ans=1;
		while(n2>1)
		{
			ans*=n2;
			n2--;
//			System.out.println("ans :" +ans);
		}
			
		return ans;
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
	
	static int ipow(int base, int exp) // Exponentiation by squaring
	{
	    int result = 1;
	    while (exp!=0)
	    {
	        if ((exp & 1)!=0)
	            result *= base;
	        exp >>= 1;
	        base *= base;
	    }

	    return result;
	}
}
