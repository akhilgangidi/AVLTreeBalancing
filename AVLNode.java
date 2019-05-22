//AVLNode object declaration
public class AVLNode 
{
	String key;
	Book value;
	int height;
	AVLNode leftPtr;
	AVLNode rightPtr;
	
	public AVLNode(Book book, String isbn)
	{
		this.key = isbn;
		this.value = book;
		this.height = 0;
		this.leftPtr = null;
		this.rightPtr = null;
	}



	
}
