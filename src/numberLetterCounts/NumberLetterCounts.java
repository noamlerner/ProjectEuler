package numberLetterCounts;

import java.util.Scanner;
/*
 * takes two integers and gives the sum of the letters that spell out all of the integers between these to numbers inclusive
 */
public class NumberLetterCounts {
	private String [] under20String;
	private String[] tensDigitString;
	public NumberLetterCounts(){
		under20String = new String[21];
		under20String[0] = "";
		under20String[1] = "one";
		under20String[2] = "two";
		under20String[3] = "three";
		under20String[4] = "four";
		under20String[5] = "five";
		under20String[6] = "six";
		under20String[7] = "seven";
		under20String[8] = "eight";
		under20String[9] = "nine";
		under20String[10] = "ten";
		under20String[11] = "eleven";
		under20String[12] = "twelve";
		under20String[13] = "thirteen";
		under20String[14] = "fourteen";
		under20String[15] = "fifteen";
		under20String[16] = "sixteen";
		under20String[17] = "seventeen";
		under20String[18] = "eighteen";
		under20String[19] = "nineteen";
		under20String[20] = "twenty";
		
		
		tensDigitString = new String[10];
		tensDigitString[0] = "";
		tensDigitString[1] = "";
		tensDigitString[2] = "twenty";
		tensDigitString[3] = "thirty";
		tensDigitString[4] = "forty";
		tensDigitString[5] = "fifty";
		tensDigitString[6] = "sixty";
		tensDigitString[7] = "seventy";
		tensDigitString[8] = "eighty";
		tensDigitString[9] = "ninety";
		
		run();
	}
	
	private String numberToString(String num){
		if(Integer.valueOf(num)<=20)return under20String[Integer.valueOf(num)];
		else if (num.length() == 2)return tensDigitString[Integer.valueOf(num.substring(0,1))] + " " + numberToString(num.substring(1));
		else if (num.length() == 3){
			if(num.substring(1).equals("00")) return under20String[Integer.valueOf(num.substring(0,1))] + " " + "hundred";
			return under20String[Integer.valueOf(num.substring(0,1))] + " " + "hundred" + " and " + numberToString(num.substring(1));
		}
		else if (num.length() == 4)return under20String[Integer.valueOf(num.substring(0,1))] + " " + "thousand" + " "+ numberToString(num.substring(1));
		return "error";
	}
	public static void main(String [] args){
		NumberLetterCounts nlc = new NumberLetterCounts();
	}
	public void run(){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Num1: ");
		String num1 = keyboard.nextLine();
		System.out.print("Num2: ");
		String num2 = keyboard.nextLine();
		keyboard.close();
		int number1 = Integer.valueOf(num1);
		int number2 = Integer.valueOf(num2);
		int sum =0;
		for( int i = number1; i<=number2; i++){
			String numberString =  numberToString(String.valueOf(i));
			System.out.print(i + ": " + numberString);
			numberString = numberString.replace(" ", "").trim();
			System.out.println(" " + numberString.length());
			sum+= numberString.length();
			System.out.println("Sum "+ sum);
			
		}
		System.out.println(sum);
	}
	
}




















