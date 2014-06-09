package latticePaths;
/*Starting in the top left corner of a 2×2 grid,
 *  and only being able to move to the right and 
 *  down, there are exactly 6 routes to the bottom 
 *  right corner.
 *  How many such routes are there through a 20×20 grid?
 */
public class LatticePaths {
	private int calculateLatticePaths(int gridSize){
		int [][] grid = new int[gridSize][gridSize];
		int count =0;
		
		for(int i = 0; i< gridSize; i++){
			for(int j =0; j<gridSize; j++){
				count++;
			}
		}
		return count;
	}
	public static void main(String [] args){
		LatticePaths lPaths = new LatticePaths();
	}
	public LatticePaths(){
		System.out.print(calculateLatticePaths(2));
	}
}
