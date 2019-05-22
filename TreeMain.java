import java.io.*;

public class TreeMain 
{

	public static void main(String[] args) throws IOException 
	{
		String fileName = "books.txt"; //name of the input file
		String input; //defines input
		
		try
		{
			BufferedReader fileInput = new BufferedReader(new FileReader(fileName)); //defines the object that reads files
		
			Tree tree = new Tree(); //defines new tree
		
			while((input = fileInput.readLine()) != null) //iterates until end of file is reached
			{
				String split[] = input.split(" "); //splits the read line where spaces occur
			
				String isbn = split[0]; //assigns isbn to the first split
				if(isbn.contains("-")) //if the isbn has -
				{
					String[] isbnSplit = isbn.split("-"); //split the string where - occurs 
				
					isbn = isbnSplit[0] + isbnSplit[1]; //concatenate the strings
				}
				String title = split[1]; //assigns second to title
				String author = split[2]; //assigns third to title
			
				Book book = new Book(title, author); //defines new book object with title and author from file
				System.out.println("Inserting ISBN " + isbn); 
				tree.insert(isbn, book); //calls insert function from Tree class
			}
			fileInput.close(); //closes fileInput
			System.out.println();
			System.out.println("Tree is now balanced!"); //displays to the user that tree is balanced
		}
		catch(IOException ex)
		{
			System.out.println("File not found!");
		}
		
	}

}
