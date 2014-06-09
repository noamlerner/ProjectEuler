package lexigraphicPermutations;

import java.util.ArrayList;
import java.util.Collections;
/*
 * A permutation is an ordered arrangement of objects. For example, 
 * 3124 is one possible permutation of the digits 1, 2, 3 and 4. If 
 * all of the permutations are listed numerically or alphabetically,
 *  we call it lexicographic order. The lexicographic permutations 
 *  of 0, 1 and 2 are:

012   021   102   120   201   210

What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */


public class LexigraphicPermutations {
	public static void main(String [] args){
		LexigraphicPermutations lPermutations = new LexigraphicPermutations();
	}
	int count = 1;
	public LexigraphicPermutations(){
		int[] nums = {0,1,2,3,4,5,6,7,8,9};
		printPermutations(nums,1000000);

	}
	public void printPermutations(int [] nums, int amountOfPermutations){
		int unfrozen = 1;
		while(count < amountOfPermutations){
			String before = arrayToString(nums);
			nums = permutationOfUnfrozenNums(nums, unfrozen);
			String after = arrayToString(nums);
			if(before.equals(after))		unfrozen++;	
			else count++;

			System.out.println(count + ": " + after);
		}
	}
	private int[] permutationOfUnfrozenNums(int [] nums,int unfrozen){
		if(unfrozen>nums.length)unfrozen = nums.length;
		int[] newNums = new int[unfrozen];
		int ufCopy = unfrozen;
		int j =0;
		for(int i = nums.length - ufCopy; i<nums.length; i++){
			newNums[j]= nums[i];
			j++;
		}
		newNums = nextSmallestPermutation(newNums);
		j=0;
		for(int i = nums.length - ufCopy; i<nums.length; i++){
			nums[i]= newNums[j];
			j++;
		}
		return nums;

	}
	private int[] nextSmallestPermutation(int [] nums){
		if(nums.length == 1) return nums;
		int [] newNums = nums;
		int minNum = newNums[0];
		int indexOfMin = 0;
		int maxNum = newNums[0];
		for(int i =1; i < newNums.length; i++){
			minNum = Math.min(minNum, newNums[i]);
			maxNum = Math.max(maxNum, newNums[i]);
			if(minNum!=newNums[indexOfMin]) indexOfMin = i;
		}

		if(indexOfMin == 0){
			return minAt0(newNums, minNum); 
		}
		else if(indexOfMin == newNums.length-1){
			return minAtEnd(newNums, maxNum);
		}
		else {
			return minInMiddle(newNums, minNum, indexOfMin);
		}
	}
	private int[] minInMiddle(int [] newNums, int minNum, int indexOfMin){
		int[] tempNums = new int[newNums.length-indexOfMin];
		int j = indexOfMin;
		for(int i =0; i <tempNums.length; i++){
			tempNums[i]=newNums[j];
			j++;
		}
		tempNums = nextSmallestPermutation(tempNums);
		j = indexOfMin;
		for(int i =0; i <tempNums.length; i++){
			newNums[j] = tempNums[i];
			j++;
		}
		return newNums;
	}
	private int[] rearrangeExisting(int[] newNums, int minNum, int indexOfMin) {
		int locMin = newNums[indexOfMin +1];
		int indexOfLocMin = indexOfMin +1;
		int indexOfLocMax = indexOfMin +1;
		int locMax = newNums[indexOfMin +1];
		for(int i = indexOfMin+1; i <newNums.length;i++){
			locMin= Math.min(locMin, newNums[i]);
			if(locMin != newNums[indexOfLocMin]) indexOfLocMin = i;
			locMax = Math.max(locMax, newNums[i]);
			if(locMax != newNums[indexOfLocMax]) indexOfLocMax = i;
		}
		if(indexOfLocMax == indexOfMin+1){			
			newNums[indexOfMin] = locMin;
			newNums[indexOfLocMin] = minNum;
			newNums = arrangeUnfrozen(newNums, newNums.length-(indexOfMin+1));
			return newNums;
		}
		if(indexOfLocMin == newNums.length-1){
			int[] tempNums = new int[newNums.length-(indexOfMin+1)];
			int j = indexOfMin+1;
			for(int i =0; i <tempNums.length; i++){
				tempNums[i]=newNums[j];
				j++;
			}
			tempNums = nextSmallestPermutation(tempNums);
			j = indexOfMin+1;
			for(int i =0; i <tempNums.length; i++){
				newNums[j] = tempNums[i];
				j++;
			}
			return newNums;
		}
		else {
			int[] tempNums = new int[newNums.length-indexOfLocMin];
			int j = indexOfLocMin;
			for(int i =0; i <tempNums.length; i++){
				tempNums[i]=newNums[j];
				j++;
			}
			tempNums = nextSmallestPermutation(tempNums);
			j = indexOfLocMin;
			for(int i =0; i <tempNums.length; i++){
				newNums[j] = tempNums[i];
				j++;
			}
			return newNums;
		}
	}
	
	private int[] minAtEnd(int[] newNums, int maxNum) {
		if(newNums[0] == maxNum)	{
			boolean done = true;
			for(int i =1; i < newNums.length; i++){
				if(Math.min(newNums[i], newNums[i-1]) == newNums[i-1]){
					done = false;
					break;
				}
			}
			if(done == true)return newNums;
			newNums = permutationOfUnfrozenNums(newNums,newNums.length-1);
			return newNums;
		}
		else {
			for(int i =1; i<newNums.length; i++){
				if((newNums[i]) == maxNum ){
					int beingChanged = newNums[i-1];
					int changedWith = newNums[i];
					int indexOfChange = i;
					for(int j =i; j < newNums.length; j++){
						if(newNums[j] > beingChanged){
							if(newNums[j] < changedWith) {
								changedWith = newNums[j];
								indexOfChange = j;
							}
						}
					}
					newNums[i-1] = changedWith;
					newNums[indexOfChange] = beingChanged;
					newNums = arrangeUnfrozen(newNums, newNums.length-i);
					return newNums;
				}
			}
		}
		return newNums;
	}
	private int[] minAt0(int[] newNums, int minNum) {
		boolean changeMin = true;
		if(newNums.length > 2)
		for(int i  = newNums.length-1; i >0; i--){
			if(newNums[i]>newNums[i-1] && i-1 !=0){
				changeMin = false;
				break;
			}
		}
		if(changeMin){
			int nextMinNum = newNums[1];
			int indexOfNextMin = 1;
			for(int i = 1; i < newNums.length; i++){
				nextMinNum = Math.min(nextMinNum, newNums[i]);
				if(nextMinNum == newNums[i]) indexOfNextMin = i;
			}
			newNums[0] = newNums[indexOfNextMin];
			newNums[indexOfNextMin] = minNum;
			newNums = arrangeUnfrozen(newNums, newNums.length-1);
			return newNums;

		}else{
			int[] tempNums = new int[newNums.length-1];
			int j = 1;
			for(int i =0; i <tempNums.length; i++){
				tempNums[i]=newNums[j];
				j++;
			}
			tempNums = nextSmallestPermutation(tempNums);
			j = 1;
			for(int i =0; i <tempNums.length; i++){
				newNums[j] = tempNums[i];
				j++;
			}
			return newNums;
		}
	}

	private int [] arrangeUnfrozen(int [] nums, int unfrozen){
		ArrayList<Integer> unfrozenInts = new ArrayList<Integer>();
		int ufCopy = unfrozen;
		for(int i = nums.length -1; ufCopy > 0; i--){
			unfrozenInts.add(nums[i]);
			ufCopy--;
		}
		ufCopy = unfrozen;
		Collections.sort(unfrozenInts);
		int j =0;
		for(int i = nums.length - ufCopy; i<nums.length; i++){
			nums[i]= unfrozenInts.get(j);
			j++;
		}
		return nums;
	}
	private String arrayToString(int [] nums){
		String numString = "";
		for(int i =0; i <nums.length; i++){
			numString+= String.valueOf(nums[i]);
		}
		return numString;


	}
}
