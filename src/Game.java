/*Author: Shubhkarman Pruthi
  Date: 4 January, 2021
  Program: Game code for Hangman*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	//Java Words
	public static String[] WORDS = new String[0];
	static Scanner input = new Scanner(System.in);
	public static final Random RANDOM = new Random();
	// Word to find
	private String wordToFind;
	// Word found stored in a char array to show progression of user
	private char[] wordFound;
	// letters already entered by user
	private ArrayList < String > letters = new ArrayList < > ();

	// Method returning randomly next word to find
	private String nextWordToFind() throws FileNotFoundException {
		File testFile = new File("./Files/Words.txt");
		Scanner inputFile = new Scanner(testFile);

		while (inputFile.hasNext()) {
			String[] temp = new String[WORDS.length + 1];
			System.arraycopy(WORDS, 0, temp, 0, WORDS.length);
			temp[temp.length - 1] = inputFile.nextLine();
			WORDS = temp;
		}

		return WORDS[RANDOM.nextInt(WORDS.length)].toUpperCase();
	}

	//Prints the instructions
	public void instructions() {
		System.out.println("\nYou are given a secret word that you have to uncover. The way to uncover it is to guess each letter in the word.");
		System.out.println("Each guess will be revealed and shown in which place the letter belongs in. ");
		System.out.println("To win the game, you either guess all the letters or guess the word directly. You can only lose if you directly guess a different word. \n");
		System.out.println("In multiplayer, whoever gets finds the final letter or guesses the word first wins.\n");	
	}

	//Method for starting a new game
	public void newGame() throws FileNotFoundException {
		letters.clear();
		wordToFind = nextWordToFind();

		//Word found initialization
		wordFound = new char[wordToFind.length()];

		for (int i = 0; i < wordFound.length; i++) {
			wordFound[i] = '_';
		}
	}

	//Method returning true if word is found by user
	public boolean wordFound() {
		return wordToFind.contentEquals(new String(wordFound));
	}

	//Method updating the word found after user entered a character
	private void enter(String c) {
		String ltr = c.toUpperCase();

		//Updates only if c has not already been entered
		if (!letters.contains(ltr)) {
			//Checking to see if word has c
			if (wordToFind.contains(ltr)) {
				//If it does, replace _ with c
				int index = wordToFind.indexOf(ltr);

				while (index >= 0) {
					wordFound[index] = (ltr).charAt(0);
					index = wordToFind.indexOf(ltr, index + 1);
				}
			} 

			//C is now an entered letter
			letters.add(ltr);
		}
	}

	//Method returning the current word state
	private String wordFoundCurrent() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < wordFound.length; i++) {
			builder.append(wordFound[i]);

			if (i < wordFound.length - 1) {
				builder.append(" ");
			}
		}

		return builder.toString();
	}

	//Method to play game
	public void play(int numberofplayers) {
		try {
			//Runs until word is found or user inputs !
			int i = 0;
			ArrayList<String> repeatedWords = new ArrayList<String>();
			System.out.println("Word: " + wordToFind); //Displays word to find, only for functionality checking purposes

			while (true) {
				String player = "P" + (i % numberofplayers + 1);
				System.out.println("(" + player + ") Enter a letter (! to guess entire word): ");


				//Gets input from user
				String str = input.next();

				//Checks to see if word is repeated, if it isn't then counter goes up
				if (repeatedWords.contains(str)) {
					System.out.println("Repeated word!");
				}
				else {
					repeatedWords.add(str);
					i++;
				}

				//If user wants to make a guess
				if (str.equals("!")) {
					System.out.println("Enter your guess: ");
					Scanner input2 = new Scanner(System.in);
					String userGuess = input2.next();
					userGuess = userGuess.toUpperCase();

					//If user guesses right, they win. If not, they lose
					if (userGuess.equals(wordToFind)){
						System.out.println("GG! " + player + " Won. It took you " + i + " attempts!");
					} 
					else {
						System.out.println("Better luck next time! Word was: " + wordToFind);
					}
					break;
				}
				//Taking first letter only if user inputs more than 1 letter
				if (str.length() > 1) {
					str = str.substring(0, 1);
				}

				//Updates word
				enter(str);

				// display current progress
				System.out.println("\n" + wordFoundCurrent());

				//Checks if user finds word
				if (wordFound()) {
					System.out.println("GG! " + player + " Won. It took you " + i + " attempts!");
					break;
				} 

				// we display attempt #
				System.out.println("Attempt : " + i);
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong!");
		}
	}

	public void playAI() {
		try {
			//Runs until word is found or user inputs !
			int i = 0;
			System.out.println("Word: " + wordToFind); //Displays word to find, only for functionality checking purposes
			ArrayList<String> repeatedWords = new ArrayList<String>();
			LinkedList<String> alphabet = new LinkedList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));

			while (true) {
				String player = "P1";

				System.out.println("(" + player + ") Enter a letter (! to guess entire word): ");

				//Gets input from user
				Scanner input4 = new Scanner(System.in);
				String string = input4.next();

				//Checks to see if word is repeated, if it isn't then counter goes up
				while (true) {
					if (repeatedWords.contains(string)) {
						System.out.println("Repeated word!");
						System.out.println("(" + player + ") Enter a letter (! to guess entire word): ");
						string = input4.next();
					}
					else {
						repeatedWords.add(string);
						alphabet.remove(string);
						i++;
						break;
					}
				}
				//If user wants to make a guess
				if (string.equals("!")) {
					System.out.println("Enter your guess: ");
					Scanner input5 = new Scanner(System.in);
					String userGuess = input5.next();
					userGuess = userGuess.toUpperCase();

					//If user guesses right, they win. If not, they lose
					if (userGuess.equals(wordToFind)){
						System.out.println("GG! " + player + " Won. It took you " + i + " attempt(s)!");
					} 
					else {
						System.out.println("Better luck next time! Word was: " + wordToFind);
					}
					break;
				}
				
				//Displaying attempt #
				System.out.println("Attempt : " + i);
				
				//Taking first letter only if user inputs more than 1 letter
				if (string.length() > 1) {
					string = string.substring(0, 1);
				}

				//Updates word
				enter(string);
				
				//Checking if user found word before computer
				if (wordFound()) {
					System.out.println("GG! " + player + " Won. It took you " + i + " attempts!");
					break;
				}
				
				//Generates random character and enters it as valid letter guess
				Random r = new Random();
				int randNum = r.nextInt(alphabet.size());
				String compLetter = alphabet.get(randNum);

				System.out.println("Computer typed: " + compLetter);

				//Enters compLetter as guess
				enter(compLetter);
				i++;
				
				repeatedWords.add(compLetter);
				alphabet.remove(compLetter);

				// display current progress
				System.out.println("\n" + wordFoundCurrent());

				//Checks if computer finds word
				if (wordFound()) {
						System.out.println("Better luck next time! Computer won. It took " + i + " attempt(s)!");
						break;
					}

				//We display attempt #
				System.out.println("Attempt : " + i);
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong!");
		}
	}
}	