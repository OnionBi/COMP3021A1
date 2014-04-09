package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class List{
	protected int noOfElements;
	protected Scanner scanner;
	
	/**
	 * Build the list using specific list file
	 * and initialize the scanner using this file
	 * @param file The file to be built
	 */
	List(File file){
		noOfElements = 0;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("not find");
		}
		
		// this will get the number of user in the userPasswordFile
		while(scanner.hasNextLine()){
			noOfElements++;
			scanner.nextLine();
		}
		scanner.close();
	}
	abstract void assignAttribute();
}
