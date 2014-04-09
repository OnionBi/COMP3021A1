package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class POS {
	private static boolean batchMode = false;
	private static String inputFile;
	private Scanner inputScanner;
	private UserList ul;
	private ProductList pl;
	private static int saleID=0;
	
	public static void main(String args[]){
		//System.out.println();
		try{
			inputFile=args[0];
			batchMode=true;
		}catch(Exception e){
			batchMode=false;
		}
		POS thePOS = new POS();
		try{
			thePOS.registration();
			thePOS.purchasing();
		}catch (NoSuchElementException e){
			System.out.println("");
			new Log("No Command Found!");
		}
	} 
	
	POS(){
		if(!batchMode)inputScanner = new Scanner(System.in);
		else
			try {
				inputScanner=new Scanner(new File(inputFile));
			} catch (FileNotFoundException e) {
				System.out.println("File NOT Find!");
				System.exit(0);
			}
		ul = new UserList(batchMode);
		
	}
	
	void registration() throws NoSuchElementException{
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
	
	void purchasing() throws NoSuchElementException {
		System.out.println("---------------------------------------------------");
		System.out.println("Welcome to the Electronic-Sales Counter!");
		
		while(true){
			if(getCommand()==2)break;
			pl = new ProductList(batchMode);
			enterProduct();
			System.out.println("--------------------------------------------------------");
			pl.printList();
			payment();
			saleID++;
		}
		new Log("logoff success!");
	}
	
	String handleSaleID(){
		if(saleID<10)return "SalesID0"+saleID;
		else return "SalesID"+saleID;
	}
	
	void enterProduct() throws NoSuchElementException {
		String id;
		int number = 0;
		System.out.println("Please enter a list of purchasing-product ID and number");
		System.out.println("--------------------------------------------------------");
		int position;
		do{
			System.out.print("Please enter the product ID: ");
			id = inputScanner.nextLine();
			position=pl.purchase(id);
			if(position!=-2&&position!=-1){
				boolean validInput=false;
				while(!validInput){
					System.out.print("Product name is "+pl.getName(position)+", enter purchase number: " );
					try{
						number=Integer.parseInt(inputScanner.nextLine());
					}catch(Exception e){
						number=-1;
					}
					if(number>=0)validInput=true;
					else {
						new Log("Incorrect Number");
						if(batchMode)System.exit(0);
					}
				}
				pl.incPurchase(position, number);
			}
		}while(position!=-1);
	}
	
	void payment() throws NoSuchElementException {
		while(true){
			float paid = 0;
			boolean validInput=false;
			while(!validInput){
				try{
					paid=Float.parseFloat(inputScanner.nextLine());
				}catch(NoSuchElementException e){
					throw e;
				}catch(Exception e){
					paid=-1;
				}
				if(paid>=0)validInput=true;
				else{
					new Log("Invalid Input");
					if(batchMode)System.exit(0);
				}
			}
			if(paid==0.0){
				new Log(handleSaleID()+" cancelled!");
				break;
			}
			else if(pl.paying(paid))break;
			else {
				new Log("Sorry, cash not enough! ");
				if(batchMode)System.exit(0);
			}
		}
	}
	
	int getCommand() throws NoSuchElementException {
		int command=0;
		while(command!=1&&command!=2){
			System.out.print("Please enter '1' to record sales or '2' to exit:");
			try{
				command=Integer.parseInt(inputScanner.nextLine());
			}catch(Exception e){
				command=0;
			}
			if(command!=1&&command!=2){
				new Log("Invalid command!");
				if(batchMode)System.exit(0);
			}
		}
		return command;
	}
}