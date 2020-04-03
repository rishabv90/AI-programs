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
		}

		if(heuristicInput.charAt(0) =='b') {
			
			//SPENCER ADD YOUR CODE HERE - You may have to change the input depending on how you are parsing initial and goal state.
			//I am parsing code as 2d arraylist of characters = ArrayList<ArrayList<Character>> initialState.
			//instructions
			//download this code from github -> open the project hw03_rishv1995 -> open source-> open package hw03_rishv1995 -> add your class files here.
			System.out.println("ADD YOUR CODE HERE SPENCER");
		}



	}
	
	
	 public static void main(String[] args) {
	    	
	    	Puzzle testPuz = new Puzzle();
	    	testPuz.findSol();
	    }

}