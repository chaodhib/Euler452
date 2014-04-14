
public class createAndShow {
	public static int m, n;

	static void process(int m, int n)
	{
		createAndShow.m=m;
		createAndShow.n=n;
		
		byte tab[][]=new byte[(int) Math.pow(m, n)][n];
		
		// remplissage
		traiterRecursivement(tab, 0, (int) Math.pow(m, n));
		
		// affichage
		show(tab,(int) Math.pow(m, n),n);
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

	static void show(byte[][]mat, int tailleX, int tailleY)
	{
		// affiche la matrice en inversant les coordonnees x et y
		for(int i=0;i<tailleX;i++)
		{
			for(int j=0;j<tailleY;j++)
			{
				System.out.print("|"+mat[i][j]+"|");
			}
			System.out.println();
		}
	}
}


