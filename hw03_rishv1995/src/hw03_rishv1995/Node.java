package hw03_rishv1995;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	
	ArrayList<ArrayList<Character>> data;
	int level;
	int fValue;
	int movement;
	
	public Node(ArrayList<ArrayList<Character>> data, int lvl,int fVal) {
		this.data = data;
		this.level = lvl;
		this.fValue = fVal;
	}
	
	
	
	public ArrayList<Node> BuildNeighbours(){
		int xPos = 0;
		int yPos = 0;
		
		ArrayList<ArrayList<Character>> allowedMoves = new ArrayList<ArrayList<Character>>();
		
		ArrayList<Node> neighbours = new ArrayList<Node>();
		
		ArrayList<Character> up = new ArrayList<Character>();
		ArrayList<Character> down = new ArrayList<Character>();
		ArrayList<Character> left = new ArrayList<Character>();
		ArrayList<Character> right = new ArrayList<Character>();
		
		ArrayList<Integer> coordinatesBlank = new ArrayList<Integer>();
		coordinatesBlank = findLocation(this.data, '_');
		//System.out.println(coordinatesBlank);
		
		xPos = coordinatesBlank.get(0);
		yPos = coordinatesBlank.get(1);
		up.add((char) (xPos-1+'0')); up.add((char) (yPos+'0'));
		down.add((char) (xPos+1+'0')); down.add((char) (yPos+'0'));
		left.add((char) (xPos+'0')); left.add((char) (yPos-1+'0'));
		right.add((char) (xPos+'0')); right.add((char) (yPos+1+'0'));
		
		

		allowedMoves.addAll(Arrays.asList(up, down, left, right));
		//System.out.println(allowedMoves);
		
		
		ArrayList<ArrayList<Character>> possibleMove = new ArrayList<ArrayList<Character>>();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		this.movement=0;
		int tmp=0;
		for (ArrayList<Character> i : allowedMoves) {
			tmp++;
			//System.out.println(i);
			possibleMove = this.isPossible(this.data, xPos, yPos, i.get(0)-48, i.get(1)-48); //we use next possible move as new node
			
			if(possibleMove != null) {
				
				
				Node newNode = new Node(possibleMove, (this.level+1), 0);
				newNode.movement = tmp;
				nodeList.add(newNode);
			}
			
		}
		
//		for (Node i : nodeList) {
//			System.out.println(i.data);
//			System.out.println(i.movement);
//			
//		}
		return nodeList;
	}
	
	
	public ArrayList<ArrayList<Character>> isPossible(ArrayList<ArrayList<Character>> data, int xPos, int yPos, int nextX, int nextY){
		ArrayList<ArrayList<Character>> newLocation = new ArrayList<ArrayList<Character>>();
		Character temp = 'x';
		//System.out.println(nextX);
		//System.out.println(nextY);
		//System.out.println(xPos);
		//System.out.println(yPos);
		
		if((nextX) >= 0 && (nextY)>= 0 && (nextX) < 3 && (nextY) < 3) {
			newLocation = this.cloneNode(data);
			
			//swap
			temp = newLocation.get(nextX).get(nextY);
			newLocation.get(nextX).set(nextY, newLocation.get(xPos).get(yPos));
			newLocation.get(xPos).set(yPos, temp);
			//System.out.println(newLocation);
			
			return newLocation;
		}
		else { // edges probs
			
			return null;
		}
		
	}
	
	public ArrayList<ArrayList<Character>> cloneNode(ArrayList<ArrayList<Character>> data){
		ArrayList<ArrayList<Character>> copy = new ArrayList<ArrayList<Character>>();
		ArrayList<Character> x = new  ArrayList<Character>();
		
		for(ArrayList<Character>i : data) {
			for (Character j : i) {
				x.add(j);
			}
			copy.add((ArrayList<Character>)x.clone());
			x.clear();
		}
		
		//System.out.println(copy);
		
		return copy;
	}
	
	
	public ArrayList<Integer> findLocation(ArrayList<ArrayList<Character>> inputData, Character c){
		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		for(int i = 0; i<3;i++) {
			for(int j = 0; j<3;j++) {
				if(inputData.get(i).get(j) == c) {
					coordinates.add(i);
					coordinates.add(j);
				}
			}
			
		}
		return coordinates; 
	}
	
	
	
	
	
}