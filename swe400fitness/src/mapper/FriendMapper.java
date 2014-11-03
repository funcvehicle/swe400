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

public class FriendMapper implements FriendFinder, Mapper
{
	private FriendGateway friendGate = new FriendGateway();
	private PersonGateway personGate = new PersonGateway();
	private KeyGateway keyGate = new KeyGateway();
	
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
		//Should do nothing
	}
	
	/**
	 * Insert a friend object as a record in the database
	 * @param person the 
	 * @param friend
	 */
	public void insert(DomainObject person, DomainObject friend)
	{
		long id = keyGate.generateKey();
		Friend fFriend = (Friend) friend;
		Person pPerson = (Person) person;
		long personID = pPerson.getId();
		long friendID = fFriend.getId();
		
		friendGate.create(id, friendID, personID);
	}
	
	/**
	 * Delete a friend record based on the friend object's id
	 */
	@Override
	public void delete(DomainObject object)
	{
		Friend friend = (Friend) object;
		long id = friend.getId();
		
		friendGate.delete(id);
	}

//	@Override
//	public void insert(DomainObject object) 
//	{
//		Friend friend = (Friend) object;
//		friendGate.create(friend.getId(), friend.getCurrentUserId(), friend.getId());
//	}


	@Override
	public Friend find(long id)
	{
		ResultSet record = friendGate.find(id);
		Friend friend = new Friend(null, id);
		
		return friend;
	}


	@Override
	public void insert(DomainObject o)
	{
		// TODO Auto-generated method stub
		
	}
}

