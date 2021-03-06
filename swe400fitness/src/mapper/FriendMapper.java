package mapper;
import gateway.FriendGateway;
import gateway.KeyGateway;
import gateway.PersonGateway;

import java.sql.ResultSet;
import java.sql.SQLException;

import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.FriendList;
import domainModel.Person;

public class FriendMapper implements Mapper
{
	private FriendGateway friendGate;
	private PersonGateway personGate;
	private KeyGateway keyGate;
	public FriendMapper(FriendGateway friendGate, PersonGateway personGate)
	{
		this.friendGate = friendGate;
		this.personGate = personGate;
		this.keyGate = new KeyGateway();
	}
	
	public Friend create(String displayName, long id, long currentUserId)
	{
		long relationshipId = keyGate.generateKey();
		Friend f = Friend.createNewFriend(displayName, id, relationshipId, currentUserId);
		return f;
	}
	
	public FriendList findFriends(Long myId)
	{
		FriendList list = new FriendList();
		try 
		{			
			ResultSet myList = friendGate.find(myId);
			while (myList.next() == true)
			{
				Friend friend = findFriend(myList, myId);
				list.addFriend(friend);
			}
			return list;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	
	private Friend findFriend(ResultSet resultSet, long myId)
	{
		try 
		{
			long id = resultSet.getLong("friendId");
			ResultSet personResultSet;
			if (id == myId)
			{
				id = resultSet.getLong("personId");
				personResultSet = personGate.find(resultSet.getLong("personId"));
			}
			else
			{
				personResultSet = personGate.find(resultSet.getLong("friendId"));
			}
			
			personResultSet.next();
			String displayName = personResultSet.getString("displayName");
			Friend friend = new Friend(displayName, id, resultSet.getLong("id"));
			return friend;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public void update(DomainObject object)
	{
		//TODO Should do nothing
	}
	
	public void insert(DomainObject person, DomainObject friend)
	{
		Friend fFriend = (Friend) friend;
		Person pPerson = (Person) person;
		long personID = pPerson.getId();
		long FriendID = fFriend.getId();
		friendGate.create(keyGate.generateKey(),FriendID, personID);
	}
	
	@Override
	public void delete(DomainObject object)
	{
		Friend friend = (Friend) object;
		long id = friend.getRelationshipId();
		friendGate.delete(id);
	}

	@Override
	public void insert(DomainObject object) 
	{
		Friend friend = (Friend) object;
		friendGate.create(friend.getRelationshipId(), friend.getCurrentUserId(), friend.getId());
	}
}

