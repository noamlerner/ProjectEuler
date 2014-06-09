package nonAbundantSums;

import java.math.BigInteger;
import java.util.ArrayList;

/*
 * A perfect number is a number for which the sum of its proper divisors
 *  is exactly equal to the number. For example, the sum of the proper 
 *  divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 
 *  28 is a perfect number.
 *  
 *  A number n is called deficient if the sum of its proper divisors
 *  is less than n and it is called abundant if this sum exceeds n.
 *  
 *   As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
 *   number that can be written as the sum of two abundant numbers is 24. By
 *   mathematical analysis, it can be shown that all integers greater than 28123 
 *   can be written as the sum of two abundant numbers. However, this upper limit 
 *   cannot be reduced any further by analysis even though it is known that the
 *   greatest number that cannot be expressed as the sum of two abundant numbers 
 *   is less than this limit.

	Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class NonAbundantSums {

	public static void main(String[] args){
		NonAbundantSums nas = new NonAbundantSums();
	}
	ArrayList<Integer> abundantNums;
	public NonAbundantSums(){
		abundantNums = new ArrayList<Integer>();
		findAbundantNums(abundantNums);
		System.out.println(findSumOfNonAbundantNums());
	}
	private void findAbundantNums(ArrayList<Integer> abundantNums){
		for(int i = 11; i < 28123; i++){
			if(i < sumOfDivisors(i)) abundantNums.add(new Integer(i));
		}
	}
	private int sumOfDivisors(int num){
		int sum = 0;
		for(int i = 1; i <=num/2; i++){
			if(num % i == 0) sum+=i;
		}
		return sum;
	}
	private BigInteger findSumOfNonAbundantNums(){
		BigInteger sumOfNums = new BigInteger("0");
		for(int i =1; i <28123;i++){
			boolean abundant = false;
			for(int j = 0; j <abundantNums.size(); j++ ){
				int abundantNum = abundantNums.get(j);
				if(abundantNum < i){
					int difference = i - abundantNum;
					if(isAbundant(difference)) {
						abundant = true;
						break;
					}
				}
				else break;
			}
			if(!abundant) {
				sumOfNums = sumOfNums.add(new BigInteger(String.valueOf(i)));
				System.out.println(i + " : " + sumOfNums);
			}
		}
		return sumOfNums;
	}
	private boolean isAbundant(int num){
		int high = abundantNums.size() -1;
		int low = 0;
		while(low <=high){
			int middle = (low + high)/2;
			int abNum = abundantNums.get(middle).intValue();
			if(num == abNum) {
				return true;
			}
			if(num < abNum) high = middle-1;
			if(num > abNum) low = middle+1;
		}
		return false;
	}
}




















