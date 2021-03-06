package be.chaouki.eulerproblem.impl;
import java.math.BigInteger;
import java.util.Arrays;

import be.chaouki.eulerproblem.utils.Tools;

import com.google.common.math.BigIntegerMath;

/**Troisieme implementation d'une solution. 
 * 
 * Une nouvelle maniere est utilisee pour arriver a la solution. Voir doc attachee.
 * Deux etapes majeurs :
 * 1) doPart1: recherche des k-uple qui satisfont les conditions A1 et A2
 * 2) doPart2: calcul du nombre de n-uple associ� a chaque k-uple solution
 * 
 * Rappel, la plus grande valeur stockable dans :
	int  : 2*10^9
	long : 9*10^18
	
	long est donc suffisant pour stocker les calculs de produits. Preuve:
	De par le probl�me, on sait que max(m)=10^9. En d'autres termes, m<=10^9
	De plus, les calculs de produits se font dans le programme de telle sorte que:
	a*b avec a et b <=m. Ce qui donne a*b <= m^2 <=> a*b <= 10^18.
	
	Long n'est n�anmoins pas suffisant pour stocker le compteur.
	Utilisation donc de BigInteger pour contenir compteur.
	
 * @author Chaouki
 *
 */
public class Attempt3
{
	
	private static int m=4, n=8;
	private static BigInteger compteur, compteur_d;
	
	private static int k[];
	
	public static void main(String[] args)
	{
//		while(true)
		{
			k=new int[m];
			findSolution();
			m+=1;
			n=m;
		}
		
	}
	
	private static void findSolution()
	{
		compteur=BigInteger.ZERO; compteur_d=BigInteger.ZERO;
		long debut= System.nanoTime();
		doPart1(0, 1, 0);
		long fin= System.nanoTime();
		System.out.print("Pour m: "+m+" et n: "+n);
		System.out.print(" La solution au probl�me est: "+Tools.scientificFormatBigInteger(compteur));
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
		System.out.println("compteur_d= "+compteur_d);
	}
	
	private static void doPart1(int sum0, long prod0, int d) // trouve l'ensemble des k uple telle que les cdts A1 et A2 sont verifiees
	{
		if(d==m)
		{
			if(sum0==n)
			{
//				compteur=compteur.add(doPart2(k));
				compteur_d=compteur_d.add(BigInteger.ONE);
				System.out.println(Arrays.toString(k));
//				show(k);
			}
				
			return;
		}
		else
		{
			long prod=prod0;
			int sum=sum0-1;
			for(int k=0 ; k<=n ; k++)
			{
				sum++;
				if(sum>n)
					return;
				if(k!=0)
					prod*=d+1;
//				if(prod>m)
//					return;
				Attempt3.k[d]=k;
				doPart1(sum, prod, d+1);
			}
		}
	}
	
	
	private static BigInteger doPart2(int k[]) // calcul le nbre de n-uples associ�s au vecteur k 
	{
		// recherche de la valeur max dans k
		int max=k[0], ind=0, n_=n;
		n_>>=1;
		for(int i=1; max<n_ && i<k.length; i++) //TODO au lieu de recalculer n/2 a� chaque tour de boucle, le faire qu'une fois et pour /2, utiliser le d�callement d'un cran vers la droite
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
		for(int i=0; i<k.length;i++) //
			if(i!=ind)
				//answer=answer.divide(factorial(k[i]));
				answer=answer.divide(BigIntegerMath.factorial(k[i])); 
		
		return answer;
	}
	

}
