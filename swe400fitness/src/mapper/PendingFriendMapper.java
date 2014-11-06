package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import gateway.KeyGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.IncomingRequestsList;
import domainModel.OutgoingRequestsList;
import domainModel.PendingRequest;

public class PendingFriendMapper implements IncomingFriendFinder, OutgoingFriendFinder, Mapper
{
	PendingFriendGateway	pendingFriendGate;
	PersonGateway			personGate;
	KeyGateway				keyGen;

	/**
	 * Constructor
	 * 
	 * @param gate
	 */
	public PendingFriendMapper()
	{
		pendingFriendGate = new PendingFriendGateway();
		personGate = new PersonGateway();
		keyGen = new KeyGateway();
	}

	

	/**
	 * Insert a new record representing this object into the table.
	 */
	@Override
	public void insert(DomainObject o)
	{
		long inquirerId = ((Friend) o).getInquirerId();
		long recipientId = ((Friend) o).getRecipientId();
		long id = ((Friend) o).getId();
		
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
