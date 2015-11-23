/*
 * David Casarrubias-Mena
 * Sinclair fuh
 */
public class Application {

	public static void main (String arg[]){
		GameOfLifeBoard board = new GameOfLifeBoard("GameOfLife.txt");
		
		for(int j = 0; j <= 10; j++){  // prints out the board. Prints for 10 generations
			System.out.println("Generation " + j); // provides the generation s
			System.out.println(board.toString());  // calls the toString method from GameOfLife class
			board.nextGeneration();  // calls the nextGeneration method from GameOfLifeclass
		}
	}
}
		
		