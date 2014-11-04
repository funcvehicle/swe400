package mappers;

import static org.junit.Assert.assertEquals;
import mapper.PendingFriendMapper;
import mockClasses.MockPendingFriendGateway;

import org.junit.Test;

import domainModel.Friend;
import domainModel.IncomingRequestsList;
import domainModel.PendingRequest;

public class testPendingFriendMapper 
{

	//Tests the ability to create a pending request
	@Test
	public void testCreate()
	{
		MockPendingFriendGateway pfgate = new MockPendingFriendGateway();
		PendingFriendMapper mapper = new PendingFriendMapper(pfgate);
		String username = "user";
		long friendId = 123;
		long currentID = 456;
		PendingRequest mapfriend = mapper.create(currentID, friendId, username);
		assertEquals(123, mapfriend.getRecipientId());
		assertEquals(456, mapfriend.getInquirerId());
	}
	
	//Tests the ability to delete a pending request
	@Test
	public void testDelete()
	{
		MockPendingFriendGateway pfgate = new MockPendingFriendGateway();
		PendingFriendMapper mapper = new PendingFriendMapper(pfgate);
		String username = "Guy";
		long friendId = 123;
		long currentID = 456;
		Friend friend = new Friend(username, friendId, currentID);
		pfgate.create(5, friendId, currentID);
		mapper.delete(friend);
		IncomingRequestsList list = mapper.findIncomingRequests(currentID);
		assertEquals("", list.toString());
	}
}
