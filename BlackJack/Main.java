import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	public static void main (String[] args) {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("			$	B L A C K J A C K	$			");
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------");

		LaunchGame();

		System.out.println("");
		System.out.println("----------------------------------------------------------------------------");	
		System.out.println("");
		System.out.println("                    ~~~~~ Made by Quentin & Daniel ~~~~~                    ");
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------");
	}
	
	public static void LaunchGame() {
		Programmes programmes = new Programmes();

		System.out.println("");
		Scanner inputName = new Scanner(System.in);
		System.out.print("Insères ton prénom : ");
		String name = inputName.nextLine();
		System.out.println("");
		System.out.println("-----------------------------------------------------------------");	

		//Print cartes Joueur :
		ArrayList<Integer> playerCards = programmes.distributionJoueur();
		System.out.println("");
		System.out.println("Main de " + name + " :");
		System.out.println("");
		System.out.print(playerCards.get(0) + " | ");
		System.out.println(playerCards.get(1));
			
		//Print cartes Dealer :
		ArrayList<Integer> dealerCards = programmes.distributionDealer();
		System.out.println("");
		System.out.println("Main de Sylvain :");
		System.out.println("");
		System.out.print(dealerCards.get(0) + " | ");
		System.out.println("?");
		


		//Définition des Actions possibles :
		String a1 = "o";
		String a2 = "n";
		String a3 = "1";
		String a4 = "11";

		//Définition des status de jeu :
		String status1 = "Playing";
		String status2 = "End";
		String status3 = "Stop";
		String status4 = "2Stop";


		//Tour du Joueur :
		String gameStatus = "Playing";
		while (gameStatus.equals(status1)) {

			//Lecture choix Joueur :
			String playerAnswer = "";
			while (!playerAnswer.equals(a1) && !playerAnswer.equals(a2)) {
				Scanner terminalInput = new Scanner(System.in);
				System.out.println();
				System.out.println("=================================================================");
				System.out.println();
				System.out.println("Veux-tu reprendre une carte ? (o/n)");
				playerAnswer = terminalInput.nextLine();
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
			}

			//Traitement choix Joueur :
			if (playerAnswer.equals(a2)) {
				gameStatus = "Stop";
				break;

			//Changement main du Joueur :
			} else {
				int newCard = (programmes.nouvelleCarte());
				if (newCard == 1) {
					String asAnswer = "";
					while (!asAnswer.equals(a3) && !asAnswer.equals(a4)) {
						System.out.println();
						System.out.println("=================================================================");
						System.out.println();
						Scanner terminalInput2 = new Scanner(System.in);
						System.out.println("Tu as pioché un AS, quelle valeur souhaites-tu ? ('1' ou '11')");
						asAnswer = terminalInput2.nextLine();
						System.out.println();
						System.out.println("-----------------------------------------------------------------");
					}
					if (asAnswer.equals(a4)) {
						newCard = 11;
					}						
				}
				playerCards.add(newCard);
			}

			//Afficher main Joueur :
			System.out.println("");
			System.out.println("Ta nouvelle main :");
			System.out.println("");
			for (int i = 0; i < playerCards.size()-1; i++) {
				System.out.print(playerCards.get(i) + " | ");
			}
			System.out.println(playerCards.get(playerCards.size()-1));
			System.out.println("");

			//Vérification total main Joueur :
			int count = 0;
			for (int j = 0; j < playerCards.size(); j++) {
				count += playerCards.get(j);	
			}
			if (count > 21) {
				System.out.println();
				System.out.println("=================================================================");
				System.out.println();				
				System.out.println("Tu as perdu ! (Tu as dépassé 21)");
				gameStatus = "End";
				break;
			}
		}


		//Tour du Dealer
		if (gameStatus.equals(status3)) {
			//Afficher jeu du Dealer retourné :
			System.out.println("");
			System.out.println("Main de Sylvain :");
			System.out.println("");
			System.out.print(dealerCards.get(0) + " | ");
			System.out.println(dealerCards.get(1));
			System.out.println("");
			
			while (gameStatus.equals(status3)) {

				//Compte total main Dealer :
				int count2 = 0;
				for (int c = 0; c < dealerCards.size(); c++) {
					count2 += dealerCards.get(c);	
				}	

				//Choix Dealer :
				if (count2 >= 17) {
					gameStatus = "2Stop";
					break;

				//Ajouter une carte :
				} else {
					int newCard2 = (programmes.nouvelleCarte());
					if (newCard2 == 1 && (count2 + 11) >= 17 && (count2 + 11) <= 21) {
						newCard2 = 11;
					}
					dealerCards.add(newCard2);
				}

				//Afficher main Dealer :
				System.out.println("-----------------------------------------------------------------");
				System.out.println("");
				System.out.println("Nouvelle main de Sylvain :");
				System.out.println("");
				for (int i = 0; i < dealerCards.size()-1; i++) {
					System.out.print(dealerCards.get(i) + " | ");
				}
				System.out.println(dealerCards.get(dealerCards.size()-1));

				//Vérification total main Dealer :
				int count3 = 0;
				for (int z = 0; z < dealerCards.size(); z++) {
					count3 += dealerCards.get(z);	
				}
				if (count3 > 21) {
					System.out.println();
					System.out.println("=================================================================");
					System.out.println();
					System.out.println("Bravo " + name + " tu as gagné ! (Sylvain a dépassé 21)");
					gameStatus = "End";
					break;
				}
			}
		}
		if (gameStatus.equals(status4)) {
			//Compte total main Dealer :
			int countDealer = 0;
			for (int y = 0; y < dealerCards.size(); y++) {
				countDealer += dealerCards.get(y);
			}	

			//Compte total main Joueur :
			int countPlayer = 0;
			for (int w = 0; w < playerCards.size(); w++) {
				countPlayer += playerCards.get(w);
			}
			
			System.out.println();
			System.out.println("=================================================================");
			System.out.println();

			//Comparaison des mains :
			if (countPlayer < countDealer) {
				System.out.println("Tu as perdu ! (Sylvain est plus proche de 21 que toi)");	
			} else if (countPlayer > countDealer) {
				System.out.println("Bravo " + name + " tu as gagné ! Tu es meilleur que Sylvain !");
			} else {
				Scanner replay = new Scanner(System.in);
				System.out.println("Égalité, veux-tu rejouer ? (o/n)");
				String replayAnswer = replay.nextLine();
				// Rejouer ?
				if (replayAnswer.equals(a1)) {
					LaunchGame();				
				}
			}
		}

	}

}
