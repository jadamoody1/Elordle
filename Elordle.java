
public class Elordle implements OnePlayerGame{

	private String target;
	private int totalIncorrectGuesses;
	private String partiallyGuessed;

	/// constructors
	public Elordle() {
		target = "";
		totalIncorrectGuesses = 0;
		partiallyGuessed = "";
	}

	public Elordle(String wrd, int guess) {
		setTarget(wrd);
		setNumIncorrectGuess(guess);
		setPartiallyGuessed("");
	}

	public Elordle(String target) {
		this(target, 6);
	}

	//// Setters
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
			totalIncorrectGuesses = 6;
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

	///// Getters
	public String getTarget() {
		return target;
	}

	public int getNumIncorrectGuess() {
		return totalIncorrectGuesses;
	}


	public String getPartiallyGuessed() {
		return partiallyGuessed;
	}

	public boolean validPlay(String word) {
		if(word.length() == 5) {

			return true;
		}
		return false;

	}
	///// OnePlayerGame methods
	public void makePlay(String p) {
		setPartiallyGuessed(null);
		for(int i = 0; i < p.length(); i++) {
			if(target.contains(String.valueOf(p.charAt(i)))) {
				int index = target.indexOf(String.valueOf(p.charAt(i)));
				if(i == index) {
					partiallyGuessed = partiallyGuessed.substring(0,i) + String.valueOf(p.charAt(i)).toUpperCase() + partiallyGuessed.substring(i + 1);
				}
				else {
					partiallyGuessed = partiallyGuessed.substring(0,i) + String.valueOf(p.charAt(i)).toLowerCase() + partiallyGuessed.substring(i + 1);
				}

			}

		}
		totalIncorrectGuesses = totalIncorrectGuesses - 1;

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
		return "Word: " + getPartiallyGuessed() + "\nGuesses Left: " + getNumIncorrectGuess() + "\n";
	}







}
