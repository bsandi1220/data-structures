package hashing;


class LinkList {


	//Attributes of LinkList class
	public Link firstLink;

	//Constructor 
	// Set linkedList as empty
	LinkList(){

		firstLink = null;

	}

	// Methods of LinkedList Class
	// Check if the linkedList is empty
	public boolean isEmpty(){

		return(firstLink == null);

	}


	// Insert an item on the front part of the linked list
	// Always use firstLink.next that is the start of the LinkList
	public void insertFirstLink(String bookName, int millionsSold) {

		Link newLink = new Link(bookName, millionsSold);

		newLink.next = firstLink;

		firstLink = newLink;

	}


	// Remove the first item in the linked list
	public Link removeFirst() {

		Link linkReference = firstLink;

		if (!isEmpty()) {
			firstLink = firstLink.next;
		}
		else {
			System.out.println("Empty LinkedList");
		}
		return linkReference;
	}


	// Display all the items in the linked list
	public void display() {

		Link theLink =firstLink;

		while(theLink != null) {

			theLink.display();

			System.out.println("Next Link: "+ theLink.next);

			theLink = theLink.next;

			System.out.println(firstLink);

		}
	}


	// Find an item in the linkedList
	public Link find(String bookName) {

		Link theLink = firstLink;

		if(!isEmpty()) {

			while (theLink.bookName != bookName) {

				if (theLink.next == null) {
					return null;
				}
				else {
					theLink =theLink.next;
				}
			}

		}
		else {
			System.out.println("Empty LinkedList");
		}
		return theLink;
	}



	// Removing an item from the list
	public Link removeLink(String bookName) {

		Link currentLink = firstLink;

		Link previousLink = firstLink;

		while (currentLink.bookName != bookName) {

			if (currentLink.next == null) {

				return null;

			}

			else {
				previousLink =currentLink;

				currentLink =currentLink.next;

			}
		}

		if (currentLink == firstLink) {

			firstLink = firstLink.next;

		}

		else {

			System.out.println("FOUND A MATCH");

			System.out.println("currentLink: " + currentLink);

			System.out.println("firstLink: " + firstLink);

			previousLink.next = currentLink.next;

		}

		return currentLink;
	}

}




