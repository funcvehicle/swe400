package gateway;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testPersonGateway {

	PersonGateway gateway;
	@Before
	public void before()
	{
		this.gateway = new PersonGateway();
	}
	@Test
	public void testFindExistsAndDelete() {
		
		gateway.insert(1, "farts", "farts", "farts");
		assertFalse(gateway.find(1) instanceof NullSet);
		assertTrue(gateway.delete(1) == SQLEnum.SUCCESS);
	}
	@Test
	public void testFindDNE()
	{
		assertTrue(gateway.find(234123423) instanceof NullSet);
	}
	@Test
	public void testDeleteDNE()
	{
		assertTrue(gateway.delete(234123412) == SQLEnum.DOES_NOT_EXIST);
	}
}
