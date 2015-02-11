package be.chaouki.eulerproblem.impl;

/** Premiere implementation d'une solution. 
 * 
 *  Le principe utilisee est une fonction recursive qui va generer 
 *  l'ensemble des n-uple telle que n_i soit compris entre 1 et m pour tout i compris entree 1 et n.
 *  On a donc m^n n-uples que l'on stocke dans "tab".
 *  Il suffit alors d'ecarter les n-uple telle que le produit des n_i > m.
 * 
 *  Celle ci necessite une quantite irrealiste de memoire pour des valeurs de n et m > 5. 
 *  De plus, il y a des bug apd de m=n=5 à cause de la perte de precision due a des divisions d'entiers.
 *  Cette solution est donc abandonnee.
 * 
 * @author Chaouki
 */
public class Attempt1 {
	final static int m=4, n=4;

	public static void main(String[] args) {
		int compteur=0;
		
		// creation du tableau
		byte tab[][]=new byte[(int) Math.pow(m, n)][n];
		
		// remplissage
		traiterRecursivement(tab, 0, (int) Math.pow(m, n));
		
		// verif
		afficher(tab,(int) Math.pow(m, n),n);
		
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
				}
			}
			if(!depasseLimite)
				compteur++;
		}
		
		System.out.println("la solution du problème est: "+compteur);
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
