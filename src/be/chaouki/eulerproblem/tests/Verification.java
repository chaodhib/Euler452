package be.chaouki.eulerproblem.tests;

import java.math.BigInteger;

public class Verification {

	public static void main(String[] args) {
		BigInteger a=new BigInteger("569455563059708461411623");
		System.out.println(a.mod(new BigInteger("1234567891")));
	}

}
