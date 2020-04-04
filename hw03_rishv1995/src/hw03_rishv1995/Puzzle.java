package hw03_rishv1995;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.*;
import java.util.Scanner;
import hw03_rishv1995.Node;



@SuppressWarnings("unused")

public class Puzzle {
	
	public int size;
	public ArrayList<Node> openList;
	public ArrayList<Node> closedList;
	
	public Puzzle() {
		this.size = 0;
		this.openList = new ArrayList<Node>();//this will change
		this.closedList = new ArrayList<Node>(); // this will change
		
	}
	
	
	
	public ArrayList<ArrayList<Character>> takeInput() {
		int size = 3; //8puzzle matrix size line
		
		ArrayList<ArrayList<Character>> input = new ArrayList<ArrayList<Character>>();
		ArrayList<Character> line = new ArrayList<Character>();
		ArrayList<Character> copy = new ArrayList<Character>();
		
		
		int i;
		
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in);
		for (i=0; i<size;i++) {
			
		    String userName = myObj.nextLine();
		    
//		    line.add(Character.getNumericValue(userName.charAt(0)));
//		    line.add(Character.getNumericValue(userName.charAt(2)));
//		    line.add(Character.getNumericValue(userName.charAt(4)));
		   
		    line.add(userName.charAt(0));
		    line.add(userName.charAt(2));
		    line.add(userName.charAt(4));
		    
		    
		    input.add((ArrayList<Character>)line.clone());
		   
		    line.clear();
		 	    
		}
		return input;
		
	}
	
	
	public int getH(ArrayList<ArrayList<Character>> initialState, ArrayList<ArrayList<Character>> goalState) { //H(x) Number of misplaced tiles. 
	    int misplacedTiles = 0;
	    
	    for(int i = 0; i<3;i++) {
			for(int j = 0; j<3;j++) {
				if(initialState.get(i).get(j) != '_'   && initialState.get(i).get(j) != goalState.get(i).get(j)) {
					misplacedTiles += 1;
				}
			}
	    }
	    
		return misplacedTiles;
	}
	
	public int getF(ArrayList<ArrayList<Character>> initialState, ArrayList<ArrayList<Character>> goalState, Node test) { // f(x) = h(x) + g(x) 
		int fValue = 0;
		int hValue = 0;
		int gValue = 0;
		
		hValue = this.getH(initialState, goalState);
		gValue = test.level;
		fValue = hValue + gValue;
		
		return fValue;
	}
	
	public void findSol() {

		System.out.println("Enter Initial State:");
		ArrayList<ArrayList<Character>> initialState = this.takeInput();
		//System.out.println(initialState);

		System.out.println("\nEnter Goal State:");
		ArrayList<ArrayList<Character>> goalState = this.takeInput();
		//System.out.println(goalState);

		System.out.println("\nSelect the heuristic");
		System.out.println(" a) Number of misplaced tiles");
		System.out.println(" b) Manhattan distance");

		Scanner myObj = new Scanner(System.in);
		String heuristicInput = myObj.nextLine();

		

		if(heuristicInput.charAt(0) =='a') {
			
			System.out.println("Misplaced tiles selected = " + heuristicInput.charAt(0));
			
			Node test = new Node(initialState, 0,0);
			test.fValue = this.getF(initialState, goalState, test);
			//System.out.println(test.fValue);

			this.openList.add(test);
			//test.isPossible(initialState, 2, 2, 2, 1);
			test.BuildNeighbours();

			ArrayList<String> direction = new ArrayList<String>();
			Node currentNode = new Node(null, 0, 0);//lets see
			int nodesExplored =0;

			while (true) {
				currentNode = this.openList.get(0);


				if (currentNode.movement == 1) {

					direction.add("Move Blank Up");
				}else if (currentNode.movement == 2) {
					direction.add("Move Blank Down");
				}else if (currentNode.movement == 3) {
					direction.add("Move Blank Left");
				}else if (currentNode.movement == 4) {
					direction.add("Move Blank Right");
				}

				if(direction.size()>200) {
					System.out.println("For the above combination of the initial/goal states, there is no solution.");
					break;
				}


				if(this.getH(currentNode.data, goalState) == 0) {

					System.out.println("\n"); // SOlution reached
					break;
				}

				//System.out.println("helloo1");
				for (Node i :currentNode.BuildNeighbours()) {
					nodesExplored++;
					i.fValue = this.getF(i.data, goalState, i);

					//System.out.println(i.data);
					this.openList.add(i);
				}
				this.closedList.add(currentNode);
				this.openList.remove(0);

				Collections.sort(this.openList, new Comparator<Node>() {
					@Override public int compare(Node p1, Node p2) {
						return p1.fValue - p2.fValue; // Ascending
					}

				});

			}
			for (String i : direction) {
				System.out.println(i);
			}
			System.out.println("Given the selected heuristic, the solution required "+ direction.size() +" moves. ");
			System.out.println("The A* explored "+ nodesExplored +" number of nodes to find this solution via misplaces tiles heuristic.");
		}else{
			
			//SPENCER ADD YOUR CODE HERE - You may have to change the input depending on how you are parsing initial and goal state.
			//I am parsing code as 2d arraylist of characters = ArrayList<ArrayList<Character>> initialState.
			//instructions
			//download this code from github -> open the project hw03_rishv1995 -> open source-> open package hw03_rishv1995 -> add your class files here.
			
			ArrayList<board> tree = new ArrayList<board>();
			ArrayList<board> solutionPath = new ArrayList<board>();
			int check,n,temp,goalIndex;
			boolean solution = false;
			temp = 0;
			
			goalIndex = 0;
			board start = new board();
			
			//Goal state & initial state clean up area ___________________________________________________________________
			//Goal state clean up
			for(int w =0; w < 3; w++) {
				for(int v = 0; v <3;v++) {
					if(goalState.get(w).get(v).equals('_') ) {
						
						goalState.get(w).set(v, '0');
					}
				}
			}
			
			//initial state clean up
			for(int w =0; w < 3; w++) {
				for(int v = 0; v <3;v++) {
					if(initialState.get(w).get(v).equals('_') ) {
						initialState.get(w).set(v, '0');
					}
				}
			}
			
			
			//____________________________________________________________________________________________________________
			//Goal board set up-------------------------------------------------------------------
			board goal = new board();
			int [][] test1 = new int [3][3];
			
			test1[0][0] = Character.getNumericValue(goalState.get(0).get(0));
			test1[0][1] = Character.getNumericValue(goalState.get(0).get(1));
			test1[0][2] = Character.getNumericValue(goalState.get(0).get(2));
			test1[1][0] = Character.getNumericValue(goalState.get(1).get(0));
			test1[1][1] = Character.getNumericValue(goalState.get(1).get(1));
			test1[1][2] = Character.getNumericValue(goalState.get(1).get(2));
			test1[2][0] = Character.getNumericValue(goalState.get(2).get(0));
			test1[2][1] = Character.getNumericValue(goalState.get(2).get(1));
			test1[2][2] = Character.getNumericValue(goalState.get(2).get(2));
			
			goal.setEightBoard(test1);
			
			//Start set up-----------------------------------------------------
			test1[0][0] = Character.getNumericValue(initialState.get(0).get(0));
			test1[0][1] = Character.getNumericValue(initialState.get(0).get(1));
			test1[0][2] = Character.getNumericValue(initialState.get(0).get(2));
			test1[1][0] = Character.getNumericValue(initialState.get(1).get(0));
			test1[1][1] = Character.getNumericValue(initialState.get(1).get(1));
			test1[1][2] = Character.getNumericValue(initialState.get(1).get(2));
			test1[2][0] = Character.getNumericValue(initialState.get(2).get(0));
			test1[2][1] = Character.getNumericValue(initialState.get(2).get(1));
			test1[2][2] = Character.getNumericValue(initialState.get(2).get(2));
			
			start.setEightBoard(test1);
			
			
			
			//tree.add(start);
			
			//first expansion
			Expand(start,goal,tree);
			expansionCount = 1; 
			//check for goal after initial expansion
			n = tree.size(); 
			for(int i = 0; i<n;++i) {
				if(tree.get(i).getHeuristic() == 0) {
					goalIndex = i;
					solution = true;
				}
			}
			
			while(!solution) {
			
			
				//find node with lowest heuristic value (F(n))
				check = 99;
				n = tree.size();//gets size of tree
				for(int i = 0; i < n;++i) {
				
					if((tree.get(i).getHeuristic() < check)&& !(tree.get(i).getExpanded()) ) {
						check =  tree.get(i).getHeuristic();//tracks minimum heuristic
						temp = i;//stores index of minimum heuristic
					}
				}
			
				//expand leaf with lowest f(n) value
				Expand(tree.get(temp),goal,tree);
				expansionCount++;
				
				if(expansionCount > 200) {
					System.out.println("For the above combination of the initial/goal states, there is no solution.");
					System.exit(0); 
				}

				
				//Goal check
				n = tree.size();
				for(int i = 0; i<n;++i) {
					if(tree.get(i).getHeuristic() == 0) {
						goalIndex = i;
						solution = true;
					}
				}
			}
			
			//print section
				//Solution path build----------------------
				//System.out.println(expansionCount);
				//int solutionCost = tree.get(goalIndex).getCost();
				board tempBoard = new board();
				//System.out.println(tree.get(goalIndex).getMove());
			
				//set tempBoard equal to goal 
				tempBoard = tree.get(goalIndex);
				solutionPath.add(tempBoard);
				while(tempBoard.getParent() != null) {
				
					tempBoard = tempBoard.getParent();
					solutionPath.add(tempBoard);
				
					//solutionCost--;
				}
				//print path
				n = solutionPath.size();
				int numberOfMoves = n;
				while(n-2 >= 0) {                    
					System.out.println("Move Blank " + solutionPath.get(n-2).getMove());
					--n;
				}
				
				System.out.println("Given the selected Heuristic the solution required " + (numberOfMoves-1) + " moves");
				System.out.println("The A* explored " + (tree.size()) +" Nodes to find the solution");
			
			
			
			//System.out.println("ADD YOUR CODE HERE SPENCER");
		}
		
			



	}
	//Sub Functions_______________________________________________________________________________________________________
	public static int expansionCount;
	public static void Swap(int[][] a, int x1, int y1, int x2, int y2 ) {
		int temp;
		
		temp = a[y1][x1];//store value 1
		a[y1][x1] = a[y2][x2];//sub value 2 int 1's location
		a[y2][x2] = temp;//sub valu 1 into 2's location
		
	}

	
	public static void Expand(board b1,board goal,ArrayList<board>tree) {
		
		int x1,y1;
		x1=y1=0;
		
		int [][]parent = new int [3][3];
		for(int k = 0; k < 3;++k) {
			for(int j = 0; j < 3; ++j) {
				parent[k][j] = b1.getEightBoard()[k][j];
			}
		}
		
		//get position of blank tile(represented by 0)
		for(int i = 0; i< 3;++i) {
			for(int j =0; j<3;++j) {
				if(parent[i][j]==0) {
					x1 = j;
					y1 = i;
				}
			}
		}
		//expansion
			//move up
		int [][]parent1 = new int [3][3];
		
		
		if((y1 !=0) && !(b1.getMove().contentEquals("down"))) {
			
			
			
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					parent1[k][j] = parent[k][j];
				}
			}
			
			Swap(parent1,x1,y1,x1,(y1-1));
			board newNode = new board(b1);
			newNode.setMove("up");
			newNode.setCost(b1.getCost()+1);
			newNode.setEightBoard(parent1);
			newNode.HeuristicCalculation(goal);
			
			tree.add(newNode);
		}
		//move right
		
		if((x1 != 2)&& !(b1.getMove().contentEquals("left"))) {
			parent1 = new int [3][3];
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					parent1[k][j] = parent[k][j];
				}
			}
			Swap(parent1,x1,y1,x1+1,y1);
			board newNode = new board(b1);
			newNode.setMove("right");
			newNode.setCost(b1.getCost()+1);
			newNode.setEightBoard(parent1);
			newNode.HeuristicCalculation(goal);
			
			
			tree.add(newNode);
		}
		//move down
		if((y1 != 2)&& !(b1.getMove().contentEquals("up"))) {
			parent1 = new int [3][3];
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					parent1[k][j] = parent[k][j];
				}
			}
			Swap(parent1,x1,y1,x1,y1+1);
			board newNode = new board(b1);
			newNode.setMove("down");
			newNode.setCost(b1.getCost()+1);
			newNode.setEightBoard(parent1);
			newNode.HeuristicCalculation(goal);
			
			tree.add(newNode);
		}
		//move left
		if((x1 != 0)&& !(b1.getMove().contentEquals("right"))) {
			parent1 = new int [3][3];
			for(int k = 0; k < 3;++k) {
				for(int j = 0; j < 3; ++j) {
					parent1[k][j] = parent[k][j];
				}
			}
			Swap(parent1,x1,y1,x1-1,y1);
			board newNode = new board(b1);
			newNode.setMove("left");
			newNode.setCost(b1.getCost()+1);
			newNode.setEightBoard(parent1);
			newNode.HeuristicCalculation(goal);
			
			tree.add(newNode);
		}
		
		b1.setExpanded();
	}
	
	public static void InversionDetector(board start, board goal) {
		int [][] b1 = new int [3][3];
		int [][] b2 = new int [3][3];
		
	}
	
	
	 public static void main(String[] args) {
	    	
	    	Puzzle testPuz = new Puzzle();
	    	testPuz.findSol();
	    }

}