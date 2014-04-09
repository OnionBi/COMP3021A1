package pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ProductList extends List{

	private String[] idList;
	private String[] nameList;
	private double[] unitPriceList;
	private int[]    stockList;
	private static File productFile = new File("productListFile.txt");
	private int[] purchasedNumberList;
	private int sum=0;
	private boolean batchMode=false;
	
	ProductList(boolean mode){
		super(productFile);
		idList = new String[noOfElements];
		nameList = new String[noOfElements];
		unitPriceList = new double[noOfElements];
		stockList = new int[noOfElements];
		purchasedNumberList = new int[noOfElements];
		assignAttribute();
		batchMode=mode;
	}
	
	public String getName(int i){
		if(i>=noOfElements||i<0)return null;
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
		if(batchMode)System.exit(0);
		return -2;
	}
	
	/** 
	 * increase the purchased number of item i
	 * @param i
	 * @param number
	 */
	public void incPurchase(int i, int number){
		    purchasedNumberList[i]+=number;
		    sum+=purchasedNumberList[i]*unitPriceList[i];
	}
	
	public void printList(){
		System.out.println("Purchasing-product list:");
		for(int i=0;i<noOfElements;i++){
			if(purchasedNumberList[i]>0){
				System.out.println(nameList[i]+" * "+purchasedNumberList[i]+" = $"+purchasedNumberList[i]*unitPriceList[i]);
				
			}
		}
		System.out.println("");
		System.out.println("The total price is $"+sum);
		System.out.print("Please pay with cash, received cash (or cancel by entering '0')$: ");
	}
	
	public boolean paying(float Money){
		if(Money<sum){
			return false;
		}
		else{
			System.out.println("Change: $"+(Money-sum));
			return true;
		}
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