import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameOfLifeBoard {
	private boolean[][] current;  // the board representing the current generation
	private boolean[][] next;    // the board representing the next generation
	private int columns;   // instance variable for columns
	private int rows;  // instance variable for rows
	
	public GameOfLifeBoard(String file_name) {  // constructor for GameOfLifeBoard class which creates a board from a text file
		int rows;  // creates variable for rows
		int column;  // creates variable for column
		String line;  

		try {
			Scanner input = new Scanner(new File(file_name));  // creates a scanner that will read in from a text file

			rows = input.nextInt();  // reads in rows
			column = input.nextInt(); // reads in columns
			line = input.nextLine();  // reads in line
		
			
			current = new boolean[rows][column];  // creates new object of the array current
			next = new boolean[rows][column];  // creates new object of the array new

			for (int row = 0; row < current.length; row++) {
				line = input.nextLine();
				for (column = 0; column < current[row].length; column++) { 
					line.charAt(column);
					if (line.charAt(column)=='*'){ // tests for a slot containing an asterisk
						current[row][column] = true; // if asterisk is present it sets current to true
					}
				}
			}
			input.close();
			
		} catch (FileNotFoundException caught) {
			System.out.println("File " + file_name + " could not be opened.");
		}

	}

	public int rows() {
		return current.length;  // returns the length of the current row

	}

	public int columns() {
		return current[0].length;  // returns the current length of column

	}

	private boolean isBetween(int low, int value, int high) { //A convenience function that check to see if a value is in a range. The range is inclusive of the bottom value, but exclusive of the top of the range. 
		boolean range = (value >= low && value <= high);  // creates boolean range and gives it a function to evaluate  
		 
		return range; // returns true or false for the range once evaluated in the function above
		
	}

	public void toggle(int row, int column) {  // method not used
			current[row][column] = !current[row][column];
			
	}
	public boolean isAlive(int row, int column) { // determines if the cell is occupied or not
		return current[(row + rows()) % rows()][(column + columns()) % columns()];  // function to determine if cell is occupied

	}

	public int neighbours(int row, int column) { // counts the number of adjacent cells that are occupied
		int counter;  // creates a counter
		counter = 0;  // initializes counter to 0
		
		for(int shiftVertically = 0; shiftVertically < 3; shiftVertically++){
			for(int shiftHorizontally = 0; shiftHorizontally < 3; shiftHorizontally++){ // checks for adjacent cells
				if(isAlive(row + shiftHorizontally - 1 , column + shiftVertically - 1)){
					counter++; // increments counter by one
				}
			}
		}
		if(isAlive(row,column)){
			counter--;  // decrements the counter by one
		}
		return counter; // returns counter

	}

	public boolean lives(boolean occupied, int neighbours) { // Applies the rules to determine if a cell will be alive in the enxt generation
		boolean lives;
		switch(neighbours){
		
		// Cases will determine whether a cell will be alive next generation 
		case 0: lives = false;
		break;
		
		case 1: lives = false;
		break;
		
		case 2: lives = occupied;
		break;
		
		case 3: lives = true;
		break;
		
		case 4: lives = false;
		break;
		
		default:
		lives = false;
		}
		return lives; // returns true or false if the cell will be occupied/alive in next generation

	}

	public void printNeighbours() { // method not used
		
	}
	public void nextGeneration(){ // computes the board in the next generation
		boolean temp[][];  // creates a boolean 
		for(int row = 0; row < current.length; row++){
			for(int column = 0; column < current[row].length; column++){
				next[row][column] = lives(current[row][column], neighbours(row,column));
			}
		}
	
	temp = current;  // sets temp equal to current
	current = next; // sets current equal to next
	next = temp;  // sets next equal to temp
	}
	public String toString() {
		String end; // creates string
		end = " "; // initializes value 
		
		for(int row = 0; row < current.length; row++){
			for(int column = 0; column < current[row].length; column++){
				if(current[row][column]){
					end = end + " * "; 
				}
				else{
					end = end + " . ";
				}
			}
			end = end + "\n";
		}
		return end;

	}

}
