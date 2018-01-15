package hashing;

public class Link {

	// Attributes in class Link
	public String bookName;
	public int millionsSold;
	public Link next;

	// Constructor 
	public Link (String bookName , int millionsSold) { //check this one

		this.bookName = bookName;
		this.millionsSold = millionsSold;

	}

	//Methods in class Link
	//Display 
	public void display() {
		System.out.println(bookName + " : " + millionsSold + ",000,000" );
	}

	// Return the bookName
	public String toString() {

		return bookName;
	}



	public static void main (String[] args) {

		LinkList theLinkedList = new LinkList();

		theLinkedList.insertFirstLink("Don Quijote", 500);
		theLinkedList.insertFirstLink("A tale of two Cities", 250);
		theLinkedList.insertFirstLink("Lord of the rings", 150);
		theLinkedList.insertFirstLink("Harry potter", 107);

        theLinkedList.display();

        System.out.println("Value of first in LinkedList " + theLinkedList.firstLink + "\n");
	
        theLinkedList.removeFirst();

        theLinkedList.display();
	
        
        System.out.println(theLinkedList.find("Lord of the rings").bookName + " Was Found");

        
        
        theLinkedList.removeLink("A tale of two Cities");
        
        System.out.println("\nA Tale of Two Cities Removed\n");

        theLinkedList.display();


	}




}
