
public class PE452 {
	final static int m=2, n=4; // max so far : 10 et 9
	static int compteur, a;
	
	public static void main(String[] args) 
	{
		//findSolution();
		createAndShow.process(m,  n);
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
					//System.out.println("La solution au problème est: "+compteur);
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

}
