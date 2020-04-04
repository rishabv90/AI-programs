package hw03_rishv1995;
import java.util.*;

public class Puzzle2 {
	
	

	public ArrayList<Node2> visited; // visited states
	public PriorityQueue<Node2> pQueue;  //sort based on f values
	public int nodesExpanded;
    public int maxQueueSize; 
    public int nodesExplored; 
    public func test;
	
	
	
	public Puzzle2() {
		this.visited =  new ArrayList<>();
		test = new func();
		this.pQueue = new PriorityQueue<Node2>(1000, test);//this will change
		 nodesExpanded=0;
		 maxQueueSize=1;
		 nodesExplored = 0;
		//this.goalState = new int[3][3];
	}
	
	@SuppressWarnings("resource")
	public int[][] takeInput() {
		String blank = "_";
		int[][] input = new int[3][3];
		Scanner scanner = new Scanner(System.in);
		
        for(int i=0; i<3; i++) {
            String[] row;
            String line = scanner.nextLine();
            row = line.split(" ");            
            
            for(int j=0; j<3; j++) {
            	
            	if(row[j].equals(blank)) {
            		//System.out.println("BLANK");
            		input[i][j] = 0;
            	}else {
            		input[i][j] = Integer.parseInt(row[j]);
            	}
            	
            }
        }
		return input;
		
	}
	
	
	public void printNode(int [][] currentNode) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(currentNode[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean equalNode(int [][] node1, int [][] node2) { // is goal state reached.
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(node1[i][j] != node2[i][j]) {
                    return false;
				}
			}
		}
		return true;
	}
	
	
	
    @SuppressWarnings("unused")
	private boolean visitedBefore(Node2 node) { //contians child??
        int[][] child = node.getMatrix();
        for(Node2 nodeX : this.visited) {
            int[][] temp = nodeX.getMatrix();
            
            boolean identical = true;
            
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                	
                    if(temp[i][j] != child[i][j]) {
                    	identical = false;
                    }
                        
            if(identical) {
                return true;
            }
        }
        return false;
    }

	
    @SuppressWarnings("unused")
	public ArrayList<Node2> getNeighbours(Node2 currentNode, int[][] goalState){
    	int xPos = -1;
        int yPos = -1;
    	int tgVal = 0;
    	int dgVal = 0;
    	int lgVal = 0;
    	int rgVal = 0;
    	int gVal = 0;
    	
    	int hVal=0;//misplacedTiles
    	int[][] currentMatrix = currentNode.getMatrix();
    	
    	ArrayList<Node2> neighbours = new ArrayList<>();
    	
    	gVal = currentNode.getgVal();
    	
    	
    	for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(currentMatrix[i][j]==0) {
                    xPos = i;
                    yPos = j;
                }
            }
        }
    	
    	//getHVal
    	for(int i=0; i<3; i++)
            for(int j=0; j<3; j++) {
                if(currentMatrix[i][j] != goalState[i][j])
                    hVal ++;
            }
    	
    	//movements
    	//create clones first
    	if(xPos-1>=0) { //up movement
    		
    		int[][] cloneMatrix = new int[currentMatrix.length][];
    		
    		
    		for(int i = 0; i < currentMatrix.length; i++) {
        		cloneMatrix[i] = currentMatrix[i].clone();
        	}
    		
    		int temp = cloneMatrix[xPos][yPos];
    		
    		cloneMatrix[xPos][yPos] = cloneMatrix[xPos-1][yPos];
    		cloneMatrix[xPos-1][yPos] = temp;
            neighbours.add(new Node2(cloneMatrix,gVal+1,hVal));
            //System.out.println("UP");
    		
    	}else{
    		neighbours.add(null);
    	}
    	
    	if(xPos+1<3) { //down movement
    		
    		int[][] cloneMatrix = new int[currentMatrix.length][];
    		
    		
    		for(int i = 0; i < currentMatrix.length; i++) {
        		cloneMatrix[i] = currentMatrix[i].clone();
        	}
    		
    		int temp = cloneMatrix[xPos][yPos];
    		
    		cloneMatrix[xPos][yPos] = cloneMatrix[xPos+1][yPos];
    		cloneMatrix[xPos+1][yPos] = temp;
            neighbours.add(new Node2(cloneMatrix,gVal+1,hVal));
            //System.out.println("DOWN");
    		
    	}else {
    		neighbours.add(null);
    	}
    	
    	if(yPos-1>=0) { //left movement
    		
    		int[][] cloneMatrix = new int[currentMatrix.length][];
    		
    		
    		for(int i = 0; i < currentMatrix.length; i++) {
        		cloneMatrix[i] = currentMatrix[i].clone();
        	}
    		
    		int temp = cloneMatrix[xPos][yPos];
    		
    		cloneMatrix[xPos][yPos] = cloneMatrix[xPos][yPos-1];
    		cloneMatrix[xPos][yPos-1] = temp;
            neighbours.add(new Node2(cloneMatrix,gVal+1,hVal));
            
            //System.out.println("LEFT");
    		
    	} else {
    		neighbours.add(null);
    	}
    	
    	
    	if(yPos+1 < 3) { //right movement
    		
    		int[][] cloneMatrix = new int[currentMatrix.length][];
    		
    		
    		for(int i = 0; i < currentMatrix.length; i++) {
        		cloneMatrix[i] = currentMatrix[i].clone();
        	}
    		
    		int temp = cloneMatrix[xPos][yPos];
    		
    		cloneMatrix[xPos][yPos] = cloneMatrix[xPos][yPos+1];
    		cloneMatrix[xPos][yPos+1] = temp;
            neighbours.add(new Node2(cloneMatrix,gVal+1,hVal));
            //System.out.println("RIGHT");
    	}else {
    		neighbours.add(null);
    	}
    	
    	return neighbours;
    }
    
    
      
    @SuppressWarnings("unused")
	public int findSolution(int[][] currentMatrix, int[][] goalState) {
    	nodesExplored=0;
    	pQueue.add(new Node2(currentMatrix,0,0));
        visited.add(new Node2(currentMatrix,0,0));
        int childFval = 99;

        while(true) {
        	if(pQueue.isEmpty()) {
        		System.out.println("Queue is empty");
        		return -1;
        	} 
        	
        	ArrayList<Node2> children;
            Node2 tempState = pQueue.remove();
            int[][] tempNode = tempState.getMatrix();
            
            
            
            for(int i = 0; i < currentMatrix.length; i++) {
            	tempNode[i] = tempNode[i].clone();
            }
            
            
            
            
            System.out.println("Fetching top node in queue with g(n)="+tempState.getgVal()+" and h(n)="+tempState.gethVal()+" and f(n)="+tempState.getfVal()+"...");
            printNode(tempNode);
            
            
            children = getNeighbours(tempState, goalState);
//            for (Node2 i : children) {
//            	if(i!=null) {
//            		System.out.println(" fVal = "+ i.getfVal() + " gVal = " + i.getgVal() + " hVal = " + i.gethVal());
//            	}
//            	
//            }
            	
            for(int i=0;i<4;i++) {
            	
            		if(children.get(i)!=null) {
            			childFval = children.get(i).getfVal();	
                    	System.out.println(childFval);
            			if(equalNode(tempState.getMatrix(), goalState)) { // successs
            				System.out.println("Goal State found!");
            				for(Node2 x: visited) {
            					printNode(x.getMatrix());
            				}
            				return 0;

            			} else {              				
            					if(!visitedBefore(children.get(i)) ) { 
            						
            						nodesExplored++;
            						pQueue.add(children.get(i));
            						visited.add(children.get(i));
            						System.out.println("Adding following child to queue: ");
            						printNode(children.get(i).getMatrix());
            					} 
            						
            				
            			}

            		}
            	
            }
            if(equalNode(tempState.getMatrix(), goalState)) { //successs
				System.out.println("Goal State found!");
				return 0;

			} 
            
           
        }
       
    }
    
	
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
	
		Puzzle2 testPuz2 = new Puzzle2();
		System.out.println("Enter Initial State:");
		int[][] initialState = testPuz2.takeInput();
		
		System.out.println("\nEnter Goal State:");
		int[][] goalState = testPuz2.takeInput();
		
		
		testPuz2.findSolution(initialState, goalState);
		
		
		
		System.out.println("Total Node Explored = " + testPuz2.nodesExplored);
		
//		System.out.println("\nSelect the heuristic");
//		System.out.println(" a) Number of misplaced tiles");
//		System.out.println(" b) Manhattan distance");
//		
//		
//		Scanner myObj = new Scanner(System.in);
//		String heuristicInput = myObj.nextLine();
//		if(heuristicInput.charAt(0) =='a'){
//			
//			
//			
//			System.out.println("a selected");
//		}
		
		
		
	}

}
