package gateway;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import recordSet.RecordSet;

public class testFriend {

	FriendGateway friendGateway;
	RecordSet dummySet;
	@Before
	public void setUp()
	{
		this.friendGateway = new FriendGateway();
	}
	@Test
	public void testCreateFindExistsAndDelete() {
		
		assertFalse(friendGateway.create("ecksfuckindee", "mydik") == SQLEnum.FAILED_SQL_ERROR);
		assertFalse(friendGateway.find("ecksfuckindee") instanceof NullSet);
		assertFalse(friendGateway.delete("ecksfuckindee", "mydik") == SQLEnum.FAILED_SQL_ERROR);
	}
	@Test
	public void testFindDoesNotExist()
	{
		assertTrue(friendGateway.find("inval!d") instanceof NullSet);
	}
	@Test
	public void createAlreadyExists()
	{
		friendGateway.create("ecksfuckindee", "mydik");
		assertTrue(friendGateway.create("ecksfuckindee", "mydik") == SQLEnum.EXISTS);
	}

}
