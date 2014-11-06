package mapper;
import gateway.FriendGateway;
import gateway.KeyGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.FriendList;
import domainModel.IncomingRequestsList;
import domainModel.OutgoingRequestsList;
import domainModel.Person;

public class FriendMapper implements FriendFinder, IncomingFriendFinder, OutgoingFriendFinder, Mapper
{
	private FriendGateway friendGate;
	private PersonGateway personGate;
	private PendingFriendGateway pendingGate;
	private KeyGateway keyGate;
	
	public FriendMapper()
	{
		friendGate = new FriendGateway();
		personGate = new PersonGateway();
		pendingGate = new PendingFriendGateway();
		keyGate = new KeyGateway();
	}
	
	@Override
	public FriendList findFriends(Long myId)
	{
		FriendList list = new FriendList();
		try 
		{			
			ResultSet myList = friendGate.findAllForUser(myId);
			while (myList.next() == true)
			{
				Friend friend = loadFriend(myList, myId);
				list.addFriend(friend);
			}
			return list;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * Load a single friend from a result set of friends
	 * @param resultSet
	 * @param myId
	 * @return
	 */
	private Friend loadFriend(ResultSet resultSet, long myId)
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
			Friend friend = new Friend(displayName, id, resultSet.getLong("id"), false);
			
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
		boolean pending = fFriend.getPending();
		
		//Use the appropriate gateway for pending or not pending
		if (pending)
			pendingGate.insert(personID, friendID, id);
		else
			friendGate.insert(id, friendID, personID);
	}
	
	/**
	 * Delete a friend record based on the friend object's relation id
	 */
	@Override
	public void delete(DomainObject object)
	{
		Friend friend = (Friend) object;
		long rID = friend.getRelationId();
		
		if (friend.getPending())
			pendingGate.delete(rID);
		else
			friendGate.delete(rID);
	}

	@Override
	public void insert(DomainObject object) 
	{
		Friend friend = (Friend) object;
		
		long key = keyGate.generateKey();
		if (friend.getPending())
			pendingGate.insert(friend.getOwnerId(), friend.getId(), key);
		else
			friendGate.insert(key, friend.getOwnerId(), friend.getId());
	}
	
	/**
	 * Returns a list of all incoming requests for one individual
	 * 
	 * @param myId
	 * @return
	 */
	@Override
	public IncomingRequestsList findIncomingRequests(long id)
	{
		IncomingRequestsList list = new IncomingRequestsList();
		
		try
		{
			ResultSet myList = pendingGate.findIncoming(id);
			
			while (myList.next() == true)
			{
				long inquirerId = myList.getLong("inquirerId");
				long relationId = myList.getLong("id");
				ResultSet inquirer = personGate.find(inquirerId);
				inquirer.next();
				String inquirerName = inquirer.getString("displayName");
				Friend request = new Friend(inquirerName, id, relationId, inquirerId, true);
				list.addIncomingRequest(request);
			}
		}
		catch (SQLException e)
		{
			System.err.println("An error occured in findIncomingRequests while processing userID " + id + ".");
			list = null;
		}
		
		return list;
	}
	
//	public Friend findIncomingRelationshipId(long requesterId, long requesteeId)
//	{
//		Friend pendingRequest = null;
//		try
//		{
//			ResultSet myList = pendingGate.findIncoming(requesteeId);
//			while (myList.next() == true)
//			{
//				if (requesterId == myList.getLong("inquirerId"))
//				{
//					long id = myList.getLong("id");
//					pendingRequest = new Friend(requesterId, id, "", true);
//				}
//			}
//		} catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		return pendingRequest;
//	}
	
	/**
	 * Returns a list of all outgoing requests for one individual
	 * 
	 * @param myId
	 * @return
	 */
	@Override
	public OutgoingRequestsList findOutgoingRequests(long id)
	{	
		OutgoingRequestsList list = new OutgoingRequestsList();
		try
		{
			ResultSet myList = pendingGate.findOutgoing(id);
			
			while (myList.next() == true)
			{
				long pendingRelationshipId = myList.getLong("id");
				long recipientId = myList.getLong("recipientId");
				CachedRowSet recipient = personGate.find(recipientId);
				if(recipient.next())
				{
					String recipientName = recipient.getString("displayName");
					Friend pendingFriend = new Friend(recipientName, pendingRelationshipId, recipientId, id, true);
					list.addOutgoingRequest(pendingFriend);
				}
			}
			
		}
		catch (SQLException e)
		{
			System.err.println("An error occured in findOutgoingRequests while processing userID " + id + ".");
		}
		
		return list;
	}
	
	public long findOutgoingRelationshipId(long requesterId, long requesteeId)
	{
		long id = 0;
		try
		{
			ResultSet myList = pendingGate.findOutgoing(requesterId);
			while (myList.next() == true)
			{
				if (requesteeId == myList.getLong("recipientId"))
				{
					id = myList.getLong("id");
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}

//	@Override
//	public Friend find(long relationId)
//	{
//		ResultSet record = friendGate.find(relationId);
//		long friendId = record.getLong("id");
//		long 
//		Friend friend = new Friend(null, relationId);
//		
//		return friend;
//	}
}

