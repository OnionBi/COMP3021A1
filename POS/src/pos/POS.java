package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class POS {
	static boolean batchMode = false;
	Scanner inputScanner;
	UserList ul;
	ProductList pl;
	
	public static void main(String args[]){
		//if(args[]!=null)batchMode=true;
		POS thePOS = new POS();
		thePOS.registration();
		thePOS.purchasing();
	} 
	
	POS(){
		inputScanner = new Scanner(System.in);
		ul = new UserList();
		pl = new ProductList();
		
	}
	
	void registration(){
		String name;
		String password;
		int position;
		System.out.println("Welcome to the Point-Of-Sale Registration System");
		do{
			System.out.print("Please enter your user name: ");
			name = inputScanner.nextLine();
			position=ul.findUserInList(name);
		}while(position==-1);
		do{
			System.out.print("Please enter your password: ");
			password = inputScanner.nextLine();
		}while(ul.checkPassword(position, password)==false);
		System.out.println("");
	}
	
	void purchasing(){
		String id;
		int number;
		System.out.println("Please enter a list of purchasing-product ID and number");
		System.out.println("--------------------------------------------------------");
		int position;
		do{
			System.out.print("Please enter the product ID: ");
			id = inputScanner.nextLine();
			position=pl.purchase(id);
			if(position!=-2&&position!=-1){
				System.out.print("Product name is "+pl.getName(position)+", enter purchase number: " );
				number=Integer.parseInt(inputScanner.nextLine());
			}
		}while(position!=-1);
		// when 
		
		
		
	}
}

abstract class List{
	protected int noOfElements;
	protected Scanner scanner;
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

class UserList extends List{

	private String[] nameList;
	private String[] passwordList;
	private static File userFile = new File("userPasswordFile.txt");
	UserList(){
		super(userFile);
		nameList = new String[noOfElements];
		passwordList = new String[noOfElements];
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
		return -1;
	}
	
	boolean checkPassword(int i,String password){
		if(passwordList[i].equals(password)){
			new Log("Log in success!");
			return true;
		}
		else{
			new Log("invalid password for "+nameList[i]);
			return false;
		}
	}
	
	@Override
	void assignAttribute() {
		
		try {
			scanner = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<noOfElements;i++){
			nameList[i]=scanner.next();
			passwordList[i]=scanner.next();
		}
	}
}


class ProductList extends List{

	private String[] idList;
	private String[] nameList;
	private double[] unitPriceList;
	private int[]    stockList;
	private static File productFile = new File("productListFile.txt");
	private int[] purchasedNumberList;
	
	ProductList(){
		super(productFile);
		idList = new String[noOfElements];
		nameList = new String[noOfElements];
		unitPriceList = new double[noOfElements];
		stockList = new int[noOfElements];
		purchasedNumberList = new int[noOfElements];
		assignAttribute();
	}
	
	public String getName(int i){
		return nameList[i];
	}
	
	public int purchase(String id){
		if(id.equals("c")){
			return -1;
		}
		for(int i=0;i<noOfElements;i++){
			if(id.equals(idList[i]))return i;
		}
		new Log("item not exsists!");
		return -2;
	}
	
	@Override
	void assignAttribute() {
		
		try {
			scanner = new Scanner(productFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<noOfElements;i++){
			idList[i]=scanner.next();
			nameList[i]=scanner.next();
			unitPriceList[i]=Double.parseDouble(scanner.next());
			stockList[i]=Integer.parseInt(scanner.next());
			purchasedNumberList[i]=0;
		}
	}
}

class Log{
	Log(String s){
		System.out.println("<LOG> "+s);
	}
}

