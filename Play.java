import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;


public class Play {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		OnePlayerGame myGame;
		System.out.println("Which game do you want to play, Snowman or Elordle?: ");
		String game = input.nextLine();
		if(game.equals("Elordle")) {
			myGame = new Elordle(chooseRandomWord());
			playGame(myGame, input);
		}
		else if (game.equals("Snowman")){
			myGame = new Snowman(chooseRandomWord());
			playGame(myGame, input);
		}


	}

	public static void playGame(OnePlayerGame myGame, Scanner scnr) {
		while(!myGame.gameOver()) {
			String state = myGame.stateOfGame();
			System.out.println(state);
			System.out.println("Make a guess: ");
			String guess = scnr.nextLine();
			if(!myGame.validPlay(guess)) {
				System.out.println("Invalid guess");
			}
			else {
				myGame.makePlay(guess);
			}


		}

	}

	// Code below here provided for selecting a random word.
	// You should ONLY edit the filename variable in chooseRandomWord below here!
	// ---------------------------------------------------------------------------------------
	public static String chooseRandomWord() {
		String filename = "dictionary5chars.txt";
		Scanner file = getFileScanner(filename);
		int countLines = 0;
		while (file.hasNextLine()) {
			file.nextLine();
			countLines++; 
		}
		int randLine = (int)(Math.random() * countLines);

		file = getFileScanner(filename);
		int curLine = 0;
		while (file.hasNextLine()) {
			String word = file.nextLine();
			if(curLine == randLine) {
				return word;
			}
			curLine++; 
		}

		return "";
	}

	public static Scanner getFileScanner(String fileName) {
		try {
			FileInputStream textFileStream = new FileInputStream(fileName);
			Scanner inputFile = new Scanner(textFileStream);
			return inputFile;
		}
		catch (IOException ex) {
			System.out.println("Warning: could not open " + fileName);
			return null;
		}
	}
}
