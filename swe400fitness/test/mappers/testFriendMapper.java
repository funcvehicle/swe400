package mappers;

import static org.junit.Assert.assertEquals;
import gateway.FriendGateway;
import gateway.PersonGateway;

import java.sql.ResultSet;

import mapper.FriendMapper;
import mapper.PersonMapper;
import mockClasses.MockFriendGateway;

import org.junit.Test;

import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.FriendList;
import domainModel.Person;

public class testFriendMapper 
{

	@Test
	public void testCreate()
	{
		PersonGateway pgate = new PersonGateway();
		MockFriendGateway fgate = new MockFriendGateway();
		FriendMapper mapper = new FriendMapper(fgate, pgate);
		String username = "user";
		long friendId = 123;
		long currentID = 456;
		Friend mapfriend = mapper.create(username, friendId, currentID);
		assertEquals(123, mapfriend.getId());
		assertEquals(456, mapfriend.getCurrentUserId());	
	}
	
	@Test
	public void testDelete()
	{
		PersonGateway pgate = new PersonGateway();
		MockFriendGateway fgate = new MockFriendGateway();
		FriendMapper mapper = new FriendMapper(fgate, pgate);
		String username = "Guy";
		long friendId = 123;
		long currentID = 456;
		Friend friend = new Friend(username, friendId, currentID);
		fgate.create(5, friendId, currentID);
		mapper.delete(friend);
		FriendList list = mapper.findFriends(currentID);
		assertEquals("", list.toString());
	}
	
}
