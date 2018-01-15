package example_data_structures;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class address_book {


	public static boolean bma (String pattern , String text){


		int pat_len 	= pattern.length() - 1;
		int text_len = text.length() - 1;
		int j; // Comparing index of the text

		for (int i = pat_len ; i <= text_len ; i++) {

			j = i;
			for (int k = pat_len ; k >= 0 ; k--) {

				// If character is the same compare next character 
				if ((pattern.toUpperCase().charAt(k)) == (text.toUpperCase().charAt(j)) ) {
					j--;	

					// If all characters are the same return true 
					if (j + pat_len + 1 == i) {
						return true;
					}		
				}

				// If character is not the same break and go to next position
				else {

					// Check if failed character is in pattern 

					// Character is in pattern
					if (pattern.toUpperCase().lastIndexOf(text.toUpperCase().charAt(j))>=0) {

						int pos = pattern.toUpperCase().lastIndexOf(text.toUpperCase().charAt(j));
						int x = Math.min(k,pos);
						i = i + pat_len - x - 1;

						break;
					}
					// Character is not in the pattern
					else {
						i = i + pat_len;
						break;
					}


				}
			}				
		}
		return false;
	}





	public static void main(String[] args)   throws IOException {





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
		while (l< total_entries ) {//fileLine.hasNext()) {

			for (int k=0 ; k<col_num ; k++) {
				inputArr[j][k] = (fileLine.next());
			}
			l++;
			j++;
		}
		fileLine.close();
		lnr.close();



		// Ask for the the string to be searched 
		int searchedField =-1;
		System.out.println("Choose a field to execute the search (1) Person’s name. (2) Email address. (3) Organization ");
		Scanner field = new Scanner(System.in);
		while (searchedField<1 || searchedField>3) {	
			field = new Scanner(System.in);
			try {
				searchedField = Integer.parseInt(field.nextLine());
				if (searchedField<1 || searchedField>3) {
					System.out.println("Input 1, 2 or 3 ((1) Person’s name. (2) Email address. (3) Organization) ");
				}
			}
			catch(Exception e){
				System.out.println("Input 1, 2 or 3 ((1) Person’s name. (2) Email address. (3) Organization) ");
			}
		}

		





		// Ask for the the string to be searched 
		System.out.println("Give a string you would like to search: ");
		Scanner item = new Scanner(System.in);
		String searchedItem = item.nextLine();
		
		
		field.close();
		item.close();

		
		
		// Create output array
		int outputIndex = 0;
		int outputItems = 0;
		String[] output = new String[total_entries]; //Change to dynamic 
		for (int i = 0 ; i< output.length ; i++) {
			if (bma(searchedItem,inputArr[i][searchedField-1])) {
				output[outputIndex]= inputArr[i][0] + "  -  " + inputArr[i][1] +  "  -  " +inputArr[i][2] + "  -  " + inputArr[i][3];
				outputIndex = outputIndex +1;
				outputItems = outputItems +1;
			}
		}


		// Print output array
		for (int i=0 ; i < outputItems ; i++) {
			System.out.println(output[i]);
		}
		if (outputItems==0) {
			System.out.println("No items found");
		}



	}


}
