package powerDigitSum;

import java.math.BigInteger;
import java.util.Scanner;
//takes a base and a power, return base^power and the sum of the digits of base^power
public class PowerDigitSum {
	
	public PowerDigitSum(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Base: ");
		int base = keyboard.nextInt();
		System.out.println("Power: ");
		int power = keyboard.nextInt();
		keyboard.close();
		BigInteger total = new BigInteger(String.valueOf(base));
		total = total.pow(power);
		System.out.println(base + "^" + power + " = " + total.toString() + " and the sum of the digits are " + getSumOfDigits(total.toString()));
	}
	
	private int getSumOfDigits(String numString){
		if(numString.substring(0,1) == ".")numString = numString.substring(1);
		if(numString.length()== 1) return Integer.valueOf(numString);
		return Integer.valueOf(numString.substring(0,1)) + getSumOfDigits(numString.substring(1));
	}
	
	public static void main(String [] args){
	PowerDigitSum pds = new PowerDigitSum();
	}
}
