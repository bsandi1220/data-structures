package example_data_structures;

public class quick_sort_strings {
	  
	
	private String array[];
	private int length;
	 
	
		// Sort array 
	
	    public void sort(String[] inputArr) {
	         
	        if (inputArr == null || inputArr.length == 0) {
	            return;
	        }
	        this.array = inputArr;
	        length = inputArr.length;
	        quickSort(0, length - 1);
	    }
	 
	    
	    // After swap  values from right to left and viceversa. execute quick sort on the left and right part again 
	    private void quickSort(int lowerIndex, int higherIndex) {
	         
	        int i = lowerIndex;
	        int j = higherIndex;
	        // calculate pivot number, I am taking pivot as middle index number
	        String pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
	        // Divide into two arrays
	    	while (i <= j) {
				/**
				 * In each iteration, we will identify a number from left side which 
				 * is greater then the pivot value, and also we will identify a number 
				 * from right side which is less then the pivot value. Once the search 
				 * is done, then we exchange both numbers.
				 */
				while (array[i].compareToIgnoreCase(pivot) < 0) {
					i++;
				}
				while (array[j].compareToIgnoreCase(pivot) > 0) {
					j--;
				}
				if (i <= j) {
					exchangeNumbers(i, j);
					//move index to next position on both sides
					i++;
					j--;
				}
			}
	        // call quickSort() method recursively
	        if (lowerIndex < j)
	            {quickSort(lowerIndex, j);}
	       
	        if (i < higherIndex)
	            {quickSort(i, higherIndex);}
	    }
	 
	    
	    
	    // Swap values
	    private void exchangeNumbers(int i, int j) {
	    		String temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	    }
	     
	    
	    
	    //Main function, execute sort
	    public static void main(String a[]){
	         
	 
	    }
	}
