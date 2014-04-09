package pos;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.SequenceInputStream;

import org.junit.Before;
import org.junit.Test;

public class POSTest {
	POS thePOS;
	@Before
	public void initPOS(){
		thePOS = new POS();
	}
	
	@Test
	public void testMain() {
		POS.main(null);
	}
	
	@Test
	public void testMainWithArgs() {
		String s[] = new String[1]; 
		s[0] = "inputFile.txt";
		POS.main(s);
	}
	
	@Test
	public void testRegistration() {
		thePOS.registration();
	}

	@Test
	public void testPurchasing() {
		thePOS.purchasing();
	}

	@Test
	public void testHandleSaleID() {
		assertEquals("handle Sale ID",thePOS.handleSaleID(),"SalesID00");
	}

	@Test
	public void testEnterProduct() {
		thePOS.enterProduct();
	}

	@Test
	public void testPayment() {
		thePOS.payment();
	}

	@Test
	public void testGetCommand() {
		thePOS.getCommand();
	}

}
