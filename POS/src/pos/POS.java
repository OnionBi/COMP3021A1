package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class POS {
	String name;
	String password;
	String[] userList;
	String[] passwordList;
	static boolean batchMode = false;
	Scanner userScanner;
	Scanner inputScanner;
	
	public static void main(String args[]){
		//if(args[]!=null)batchMode=true;
		new POS();
	} 
	
	POS(){
		// first implement inputMode
		inputScanner = new Scanner(System.in);
		try {
			userScanner = new Scanner(new File("userPasswordFile.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("not find");
		}
		int noOfLine = 0;
		while(userScanner.hasNextLine()){
			noOfLine++;
			System.out.println(userScanner.nextLine());
		}
		System.out.println(noOfLine);
		//userList = new String[10];
		//passwordList = new String[10];
		//registration();
	}
	
	void registration(){
		System.out.println("Welcome to the Point-Of-Sale Registration System");
		System.out.print("Please enter your user name: ");
		name = inputScanner.next();
		
	}
	
	class Log{
		Log(String s){
			System.out.println("<LOG>"+s);
		}
	}
}

