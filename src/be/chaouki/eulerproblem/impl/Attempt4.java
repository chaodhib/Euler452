package be.chaouki.eulerproblem.impl;
import java.math.BigInteger;

import be.chaouki.eulerproblem.utils.Tools;

import com.google.common.math.BigIntegerMath;

/**Troisieme implementation d'une solution. 
 * 
 * Refonte de la methode doPart1() en cours.
 * 
 * Rappel, la plus grande valeur stockable dans :
	int  : 2*10^9
	long : 9*10^18
	
	long est donc suffisant pour stocker les calculs de produits. Preuve:
	De par le problème, on sait que max(m)=10^9. En d'autres termes, m<=10^9
	De plus, les calculs de produits se font dans le programme de telle sorte que:
	a*b avec a et b <=m. Ce qui donne a*b <= m^2 <=> a*b <= 10^18.
	
	Long n'est néanmoins pas suffisant pour stocker le compteur.
	Utilisation donc de BigInteger pour contenir compteur.
	
 * @author Chaouki
 *
 */
public class Attempt4
{
	private int m, n;
	private BigInteger compteur;
	
	private int k[];
	
	public static void main(String[] args)
	{
		int m=10, n=10;
		Attempt4 solver=new Attempt4();
		while(true)
		{
			solver.setM(m);
			solver.setN(n);
			solver.findSolution();
			m+=10;
			n=m;
		}
		
	}
	
	private void findSolution()
	{
		this.k=new int[m];
		compteur=BigInteger.ZERO;
		long debut= System.nanoTime();
		doPart1();
		long fin= System.nanoTime();
		System.out.print("Pour m: "+m+" et n: "+n);
		System.out.print(" La solution au problème est: "+Tools.scientificFormatBigInteger(compteur));
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
	}
	
	private void doPart1() // trouve l'ensemble des k uple telle que les cdts A1 et A2 sont verifiees
	{
		// En cours. Le but est de changer la maniere dont les k-uples sont trouvés. La principale difficulte est de trouver
		// un algorithme plus efficase pour trouver les solutions de l equation diophantine lineare A2 (voir mes notes)
		// la piste que j'explore actuellement est le fait que ca revient a enumerer les solutions 
		// d'une combinaison avec remplacements (voir stars and bars problem)
		//TODO: calcul des produits du plus grand au plus petit terme
	}
	
	private BigInteger doPart2(int k[]) // calcul le nbre de n-uples associés au vecteur k 
	{
		// recherche de la valeur max dans k
		int max=k[0], ind=0, n_=n;
		n_>>=1;
		for(int i=1; max<n_ && i<k.length; i++) //TODO au lieu de recalculer n/2 a  chaque tour de boucle, le faire qu'une fois et pour /2, utiliser le décallement d'un cran vers la droite
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
				answer=answer.divide(BigIntegerMath.factorial(k[i])); 
		
		return answer;
	}

	public void setM(int m) {
		this.m = m;
	}

	public void setN(int n) {
		this.n = n;
	}
	

}
