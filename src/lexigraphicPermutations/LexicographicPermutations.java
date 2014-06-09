package lexigraphicPermutations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.ietf.jgss.Oid;

public class LexicographicPermutations {

	public static void main(String[] args) throws FileNotFoundException{
		LexicographicPermutations lexicographicPermutations = new LexicographicPermutations();
	}
	public LexicographicPermutations() throws FileNotFoundException{
		int[]nums = {0,1,2,3,4,5,6,7,8,9};
		printPermutations(nums, 1000000);
	}
	int count = 1;
	public void printPermutations(int [] nums, int amountOfPermutations) throws FileNotFoundException{
		int unfrozen = 1;
		while(count < amountOfPermutations){
			String before = arrayToString(nums);
			nums = permutationOfUnfrozenNums(nums, unfrozen);
			String after = arrayToString(nums);
			if(before.equals(after)) 	unfrozen++;	
			else count++;
			System.out.println(count + ": " + after);
		}
	}
	private String arrayToString(int [] nums){
		String numString = "";
		for(int i =0; i <nums.length; i++){
			numString+= String.valueOf(nums[i]);
		}
		return numString;
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
		if(nums.length <2) return nums;
		int indexOfMin = indexOfMin(nums);
		if(indexOfMin == 0) return minAt0(nums);
		if(indexOfMin == nums.length-1) return minAtEnd(nums);
		else return passWithoutBeg(nums, 1);
	}
	private int indexOfMin(int [] nums){
		int min = nums[0];
		int index = 0;
		for(int i = 1; i < nums.length; i ++){
			if(nums[i] < min){
				min = nums[i];
				index = i;
			}
		}
		return index;
	}

	private int[] minAt0(int [] nums){
		if(maxToMin(nums, 1)){
			int min = nums[0];
			int indexOfNextMin = indexOfNextSmallestNum(nums);
			nums[0] = nums[indexOfNextMin];
			nums[indexOfNextMin] = min;
			arrangeWithout0(nums);
			return nums;
		}
		else return passWithoutBeg(nums, 1);

	}
	private boolean maxToMin(int [] nums, int ignoreFromBeginning){
		for(int i = ignoreFromBeginning+1; i<nums.length; i++){
			if(nums[i-1]<nums[i]){
				return false;
			}
		}
		return true;
	}
	private int indexOfNextSmallestNum(int[]nums){
		int index = 1;
		int numAt0 = nums[0];
		int nextMin = nums[1];
		for(int i =2; i < nums.length; i++){
			if(nums[i] > numAt0 && nums[i] <nextMin){
				index = i;
				nextMin = nums[i];
			}
		}
		return index;
	}
	private int [] passWithoutBeg(int [] nums, int beg){
		int [] tempNums = new int[nums.length -beg];
		int j = beg;
		for(int i = 0; i < tempNums.length; i++ ){
			tempNums[i] = nums[j];
			j++;
		}
		tempNums = nextSmallestPermutation(tempNums);
		j=beg;
		for(int i = 0; i<tempNums.length; i++){
			nums[j] = tempNums[i];
			j++;
		}
		return nums;
	}

	private int[] minAtEnd(int [] nums){
		if(maxToMin(nums,0)){
			return nums;
		}else{
			int maxIndex = indexOfMax(nums);
			if(maxIndex == 0) return passWithoutBeg(nums, 1);
			else {
				if(maxToMin(nums,maxIndex)){
					int [] tempNums = new int[nums.length - (maxIndex -1)];
					int j = maxIndex -1;
					for(int i =0; i< tempNums.length; i++){
						tempNums[i] = nums[j];
						j++;
					}
					int nextSmallIndex = indexOfNextSmallestNum(tempNums);
					int numAt0 = tempNums[0];
					tempNums[0] = tempNums[nextSmallIndex];
					tempNums[nextSmallIndex] = numAt0;
					arrangeWithout0(tempNums);
					j = maxIndex -1;
					for(int i =0; i< tempNums.length; i++){
						nums[j] = tempNums[i];
						j++;
					}
				} else {
					return passWithoutBeg(nums, maxIndex+1);
				}
			}
			return nums;
		}
	}

private int [] arrangeWithout0(int [] nums){
	ArrayList<Integer> unfrozenInts = new ArrayList<Integer>();
	for(int i = 1; i <nums.length; i++) 	unfrozenInts.add(nums[i]);

	Collections.sort(unfrozenInts);
	int j =0;
	for(int i = 1; i <nums.length;i++){
		nums[i] = unfrozenInts.get(j);
		j++;
	}
	return nums;
}
private int indexOfMax(int [] nums){
	int max = nums[0];
	int index = 0;
	for(int i = 1; i < nums.length; i ++){
		if(nums[i] > max){
			max = nums[i];
			index = i;
		}
	}
	return index;
}
}
