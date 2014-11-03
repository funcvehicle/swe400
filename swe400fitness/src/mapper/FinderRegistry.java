package mapper;

import gateway.FriendGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.PendingRequest;
import domainModel.Person;

/**
 * 
 * @author Connor Fox
 *
 * Allows domain logic to use a common set of finders to find needed records as objects
 */
public class FinderRegistry
{
	public static ThreadLocal<MapperRegistry> current = new ThreadLocal<MapperRegistry>();
	
	protected PersonFinder personFinder = new PersonMapper();
	protected FriendFinder friendFinder = new FriendMapper();
	protected IncomingFriendFinder incomingFriendFinder = new IncomingFriendFinder();
	protected OutgoingFriendFinder outgoingFriendFinder = new OutgoingFriendFinder();
	
	protected PersonMapper pm;
	protected FriendMapper fm;
	protected PendingFriendMapper pfm;
	
	private static FinderRegistry soleInstance = new FinderRegistry();
	
	private static FinderRegistry getInstance()
	{
		return soleInstance;
	}
	
	public static PersonFinder personFinder()
	{
		return getInstance().personFinder;
	}
	
	public static FriendFinder friendFinder()
	{
		return getInstance().friendFinder;	
	}
	
	public static IncomingFriendFinder incomingFriendFinder()
	{
		return getInstance().incomingFriendFinder;
	}
	
	public static OutgoingFriendFinder outgoingFriendFinder()
	{
		return getInstance().outgoingFriendFinder;
	}
}
