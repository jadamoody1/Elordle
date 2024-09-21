
public interface OnePlayerGame {


	public void makePlay(String p);


	public boolean validPlay(String letter);


	public boolean gameOver();


	public String stateOfGame();

}
