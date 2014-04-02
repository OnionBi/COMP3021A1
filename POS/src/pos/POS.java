package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class POS {
	String name;
	String password;
	static boolean batchMode = false;
	Scanner inputScanner;
	UserList ul;
	
	public static void main(String args[]){
		//if(args[]!=null)batchMode=true;
		new POS();
	} 
	
	POS(){
		inputScanner = new Scanner(System.in);
		ul = new UserList();
		registration();
	}
	
	
	void registration(){
		System.out.println("Welcome to the Point-Of-Sale Registration System");
		do{
			System.out.print("Please enter your user name: ");
			name = inputScanner.next();
		}while(ul.findUserInList(name)==-1);
		
	}
	
	
	
}

class UserList{

	Scanner userScanner;
	private String[] nameList;
	private String[] passwordList;
	private int noOfUser = 0;
	UserList(){
		File userFile = new File("userPasswordFile.txt");
		try {
			userScanner = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			System.out.println("not find");
		}
		
		// this will get the number of user in the userPasswordFile
		while(userScanner.hasNextLine()){
			noOfUser++;
			userScanner.nextLine();
		}
		System.out.println(noOfUser);
		nameList = new String[noOfUser];
		passwordList = new String[noOfUser];
		
		
		userScanner.close();
		try {
			userScanner = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<noOfUser;i++){
			nameList[i]=userScanner.next();
			passwordList[i]=userScanner.next();
		}
	}
	/** find if the user is in the list
	 * 
	 * @param name the userName Entered by user
	 * @return the position of the userName, if not find, return -1;
	 */
	int findUserInList(String name){
		for(int i=0;i<noOfUser;i++){
			if(name.equals(nameList[i]))return i;
		}
		new Log("Invalid user name!");
		return -1;
	}
	
}

class Log{
	Log(String s){
		System.out.println("<LOG> "+s);
	}
}

