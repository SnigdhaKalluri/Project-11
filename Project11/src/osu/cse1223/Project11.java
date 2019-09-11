/*
 * Project11.java
 * 
 *   A program that plays simple word guessing game.  In this game the user provides a list of 
 *   words to the program.  The program randomly selects one of the words to be guessed from 
 *   this list.  The player then guesses letters in an attempt to figure out what the hidden 
 *   word might be.  The number of guesses that the user takes are tracked and reported at the 
 *   end of the game.
 *   
 * 
 * @author Snigdha Kalluri
 * 
 */
package osu.cse1223;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Project11 {

	
	public static void main(String[] args) {
		// Fill in the body
		Scanner inScanner= new Scanner(System.in);
		//String inString = getFileName(inScanner);
		//Scanner fileScanner = new Scanner(inString);
		
		boolean t = true;
		
		while (t)
		{
			String inString = getFileName(inScanner);
			
			try {
				File file = new File (inString);
				Scanner fileScanner = new Scanner (file);
				
				ArrayList<String> list = getList(fileScanner);
				System.out.println("Read " + list.size() + " from the file");
				
				boolean check = false;
				int guessCount = 0;
				String correctWord = getRandomWord(list);
				correctWord.toUpperCase();
		
				
				String guess = starWord(correctWord);
				System.out.println("The word to guess is: " + guess);
				
				String pastGuess = "";
				while (check==false)
				{
				System.out.println("Your previous guesses: " + pastGuess);
				char charGuess = getCharacterGuess(inScanner);
				pastGuess = pastGuess + charGuess + ", ";
				int count = checkChar(charGuess,correctWord);
				System.out.println("Your guess " + charGuess + " occurs in " + count + " positions");
				
				System.out.println("");
				
				guess = modifyGuess(charGuess, correctWord, guess );
				System.out.println("The word to guess is now " + guess);
				System.out.print("Enter you guess: ");
				String newGuess = inScanner.nextLine();
				newGuess.toUpperCase();
				
				check = checkWord(newGuess, correctWord);
				
				if (check)
				{
					System.out.println("Congratulations! " + newGuess + " is the correct word.");
				}
				
				else 
				{
					System.out.println("That is not the correct word.");
					System.out.println("");
					System.out.println("Please guess another letter and try again.");
				}
				
				guessCount++;
				System.out.println("");
				
				}
				
				System.out.println("You guessed the word in " + guessCount + " guesses");
				System.out.print("Would you like a rematch [y/n]? ");
				String again = inScanner.nextLine();
				if (again=="y")
				{
					t = true;
				}
				else
				{
					t=false;
				}
				
			}
			
			catch(IOException e)
			{
			System.out.println(e);
				
			}	
		}
		
		System.out.println("Goodbye!");
	}
	
	
	private static String getFileName(Scanner inScanner) {

		System.out.print("Enter a filename containing your wordlist: ");
		String FileName = inScanner.nextLine();
		
		while (FileName.length()==0)
		{
			System.out.println("ERROR: please enter non empty line");
			System.out.print("Enter a filename containing your wordlist: ");
			FileName = inScanner.nextLine();	
		}
		
		return FileName;
		
		
	}
	
	
	
	
	
	
	// Given a Scanner as input, returns a List<String> of strings read from the Scanner.
	// The method should read words from the Scanner until there are no more words in the file
	// (i.e. inScanner.hasNext() is false).  The list of strings should be returned to the calling program.
	public static ArrayList<String> getList(Scanner inScanner) {
		// Fill in the body
		ArrayList<String> list = new ArrayList<String>();
		
		while (inScanner.hasNext())
		{
		list.add(inScanner.nextLine().toUpperCase());
		}
		return list;
		
	}

	// Given two strings as input, compares the first string (guess) to the second
	// (solution) character by character.  If the two strings are not exactly the same,
	// return false.  Otherwise return true.
	public static boolean checkWord(String guess, String solution) {
		// Fill in the body
		boolean t = true;
		int i = 0;
		
		while (t=true && i<guess.length())
		{
			if (guess.charAt(i)!=solution.charAt(i))
			{
				t = false;
			}
			
		}
		return t;
		
	}
		
	
	
	// Given a ArrayList<String> list of strings as input, randomly selects one of the strings
	// in the list and returns it to the calling program.
	public static String getRandomWord(ArrayList<String> inList) {
		// Fill in the body
		double random = Math.random();
		int randomIndex = (int) (inList.size() * random);

		return inList.remove(randomIndex);
		
	}
	

	// Given a Scanner as input, prompt the user to enter a character.  If the character
	// enters anything other than a single character provide an error message and ask
	// the user to input a single character.  Otherwise return the single character to
	// the calling program.
	public static char getCharacterGuess(Scanner inScanner) {
		// Fill in the body
		
		System.out.print("Enter a character to guess:  ");
		String input = inScanner.nextLine();
		char inChar = input.charAt(0);
		
		
		while (input.length()!=1)
		{
			System.out.println("ERROR: input must be a single character!");
			System.out.print("Enter a character to guess: ");
			input = inScanner.nextLine();
			inChar = input.charAt(0);
		}
		return inChar;
		
		
	}
	
	// Given a character inChar and a ArrayList<Character> list of characters, check to see if the
	// character inChar is in the list.  If it is, return true.  Otherwise, return false. 
	public static boolean checkInList(char inChar, ArrayList<Character> inList) {
		// Fill in the body
		boolean t = true;
		
		if (!inList.contains(inChar))
		{
			t = false;
		}
		return t;
			
		
	}
	
	// Given a String, return a String that is the exact same length but consists of 
	// nothing but '*' characters.  For example, given the String DOG as input, return
	// the string ***
	public static String starWord(String inWord) {
		// Fill in the body
		
		int i = 0;
		String star = "";
		while (i<inWord.length())
		{
		star  = star + "*";
		i++;
		}
		return star;
	}
	
	// Given a character and a String, return the count of the number of times the
	// character occurs in that String.
	public static int checkChar(char guessChar, String guessWord) {
		// Fill in the body
		int count = 0;
		int i = 0;
		while (i<guessWord.length())
		{
			if (guessChar==guessWord.charAt(i))
			{
				count++;
			}
		i++;
		}
		return count;
	}

	// Given a character, a String containing a word, and a String containing a 'guess'
	// for that word, return a new String that is a modified version of the 'guess' 
	// string where characters equal to the character inChar are uncovered.
	// For example, given the following call:
	//   modfiyGuess('G',"GEOLOGY", "**O*O*Y")
	// This functions should return the String "G*O*OGY".
	public static String modifyGuess(char inChar, String word, String currentGuess) {
		// Fill in the body
		for(int i=0;i<word.length();i++)

		{char currentC=word.charAt(i);

		String newGuess="";

		if(currentC==inChar)

		{newGuess=newGuess+currentC;

		}

		else

		{newGuess=newGuess+currentGuess.charAt(i);

		}

		}

		return currentGuess;

		}
}


		
		
