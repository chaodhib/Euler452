package be.chaouki.eulerproblem.tests.atp4;

import java.math.BigInteger;

import be.chaouki.eulerproblem.utils.Tools;

public class Verification {

	public static void main(String[] args) {
		BigInteger a=new BigInteger("541176768851948155");
		System.out.println(a.mod(new BigInteger("1234567891")));
		
		System.out.println(
				1770*6+3540*68+34220*2+102660*16+205320*12+487635+1950540*5+2925810+5851620*2+5461512+27307560+1+59*60);
	
		byte[] eqSol={3,3};
		System.out.println(!Tools.prodAboveLimitESShifted(eqSol, 216));
	}

}
