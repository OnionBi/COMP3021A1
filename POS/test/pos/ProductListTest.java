package pos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductListTest {
	ProductList testPL;
	@Before
	public void initTest(){
		testPL = new ProductList(false);
		testPL.incPurchase(1,2);
	}
	
	@Test
	public void testGetNameTrue() {
		assertEquals("get name of within the range",testPL.getName(0),"pencil");
	}
	
	@Test
	public void testGetNameFalse() {
		assertEquals("get name of within the range",testPL.getName(20),null);
	}

	@Test
	public void testPurchaseNormal() {
		assertEquals("purchase in normal process",testPL.purchase("ID001"),0);
	}
	
	@Test
	public void testPurchaseCancel() {
		assertEquals("Cancel purchase",testPL.purchase("c"),-1);
	}
	
	@Test
	public void testPurchaseNotExists() {

		assertEquals("item not exist",testPL.purchase("abcd"),-2);
	}
	
	@Test
	public void testPrintList() {
		testPL.printList();
	}
	
	@Test
	public void testPayingEnough() {
		assertTrue("Enough Money",testPL.paying(400));
	}
	
	@Test
	public void testPayingNotEnough() {
		assertFalse("Not Enough Money",testPL.paying(0));
	}

}
