package example_data_structures;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;





public class inverted_index {

	WordList [] theArray;

	public	int arraySize= 967;

	public static	List<Word> listItems = new ArrayList<>();

	public static	List<Integer> searchItems = new ArrayList<Integer>();



	public static class Word{

		// Attributes of Word

		public String theWord;
		public int index;

		public int key;

		public Word next;

		// Constructor of Word

		public Word(String theWord, int index) {

			this.theWord = theWord;
			this.index = index;
		}

		public String getWord() {
			return theWord;
		}

		public int getIndex() {
			return index;
		}

	}

	public static  void inv_index ()   throws IOException {

		// Import data from the file

		// Read and create an array with data from address book  
		Scanner fileLine = new Scanner(new File ("/Users/bsandi/eclipse-workspace/5511/src/assignment_4/ds17s-Asg4-data"));

		// Length of the array
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File ("/Users/bsandi/eclipse-workspace/5511/src/assignment_4/ds17s-Asg4-data")));
		lnr.skip(Long.MAX_VALUE);
		int total_entries = lnr.getLineNumber()+1;

		fileLine.useDelimiter("\\n|:");
		int col_num = 4;




		//Read data from text file
		String[][] inputArr = new String[total_entries][col_num];	
		int j = 0;
		int l = 0;
		while (l< total_entries ) {

			for (int k=0 ; k<col_num ; k++) {
				inputArr[j][k] = (fileLine.next());
			}
			l++;
			j++;
		}
		fileLine.close();
		lnr.close();



		// Creating Array with words and indexes

		for (int k = 0 ; k < inputArr.length ; k++) {

			String[] test = inputArr[k][2].split("\\s+|,\\s+,|\\)|\\(");	

			for (int i = 0 ; i < test.length ; i++) {

				Word theWord = new Word(test[i], k);

				listItems.add(theWord);

			}

		}

	}




	// Hash function, provide a String with a Hash value
	public int stringHashFunction (String wordToHash) {

		int hashKeyValue = 0;

		for (int i = 0; i< wordToHash.length(); i++) {

			int charCode = wordToHash.toLowerCase().charAt(i);

			hashKeyValue = (hashKeyValue * 27 + charCode) % arraySize;

		}


		return hashKeyValue;

	}


	// Class open Hashing Methods

	// Constructor of the openHashing or WordList
	inverted_index () throws IOException {



		theArray = new WordList[arraySize];

		for(int i = 0; i < arraySize; i++) {

			theArray[i] = new WordList();

		}

		inv_index();

		addTheArray(listItems);

	}



	public void insert(Word newWord) {

		String wordToHash = newWord.theWord;
		int hashKey = stringHashFunction(wordToHash);

		theArray[hashKey].insert(newWord, hashKey);

	}




	// Add elements to wordList

	public void addTheArray(List<Word> elementsToAdd) {

		for (int i =0 ; i < elementsToAdd.size() ; i++) {

			String word = elementsToAdd.get(i).getWord();
			int index = elementsToAdd.get(i).getIndex();

			Word newWord = new Word(word, index);

			insert(newWord);
		}

	}


	// Display array
	public void displayTheArray() {

		for (int i = 0; i < arraySize; i++) {

			System.out.println("theArray Index " + i);
			theArray[i].displayWordList();
		}
	}

	
	// Find terms
	public void findTheArray(String term) {

		int termHash = stringHashFunction(term);

		theArray[termHash].findWordList();

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

			while(current != null) { 

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


		// Find the WordList
		public void findWordList() {

			searchItems.clear();

			Word current = firstWord;

			while (current != null) {

				if (current != null) {
					searchItems.add(current.index);
				}
				current = current.next;

			}

		}




	}





	public static void main (String[] args ) throws IOException 
	{

		// Import data from the file

		// Read and create an array with data from address book  
		Scanner fileLine = new Scanner(new File ("/Users/bsandi/eclipse-workspace/5511/src/assignment_4/ds17s-Asg4-data"));

		// Length of the array
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File ("/Users/bsandi/eclipse-workspace/5511/src/assignment_4/ds17s-Asg4-data")));
		lnr.skip(Long.MAX_VALUE);
		int total_entries = lnr.getLineNumber()+1;

		fileLine.useDelimiter("\\n|:");
		int col_num = 4;




		//Read data from text file
		String[][] inputArr = new String[total_entries][col_num];	
		int j = 0;
		int l = 0;
		while (l< total_entries ) {
			for (int k=0 ; k<col_num ; k++) {
				inputArr[j][k] = (fileLine.next());
			}
			l++;
			j++;
		}
		fileLine.close();
		lnr.close();

		
		// Ask for the the string to be searched 
		System.out.println("Give a string you would like to search: ");
		Scanner item = new Scanner(System.in);
		String searchedItem = item.nextLine().toLowerCase();
		
		item.close();

		inverted_index  wordHashTable = new inverted_index();
		
		wordHashTable.findTheArray(searchedItem);

		System.out.println();

		for(int i = 0 ; i < searchItems.size(); i++) {
			
			int k = searchItems.get(i);

			if (inputArr[k][2].toLowerCase().contains(searchedItem)) {
				System.out.println(inputArr[k][0] + "  -  " + inputArr[k][1] +  "  -  " +inputArr[k][2] + "  -  " + inputArr[k][3]);
			}
		}




	}


}
