package namesScores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.ietf.jgss.Oid;
import org.w3c.dom.NameList;

/*
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over 
 * five-thousand first names, begin by sorting it into alphabetical order. Then working out
the alphabetical value for each name, multiply this value by its alphabetical position in 
the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is worth 
3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score 
of 938 × 53 = 49714.
What is the total of all the name scores in the file?
 */
public class NamesScores {
	ArrayList<String>namesList = new ArrayList<String>();
	String [] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	public static void main(String [] args) throws FileNotFoundException{
		NamesScores ns = new NamesScores();
	}
	public NamesScores() throws FileNotFoundException{
		getNames();
		Collections.sort(namesList);		
		System.out.println(calcScore().toString()); 
	}

	private void getNames() throws FileNotFoundException{
		File textFile = new File("/UsedInProg/names.txt");
		Scanner reader = new Scanner(textFile);
		reader.useDelimiter(",");
		while(reader.hasNext()){
			String tempString = reader.next();
			tempString = tempString.substring(1,tempString.length()-1);
			namesList.add(tempString);
		}
	}
	private BigInteger calcScore(){
		BigInteger score = new BigInteger("0");
		for(int i = 0; i < namesList.size(); i++){
			String tempString = namesList.get(i);
			int tempScore =0;
			
			while(tempString.length()!=0){
				
				for(int k =0; k<alphabet.length;k++){
					if(alphabet[k].equalsIgnoreCase(tempString.substring(0, 1))){
						tempScore += k+1;
						tempString = tempString.substring(1);
						break;
					}
				}
				
			}
			tempScore = tempScore * (i+1);
			score = score.add(new BigInteger(String.valueOf(tempScore)));
		}
		return score;
	}
	
}