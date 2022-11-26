/*Author: Shubhkarman Pruthi
  Date: 4 January, 2021
  Program: Title Art code for Hangman Title Screen*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TitleArt {

	public String title() throws FileNotFoundException { //Returns string of title from Text File
		
		File file = new File("./Files/Title.txt");
		Scanner scan = new Scanner(file);
		
		//Adds each line to a string
		String titleContent = "";
		while(scan.hasNextLine()) {
			titleContent = titleContent.concat(scan.nextLine() + "\n");
		}
		return titleContent;
	}

}
