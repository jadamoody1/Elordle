import java.util.ArrayList;

public class Snowman implements OnePlayerGame{


	private String target;
	private int totalIncorrectGuesses;
	private String partiallyGuessed;
	private String incorrectGuesses;



	public Snowman() {

		target = "";
		totalIncorrectGuesses = 0;
		partiallyGuessed = "";
		incorrectGuesses = ""; 
	}

	/////// constructors
	public Snowman(String wrd, int guess) {
		setTarget(wrd);
		setNumIncorrectGuess(guess);
		setPartiallyGuessed("");
		setIncorrectGuesses("");

	}

	public Snowman(String target) {
		this(target, 7);
	}

	//////// setters
	public void setTarget(String target) {
		if(target == null) {
			this.target = "";
		}
		else if(target.contains(" ")) {
			int index = target.indexOf(" ");
			this.target = target.substring(0, index);
		}
		else {
			this.target = target;
		}

	}


	public void setNumIncorrectGuess(int num) {
		if((num < 0) || (num > 25)) {
			totalIncorrectGuesses = 7;
		}
		else {
			totalIncorrectGuesses = num;
		}
	}


	public void setPartiallyGuessed(String partiallyGuessed) {
		if((partiallyGuessed == null) || (partiallyGuessed.length() != target.length())) {
			this.partiallyGuessed = "";
			for(int i = 0; i < target.length(); i++) {
				this.partiallyGuessed = this.partiallyGuessed + "-";
			}
		}
		else {
			this.partiallyGuessed = partiallyGuessed;
		}
	}


	public void setIncorrectGuesses(String incorrectGuesses) {
		if(incorrectGuesses == null) {
			this.incorrectGuesses = "";
		}
		else {
			this.incorrectGuesses = incorrectGuesses;
		}
	}

	/////// getters

	public String getTarget() {
		return target;
	}

	public int getNumIncorrectGuess() {
		return totalIncorrectGuesses;
	}


	public String getPartiallyGuessed() {
		return partiallyGuessed;
	}

	public String getIncorrectGuesses() {
		return incorrectGuesses;
	}

	////// OnePlayerGame methods

	public boolean validPlay(String letter) {
		if(letter.length() == 1) {
			if(Character.isLowerCase(letter.charAt(0))) {
				if(!incorrectGuesses.contains(letter) && (!partiallyGuessed.contains(letter))) {
					return true;
				}
			}
		}
		return false;
	}

	public void makePlay(String p) {
		if(!target.contains(p)) {
			incorrectGuesses = incorrectGuesses + p;
			totalIncorrectGuesses = totalIncorrectGuesses - 1;
		}
		else {
			//int index = target.indexOf(p);
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			for(int i = 0; i < target.length(); i++) {
				if(String.valueOf(target.charAt(i)).equals(p)) {
					indexes.add(i);
				}
			}
			for(int num : indexes) {
				partiallyGuessed = partiallyGuessed.substring(0, num) + p + partiallyGuessed.substring(num + 1);
			}
		}


	}

	public boolean gameOver() {
		if((totalIncorrectGuesses == 0) && (partiallyGuessed.contains("-"))) {
			String state = stateOfGame();
			System.out.println(state);
			System.out.println("You lost :(");
			System.out.println("The word was: " + target);
			return true;
		}
		else if(!partiallyGuessed.contains("-")) {
			String state = stateOfGame();
			System.out.println(state);
			System.out.println("You won!");
			return true;
		}
		return false;
	}

	public String stateOfGame() {
		return "Word: " + getPartiallyGuessed() + "\nIncorrect letters: " + getIncorrectGuesses() + "\nGuesses Left: " + getNumIncorrectGuess() + "\n";

	}





}
