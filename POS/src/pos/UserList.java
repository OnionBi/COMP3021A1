package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserList extends List{

	private String[] nameList;
	private String[] passwordList;
	private boolean batchMode;
	private static File userFile = new File("userPasswordFile.txt");
	UserList(boolean mode){
		super(userFile);
		nameList = new String[noOfElements];
		passwordList = new String[noOfElements];
		batchMode=mode;
		assignAttribute();
	}
	/** find if the user is in the list
	 * 
	 * @param name the userName Entered by user
	 * @return the position of the userName, if not find, return -1;
	 */
	int findUserInList(String name){
		for(int i=0;i<noOfElements;i++){
			if(name.equals(nameList[i]))return i;
		}
		new Log("Invalid user name!");
		if(batchMode)System.exit(0);
		return -1;
	}
	
	boolean checkPassword(int i,String password){
		if(passwordList[i].equals(password)){
			new Log("Log in success!");
			return true;
		}
		else{
			new Log("invalid password for "+nameList[i]);
			if(batchMode)System.exit(0);
			return false;
		}
	}
	
	@Override
	void assignAttribute() {
		
		try {
			scanner = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			
		}
		
		for(int i=0;i<noOfElements;i++){
			nameList[i]=scanner.next();
			passwordList[i]=scanner.next();
		}
	}
}