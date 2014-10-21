package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import gateway.FriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.FriendList;
import domainModel.Person;

/*
 * Author: Hayden Cook
 */
public class FriendMapper implements Mapper
{
	FriendGateway friendGate;
	PersonGateway personGate;
	
	public FriendMapper(FriendGateway friendGate)
	{
		friendGate = new FriendGateway();
		personGate = new PersonGateway();
	}
	
	public Friend findAll(Long myId)
	{
		FriendList list = new FriendList();
		try {			
			ResultSet myList = friendGate.find(myId);
			myList.next();
			while (myList.next() == true)
			{
				long relationshipId = myList.getLong("id");
				Friend friend = find(relationshipId);
				list.addFriend(friend);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	public Friend find(Long relationshipId)
	{
		ResultSet record = friendGate.find(relationshipId);
		try
		{
			record.next();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return loadOne(record);
	}
	
	private Friend loadOne(ResultSet record)
	{
		try {
		long id = record.getLong("friendId");
		ResultSet friendSet = personGate.find(id);
		String displayName = friendSet.getString("displayName");
		Friend friend = new Friend(displayName, id);
		return friend;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	@Override
	public void update(DomainObject object)
	{
		
	}
	
	@Override
	public void insert(DomainObject object)
	{
		//Friend friend = (Friend) object;
		//String displayName = friend.getDisplayName();
		//long id = friend.getId();
		//friendGate.insert(id, displayName);
		// TODO
	}
	
	@Override
	public void delete(DomainObject object)
	{
		//Friend friend = (Friend) object;
		//long id = friend.getId();
		// TODO Check with Gateway
		//friendGate.delete(id);
	}
}

