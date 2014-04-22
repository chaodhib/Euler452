import java.math.BigInteger;
import com.google.common.math.BigIntegerMath;
import org.apache.commons.math3.special.Gamma;


public class PE452 // cmd profiler : jvisualvm
{
	static int m=10, n=m; // max so far :  15 et 15 en 22s
	static BigInteger compteur, compteur_d;
	
	static int k[];
	
	public static void main(String[] args) 
	{
//		int k[]={0,0,0,2,0,0,2,0,0,0,0,2,0,0,9};
//		System.out.println(doPart2(k));
		
//		while(true)
		{
			k=new int[m];
			findSolution2();
			m+=50;
			n=m;
		}

	}
	
	static void findSolution2()
	{
		compteur=BigInteger.ZERO; compteur_d=BigInteger.ZERO;
		long debut= System.nanoTime();
		doPartX(0, 1, 0);
		long fin= System.nanoTime();
		System.out.print("Pour m: "+m+" et n: "+n);
		System.out.print(" La solution au problème est: "+compteur+" e"+(compteur.toString().length()-1));
		System.out.println(" et le calcul a pris: "+ (fin-debut)/1000000 +"ms");
		System.out.println("compteur_d: "+compteur_d);
		System.out.println("Arrangments: "+calculArrangmentsAvecR());
	}
	
	static long calculArrangmentsAvecR()
	{
		long resultat=1;
		for(int k=0;k!=n+1;k++)
			resultat*=n+k+1;
		
		return resultat;
	}
	
	static void doPartX(int sum0, int prod0, int d)
	{
		if(d==m)
		{
			if(sum0==n)
			{
				compteur=compteur.add(doPart3v2(k));
				compteur_d=compteur_d.add(BigInteger.ONE);
				show(k);
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
				if(prod>m)
					return;
				PE452.k[d]=k;
				doPartX(sum, (int)prod, d+1);
			}
		}
	}
	
	static BigInteger doPart3(int k[]) // calcul le nbre de n-uples associés au vecteur k 
	{
		BigInteger answer=BigIntegerMath.factorial(n);
		for(int i=0; i<m;i++)
			answer=answer.divide(BigIntegerMath.factorial(k[i]));
		
		return answer;
	}
	
	static BigInteger doPart3v2(int k[]) // calcul le nbre de n-uples associés au vecteur k 
	{
		// recherche de la valeur max dans k
		int max=k[0], ind=0;
		for(int i=1; max<n/2 && i<k.length; i++) //TODO au lieu de recalculer n/2 à chaque tour de boucle, le faire qu'une fois et pour /2, utiliser le décallement d'un cran vers la droite
		{
			if(k[i]>max)
			{
				max=k[i];
				ind=i;
			}
		}
		
		// <=========== TODO Posibilité ici d'optmiser le calcul des factorielles soit par la fonction gamma soit en calculant le log soit les deux en meme temps
		// simplif du calcul de quotient de factorielle
		BigInteger answer=BigInteger.ONE;
		for(int i=0;i<n-max;i++)
			answer=answer.multiply(BigInteger.valueOf(n-i));
		
		// calcul des factorielles restantes
		for(int i=0; i<k.length;i++) //
			if(i!=ind)
				//answer=answer.divide(factorial(k[i]));
				answer=answer.divide(BigIntegerMath.factorial(k[i])); 
		
//		show(k);
//		System.out.println(" et un compte associé de: " +answer);
		return answer;
	}
	
	static BigInteger doPart3v3(int k[]) //pas précis (nécessite tweaks sur la précision) et pas particulierement efficasement utile
	{
		double answer=Gamma.logGamma(n+1);
		for(int i=0;i<m;i++)
			answer-=Gamma.logGamma(k[i]+1);
		
		return BigInteger.valueOf((long)java.lang.Math.exp(answer));
	}
	
	static void show(int[] k) 
	{
		for(int i=0; i<k.length ; i++)
			System.out.print("|"+k[i]+"|");
		System.out.println();
	}

	static BigInteger factorial(int n2)	// n2 >=0  
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
	

}
