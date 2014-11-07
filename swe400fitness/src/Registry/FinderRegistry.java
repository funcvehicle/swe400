package Registry;

import mapper.FriendFinder;
import mapper.FriendMapper;
import mapper.IncomingFriendFinder;
import mapper.OutgoingFriendFinder;
import mapper.PersonFinder;
import mapper.PersonMapper;

/**
 * @author Connor Fox
 * Allows domain logic to use a common set of finders to find needed records as objects
 */
public class FinderRegistry
{	
	protected PersonFinder personFinder = new PersonMapper();
	protected FriendFinder friendFinder = new FriendMapper();
	protected IncomingFriendFinder incomingFriendFinder = new FriendMapper();
	protected OutgoingFriendFinder outgoingFriendFinder = new FriendMapper();
	
	private static FinderRegistry soleInstance = new FinderRegistry();
	
	/**
	 * Gets an instance of the Finder Registry
	 * @return soleInstance
	 */
	private static FinderRegistry getInstance()
	{
		return soleInstance;
	}
	
	/**
	 * Gets an instance of the Person Finder
	 * @return personFinder
	 */
	public static PersonFinder personFinder()
	{
		return getInstance().personFinder;
	}
	
	/**
	 * Gets a new instance of the friend finder
	 * @return friendFinder
	 */
	public static FriendFinder friendFinder()
	{
		return getInstance().friendFinder;	
	}
	
	/**
	 * Gets a new instance of the incoming friend finder
	 * @return incomingFriendFinder
	 */
	public static IncomingFriendFinder incomingFriendFinder()
	{
		return getInstance().incomingFriendFinder;
	}
	
	/**
	 * Gets a new instance of the outgoing friend finder
	 * @return outgoingFriendFinder
	 */
	public static OutgoingFriendFinder outgoingFriendFinder()
	{
		return getInstance().outgoingFriendFinder;
	}
}
