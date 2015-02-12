package be.chaouki.eulerproblem.impl;

/** Deuxieme implementation d'une solution. 
 *  Le principe utilisee est une fonction recursive qui va generer 
 *  l'ensemble des n-uple telle que n_i soit compris entre 1 et m pour tout i compris entree 1 et n.
 *  On a donc m^n n-uples que l'on stocke dans "tab".
 *  Il suffit alors d'ecarter les n-uple telle que le produit des n_i > m.
 *  La difference ici est que les n-uples ne sont pas sauvegardes en memoire.
 *  La valeur compteur, celle qui stock le nombre de n-uple solutions rencontres
 *  jusqu'a present, est incremente directement dans la condition d'arret 
 *  de la methode recursive.
 * 
 * @author Chaouki
 *
 */
public class Attempt2 {
	static int m=1, n=1; // 65 et 65 en 48s, sol = 224212769
	static int compteur;

	public static void main(String[] args) 
	{
		while(true)
		{
			findSolution();
			m+=9;
			n=m;
		}
	}

	static void findSolution()
	{
		compteur=0;
		
		long debut= System.nanoTime();
//		traiterRecursivement(1, 0);
		traiterRecursivement2(1, n);
		long fin= System.nanoTime();
		System.out.print("Pour m: "+m+" et n: "+n);
		System.out.print(" La solution au probleme est: "+compteur);
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
	}
	
	static void traiterRecursivement(int prec, int d) //param initiaux :(1, 0).
	{
		if(d==n)
		{
			if(prec<=m)
				compteur++;
			return;
		}
		else
		{
			for(int i=0 ; i<m ; i++)
				traiterRecursivement((int)(i+1)*prec, d+1);
		}
	}
	
	
	static void traiterRecursivement2(int prec, int d) //param initiaux :(1, n).
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
					traiterRecursivement2((int)produit, d-1);
			}
		}
	}

	

}
