
public class PE452 {
	final static int m=10, n=7; // max so far : 10 et 7

	public static void main(String[] args) {
		int compteur=0;
		
		// creation du tableau
		byte tab[][]=new byte[(int) Math.pow(m, n)][n];
		
		// remplissage
		traiterRecursivement(tab, 0, (int) Math.pow(m, n));
		
		// verif
		//afficher(tab,(int) Math.pow(m, n),n);
		
/**		// tri par ordre croissant
		for (int i=0;i<(m^n);i++)
			Arrays.sort(tab[i]);
		
		// on enleve les doublets
**/			
		// on enleve les n uples dont le produit > m et on compte ce qu'il reste
		for(int i=0; i<(int) Math.pow(m, n) ; i++)
		{
			int produit=tab[i][0];
			boolean depasseLimite=false;
			for(int j=1;!depasseLimite && j<n;j++)
			{
				produit*=tab[i][j];
				if(produit>m)
				{
					depasseLimite=true;
					//break; // TODO a ameliorer / verifier
				}
			}
			if(!depasseLimite)
				compteur++;
		}
		
		System.out.println("la solution du problème est: "+compteur);
		System.out.println("modulo-n de la solution: "+ compteur%n);
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
	
	static void afficher(byte[][]mat, int tailleX, int tailleY)
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
