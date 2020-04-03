package hw03_rishv1995;

public class Node2 {
	
	public int [][] matrix; //matrix representation
	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int gethVal() {
		return hVal;
	}

	public void sethVal(int hVal) {
		this.hVal = hVal;
	}

	public int getgVal() {
		return gVal;
	}

	public void setgVal(int gVal) {
		this.gVal = gVal;
	}

	public int hVal; //heuristic values
	public int gVal; //cost
	
	public Node2(int [][]matrix, int hVal, int gVal) {
		this.matrix = matrix;
		this.hVal = hVal;
		this.gVal = gVal;
	}
	
	public int getfVal(){
		return (this.gVal + this.hVal);
	}
	
}
