package pos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserListTest {
	UserList testUL;
	@Before
	public void initTest(){
		testUL = new UserList(false);
	}
	
	@Test
	public void testFindUserInListTrue() {
		assertTrue("user in the list",testUL.findUserInList("chunlin")>=0);
	}
	
	@Test
	public void testFindUserInListFalse() {
		assertTrue("user not in the list",testUL.findUserInList("lala")==-1);
	}

	@Test
	public void testCheckPasswordTrue() {
		assertTrue("password right",testUL.checkPassword(0, "chunlin"));
	}
	
	@Test
	public void testCheckPasswordFalse() {
		assertFalse("password right",testUL.checkPassword(0, "lala"));
	}

}
