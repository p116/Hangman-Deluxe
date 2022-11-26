/*Author: Shubhkarman Pruthi
  Date: 4 January, 2021
  Program: Client code for Hangman*/

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Client {
public static void main(String[] args) throws FileNotFoundException {
		TitleArt titleart;

		titleart = new TitleArt();

		System.out.println(titleart.title());
		Game hangmanGame = new Game();


		while (true) {
			System.out.println("\nEnter 1 to begin a single-player game, 2 for multi-player game, 3 for player vs AI, 4 for instructions, or 5 to exit: ");
			Scanner input3 = new Scanner(System.in);
			String userInput = input3.nextLine();
			//Single Player
			if (userInput.equals("1")) {
				hangmanGame.newGame();
				hangmanGame.play(1);

			}
			//Multiplayer
			else if (userInput.equals("2")) {
				hangmanGame.newGame();
				System.out.println("Enter the amount of players: ");
				Scanner input4 = new Scanner(System.in);
				int userPlayers = input3.nextInt();
				hangmanGame.play(userPlayers);
			}
			//Player vs AI
			else if (userInput.equals("3")) {
				hangmanGame.newGame();
				hangmanGame.playAI();
			}
			//Instructions
			else if (userInput.equals("4")) {
				hangmanGame.instructions();
			}
			//Exit program
			else if (userInput.equals("5")) {
				System.out.println("Bye!");
				break;
			}

		}

	}
}