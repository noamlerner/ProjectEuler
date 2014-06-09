package maximumPathSum1;
/*
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom of the triangle below:

75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MaximumPathSum1 {
	int [][] triangle;
	int [][] scoreTriangle;
	boolean done = false;
	ArrayList<PathThroughTriangle>paths = new ArrayList<PathThroughTriangle>();
	public static void main(String[] args){
		MaximumPathSum1 mp1 = new MaximumPathSum1();
	}
	public MaximumPathSum1(){
		getTriangle();
		assignPointVaules();
		final long startTime = System.currentTimeMillis();
		choosingPath();
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );
	}
	private void getTriangle(){
		Path p1 = Paths.get("/data.txt");
		List<String> triangleList = null;
		try {
		triangleList = Files.readAllLines(p1, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		triangle = new int[triangleList.size()][triangleList.size()];
		scoreTriangle = new int[triangleList.size()][triangleList.size()];
		for (int i =0; i <triangleList.size();i++){
			int count = 0;
			triangleList.set(i, triangleList.get(i)+" ");
			for(int j =0; j<triangleList.get(i).length(); j++){
				if(triangleList.get(i).substring(j,j+1).equals(" ")){
					String temp = triangleList.get(i).substring(0,j).trim();
					triangle[i][count] = Integer.valueOf(temp);
					triangleList.set(i, triangleList.get(i).substring(j+1)); 
					count++;
					j =0;
				}
			}
			for(int j =i+1; j < triangle.length; j++){
				triangle[i][j]= -1; 
			}
		}
	}
	private void assignPointVaules(){
		for(int i = 0; i < triangle.length; i++){
			int maxNum = 0;
			for(int j =0; j<=i;j++) 
						maxNum = Math.max(triangle[i][j], maxNum);			
			for(int j =0; j<triangle.length;j++) {
				if(triangle[i][j] != -1)
					scoreTriangle[i][j] = maxNum - triangle[i][j];
				else scoreTriangle[i][j] = 9999;
			}
		}
	}
	
	private void choosingPath(){
		
		paths.add(new PathThroughTriangle(0, 0, 0, triangle[0][0]));
		extendPath(0);
		while(!done){
			extendPath(lowestPotentialPath());
		}
	}
	private void extendPath(int pathNum){
		PathThroughTriangle tempPath = paths.get(pathNum);
		if(tempPath.iPosition()+1 < triangle.length){
		PathThroughTriangle newPath1 = new PathThroughTriangle(tempPath.iPosition()+1, tempPath.jPosition(), 
															  tempPath.potentialScore() + scoreTriangle[tempPath.iPosition()+1][tempPath.jPosition()], 
															  tempPath.actualScore()+triangle[tempPath.iPosition()+1][tempPath.jPosition()]);
		paths.set(pathNum, newPath1);
			if(scoreTriangle[tempPath.iPosition() +1][tempPath.jPosition()+1] != -9999){
				PathThroughTriangle newPath2 = new PathThroughTriangle(tempPath.iPosition()+1, tempPath.jPosition()+1, 
																tempPath.potentialScore() + scoreTriangle[tempPath.iPosition()+1][tempPath.jPosition()+1], 
																tempPath.actualScore()+triangle[tempPath.iPosition()+1][tempPath.jPosition()+1]);
				paths.add(newPath2);	
				System.out.println("Path  Actual Score  Potential Score");
				for(int i =0; i <paths.size(); i++)
					System.out.println(i+"\t"+paths.get(i).actualScore()+"\t\t"+paths.get(i).potentialScore());
				System.out.println("--------------------------------------------------------------------------");
			}
		}
		else if(pathNum == lowestPotentialPath()){
			done = true;
			System.out.println("The highst path sum is: " + paths.get(pathNum).actualScore());
		}
	}
	
	private int lowestPotentialPath(){
		int lowestPotPos = 0;
		for(int k =1; k < paths.size(); k++){
			if(paths.get(lowestPotPos).potentialScore()>paths.get(k).potentialScore()){
				lowestPotPos = k;
			}
		}
		return lowestPotPos;
	}
}
























