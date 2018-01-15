package hashing;

import java.util.Arrays;

public class hashFunction {

	String[] arr;
	int arraySize;
	int itemsInArray = 0;


	// Main 
	public static void main(String[] args) {

		hashFunction theFunc = new hashFunction(30);

		//Function array 1
		//		String[] elementsToAdd = { "1" ,"5" ,"7" ,"11" ,"3" ,"4" };

		//		theFunc.hashFunction1(elementsToAdd, theFunc.arr);

		//Function array 2
		String[] elementsToAdd = { "100", "510", "170", "214", "268", "398",

				"235", "802", "900", "723", "699", "1", "16", "999", "890",

				"725", "998", "978", "988", "990", "989", "984", "320", "321",

				"400", "429", "450", "50", "660", "624" };

		theFunc.hashFunction2(elementsToAdd, theFunc.arr);


		theFunc.displayTheStack();

		theFunc.findKey("429");
	}

	// Hash Function, store x in x
	public void hashFunction1 (String[] stringsForArray, String[] arr) {

		for (int i = 0 ; i < stringsForArray.length ; i++) {

			String newElement = stringsForArray[i];

			arr[Integer.parseInt(newElement)] = newElement;

		}

	}


	public void hashFunction2 (String[] stringsForArray, String[] arr) {

		for (int i = 0 ; i < stringsForArray.length ; i++) {

			String newElement = stringsForArray[i];

			int arrayIndex = Integer.parseInt(newElement) % 29 ;

			System.out.println("Modulus Index= " + arrayIndex + " for value " + newElement);

			while (arr[arrayIndex] != "-1") {

				++arrayIndex;

				System.out.println("Collision try " + arrayIndex + " instead");

				arrayIndex %= arraySize;
			}
			arr[arrayIndex] = newElement;

		}

	}

	public String findKey(String key) {

		int arrayIndexHash = Integer.parseInt(key) % 29 ;

		int counter = 0;
		while (arr[arrayIndexHash] != "-1"){
			System.out.println("Loop: " + counter);
			if (arr[arrayIndexHash]==key) {
				System.out.println(key + " found at "+ arrayIndexHash);
				return arr[arrayIndexHash];
			}
			++arrayIndexHash;
			arrayIndexHash %= arraySize;
			counter = counter +1;
			if (counter > arraySize) {
				break;
			}


		}
		System.out.println(key + " not found");
		return arr[arrayIndexHash];
	}






	// Class 
	hashFunction (int size) {
		arraySize = size;
		arr = new String[size];
		Arrays.fill(arr, "-1");

	}




	// Print array
	public void displayTheStack() {

		int increment = 0;

		for (int m = 0; m < 3; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)

				System.out.print("-");
			System.out.println();
			for (int n = increment - 10; n < increment; n++) {
				System.out.format("| %3s " + " ", n);
			}
			System.out.println("|");
			for (int n = 0; n < 71; n++)
				System.out.print("-");
			System.out.println();
			for (int n = increment - 10; n < increment; n++) {
				if (arr[n].equals("-1"))
					System.out.print("|      ");
				else
					System.out.print(String.format("| %3s " + " ", arr[n]));
			}
			System.out.println("|");
			for (int n = 0; n < 71; n++)
				System.out.print("-");
			System.out.println();
		}
	}


}
