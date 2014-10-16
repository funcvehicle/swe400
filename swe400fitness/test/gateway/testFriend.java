package gateway;

import static org.junit.Assert.*;
import gateway.FriendGateway;
import gateway.NullSet;
import gateway.SQLEnum;

import org.junit.Before;
import org.junit.Test;


public class testFriend {

	FriendGateway friendGateway;
	@Before
	public void setUp()
	{
		this.friendGateway = new FriendGateway();
	}
	@Test
	public void testCreateFindExistsAndDelete() {
		
		assertFalse(friendGateway.create(1, 2) == SQLEnum.FAILED_SQL_ERROR);
		assertFalse(friendGateway.find(1) instanceof NullSet);
		assertFalse(friendGateway.delete(1, 2) == SQLEnum.FAILED_SQL_ERROR);
	}
	@Test
	public void testFindDoesNotExist()
	{
		assertTrue(friendGateway.find(-1) instanceof NullSet);
	}
	@Test
	public void createAlreadyExists()
	{
		friendGateway.create(1, 2);
		assertTrue(friendGateway.create(1, 2) == SQLEnum.EXISTS);
	}

}
