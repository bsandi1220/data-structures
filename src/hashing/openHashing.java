package hashing;

import java.util.Scanner;

public class openHashing {


	WordList [] theArray;

	int arraySize;


	public String[][] elementsToAdd = {

			{ "ace", "Very good" },
			{ "act", "Take action" },
			{ "add", "Join (something) to something else" },
			{ "age", "Grow old" },
			{ "ago", "Before the present" },
			{ "aid", "Help, assist, or support" },
			{ "aim", "Point or direct" },
			{ "air", "Invisible gaseous substance" },
			{ "all", "Used to refer to the whole quantity" },
			{ "amp", "Unit of measure for the strength of an electrical current" },
			{ "and", "Used to connect words" }, { "ant", "A small insect" },
			{ "any", "Used to refer to one or some of a thing" },
			{ "ape", "A large primate" },
			{ "apt", "Appropriate or suitable in the circumstances" },
			{ "arc", "A part of the circumference of a curve" },
			{ "are", "Unit of measure, equal to 100 square meters" },
			{ "ark", "The ship built by Noah" },
			{ "arm", "Two upper limbs of the human body" },
			{ "art", "Expression or application of human creative skill" },
			{ "ash", "Powdery residue left after the burning" },
			{ "ask", "Say something in order to obtain information" },
			{ "asp", "Small southern European viper" },
			{ "ass", "Hoofed mammal" },
			{ "ate", "To put (food) into the mouth and swallow it" },
			{ "atm", "Unit of pressure" },
			{ "awe", "A feeling of reverential respect" },
			{ "axe", "Edge tool with a heavy bladed head" },
			{ "aye", "An affirmative answer" } };


	
	// Hash function, provide a String with a Hash value
	public int stringHashFunction (String wordToHash) {

		int hashKeyValue = 0;

		for (int i = 0; i< wordToHash.length(); i++) {

			int charCode = wordToHash.charAt(i) -96;

			int HKVTemp = hashKeyValue;

			hashKeyValue = (hashKeyValue * 27 + charCode) % arraySize;

			System.out.println("Hash Key value " + HKVTemp + " * 27 Character code " + charCode + " % arraySize " + arraySize + " = " + hashKeyValue);

		}
		
		System.out.println();

		return hashKeyValue;

	}

	
	// Class open Hashing Methods
	
	// Constructor of the openHashing or WordList
	openHashing (int size){

		arraySize = size;

		theArray = new WordList[size];

		for(int i = 0; i < arraySize; i++) {

			theArray[i] = new WordList();

		}

		addTheArray(elementsToAdd);

	}


	public void insert(Word newWord) {

		String wordToHash = newWord.theWord;
		int hashKey = stringHashFunction(wordToHash);

		theArray[hashKey].insert(newWord, hashKey);

	}


	public Word find(String wordToFind) {

		int hashKey = stringHashFunction(wordToFind);

		Word theWord = theArray[hashKey].find(hashKey, wordToFind);

		return theWord;

	}



	public void addTheArray(String[][] elementsToAdd) {

		for (int i =0 ; i < elementsToAdd.length ; i++) {

			String word = elementsToAdd[i][0];
			String definition = elementsToAdd[i][1];

			Word newWord = new Word(word, definition);

			insert(newWord);
		}

	}



	public void displayTheArray() {

		for (int i = 0; i < arraySize; i++) {

			System.out.println("theArray Index " + i);
			theArray[i].displayWordList();

		}


	}

	// Word Class
	
	class Word{

		// Attributes of Word
		
		public String theWord;
		public String definition;

		public int key;

		public Word next;

		// Constructor of Word
		
		public Word(String theWord, String definition) {

			this.theWord = theWord;
			this.definition = definition;
		}

		// Methods of Word
		
		// Return a Word and definition
		public String toString() {
			return theWord + " : " + definition;
		}


	}


	// WordList Class
	
	class WordList{
		
		// Attributes of WordList
		
		public Word firstWord = null;

		
		// Methods of WordList
		
		//Insert a new word
		public void insert(Word newWord , int hashKey) {

			Word previous = null;
			Word current = firstWord;

			newWord.key = hashKey;

			while(current != null && newWord.key > current.key ){

				previous = current;
				current = current.next;
			}

			if(previous == null) {
				firstWord = newWord;
			}

			else {
				previous.next = newWord;
			}

			newWord.next = current;
		}

		
		// Display the WordList
		public void displayWordList() {

			Word current = firstWord;

			while (current != null) {

				System.out.println(current);
				current = current.next;

			}
		}

		public Word find(int hashKey, String wordToFind) {

			Word current = firstWord;

			while(current != null && current.key <= hashKey) {


				if(current.theWord.equals(wordToFind)) {
					return current;
				}

				current = current.next;
			}
			return null;
		}


	}


	public static void main (String[]  args) {

		Scanner input =new Scanner(System.in);
		
		openHashing  wordHashTable = new openHashing(10);
		
		String wordLookup = "a";
		
		while(!wordLookup.equalsIgnoreCase("x")){
			
			System.out.println(": ");
			
			wordLookup = input.nextLine();
			
			System.out.println(wordHashTable.find(wordLookup));
			
		}

		input.close();
	}

}
