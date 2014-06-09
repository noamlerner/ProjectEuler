package factorialDigitSum;

import java.math.BigInteger;
import java.util.Scanner;
//finds the sum of the digits of the factorial of a inputed number.
public class FactorialDigitSum {
	
	public FactorialDigitSum(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Number: ");
		int num = keyboard.nextInt();
		BigInteger factorial = getFactorial(new BigInteger(String.valueOf(num)));
		System.out.println(getSumOfDigits(factorial.toString()));
		keyboard.close();
	}
	public static void main (String [] args){
		FactorialDigitSum fds = new FactorialDigitSum();
	}
	private BigInteger getFactorial(BigInteger num){
		if(num.toString().equals("1"))return new BigInteger("1");
		return num.multiply(getFactorial(num.subtract(new BigInteger("1"))));
	}
	private int getSumOfDigits(String numString){
		if(numString.substring(0,1) == ".")numString = numString.substring(1);
		if(numString.length()== 1) return Integer.valueOf(numString);
		return Integer.valueOf(numString.substring(0,1)) + getSumOfDigits(numString.substring(1));
	}

}