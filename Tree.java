//Tree object declaration
public class Tree 
{
	private AVLNode root; //defines root as AVLNode
	
	//constructor makes root null
	public Tree()
	{
		this.root = null; 
	}
	//insert function makes root call balance function
	public void insert(String isbn, Book book)
	{
		this.root = balance(isbn, book, this.root);
	}
	//to get height of tree
	private int height(AVLNode n)
	{
		if(n != null)
			return n.height;
		
		else
			return -1;
	}
	//to get the max value
	public int max(int l, int r)
	{
		if(l <= r)
			return r;
		
		else
			return l;
	}
	//LeftRight rotation implementation
	public AVLNode leftRightRotation(AVLNode n)
	{
		n.leftPtr = rightRotation(n.leftPtr);
		return leftRotation(n);
	}
	//RightLeft rotation implementation
	public AVLNode rightLeftRotation(AVLNode n)
	{
		n.rightPtr = leftRotation(n.rightPtr);
		return rightRotation(n);
	}
	//Left rotation implementation
	public AVLNode leftRotation(AVLNode o)
	{
		AVLNode n = o.leftPtr;
		
		o.leftPtr = n.rightPtr;
		n.rightPtr = o;
		
		o.height = max(height(o.leftPtr), height(o.rightPtr)) + 1;
		n.height = max(height(n.leftPtr), height(n.rightPtr)) + 1;
		
		
		return n;
	}
	//right rotation implementation
	public AVLNode rightRotation(AVLNode n)
	{
		AVLNode o = n.rightPtr;
		
		n.rightPtr = o.leftPtr;
		o.leftPtr = n;
		
		o.height = max(height(o.leftPtr), height(o.rightPtr)) + 1;
		n.height = max(height(n.leftPtr), height(n.rightPtr)) + 1;
		
		return o;
	}
	//Self-balancing tree implementation
	private AVLNode balance(String isbn, Book book, AVLNode n)
	{
		//defines isbnVal as a double and turns string to a double
		double isbnVal = Double.parseDouble(isbn);
		
		//if node is null, creates new node as assigns it to n
		if (n == null)
			n = new AVLNode(book, isbn);
		
		//defines keyVal as double and converts string to double
		double keyVal = Double.parseDouble(n.key);
		
		//if isbnVal is greater than keyVal
		if(isbnVal > keyVal)
		{
			//right node of n calls balance recursively
			n.rightPtr = balance(isbn, book, n.rightPtr);
			//if the height of rightPtr - leftPtr is equal to 2, imbalance occurs
			if(height(n.rightPtr) - height(n.leftPtr) == 2)
			{
				System.out.print("Imbalance occurred at inserting ISBN " + isbn);
				double rightPtrVal = Double.parseDouble(n.rightPtr.key); //turns string to double and 
																		 //assign it to rightPtrVal
				//if isbnVal is less than rightPtrVal
				if (isbnVal < rightPtrVal)
				{ 
					System.out.print("; fixed in RightLeft Rotation\n");
					n = rightLeftRotation(n); //calls rightLeftRotation with argument n and assigns it to n
				}
				else
				{
					System.out.print("; fixed in Right Rotation\n");
					n = rightRotation(n); //calls rightRotation with argument n and assigns it to n
				}
			}
		}
		//if isbnVal is less than keyVal
		else if(isbnVal < keyVal)
		{
			//calls balance method recursively and assigns it to n.leftPtr
			n.leftPtr = balance(isbn, book, n.leftPtr);
			if(height(n.leftPtr) - height(n.rightPtr) == 2)
			{
				System.out.print("Imbalance occurred at inserting ISBN " + isbn);
				double leftPtrVal = Double.parseDouble(n.leftPtr.key); //turns string to double and 
				 													   //assign it to rightPtrVal
				//if isbnVal is more than leftPtrVal
				if(isbnVal > leftPtrVal)
				{
					System.out.print("; fixed in LeftRight Rotation\n");
					n = leftRightRotation(n); //calls leftRightRotation with argument n and assigns it to n
				}
				else
				{
					System.out.print("; fixed in Left Rotation\n");
					n = leftRotation(n); //calls leftRotation with argument n and assigns it to n
				}
			}
		}
		//assigns the max of leftPtr and rightPtr + 1 and assigns it to n.height
		n.height = max(height(n.leftPtr), height(n.rightPtr)) + 1;
		
		//return n
		return n;
	}
}
