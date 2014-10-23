package mapper;
import gateway.FriendGateway;
import gateway.PersonGateway;
import java.sql.ResultSet;
import java.sql.SQLException;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.FriendList;

/*
 * Author: Hayden Cook
 */
public class FriendMapper implements Mapper
{
	private FriendGateway friendGate;
	private PersonGateway personGate;
	
	public FriendMapper(FriendGateway friendGate, PersonGateway personGate)
	{
		this.friendGate = friendGate;
		this.personGate = personGate;
	}
	
	public FriendList findFriends(Long myId)
	{
		FriendList list = new FriendList();
		try 
		{			
			ResultSet myList = friendGate.find(myId);
			while (myList.next() == true)
			{
				long relationshipId = myList.getLong("id");
				Friend friend = findFriend(relationshipId);
				list.addFriend(friend);
			}
			return list;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
//	public Friend find(Long relationshipId) TODO Not really sure why this method is here, it may be significant
//	{
//		ResultSet record = friendGate.find(relationshipId);
//		try
//		{
//			record.next();
//		} catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		return loadOne(record);
//	}
	
	private Friend findFriend(Long relationshipId)
	{
		try 
		{
			ResultSet friendResultSet = friendGate.find(relationshipId);
			long id = friendResultSet.getLong("friendId");
			ResultSet personResultSet = personGate.find(id);
			String displayName = personResultSet.getString("displayName");
			Friend friend = new Friend(displayName, id);
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

