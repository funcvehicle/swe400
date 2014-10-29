package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import gateway.KeyGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.PendingFriend;
import domainModel.IncomingRequestsList;
import domainModel.OutgoingRequestsList;

public class PendingFriendMapper implements Mapper
{
	PendingFriendGateway gate;
	PersonGateway personGate;
	KeyGateway keyGen;
	
	/**
	 * Constructor
	 * @param gate
	 */
	public PendingFriendMapper(PendingFriendGateway gate)
	{
		gate = new PendingFriendGateway();
		personGate = new PersonGateway();
	}
	
	/**
	 * Returns a list of all incoming requests
	 * for one individual
	 * @param myId
	 * @return
	 */
	public IncomingRequestsList findIncomingRequests(Long myId)
	{
		IncomingRequestsList list = new IncomingRequestsList();
		try {
			ResultSet myList = gate.findIncoming(myId);
			while (myList.next() == true)
			{
				long pendingRelationshipId = myList.getLong("id");
				PendingFriend pendingFriend = findInquirer(pendingRelationshipId);
				list.addIncomingRequest(pendingFriend);
			}
			return list;
		}
		catch (SQLException e)
		{
			//TODO
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Finds the person who requested me
	 * @param pendingRelationshipId
	 * @return
	 */
	private Friend findInquirer(long pendingRelationshipId)
	{
		try {
		ResultSet record = gate.findIncoming(pendingRelationshipId);
		long id = record.getLong("inquirerId");
		ResultSet personRecord = personGate.find(id);
		String displayName = personRecord.getString("displayName");
		PendingFriend pendingFriend = new PendingFriend(displayName, id);
		return pendingFriend;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a list of all outgoing requests
	 * for one individual
	 * @param myId
	 * @return
	 */
	public OutgoingRequestsList findOutgoingRequests(Long myId)
	{
		OutgoingRequestsList list = new OutgoingRequestsList();
		try {
			ResultSet myList = gate.findOutgoing(myId);
			while (myList.next() == true)
			{
				long pendingRelationshipId = myList.getLong("id");
				PendingFriend pendingFriend = findRecipient(pendingRelationshipId);
				list.addPerson(pendingFriend);
			}
			return list;
		}
		catch (SQLException e)
		{
			//TODO
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Finds the person whom I requested
	 * @param pendingRelationshipId
	 * @return
	 */
	private Friend findRecipient(long pendingRelationshipId)
	{
		try {
		ResultSet record = gate.findOutgoing(pendingRelationshipId);
		long id = record.getLong("inquirerId");
		ResultSet personRecord = personGate.find(id);
		String displayName = personRecord.getString("displayName");
		PendingFriend pendingFriend = new PendingFriend(displayName, id);
		return pendingFriend;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void insert(DomainObject object)
	{
//		Friend pendingFriend = (Friend) o;
//		String displayName = pendingFriend.getDisplayName();
//		long id = pendingFriend.getId();
//		gate.insert(id, displayName);
		
	}

	@Override
	public void update(DomainObject o)
	{
		// Should do nothing
		
	}

	@Override
	public void delete(DomainObject o)
	{
//		Friend pendingFriend = (Friend) o;
//		long id = pendingFriend.getId();
//		gate.delete(id);
	}

}
