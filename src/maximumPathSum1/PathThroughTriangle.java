package maximumPathSum1;

public class PathThroughTriangle {
	private int i,j;
	private int potentialScore=0;
	private int actualScore=0;
		
	public PathThroughTriangle(int I, int J, int potScore, int actScore){
		i = I;
		j = J;
		potentialScore = potScore;
		actualScore = actScore;
	}
	public int potentialScore(){
		return potentialScore;
	}
	public int actualScore(){
		return actualScore;
	}
	
	public int iPosition(){
		return i;
	}
	public int jPosition(){
		return j;
	}
	
}
