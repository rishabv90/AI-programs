import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;


public class Queens {
	public static int backtrack = 0;
	public static int backtrack2 =0;
	public static int backtrack3 = 0;
	public static int forwardCheck = 0;
	public static int solutions = 0;
	public static int colNum = 0;
	public static char colVal = 'X';
	public static int oldK = 0;
	public static int oldK2 = 0;
	public static int totalForward = 0;
	public static int totalLook = 0;
	
	
	
    public static boolean constraints(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n])
            {
            	return false;
            }	// same column
            if ((q[i] - q[n]) == (n - i)) { 
            	return false;   // same major diagonal
            }
            if ((q[n] - q[i]) == (n - i)) {
            	return false;   // same minor diagonal
            }
        }
        return true;
    }
    
    public static boolean lookAhead(int[] q, int n) {
        for (int i = 0; i < n; i++) {
        	if(n==8) {
        		return false;
        	}
            if (q[i] == q[n])
            {
            	return false;
            }	// same column
            if ((q[i] - q[n]) == (n - i)) { 
            	return false;   // same major diagonal
            }
            if ((q[n] - q[i]) == (n - i)) {
            	return false;   // same minor diagonal
            }
            //System.out.println("n = " + n + " and i = " + i);
        }
        return true;
    }

   
    public static void printQueens(int[] q) {
    	//System.out.println(Arrays.toString(q));
    	System.out.println();
        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) { 
                	System.out.print("Q ");
                }
                else {    
                	System.out.print("# ");
                }
            }
            System.out.println();
        }  
        

        
        
    }

    public static void printOutput(int [] q, int numSol) {
    	
    	Hashtable<Integer, Character> numbers = new Hashtable<Integer, Character>();
    	numbers.put(1, 'A'); numbers.put(2,'B'); numbers.put(3,'C');  numbers.put(4,'D'); numbers.put(5, 'E'); numbers.put(6,'F'); numbers.put( 7,'G');  numbers.put(8,'H'); 
    	  
    	
    	
    	if(numSol != 0) {
    		System.out.println("Solution " +  numSol + " with queen 1 in 1" + colVal + ":");
    		System.out.println("The positions of the queens are:");
    		for(int i = 0; i< q.length; i++){
    			System.out.println("Row " + (i+1) + ": " + (i+1)+numbers.get(q[i]+1));

    			}
    		System.out.println("\nTotal numbers of backtracks before this solution was found:");
    		System.out.println("Forward Checking: " + backtrack);
    		System.out.println("Directional Look Ahead: " + backtrack2 + "\n");
    		totalForward += backtrack;
    		totalLook += backtrack2;
    		
    		System.out.println("\nTotal Backtracks in Column input");
    		System.out.println("Total Forward checks = "+ totalForward);
    		System.out.println("Total Directional Look Ahead checks = " + totalLook);
    		backtrack=0;
    	}
    }
  
    public static void passingFunction(int n, int x, char c) {
    	colNum  = x;
    	colVal = c;
    	int[] a = new int[n];  
        findSolution(a, 0);
    }

    public static void findSolution(int[] q, int k) { //q = Queue k = counter for q //every time a que is passed with 4 counters, then is consistant checks
        int n = q.length;
        
        
        //System.out.println(Arrays.toString(q));
        if (k == n) { 	
        	
        	if(q[0]==colNum) {
        		//printQueens(q);
        		solutions++;
        		printOutput(q, solutions);
        		backtrack2 = 0;
        		//System.exit(0);
        	}
        	
        	//System.exit(0);
        	}
        else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                //System.out.println("XXXvalue of q = " + Arrays.toString(q));
                if (constraints(q, k)) {
                	//System.out.println("FC met i = " + i + " k value= " + k);
                	if(lookAhead(q,k+1) && (k+1)!=8) {
                		if(oldK2>k && q[0] == colNum && k!=0) {
                    		backtrack2 += oldK2-k;
                    	}
                		oldK2 = k;
                		//System.out.println("DLA - Value of k = " + k  + " --  Array Val = "+ Arrays.toString(q) + " --- Backtrak Value: " + backtrack2);
                	}
                	
                	if(oldK>k && q[0] == colNum && k!=0) {
                		backtrack += oldK-k;
                	}
                	
                	
                	//System.out.println("FCCC - Value of k = " + k  + " --  Array Val = "+ Arrays.toString(q) + " --- Backtrak Value: " + backtrack);
                	oldK=k;
                	
                	findSolution(q, k+1);
                }	
            }
            
        }
    }  

    public static void main(String[] args) {
    	char[] col = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'}; // test for 8
    	
    	@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in); 
    	
    	
    	// Create a Scanner object
        System.out.println("Please enter a postion of a queen: ");
        String queenPosition = myObj.nextLine();
        
        char inputCol = queenPosition.charAt(1);
        int colNum=0;
        int len = col.length; 
        int i = 0; 
        
        
        while (i < len) { 
            if (col[i] == inputCol) { 
                colNum = i; 
                break;
            } 
            else { 
                i = i + 1; 
            } 
        } 
     
        int n = Integer.parseInt("8");
        passingFunction(n, colNum, inputCol);
    }

}