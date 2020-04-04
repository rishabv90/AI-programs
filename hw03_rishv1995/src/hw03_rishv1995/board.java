package hw03_rishv1995;

public class board {



		
		private board parent;
		private String move;
		private int heuristic;
		private int cost;
		int [][] eightBoard = new int [3][3];
		boolean expanded = false;
		
		public board() {
			parent = null;
			move = "none";
			cost = 0;
		}
		public board(board b1) {
			parent = b1;
			move = "Error";
			heuristic = 999;
			
		}
		
		public void setParent(board b1){
			parent = b1;
		}
		
		public board getParent() {
			return parent;
		}
		
		public void setMove(String m1) {
			move = m1;
		}
		
		public String getMove() {
			return move;
		}
		
		public int[][] getEightBoard() {
			return eightBoard;
		}
		
		public void setEightBoard(int [][]x) {
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					eightBoard[k][j] = x[k][j];
				}
			}
		}
		
		public int getCost() {
			return cost;
		}
		
		public void setCost(int x) {
			cost = x;
		}
		
		public int getHeuristic() {
			return heuristic;
		}
		
		public boolean getExpanded() {
			return expanded;
		}
		
		public void setExpanded() {
			expanded = true;
		}
		
		//calculates manhattan heuristic value
		public void HeuristicCalculation(board goal) {
			int x1,y1,x2,y2;
			int manhatten;
			
			manhatten =x1=x2=y1=y2=0;
			
			int [][]b1 = new int [3][3];
			
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					b1[k][j] = this.getEightBoard()[k][j];
				}
			}
			
			
			int [][]b2 = new int [3][3];
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					b2[k][j] = goal.getEightBoard()[k][j];
				}
			}

			
			for(int i = 1; i< 9; ++i) {
				
				for(int j = 0; j<3;++j) {
					for(int k =0;k<3; ++k) {
						if(b1[j][k] == i) {//checks if tile value is desired value then gets coordinates if it is.
							x1 = k;
							y1 = j;
							
						}
						if(b2[j][k] == i) {//checks if tile value is desired value then gets coordinates if it is.
							x2 = k;
							y2 = j;
							
						}
					}
				}
				manhatten = manhatten + (Math.abs(x1-x2)+Math.abs(y1-y2));//finds heuristic value of 2d array
			}
			
			heuristic = manhatten;
		}
		
		//____________________________Movement________________________________________________________________________________________
		
	


}
