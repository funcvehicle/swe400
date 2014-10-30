package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import gateway.KeyGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.IncomingRequestsList;
import domainModel.OutgoingRequestsList;
import domainModel.PendingRequest;

public class PendingFriendMapper implements Mapper
{
	PendingFriendGateway	pendingFriendGate;
	PersonGateway			personGate;
	KeyGateway				keyGen;

	/**
	 * Constructor
	 * 
	 * @param gate
	 */
	public PendingFriendMapper(PendingFriendGateway gate)
	{
		pendingFriendGate = gate;
		personGate = new PersonGateway();
		keyGen = new KeyGateway();
	}
	
	/**
	 * Create a new pending request with a valid id
	 * @param inquirerId
	 * @param recipientId
	 * @param displayName
	 * @return
	 */
	public PendingRequest create(long inquirerId, long recipientId, String displayName)
	{
		long id = keyGen.generateKey();
		PendingRequest request = PendingRequest.createNewPendingRequest(inquirerId, recipientId, id, displayName);
		return request;
	}

	/**
	 * Returns a list of all incoming requests for one individual
	 * 
	 * @param myId
	 * @return
	 */
	public IncomingRequestsList findIncomingRequests(Long myId)
	{
		IncomingRequestsList list = new IncomingRequestsList();
		
		try
		{
			ResultSet myList = pendingFriendGate.findIncoming(myId);
			
			while (myList.next() == true)
			{
				long inquirerId = myList.getLong("inquirerId");
				long relationId = myList.getLong("id");
				ResultSet inquirer = personGate.find(inquirerId);
				inquirer.next();
				String inquirerName = inquirer.getString("displayName");
				PendingRequest pendingFriend = new PendingRequest(inquirerId, myId, relationId, inquirerName);
				list.addIncomingRequest(pendingFriend);
			}
		}
		catch (SQLException e)
		{
			// TODO
			e.printStackTrace();
			list = null;
		}
		
		return list;
	}
	
	public PendingRequest findIncomingRelationshipId(long requesterId, long requesteeId)
	{
		PendingRequest pendingRequest = null;
		try
		{
			ResultSet myList = pendingFriendGate.findIncoming(requesteeId);
			while (myList.next() == true)
			{
				if (requesterId == myList.getLong("inquirerId"))
				{
					long id = myList.getLong("id");
					pendingRequest = new PendingRequest(requesterId, requesteeId, id, "");
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return pendingRequest;
	}
	
	/**
	 * Returns a list of all outgoing requests for one individual
	 * 
	 * @param myId
	 * @return
	 */
	public OutgoingRequestsList findOutgoingRequests(Long myId)
	{
		OutgoingRequestsList list = new OutgoingRequestsList();
		try
		{
			ResultSet myList = pendingFriendGate.findOutgoing(myId);
			
			while (myList.next() == true)
			{
				long pendingRelationshipId = myList.getLong("id");
				long recipientId = myList.getLong("recipientId");
				CachedRowSet recipient = personGate.find(recipientId);
				if(recipient.next())
				{
					String recipientName = recipient.getString("displayName");
					PendingRequest pendingFriend = new PendingRequest(myId, recipientId, pendingRelationshipId, recipientName);
					list.addPerson(pendingFriend);
				}
			}
			
		}
		catch (SQLException e)
		{
			list = new OutgoingRequestsList();
		}
		
		return list;
	}
	
	public long findOutgoingRelationshipId(long requesterId, long requesteeId)
	{
		long id = 0;
		try
		{
			ResultSet myList = pendingFriendGate.findOutgoing(requesterId);
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

	/**
	 * Insert a new record representing this object into the table.
	 */
	@Override
	public void insert(DomainObject o)
	{
		long inquirerId = ((PendingRequest) o).getInquirerId();
		long recipientId = ((PendingRequest) o).getRecipientId();
		long id = ((PendingRequest) o).getId();
		
		if (pendingFriendGate == null)
		{
			System.out.println("fuck");
		}
		
		pendingFriendGate.insert(inquirerId, recipientId, id);
	}

	@Override
	public void update(DomainObject o)
	{
		// Nothing here! Keep it that way.
	}

	/**
	 * Delete the record with the object's given id.
	 */
	@Override
	public void delete(DomainObject o)
	{
		pendingFriendGate.delete(o.getId());
	}

}
