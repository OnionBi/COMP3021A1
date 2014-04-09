package pos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Log{
	static FileWriter out;
	static File outputFile = new File("logAndSales.txt");
	static {
		outputFile.delete();
		try {
			outputFile.createNewFile();
			out = new FileWriter("logAndSales.txt",true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	Log(String s){
		
		try{
			out = new FileWriter("logAndSales.txt",true);
	        out.write(s+System.getProperty( "line.separator"));
	        out.close();
		}catch(Exception e){
			
		}
		System.out.println("<LOG> "+s);
	}
}