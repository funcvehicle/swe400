package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import Registry.FinderRegistry;

import gateway.FriendGateway;
import gateway.KeyGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.FriendList;
import domainModel.IncomingRequestsList;
import domainModel.OutgoingRequestsList;
import domainModel.Person;

/**
 * 
 * @author Connor Fox
 * 
 */
public class PersonMapper implements PersonFinder, Mapper
{
	PersonGateway	personGate;
	FriendGateway 	friendGate;
	PendingFriendGateway pendingFriendGate;
	KeyGateway		keyGen;

	public PersonMapper()
	{
		this.personGate = new PersonGateway();
		this.friendGate = new FriendGateway();
		this.pendingFriendGate = new PendingFriendGateway();
		keyGen = new KeyGateway();
	}

	/**
	 * Find a person with the given id.
	 * 
	 * @param id
	 * @return
	 */
	public Person find(long id)
	{
		ResultSet rs = personGate.find(id);

		try
		{
			if (rs.next() == false)
				return null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return load(rs);
	}

	/**
	 * Find a person with the given username.
	 * @param username
	 * @return
	 */
	public Person find(String username)
	{
		ResultSet rs = personGate.find(username);

		try
		{
			if (rs.next() == false)
				return null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return load(rs);
	}

	/**
	 * Construct a Person object from a ResultSet.
	 * 
	 * @param rs result to use to construct the person
	 * @return a constructed person object
	 */
	private Person load(ResultSet rs)
	{
		String userName, displayName, password;
		try
		{
			long id = rs.getLong("id");
			userName = rs.getString("userName");
			displayName = rs.getString("displayName");
			password = rs.getString("password");
			Person result = new Person(userName, displayName, password);
			result.setId(id);
			
			//load the various friend lists
			FriendList myFriends = FinderRegistry.friendFinder().findFriends(id);
			IncomingRequestsList myIncoming = FinderRegistry.incomingFriendFinder().findIncomingRequests(id);
			OutgoingRequestsList myOutgoing = FinderRegistry.outgoingFriendFinder().findOutgoingRequests(id);
			
			result.setFriendList(myFriends);
			result.setIncomingRequests(myIncoming);
			result.setOutgoingRequests(myOutgoing);
			
			return result;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Update the person's record in the people table.
	 */
	@Override
	public void update(DomainObject o)
	{
		Person p = (Person) o;
		String userName = p.getUserName();
		String password = p.getPassword();
		String displayName = p.getDisplayName();
		long id = p.getId();
		
		personGate.update(id, userName, password, displayName);
	}

	/**
	 * Insert a person into the people table.
	 */
	@Override
	public void insert(DomainObject o)
	{
		Person p = (Person) o;
		long id = keyGen.generateKey();
		String userName = p.getUserName();
		String password = p.getPassword();
		String displayName = p.getDisplayName();

		personGate.insert(id, userName, displayName, password);
	}

	/**
	 * Delete the person from the people table
	 */
	@Override
	public void delete(DomainObject o)
	{
		Person p = (Person) o;
		long id = p.getId();

		personGate.delete(id);
	}
}
