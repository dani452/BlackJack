import java.lang.Math;
import java.util.ArrayList;

public class Programmes {

	int[] cardGame = {1,2,3,4,5,6,7,8,9,10,10,10,10};
	public int getRandom () {
		int rnd = (int)(Math.random()*(cardGame.length));
		return cardGame[rnd];
	}

	public ArrayList<Integer> distributionJoueur () {
		ArrayList<Integer> cartesJoueur = new ArrayList<Integer>();
		cartesJoueur.add(getRandom());
		cartesJoueur.add(getRandom());
		return cartesJoueur;
	}
	
	public ArrayList<Integer> distributionDealer () {
		ArrayList<Integer> cartesDealer = new ArrayList<Integer>();
		cartesDealer.add(getRandom());
		cartesDealer.add(getRandom());
		return cartesDealer;
	}

	public int nouvelleCarte () {
		int newCard = getRandom();
		return newCard;
	}

}
